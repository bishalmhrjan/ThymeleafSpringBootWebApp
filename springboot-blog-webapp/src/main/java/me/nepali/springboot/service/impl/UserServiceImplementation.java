package me.nepali.springboot.service.impl;

import me.nepali.springboot.dto.RegistrationDTO;
import me.nepali.springboot.entity.Role;
import me.nepali.springboot.entity.User;
import me.nepali.springboot.repository.RoleRepository;
import me.nepali.springboot.repository.UserRepository;
import me.nepali.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImplementation(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getFirstName()+" "+registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());
        Role role = roleRepository.findByName("ROLLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
