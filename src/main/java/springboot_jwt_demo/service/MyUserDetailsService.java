package springboot_jwt_demo.service;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("kartik_m".equals(username)) {
            return new User(
                "kartik_m",
                "{noop}Secret@123",  // {noop} = plain password (no encryption)
                new ArrayList<>()
            );
        }
        throw new UsernameNotFoundException("User not found");
    }
}

