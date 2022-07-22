package com.esprit.pidev2022.security.controller;

import com.esprit.pidev2022.security.load.request.LoginRequest;
import com.esprit.pidev2022.security.load.request.SignupRequest;
import com.esprit.pidev2022.security.load.response.JwtResponse;
import com.esprit.pidev2022.security.load.response.MessageResponse;
import com.esprit.pidev2022.security.model.ERole;
import com.esprit.pidev2022.security.model.Role;
import com.esprit.pidev2022.security.model.User;
import com.esprit.pidev2022.security.repository.RoleRepository;
import com.esprit.pidev2022.security.repository.UserRepository;
import com.esprit.pidev2022.security.jwt.JwtUtils;
import com.esprit.pidev2022.security.services.UserDetailsImpl;

import com.esprit.pidev2022.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AuthController {

    @Autowired // (injection d'un bean /objet)
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder; // le bean de cryptage

    @Autowired
    AccountService accountservice;

    @PostMapping("/signup")
    public ResponseEntity<?> register_user(@Valid @RequestBody SignupRequest signupRequest) {

        // email unique
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new MessageResponse("Error : Email is already in use !!!!!!"));
        }

        // username unique

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            new MessageResponse("Error : Username is already in use !!!!!!"));
        }

        // cryptage du password

        User user = new User(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> subroles = signupRequest.getRole(); // récupérer les roles (String) as input

        Set<Role> roles = new HashSet<>(); // liste des roles à accorder ---> output

        if (subroles == null) {
            // le role par défaut (role user)
            Role userrole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error : role is not found"));
            roles.add(userrole);
        } else {
            // un ensemble de role à traiter
            // for each role in subroles
            subroles.forEach(
                    role -> {

                        switch (role) {
                            case "admin":
                                Role roleadmin = roleRepository.findByName(ERole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error : role is not found"));
                                roles.add(roleadmin);
                            case "secretaire":
                                Role rolesec = roleRepository.findByName(ERole.ROLE_BANKER)
                                        .orElseThrow(() -> new RuntimeException("Error : role is not found"));
                                roles.add(rolesec);
                            default:
                                // le role par défaut
                                Role userrole = roleRepository.findByName(ERole.ROLE_USER)
                                        .orElseThrow(() -> new RuntimeException("Error : role is not found"));
                                roles.add(userrole);
                        }
                    }

            );

        }

        user.setRoles(roles); // accorder la liste des Roles

        userRepository.save(user); // enregistrer dans la base
        accountservice.createDepositAccount(user);
        accountservice.createSavingAccount(user);



        return ResponseEntity.ok(new MessageResponse("User Registred successfully !!!!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> auth_user(@Valid @RequestBody LoginRequest loginRequest) {

        // pour l'authentification (vérifier l'existance de l'utilisateur)

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication); // traiter l'user selon ses droits
                                                                              // d'accées
        String jwt = jwtUtils.generateJwtToken(authentication); // génération du token

        UserDetailsImpl userdetails = (UserDetailsImpl) authentication.getPrincipal(); // get l'utilisateur principal

        // récupérer la list des roles

        List<String> roles = userdetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userdetails.getId(),
                        userdetails.getUsername(),
                        userdetails.getEmail(),
                        roles));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
