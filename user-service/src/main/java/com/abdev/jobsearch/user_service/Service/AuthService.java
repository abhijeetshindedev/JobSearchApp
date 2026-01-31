package com.abdev.jobsearch.user_service.Service;

// import ch.qos.logback.core.net.SyslogOutputStream;
import com.abdev.jobsearch.user_service.DTO.AuthResponse;
import com.abdev.jobsearch.user_service.DTO.LoginRequest;
import com.abdev.jobsearch.user_service.DTO.RegisterRequest;
import com.abdev.jobsearch.user_service.Entity.Role;
import com.abdev.jobsearch.user_service.Entity.User;
import com.abdev.jobsearch.user_service.Repo.RoleRepo;
import com.abdev.jobsearch.user_service.Repo.UserRepo;
import com.abdev.jobsearch.user_service.security.JwtService;
// import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
//     private final GoogleTokenVerifierService googleTokenVerifierService;

    public void register(RegisterRequest request) {

        Role userRole = roleRepository
                .findByName("USER")
                .orElseGet(() -> roleRepository.save(new Role("USER")));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.getRoles().add(userRole);

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        System.out.println("in login");
        // String email = request.getEmail();
        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();
        System.out.println("email : "+email);
        System.out.println("password : "+password);

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );
            System.out.println("authentication : "+authentication);
        //     User user = userRepository.findByEmail(email).orElseThrow( () -> new RuntimeException("User not found"));
            UserDetails user = (UserDetails) authentication.getPrincipal();
            System.out.println("userdetails : "+user);
            String token = jwtService.generateToken(user);
            System.out.println("token : "+token);
            return new AuthResponse(token);
        } catch (Exception e) {
            e.printStackTrace(); // shows exact reason
            throw e;
        }

    }

//     public AuthResponse googleLogin(String idToken) {

//         GoogleIdToken.Payload payload =
//                 googleTokenVerifierService.verify(idToken);

//         String email = payload.getEmail();
//         String googleId = payload.getSubject();
//         String name = (String) payload.get("name");

//         User user = userRepository.findByEmail(email)
//                 .orElseGet(() -> {

//                     Role role = roleRepository.findByName("USER")
//                             .orElseThrow();

//                     User newUser = new User();
//                     newUser.setEmail(email);
//                     newUser.setFullName(name);
//                     newUser.setProvider("GOOGLE");
//                     newUser.setProviderId(googleId);
//                     newUser.getRoles().add(role);

//                     return userRepository.save(newUser);
//                 });

//         UserDetails userDetails =
//                 new org.springframework.security.core.userdetails.User(
//                         user.getEmail(), "", List.of());

//         return new AuthResponse(jwtService.generateToken(userDetails));
//     }
}
