package com.practice.hospitalmanagement.exception;



//📌 Rules it follows
//
//No HTTP
//
//No Spring annotations
//
//Extends RuntimeException
//
//Message explains the problem

public class UserNotFound extends RuntimeException {

 public UserNotFound  (long Userid){ //exception do nothing just carry the message
     super("user with this id "+ Userid + " not found"); // it's telling the java and java telling the spring what went wrong then it search for the @exceptionhandler or @controladvice
 }
}
