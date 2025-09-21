package al.spinit.spinit.service;

import al.spinit.spinit.config.JwtUtils;
import al.spinit.spinit.dto.SignUpRequest;
import al.spinit.spinit.entity.Role;
import al.spinit.spinit.entity.User;
import al.spinit.spinit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;
}