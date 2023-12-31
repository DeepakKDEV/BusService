package com.example.BusService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Validated
public class AdminAboutBus {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private  Long   id;
       @NotNull
       private  String  name;
       @Email
       private  String email;

       //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$!%])[A-Za-z\\d@#$!%]{8,}$", message = "Invalid password pattern")
       @Size(min = 11, message = "Password must be at least 11 characters long")
       private    String password;
       private  Integer seats;
       @NotBlank
       private  String  travel_company;

       public Long getId() {
              return id;
       }

       public void setId(Long id) {
              this.id = id;
       }

       public String getName() {
              return name;
       }

       public void setName(String name) {
              this.name = name;
       }

       public Integer getSeats() {
              return seats;
       }

       public void setSeats(Integer seats) {
              this.seats = seats;
       }

       public String getTravel_company() {
              return travel_company;
       }

       public void setTravel_company(String travel_company) {
              this.travel_company = travel_company;
       }


       @OneToOne(mappedBy = "adminAboutBus")
       Customer customer;



}
