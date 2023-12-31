package com.example.BusService.Repo;

import com.example.BusService.Model.Authentication.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
