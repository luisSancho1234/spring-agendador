package com.project.scheduler.service;

import com.project.scheduler.models.entidades.UsuarioEntidade;
import com.project.scheduler.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    // Method to load user details by username (email)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<UsuarioEntidade> userInfo = repository.findByUsuTxEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        UsuarioEntidade user = userInfo.get();

        List<SimpleGrantedAuthority> authorities =
                Arrays.stream(user.getUsuTxRoles().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
        return new User(user.getUsuTxEmail(), user.getUsuTxSenha(), authorities);
    }

    // Add any additional methods for registering or managing users
    public String addUser(UsuarioEntidade userInfo) {
        // Encrypt password before saving
        String role = userInfo.getUsuTxRoles();
        if(role == null || role.isBlank()) {
            userInfo.setUsuTxRoles("ROLE_USER");
        }
        userInfo.setUsuTxSenha(Objects.requireNonNull(encoder.encode(userInfo.getUsuTxSenha())));
        repository.save(userInfo);
        return "User added successfully!";
    }
}
