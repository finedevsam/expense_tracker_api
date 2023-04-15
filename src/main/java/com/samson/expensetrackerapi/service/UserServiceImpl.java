package com.samson.expensetrackerapi.service;

import com.samson.expensetrackerapi.entity.User;
import com.samson.expensetrackerapi.entity.UserModel;
import com.samson.expensetrackerapi.exceptions.ItemAlreadyExistException;
import com.samson.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.samson.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserModel user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistException("Email already exist");
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        return userRepository.save(newUser);
    }

    @Override
    public User readUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User updateUser(UserModel user, Long id) {
        User ex = readUser(id);
        ex.setName(user.getName() != null ? user.getName(): ex.getName());
        ex.setAge(user.getAge() != null ? user.getAge(): ex.getAge());
        ex.setEmail(user.getEmail() != null ? user.getEmail(): ex.getEmail());
        ex.setPassword(user.getPassword() != null ? user.getPassword(): ex.getPassword());
        return ex;
    }
}
