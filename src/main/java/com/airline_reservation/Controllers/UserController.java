package com.airline_reservation.Controllers;

import com.airline_reservation.DTOs.GeneralDto;
import com.airline_reservation.DTOs.ResponseDtos.FlightResponseDto;
import com.airline_reservation.DTOs.ResponseDtos.TicketResponseDto;
import com.airline_reservation.Entities.Ticket;
import com.airline_reservation.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/update-password")
    public ResponseEntity<GeneralDto> updatePassword(@RequestParam Long userId, @RequestParam String password) {
        GeneralDto responseDto = userService.updateUserPassword(userId, password);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get-flights")
    public ResponseEntity<List<FlightResponseDto>> getFlights(@RequestParam String from, @RequestParam String to) {
        List<FlightResponseDto> responseDtos = userService.getFlights(from, to);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }


    @PostMapping("/book-flight")
    public ResponseEntity<TicketResponseDto> bookFlight(@RequestParam Long userId, @RequestParam Long flightId) {
        TicketResponseDto responseDto = userService.bookFlight(userId, flightId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/booked-tickets")
    public ResponseEntity<List<TicketResponseDto>> viewBookedTickets(@RequestParam Long userId) {
        List<TicketResponseDto> ticketList = userService.viewBookedTickets(userId);
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @DeleteMapping("/cancel-ticket")
    public ResponseEntity<GeneralDto> cancelTicket(@RequestParam Long ticketId) {
        GeneralDto responseDto = userService.cancelTicket(ticketId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
