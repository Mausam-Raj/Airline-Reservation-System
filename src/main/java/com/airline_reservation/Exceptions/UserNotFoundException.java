package com.airline_reservation.Exceptions;
/*
 * @author : Mausam Raj
 * @Date : 31-05-2024
 */


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
