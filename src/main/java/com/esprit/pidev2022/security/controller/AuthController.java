package com.esprit.pidev2022.security.controller;

import com.esprit.pidev2022.entities.MyConstants;
//import com.esprit.pidev2022.security.ActiveUserStore;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
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
    public JavaMailSender emailSender;
   // @Autowired
   // ActiveUserStore activeUserStore;

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
        user.setState(false);

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
                            case "banker":
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

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(MyConstants.FRIEND_EMAIL2);
        message.setSubject("Following your registration ");
        message.setText("Dear "+signupRequest.getUsername()  +
                "\n" +
                "We confirm receipt of your registration, your account will be ready for use as soon as it is validated by our agents.\n" +
                "\n" +
                "You can follow your registration request via the link : \n" +
                "\n" +
                "To send a complaint about your registration please contact us at mohamedBechir.lahmedi@esprit.tn or fares.elouissi@esprit.tn \n" +
                "\n" +
                "thank you for your trust in our bank! "+"\n" +
                "\n" +
                "This is an automatic mail please do not reply "
        );

        // Send Message!
        this.emailSender.send(message);

        return ResponseEntity.ok(new MessageResponse("User Registred successfully !!!!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> auth_user(@Valid @RequestBody LoginRequest loginRequest,Locale locale, Model model) {

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

      //  if(userdetails.isState()==false){
      //     return ResponseEntity.ok(new MessageResponse("Account not activated yet, please contact us on... !!!"));
      //  }
        //if(true){
        //    model.addAttribute("users", activeUserStore.getUsers());
         //   return ResponseEntity.ok(new MessageResponse("users"+activeUserStore.getUsers().isEmpty()));
     //   }

       return ResponseEntity.ok(
              new JwtResponse(
                        jwt,
                       userdetails.getId(),
                       userdetails.getUsername(),
                        userdetails.getEmail(),
                       roles));
    }

    @GetMapping("/check_existing_user/{email}")
    public ResponseEntity<?> Check_user(@PathVariable("email") String email){

        if(userRepository.existsByEmail(email) !=false){

            Optional<User> user = userRepository.findByEmail(email);
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(MyConstants.FRIEND_EMAIL2);
            message.setSubject("Reseting password... ");
            message.setText("Dear "+user.get().getUsername() +
                    "\n" +
                    "We confirm receipt of your request to change your password, please access the following link to change it .\n"
            );

            // Send Message!
            this.emailSender.send(message);

        }

        return ResponseEntity.ok(new MessageResponse("User Registred successfully !!!!"));
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

  //  public String getLoggedUsers(Locale locale, Model model) {
    //    model.addAttribute("users", activeUserStore.getUsers());
        //return activeUserStore.getUsers();
     //   return "users";
   // }