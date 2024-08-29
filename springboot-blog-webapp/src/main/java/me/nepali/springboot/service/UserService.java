package me.nepali.springboot.service;

import me.nepali.springboot.dto.RegistrationDTO;
import me.nepali.springboot.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    User findByEmail(String email);
}
