package com.example.BusService.Model;

import com.example.BusService.Model.Enums.DropArea;
import com.example.BusService.Model.Enums.SeatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TicketBooking {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private  Integer   id;
       @NotBlank
       private  Integer price;
       @Enumerated(value = EnumType.STRING)
       private SeatType seatType;
       @Enumerated(value = EnumType.STRING)
       private  DropArea dropArea;



       @OneToOne(mappedBy = "ticketBooking")
       Customer customer;


}
