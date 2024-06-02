package com.airline_reservation.Exceptions;
/*
 * @author : Mausam Raj
 * @Date : 31-05-2024
 */


public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(String message) {
        super(message);
    }
}
