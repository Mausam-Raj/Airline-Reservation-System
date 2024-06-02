package com.airline_reservation.Entities;

import com.airline_reservation.Enums.Airport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;
    @Column(nullable = false)
    private String flightName;
    @Column(nullable = false)
    private int totalSeats;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Airport departureAirport;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Airport destinationAirport;
    @Column(nullable = false)
    private String departureTime;
    @Column(nullable = false)
    private String arrivalTime;
    @Column(nullable = false)
    private int duration;
    @Column(nullable = false)
    private int price;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<Ticket> soldTickets;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private List<User> passengers;
}
