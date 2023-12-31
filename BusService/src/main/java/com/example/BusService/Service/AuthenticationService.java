package com.example.BusService.Service;

import com.example.BusService.Model.Authentication.AuthenticationToken;
import com.example.BusService.Repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

        @Autowired
       IAuthenticationRepo   iAuthenticationRepo;

    public boolean authenticateForBookingTicket(String email, String tokenValue) {

        AuthenticationToken token =iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token !=null){
            return   token.getCustomer_id().getEmail().equals(email);
        }

        return false;
    }

    public boolean authenticateGetTickets(Integer id, String email, String tokenValue) {
        AuthenticationToken token =iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token !=null){
            return   token.getCustomer_id().getEmail().equals(email);
        }

        return false;
    }
    public boolean updateTciket(Integer ticketBooking, String email, String tokenValue) {
        AuthenticationToken token =iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token !=null){
            return   token.getCustomer_id().getEmail().equals(email);
        }

        return false;

    }
    public boolean customerAuthentication(String email, String tokenValue) {

          AuthenticationToken token=iAuthenticationRepo.findFirstByTokenValue(tokenValue);
          if(token !=null){
              return  token.getCustomer_id().getEmail().equals(email);
          }
          return  false;

    }

    // ticket cancel
    public boolean CancleAuthentication(String email, String tokenValue) {
        AuthenticationToken token=iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getCustomer_id().getEmail().equals(email);
        }else
        {
            return false;
        }
    }
    //customer  sign-out
    public void deleteToken(String tokenValue) {
        AuthenticationToken token =  iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        iAuthenticationRepo.delete(token);
    }
    public void CreationToken(AuthenticationToken token) {
        iAuthenticationRepo.save(token);
    }

    public boolean authenticationAdmin(String email, String tokenValue) {
        AuthenticationToken token =iAuthenticationRepo.findFirstByTokenValue(tokenValue);
        if(token !=null){
            return   token.getBus_id().getEmail().equals(email);
        }

        return false;
    }



}
