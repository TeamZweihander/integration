package com.zweihander.navup.user.service;



import com.zweihander.navup.user.domain.User;
import com.zweihander.navup.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findOne(id);
    }
    @Override
    public User getBy(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void deleteByUsername(String username){
        User temp = getBy(username);
        userRepository.delete(temp);
    }
}
