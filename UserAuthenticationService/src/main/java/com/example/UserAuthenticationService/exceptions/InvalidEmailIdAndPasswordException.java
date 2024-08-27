package com.example.UserAuthenticationService.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason="Invalid EmailId or Password")
public class InvalidEmailIdAndPasswordException extends Exception{
}
