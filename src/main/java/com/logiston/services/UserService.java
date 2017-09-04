package com.logiston.services;


import com.logiston.entity.User;

import java.util.List;

/**
 * @author Pavel Putrenkov
 */
public interface UserService {
    void setUpUserData(User user);

    List<User> listAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    User findUserByEmail(String email);

    void delete(Long id);

    void saveEditUser(User user);
}
