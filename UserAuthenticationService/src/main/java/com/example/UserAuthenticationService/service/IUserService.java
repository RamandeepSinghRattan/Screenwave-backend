package com.example.UserAuthenticationService.service;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exceptions.InvalidEmailIdAndPasswordException;
import com.example.UserAuthenticationService.exceptions.UserAlreadyExitsException;
import com.example.UserAuthenticationService.exceptions.UserNotFoundException;

public interface IUserService {

    User saveUser(User user) throws UserAlreadyExitsException;
    User findUserByEmailIdAndPassword(String email, String password) throws InvalidEmailIdAndPasswordException;
    String getUserProfilePicture(String email) throws UserNotFoundException;
}
