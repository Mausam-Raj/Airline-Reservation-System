package com.airline_reservation.Exceptions;
/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(String message) {
        super(message);
    }
}
