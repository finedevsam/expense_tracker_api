package com.samson.expensetrackerapi.service;

import com.samson.expensetrackerapi.entity.User;
import com.samson.expensetrackerapi.entity.UserModel;
import com.samson.expensetrackerapi.exceptions.ItemAlreadyExistException;
import com.samson.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.samson.expensetrackerapi.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptEncoder;

    @Override
    public User createUser(UserModel user){
        if (userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistException("Email already exist");
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(bCryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public User readUser() {
        Long userId = getLoggedInUser().getId();
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User updateUser(UserModel user) {
        User ex = readUser();
        ex.setName(user.getName() != null ? user.getName(): ex.getName());
        ex.setAge(user.getAge() != null ? user.getAge(): ex.getAge());
        ex.setEmail(user.getEmail() != null ? user.getEmail(): ex.getEmail());
        ex.setPassword(user.getPassword() != null ? bCryptEncoder.encode(user.getPassword()): ex.getPassword());
        return userRepository.save(ex);
    }

    @Override
    public void deleteUser() {
        User user = readUser();
        userRepository.delete(user);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        System.out.println(email);
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
