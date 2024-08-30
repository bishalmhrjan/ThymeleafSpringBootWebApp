package me.nepali.springboot.security;

import me.nepali.springboot.entity.User;
import me.nepali.springboot.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println("user name is "+user.getName()+"----------------");
        if(user != null){
            org.springframework.security.core.userdetails.User authenticatedUser =
                    new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getPassword(),
                            user.getRoles().stream()
                                    .map((role) -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
                                    .collect(Collectors.toList())
                    );
           // System.out.println("role of user is ");
            System.out.println("user name is inside not null "+user.getName()+"----------------");

            return authenticatedUser;
        }else {
            throw new UsernameNotFoundException("Invalid username and password");
        }
    }
}