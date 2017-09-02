package com.logiston.controllers;


import com.logiston.entity.Role;
import com.logiston.entity.User;
import com.logiston.repository.RoleRepository;
import com.logiston.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private UserService userService;

    private RoleRepository roleRepository;


    @GetMapping(value = "/user/users")
    public String list(Model model) {
        Iterable<User> users = userService.listAllUsers();
        model.addAttribute("users", users);
        return "/user/users";
    }

    @GetMapping("user/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("user", userById);
        model.addAttribute("roles", roleRepository.findAll());

        List<Role> collect = new ArrayList<>(userById.getRoles());

        Role currentRole = collect.get(0);
        model.addAttribute("currentRole", currentRole);


        return "/user/userEdit";
    }

    @GetMapping("user/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/user/users";
    }

    @PostMapping(value = "user/{id}")
    public String saveProduct(HttpServletRequest request, @PathVariable Long id, User user) {
        Integer userRole = new Integer(request.getParameter("userRole"));

        Role role = roleRepository.findOne(userRole);

        User userById = userService.getUserById(id);
        userById.setName(user.getName());
        userById.setLastName(user.getLastName());
        userById.setEmail(user.getEmail());
        userById.setActive(user.getActive());
        userById.setRoles(new HashSet<Role>(Collections.singletonList(role)));

        userService.saveEditUser(userById);

        return "redirect:/user/users";
    }
}
