package com.airline_reservation.DTOs.RequestDtos;

import com.airline_reservation.Enums.Gender;
import com.airline_reservation.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author : Mausam Raj
 * @Date : 02-03-2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String name;
    private int age;
    private Gender gender;
    private String email;
    private String password;
    private String address;
    private String dateOfBirth;
    private Role role;
}
