package springboot_jwt_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.equals("kartik_m")) {
        return User.builder()
                .username("kartik_m")
                .password(passwordEncoder().encode("Secret@123")) // Hash the password
                .roles("USER")
                .build();
    } else {
        throw new UsernameNotFoundException("User not found: " + username);
    }
}

// Inject passwordEncoder manually if needed
@Autowired
private PasswordEncoder passwordEncoder;


}

