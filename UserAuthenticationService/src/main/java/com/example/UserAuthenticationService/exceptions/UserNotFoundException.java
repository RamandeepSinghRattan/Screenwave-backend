package com.example.UserAuthenticationService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Specified user does not exist.")
public class UserNotFoundException extends Exception{
}
