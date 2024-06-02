package com.airline_reservation.Services;

import com.airline_reservation.DTOs.GeneralDto;
import com.airline_reservation.DTOs.RequestDtos.UserRequestDto;
import com.airline_reservation.DTOs.ResponseDtos.FlightResponseDto;
import com.airline_reservation.DTOs.ResponseDtos.TicketResponseDto;
import com.airline_reservation.DTOs.ResponseDtos.UserResponseDto;
import com.airline_reservation.Entities.Flight;
import com.airline_reservation.Entities.Ticket;
import com.airline_reservation.Entities.User;
import com.airline_reservation.Exceptions.FlightNotFoundException;
import com.airline_reservation.Exceptions.TicketNotFoundException;
import com.airline_reservation.Exceptions.UserNotFoundException;
import com.airline_reservation.Repositories.FlightRepository;
import com.airline_reservation.Repositories.TicketRepository;
import com.airline_reservation.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDto registerUser(UserRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .age(requestDto.getAge())
                .gender(requestDto.getGender())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .address(requestDto.getAddress())
                .dateOfBirth(requestDto.getDateOfBirth())
                .role(requestDto.getRole())
                .bookedTickets(new ArrayList<>())
                .flight(null)
                .build();
        userRepository.save(user);

        return UserResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .age(user.getAge())
                .gender(user.getGender())
                .email(user.getEmail())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }

    public GeneralDto updateUserPassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found!!"));
        user.setPassword(passwordEncoder.encode(password));
        return new GeneralDto("Password Updated Successfully!!");
    }

    public List<FlightResponseDto> getFlights(String from, String to) {
        List<Flight> flightList = flightRepository.findAll();
        List<FlightResponseDto> availableFlightList = new ArrayList<>();

        for(Flight flight : flightList) {
            System.out.println(flight);
            if(flight.getDepartureAirport().toString().equals(from) && flight.getDestinationAirport().toString().equals(to)) {
                FlightResponseDto responseDto = FlightResponseDto.builder()
                        .flightId(flight.getFlightId())
                        .flightName(flight.getFlightName())
                        .totalSeats(flight.getTotalSeats())
                        .departureAirport(flight.getDepartureAirport())
                        .destinationAirport(flight.getDestinationAirport())
                        .departureTime(flight.getDepartureTime())
                        .arrivalTime(flight.getArrivalTime())
                        .duration(flight.getDuration())
                        .price(flight.getPrice())
                        .availableSeats(flight.getTotalSeats() - flight.getPassengers().size())
                        .build();

                availableFlightList.add(responseDto);
            }
        }
        return availableFlightList;
    }

    public TicketResponseDto bookFlight(Long userId, Long flightId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found!!"));
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoundException("Flight Not Found!!"));

        Ticket ticket = Ticket.builder()
                .seatNumber((flight.getPassengers().size() + 1 + ""))
                .flight(flight)
                .user(user)
                .build();

        ticketRepository.save(ticket);

        return TicketResponseDto.builder()
                .ticketId(ticket.getTicketId())
                .seatNumber(ticket.getSeatNumber())
                .flightName(flight.getFlightName())
                .departureAirport(flight.getDepartureAirport().toString())
                .destinationAirport(flight.getDestinationAirport().toString())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .duration(flight.getDuration())
                .price(flight.getPrice())
                .passengerName(user.getName())
                .passengerAge(user.getAge())
                .gender(user.getGender().toString())
                .email(user.getEmail())
                .build();
    }

    public List<TicketResponseDto> viewBookedTickets(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found!!"));
        if(user != null) {
            List<Ticket> ticketList = user.getBookedTickets();
            List<TicketResponseDto> responseDtoList = new ArrayList<>();
            for(Ticket ticket : ticketList) {
                Flight flight = ticket.getFlight();
                TicketResponseDto responseDto = TicketResponseDto.builder()
                        .ticketId(ticket.getTicketId())
                        .seatNumber(ticket.getSeatNumber())
                        .flightName(flight.getFlightName())
                        .departureAirport(flight.getDepartureAirport().toString())
                        .destinationAirport(flight.getDestinationAirport().toString())
                        .departureTime(flight.getDepartureTime())
                        .arrivalTime(flight.getArrivalTime())
                        .duration(flight.getDuration())
                        .price(flight.getPrice())
                        .passengerName(user.getName())
                        .passengerAge(user.getAge())
                        .gender(user.getGender().toString())
                        .email(user.getEmail())
                        .build();

                responseDtoList.add(responseDto);
            }
            return responseDtoList;
        }
        return new ArrayList<>();
    }

    public GeneralDto cancelTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket Not Found!!"));
        ticketRepository.delete(ticket);
        return new GeneralDto("Ticket Cancelled Successfully!!");
    }
}
