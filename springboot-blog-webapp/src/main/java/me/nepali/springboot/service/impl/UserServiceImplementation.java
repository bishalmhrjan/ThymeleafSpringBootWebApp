package me.nepali.springboot.service.impl;

import me.nepali.springboot.dto.RegistrationDTO;
import me.nepali.springboot.entity.Role;
import me.nepali.springboot.entity.User;
import me.nepali.springboot.repository.RoleRepository;
import me.nepali.springboot.repository.UserRepository;
import me.nepali.springboot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository,PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder= passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDTO registrationDTO) {
        User user = new User();
        user.setName(registrationDTO.getFirstName()+" "+registrationDTO.getLastName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Role role = roleRepository.findByName("ROLLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
