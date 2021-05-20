package br.com.register.api.service.impl;

import br.com.register.api.entity.UserPrincipal;
import br.com.register.api.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserPrincipalRepository userPrincipalRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = Optional.ofNullable(userPrincipalRepository.findByUserName(userName))
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_CLIENT");
        return new User(userPrincipal.getUserName(),
                userPrincipal.getPassword(),
                userPrincipal.isAdmin() ? authorityListAdmin : authorityListUser);
    }
}
