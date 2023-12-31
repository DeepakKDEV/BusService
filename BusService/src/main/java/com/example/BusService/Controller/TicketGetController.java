package com.example.BusService.Controller;

import com.example.BusService.Model.TicketBooking;
import com.example.BusService.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketGetController {

           @Autowired
          TicketService ticketService;



           @GetMapping("Get/Ticket/list/all")
           public List<TicketBooking>  getTicket(@RequestParam String email, @RequestParam  String tokenVal){
               return ticketService. getTicket(email,tokenVal);
           }
}
