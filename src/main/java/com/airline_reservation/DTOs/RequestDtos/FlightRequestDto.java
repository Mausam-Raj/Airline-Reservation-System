package com.airline_reservation.DTOs.RequestDtos;
/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */

import com.airline_reservation.Enums.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightRequestDto {
    private String flightName;
    private int totalSeats;
    private Airport departureAirport;
    private Airport destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private int duration;
    private int price;
}
