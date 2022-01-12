package ru.buildservice.project.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.buildservice.project.entity.Resources;
import ru.buildservice.project.repository.ResourcesRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private ResourcesRepository resourcesDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resources resources = resourcesDao.findByUsername(username);
        if (resources == null) {
            throw new UsernameNotFoundException("I don't know who you are " + username);
        }
        UserDetails user = User.builder()
                .username(resources.getUsername())
                .password(resources.getPassword())
                .roles(resources.getRole())
                .build();
        return user;
    }
}
