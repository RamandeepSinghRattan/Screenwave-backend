package com.example.UserAuthenticationService.controller;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exceptions.InvalidEmailIdAndPasswordException;
import com.example.UserAuthenticationService.exceptions.UserAlreadyExitsException;
import com.example.UserAuthenticationService.exceptions.UserNotFoundException;
import com.example.UserAuthenticationService.security.SecurityTokenGeneratorI;
import com.example.UserAuthenticationService.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class UserController {

  IUserService iUserService;
  SecurityTokenGeneratorI securityTokenGeneratorI;

    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGeneratorI securityTokenGeneratorI) {
        this.iUserService = iUserService;
        this.securityTokenGeneratorI = securityTokenGeneratorI;
    }



  @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExitsException {
      try
      {
          User registeredUser=iUserService.saveUser(user);
          return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
      }
      catch (UserAlreadyExitsException e) {
          throw new UserAlreadyExitsException();
      }
      catch (Exception e)
      {
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws InvalidEmailIdAndPasswordException {

        try {

            User foundUser = iUserService.findUserByEmailIdAndPassword(user.getEmail(), user.getPassword());
            Map<String, String> token = securityTokenGeneratorI.createToken(foundUser);

            return new ResponseEntity<>(token, HttpStatus.OK);

        }
        catch (InvalidEmailIdAndPasswordException e){
            throw  e;
        }
          catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("userProfile/{emailId}")
    public ResponseEntity<?> getUserProfile(@PathVariable String emailId) throws UserNotFoundException {
        return new ResponseEntity<>(iUserService.getUserProfilePicture(emailId),HttpStatus.OK);
    }

}
