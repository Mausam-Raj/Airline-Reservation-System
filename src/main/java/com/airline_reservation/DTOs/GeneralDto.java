package com.airline_reservation.DTOs;
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
public class GeneralDto {
    String message;
}
