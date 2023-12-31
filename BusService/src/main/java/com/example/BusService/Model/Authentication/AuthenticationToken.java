package com.example.BusService.Model.Authentication;

import com.example.BusService.Model.AdminAboutBus;
import com.example.BusService.Model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    public AuthenticationToken(Customer existingCustomer) {
        this.customer_id=existingCustomer;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }

    public AuthenticationToken(AdminAboutBus existingBus) {
        this.Bus_id=existingBus;
        this.tokenCreationTime = LocalDateTime.now();
        this.tokenValue = UUID.randomUUID().toString();
    }
    @OneToOne
    @JoinColumn(name = "fk_Bus_id")
    AdminAboutBus Bus_id;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public LocalDateTime getTokenCreationTime() {
        return tokenCreationTime;
    }

    public void setTokenCreationTime(LocalDateTime tokenCreationTime) {
        this.tokenCreationTime = tokenCreationTime;
    }

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    @OneToOne
    @JoinColumn(name = "fk_customer_id")
   Customer customer_id;
}
