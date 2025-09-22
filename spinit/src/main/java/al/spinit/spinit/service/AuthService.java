package al.spinit.spinit.service;

import al.spinit.spinit.config.JwtUtils;
import al.spinit.spinit.dto.JwtResponse;
import al.spinit.spinit.dto.LogInRequest;
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



    public String customerRegistration(SignUpRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(Role.CUSTOMER);

        return userRepository.save(user).getUsername();
    }
    public String adminRegistration(SignUpRequest signUpRequest) {
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already taken!");
        }
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole(Role.ADMIN);

        return userRepository.save(user).getUsername();
    }
    public JwtResponse loginUser(LogInRequest logInRequest) {
        User user = userRepository.findByUsername(logInRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(logInRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }
        String token = jwtUtils.generateToken(user.getUsername());
        return new JwtResponse(token, user.getUsername(), user.getRole());
    }
}