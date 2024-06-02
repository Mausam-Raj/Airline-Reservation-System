package com.airline_reservation.Controllers;
/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */

import com.airline_reservation.DTOs.RequestDtos.FlightRequestDto;
import com.airline_reservation.DTOs.RequestDtos.FlightUpdateRequestDto;
import com.airline_reservation.DTOs.ResponseDtos.FlightResponseDto;
import com.airline_reservation.Services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/add-flight")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightResponseDto> addFlight(@RequestBody FlightRequestDto requestDto) {
        FlightResponseDto responseDto = flightService.addFlight(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/update-flight")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightResponseDto> updateFlightDetails(@RequestBody FlightUpdateRequestDto requestDto) {
        FlightResponseDto responseDto = flightService.updateFlightDetails(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete-flight")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FlightResponseDto> deleteFlight(@RequestParam Long flightId) {
        FlightResponseDto responseDto = flightService.deleteFlight(flightId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
