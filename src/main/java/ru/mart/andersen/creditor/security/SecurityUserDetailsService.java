package ru.mart.andersen.creditor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mart.andersen.creditor.model.User;
import ru.mart.andersen.creditor.repository.UserRepository;
import ru.mart.andersen.creditor.service.UserService;

import java.util.Collection;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Login " + login + " not found"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                getAuthorities(user));
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String userRole = user.getRole().getAuthority();
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRole);
        return authorities;
    }
}
