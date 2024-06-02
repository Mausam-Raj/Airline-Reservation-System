package com.airline_reservation.Services;
/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */

import com.airline_reservation.DTOs.RequestDtos.FlightRequestDto;
import com.airline_reservation.DTOs.RequestDtos.FlightUpdateRequestDto;
import com.airline_reservation.DTOs.ResponseDtos.FlightResponseDto;
import com.airline_reservation.Entities.Flight;
import com.airline_reservation.Exceptions.FlightNotFoundException;
import com.airline_reservation.Repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public FlightResponseDto addFlight(FlightRequestDto requestDto) {
        Flight flight = Flight.builder()
                .flightName(requestDto.getFlightName())
                .totalSeats(requestDto.getTotalSeats())
                .departureAirport(requestDto.getDepartureAirport())
                .destinationAirport(requestDto.getDestinationAirport())
                .departureTime(requestDto.getDepartureTime())
                .arrivalTime(requestDto.getArrivalTime())
                .duration(requestDto.getDuration())
                .price(requestDto.getPrice())
                .passengers(new ArrayList<>())
                .soldTickets(new ArrayList<>())
                .build();

        flightRepository.save(flight);

        return FlightResponseDto.builder()
                .flightId(flight.getFlightId())
                .flightName(flight.getFlightName())
                .totalSeats(flight.getTotalSeats())
                .departureAirport(flight.getDepartureAirport())
                .destinationAirport(flight.getDestinationAirport())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .duration(flight.getDuration())
                .price(flight.getPrice())
                .availableSeats(flight.getTotalSeats() - flight.getSoldTickets().size())
                .build();
    }

    public FlightResponseDto updateFlightDetails(FlightUpdateRequestDto requestDto) {
        try{
            Flight flight = flightRepository.findById(requestDto.getFlightId()).get();

            flight.setFlightName(requestDto.getFlightName());
            flight.setTotalSeats(requestDto.getTotalSeats());
            flight.setDepartureAirport(requestDto.getDepartureAirport());
            flight.setDestinationAirport(requestDto.getDestinationAirport());
            flight.setDepartureTime(requestDto.getDepartureTime());
            flight.setArrivalTime(requestDto.getArrivalTime());
            flight.setDuration(requestDto.getDuration());
            flight.setPrice(requestDto.getPrice());

            flightRepository.save(flight);

            return FlightResponseDto.builder()
                    .flightId(flight.getFlightId())
                    .flightName(flight.getFlightName())
                    .totalSeats(flight.getTotalSeats())
                    .departureAirport(flight.getDepartureAirport())
                    .destinationAirport(flight.getDestinationAirport())
                    .departureTime(flight.getDepartureTime())
                    .arrivalTime(flight.getArrivalTime())
                    .duration(flight.getDuration())
                    .price(flight.getPrice())
                    .availableSeats(flight.getTotalSeats() - flight.getSoldTickets().size())
                    .build();

        } catch (Exception e) {
            throw new FlightNotFoundException("Flight cannot be found!! Please check flightId.");
        }
    }

    public FlightResponseDto deleteFlight(Long flightId){
        Optional<Flight> flight = flightRepository.findById(flightId);
        if(flight.isPresent()) {
            FlightResponseDto responseDto = FlightResponseDto.builder()
                    .flightId(flight.get().getFlightId())
                    .flightName(flight.get().getFlightName())
                    .totalSeats(flight.get().getTotalSeats())
                    .departureAirport(flight.get().getDepartureAirport())
                    .destinationAirport(flight.get().getDestinationAirport())
                    .departureTime(flight.get().getDepartureTime())
                    .arrivalTime(flight.get().getArrivalTime())
                    .duration(flight.get().getDuration())
                    .price(flight.get().getPrice())
                    .availableSeats(0)
                    .build();

            flightRepository.deleteById(flightId);

            return responseDto;
        }else {
            throw new FlightNotFoundException("Flight Not Found!!");
        }

    }

}
