package ru.buildservice.project.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.buildservice.project.entity.Users;
import ru.buildservice.project.entity.Roles;
import ru.buildservice.project.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userDao.findByUsername(username);
        Roles role = users.getRoles();
        String roleName = role.getRoleName();

        if (users == null) {
            throw new UsernameNotFoundException("I don't know who you are " + username);
        }
        UserDetails user = User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                .roles(roleName)
                .build();
        return user;
    }
}
