package com.zweihander.navup.user.service;

import com.zweihander.navup.user.domain.User;

public interface UserService {

    User save(User user);

    User update(User user);

    User getById(Long id);
    User getBy(String username);

    void deleteById(Long id);
    void deleteByUsername(String username);
}
