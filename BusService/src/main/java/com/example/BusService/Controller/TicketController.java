package com.example.BusService.Controller;

import com.example.BusService.Model.TicketBooking;
import com.example.BusService.Service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

       @Autowired
       TicketService ticketService;

    @PostMapping("bus/ticket/added/by/list")
    public String ticketBooked(@RequestBody @Valid List<TicketBooking> newTicket, @RequestParam  @Valid  String email, @RequestParam  @Valid  String tokenValue) {
        return ticketService.ticketBooked(newTicket, email, tokenValue);
    }

}
