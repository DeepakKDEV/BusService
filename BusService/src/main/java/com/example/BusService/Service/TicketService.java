package com.example.BusService.Service;

import com.example.BusService.Model.TicketBooking;
import com.example.BusService.Repo.ITicketBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

       @Autowired
    ITicketBookingRepo iTicketBookingRepo;

       @Autowired
       AuthenticationService authenticationService;

    public String ticketBooked(List<TicketBooking> newTicket, String email, String tokenValue) {
         if(authenticationService.authenticateForBookingTicket(email,tokenValue)){
            iTicketBookingRepo.saveAll(newTicket);
            return  "new ticket Booking";
        }
        return "token is  not valid ";

    }

    public List<TicketBooking> customerTicket(Integer id, String email, String tokenValue) {
        // Authenticate the user first
        if (authenticationService.authenticateGetTickets(id, email, tokenValue)) {
            // If authentication succeeds, retrieve the tickets
            return iTicketBookingRepo.findByCustomerId(id);
        }
        return null;
    }

        public String updateTicket (Integer id, String email, String tokenValue){
            if (authenticationService.updateTciket(id, email, tokenValue)) {
                TicketBooking ticketBooking1 = iTicketBookingRepo.findById(Long.valueOf(id)).orElseThrow();
                ticketBooking1.setSeatType(ticketBooking1.getSeatType());
                iTicketBookingRepo.save(ticketBooking1);
                return "ticket type change and  updated";
            }
            return null;
        }


        public String Cancel (Integer id, String email, String tokenValue){

            if (authenticationService.CancleAuthentication(email, tokenValue)) {
                iTicketBookingRepo.deleteById(Long.valueOf(id));
                return "ticket deleted";
            }

            return null;
        }

    public List<TicketBooking> getTicket(String email, String tokenVal) {
        if(authenticationService.authenticateForBookingTicket(email,tokenVal));
              return  iTicketBookingRepo.findAll();
    }
}


