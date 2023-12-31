package com.example.BusService.Repo;

import com.example.BusService.Model.TicketBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITicketBookingRepo extends JpaRepository<TicketBooking, Long> {


    List<TicketBooking> findByCustomerId(Integer id);
}
