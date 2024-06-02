package com.airline_reservation.DTOs.RequestDtos;
/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequestDto {
    private String email;
    private String password;
}
