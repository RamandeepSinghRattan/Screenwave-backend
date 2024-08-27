package com.example.UserAuthenticationService.service;

import com.example.UserAuthenticationService.domain.User;
import com.example.UserAuthenticationService.exceptions.InvalidEmailIdAndPasswordException;
import com.example.UserAuthenticationService.exceptions.UserAlreadyExitsException;
import com.example.UserAuthenticationService.exceptions.UserNotFoundException;
import com.example.UserAuthenticationService.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    IUserRepository iUserRepository;


    @Autowired
    public UserServiceImpl(IUserRepository iUserRepository) {

        this.iUserRepository = iUserRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExitsException {
        if(iUserRepository.findByEmail(user.getEmail()).isPresent())
        {
            throw new UserAlreadyExitsException();
        }
            return iUserRepository.save(user);

    }

    @Override
    public User findUserByEmailIdAndPassword(String email, String password) throws InvalidEmailIdAndPasswordException {
    User user=iUserRepository.findByEmailAndPassword(email,password);
    if(user==null)
    {
    throw new InvalidEmailIdAndPasswordException();
    }
     return user;
    }

    @Override
    public String getUserProfilePicture(String email) throws UserNotFoundException {

    if(iUserRepository.findByEmail(email).isEmpty())
        {
        throw new  UserNotFoundException();
        }
       User user=iUserRepository.findByEmail(email).get();

        return user.getImageUrl();
    }


}
