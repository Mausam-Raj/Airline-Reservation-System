package com.airline_reservation.DTOs.ResponseDtos;
/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */

import com.airline_reservation.Enums.Gender;
import com.airline_reservation.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long userId;
    private String name;
    private int age;
    private Gender gender;
    private String email;
    private String address;
    private Role role;
}
