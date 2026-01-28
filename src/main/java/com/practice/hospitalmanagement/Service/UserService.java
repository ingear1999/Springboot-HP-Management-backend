package com.practice.hospitalmanagement.Service;


import com.practice.hospitalmanagement.Dto.RequestDto.RequestUserDTO;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateUserPasswordDto;
import com.practice.hospitalmanagement.Dto.RequestDto.UpdateUserProfileDto;
import com.practice.hospitalmanagement.Dto.RespondDto.ResponseUserDto;
import com.practice.hospitalmanagement.Entity.usersEntity.Users;
import com.practice.hospitalmanagement.Repository.UserRepository;
import com.practice.hospitalmanagement.exception.UserNotFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {
    final private  UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //Create Acc For The Users
    //=======================================================================
    public ResponseUserDto registerUser (RequestUserDTO userDto){

        Users userEntity =new Users();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userEntity.setEmail(userDto.getEmail());
        userEntity.setLastActiveAt(LocalDateTime.now());

        Users saved = userRepository.save(userEntity);

        return new ResponseUserDto(
                saved.getUserName(),
                saved.getFirstName()+" "+saved.getLastName(), //fullname
                saved.getEmail()

        );
  }
//    Service layer (business logic)
//    Does NOT handle HTTP
//    Does NOT build responses
//    Just throws exceptions when something is wrong


    //Check User Acc
    //======================================================================================
    public ResponseUserDto findbyId(long id){
        return  userRepository.findById(id).map(Users -> new ResponseUserDto(
                Users.getUserName(),
                Users.getFirstName()+" "+Users.getLastName(),
                Users.getEmail()
                )
        ).orElseThrow(()->new UserNotFound(id)); // Exception handlers act like a central service for exceptions
    }


    //update UserInformation
    //=============================================================================

    public ResponseUserDto updateUserProfile(long id, UpdateUserProfileDto dto){
        Users userEntity = userRepository.findById(id).orElseThrow(()->new UserNotFound(id));

         boolean update = false;

        if(!dto.getFirstName().equals(userEntity.getFirstName())){
            userEntity.setFirstName(dto.getFirstName());
            update = true;
        }

        if(!dto.getLastName().equals(userEntity.getLastName())){
            userEntity.setLastName(dto.getLastName());
            update = true;
        }

        if(!dto.getEmail().equals(userEntity.getEmail())){
            userEntity.setEmail(dto.getEmail());
            update = true;
        }

        if(!update){
            throw new RuntimeException("Nothing is updated");
        }


            Users saved = userRepository.save(userEntity);
            return new ResponseUserDto(
                    saved.getUserName(),
                    saved.getFirstName()+" "+saved.getLastName(),
                    saved.getEmail());

    }



       //Delete User Acc
    //================================================================

    public void deleteAccount(long id){
        Users users = userRepository.findById(id)
                .orElseThrow(()->new UserNotFound(id));
        userRepository.delete(users);

//        userRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("User not found"));
//        userRepository.deleteById(id);// also fine
    }


    //User Update The Password
    //================================================================
    public void updatePassword(long id, UpdateUserPasswordDto updateUserPasswordDto) {

        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound(id));

        // compare raw password with hashed password
        if (!passwordEncoder.matches(updateUserPasswordDto.getOldPassword() , user.getPassword())) { //Is the password the user typed the same as the one they used before?
            throw new RuntimeException("Old password does not match");
        }

        // encode new password before saving
        user.setPassword(passwordEncoder.encode(updateUserPasswordDto.getNewPassword()));
        userRepository.save(user);
    }

}
