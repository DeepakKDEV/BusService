package com.example.BusService.Repo;

import com.example.BusService.Model.AdminAboutBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminAboutBus extends JpaRepository<AdminAboutBus,Long> {

    AdminAboutBus findFirstByEmail(String newEmail);
}