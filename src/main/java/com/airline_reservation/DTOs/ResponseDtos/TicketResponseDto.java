package com.airline_reservation.DTOs.ResponseDtos;
/*
 * @author : Mausam Raj
 * @Date : 31-05-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDto {
    private Long ticketId;
    private String seatNumber;
    private String flightName;
    private String departureAirport;
    private String destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private int duration;
    private int price;
    private String passengerName;
    private int passengerAge;
    private String gender;
    private String email;

}
