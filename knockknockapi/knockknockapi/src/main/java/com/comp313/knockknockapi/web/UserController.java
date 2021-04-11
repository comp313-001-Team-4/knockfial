package com.comp313.knockknockapi.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp313.knockknockapi.domain.Orders;
import com.comp313.knockknockapi.domain.TechDetails;
import com.comp313.knockknockapi.domain.User;
import com.comp313.knockknockapi.domain.UserDetails;
import com.comp313.knockknockapi.payload.JWTLoginSucessReponse;
import com.comp313.knockknockapi.payload.LoginRequest;
import com.comp313.knockknockapi.repositories.OrderRepo;
import com.comp313.knockknockapi.repositories.TechDetailsRepo;
import com.comp313.knockknockapi.repositories.UserDetailsRepo;
import com.comp313.knockknockapi.repositories.UserRepository;
import com.comp313.knockknockapi.security.JwtTokenProvider;
import com.comp313.knockknockapi.services.MapValidationErrorService;
import com.comp313.knockknockapi.services.UserService;
import com.comp313.knockknockapi.validator.UserValidator;
import static com.comp313.knockknockapi.security.SecurityConstants.TOKEN_PREFIX;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;
    
    @Autowired
     private OrderRepo orderRepo;
	 @Autowired
	    private TechDetailsRepo techDetailsRepository;
	 
	 @Autowired
	    private UserDetailsRepo userDetailsRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
@Autowired
    private UserDetailsRepo userRepository;

// user login method
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = TOKEN_PREFIX +  tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
    }

    // register user method
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        // Validate passwords match
        userValidator.validate(user,result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

        User newUser = userService.saveUser(user);

        return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
    // adding user details
    @PostMapping("/adduserdetails")
    public ResponseEntity<?> addUserDetails(@Valid @RequestBody UserDetails userDetails, BindingResult result,Principal principal){
        // Validate passwords match
        //userValidator.validate(userDetails,result);
        userDetails.setUsername(principal.getName());
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

       // User newUser = userService.saveUser(userDetails);
        UserDetails newUserDetails =userService.saveUserDetails(userDetails);

        return  new ResponseEntity<UserDetails>(newUserDetails, HttpStatus.CREATED);
    }
    @PostMapping("/addtechdetails")
    public ResponseEntity<?> addTechDetails(@Valid @RequestBody TechDetails techDetails, BindingResult result,Principal principal){
        // Validate passwords match
        //userValidator.validate(userDetails,result);
       techDetails.setUsername(principal.getName());
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)return errorMap;

       // User newUser = userService.saveUser(userDetails);
        TechDetails newTechDetails =userService.saveTechDetails(techDetails);

        return  new ResponseEntity<TechDetails>(newTechDetails, HttpStatus.CREATED);
    }
    
    //getting technician details by name
    @GetMapping("/techdetails/{name}")
    public Iterable<TechDetails> getAllTech(@PathVariable String name,Principal principal){
    	
    	return userService.findAllByType(name,principal);
    	}
    //getting all technicians
    @GetMapping("/technicians")
    public Iterable<TechDetails> getAllTechnicians(){
    	System.out.println(techDetailsRepository.findAll());
    	return techDetailsRepository.findAll();
    	}
    
    // getting all users
    @GetMapping("/users")
    public Iterable<UserDetails> getAllUsers(){
    	System.out.println(userDetailsRepository.findAll());
    	return userDetailsRepository.findAll();
    	}
    
    // getting order details
    @GetMapping("/orders")
    public Iterable<Orders> getAllOrders(){
               Iterable<Orders> orders= orderRepo.findAll();
                   for(Orders order: orders) {
                	   System.out.print(order.getCategory());
                   }
    	return orderRepo.findAll() ;
    	}
// getting order details by user name
    
    @GetMapping("/ordersbyid")
    public Optional<Orders> getAllOrdersId(int id){
               Iterable<Orders> orders= orderRepo.findAll();
                   for(Orders order: orders) {
                	   System.out.print(order.getCategory());
                   }
    	return orderRepo.findById(id) ;
    	}
    
    @PostMapping("/saveorders")
    public Orders saveOrder(Orders order){
              
    	return orderRepo.save(order) ;
    	}
}
