package com.airline_reservation.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @Column(nullable = false)
    private String seatNumber;
    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
