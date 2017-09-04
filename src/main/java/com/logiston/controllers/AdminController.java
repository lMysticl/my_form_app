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

/**
 * @author Pavel Putrenkov
 */
@Controller
@AllArgsConstructor
public class AdminController {

    private UserService userService;

    private RoleRepository roleRepository;

    /**
     * Method gets List of all users, can create {@link List<User>} entity with any role.
     *
     * @param model set all users {@link List<User>} to page.
     *
     * @return to user.html
     */
    @GetMapping(value = "/user/users")
    public String list(Model model) {
        List<User> users = userService.listAllUsers();
        model.addAttribute("users", users);
        return "/user/users";
    }
    /**
     * Updates {@link User} entity associated with provided id param.
     *
     * @param model user to update.
     *
     * @return to userEdit.html
     */
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

    /**
     * Delete {@link User} user
     *  by user
     *  @param  id .
     *
     */
    @GetMapping("user/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/user/users";
    }

    /**
     * Method for admin, can save {@link User} entity with any role.
     *
     * @param user  {@link User} entity.
     *
     * @return save {@link User} entity.
     */
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
