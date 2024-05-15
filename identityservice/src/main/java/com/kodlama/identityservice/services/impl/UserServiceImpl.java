package com.kodlama.identityservice.services.impl;

import com.kodlama.identityservice.dtos.requests.RegisterRequest;
import com.kodlama.identityservice.entities.User;
import com.kodlama.identityservice.mappers.UserMapper;
import com.kodlama.identityservice.repositories.UserRepository;
import com.kodlama.identityservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new AccessDeniedException("No user found"));
    }

    @Override
    public void register(RegisterRequest request) {
        userWithSameEmailRule();
        User user = userMapper.userFromRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    private void userWithSameEmailRule()
    {

    }
}
