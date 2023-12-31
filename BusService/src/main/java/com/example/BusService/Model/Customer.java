
package com.example.BusService.Model;

import com.example.BusService.Model.Enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Integer id;

      @NotBlank
      private String name;

      @NotNull
      @Min(value = 18, message = "Age must be greater than or equal to 18")
      private Integer age;

      @Enumerated(value = EnumType.STRING)
      private Gender gender;

      @Email
      private String email;

      //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$!%])[A-Za-z\\d@#$!%]{8,}$", message = "Invalid password pattern")
      @Size(min = 9, message = "Password must be at least 9 characters long")
      private String password;

      @Size(min = 10, max = 10, message = "Phone number must be exactly 10 characters")
      private String phone_number;

      @OneToOne()
      @JoinColumn(name = "FK_Bus_id")
      AdminAboutBus adminAboutBus;

     @OneToOne()
     @JoinColumn(name = "FK_Ticket_id")
     TicketBooking ticketBooking;

}