package com.example.BusService.Service;

import com.example.BusService.Model.Authentication.AuthenticationToken;
import com.example.BusService.Model.Customer;
import com.example.BusService.Repo.IAuthenticationRepo;
import com.example.BusService.Repo.ICustomerRepo;
import com.example.BusService.Service.PasswordEncrypt.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

        @Autowired
    ICustomerRepo iCustomerRepo;
        @Autowired
    IAuthenticationRepo iAuthenticationRepo;

        @Autowired
        AuthenticationService authenticationService;



    public String customerSignUp(Customer newCustomer) {

        String newEmail = newCustomer.getEmail();

        Customer existCustomer =iCustomerRepo.findFirstByEmail(newEmail);

        if (existCustomer != null) {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newCustomer.getPassword();

        try {
            String encryptedPassword = PasswordEncrypt.encrypt(signUpPassword);

             newCustomer.setPassword(encryptedPassword);

            iCustomerRepo.save(newCustomer);
            return "User registered Successfully";

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String customerSignIn(String email, String password) {
        Customer existingCustomer = iCustomerRepo.findFirstByEmail(email);

        if (existingCustomer == null) {
            return "Not a valid email, Please sign up first !!!";
        }

        try {
            String encryptedInputPassword = PasswordEncrypt.encrypt(password);

            if (existingCustomer.getPassword().equals(encryptedInputPassword)) {
                AuthenticationToken token = new AuthenticationToken(existingCustomer);
                // authenticationTokenService.CreationToken(token);
                iAuthenticationRepo.save(token);
                return token.getTokenValue();
            } else {
                return "Invalid Credentials!!!";
            }
        } catch (NoSuchAlgorithmException e) {
            return "Internal Server issues while saving password, try again later!!!";
        }
    }



    public String customerSignOut(String email, String tokenValue) {
           if(authenticationService.customerAuthentication(email,tokenValue)){
               authenticationService.deleteToken(tokenValue);
               return "Customer sign out successfully";
           }
        return "token value is not right";
    }

    public List<Customer> getCustomer(String email, String tokenValue) {
        if(authenticationService.authenticateForBookingTicket(email,tokenValue)){
          return iCustomerRepo.findAll();
        }


        //return null;
        return Collections.emptyList();
    }
}
