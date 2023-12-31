package com.example.BusService.Repo;

import com.example.BusService.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepo extends JpaRepository<Customer, Integer> {
       Customer findFirstByEmail(String newEmail);
}
