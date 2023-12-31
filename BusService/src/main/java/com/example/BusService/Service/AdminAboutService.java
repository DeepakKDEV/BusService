package com.example.BusService.Service;

import com.example.BusService.Model.AdminAboutBus;
import com.example.BusService.Model.Authentication.AuthenticationToken;
import com.example.BusService.Repo.IAdminAboutBus;
import com.example.BusService.Service.PasswordEncrypt.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class AdminAboutService {
        
         @Autowired
         IAdminAboutBus iAboutBusRepo;
         @Autowired
         AuthenticationService authenticationService;

    public String AdminSignUp(AdminAboutBus aboutBus) {

          String   newEmail=aboutBus.getEmail();
          AdminAboutBus existingEmail=iAboutBusRepo.findFirstByEmail(newEmail);
          if(existingEmail !=null){
              return  "this email are already having reEnter  new  email";
          }
          String password=aboutBus.getPassword();
          String  passwordEncrypted;
          try{
              passwordEncrypted= PasswordEncrypt.encrypt(password);
             aboutBus.setPassword(passwordEncrypted);

             iAboutBusRepo.save(aboutBus);
              return "Admin  registered Successfully done";

          } catch (NoSuchAlgorithmException e) {
             // throw new RuntimeException(e);
              return "Internal Server issues while saving password, try again later!!!";
          }

    }


    public String AdminSignIn(String email, String password) {
           String  newEmail = email;
            AdminAboutBus existingBus =iAboutBusRepo.findFirstByEmail(newEmail);

           if(existingBus ==null){
            return "Not a valid email, Please sign up first !!!";
        }
           String  newPassword=password;
           String Password;
           try{
               Password=PasswordEncrypt.encrypt(newPassword);
               if(existingBus.getPassword().equals(password)){
                   AuthenticationToken token=new AuthenticationToken(existingBus);
                    authenticationService.CreationToken(token);
                   return token.getTokenValue();

               } else {
            return "Invalid Credentials!!!";
        }
    } catch (NoSuchAlgorithmException e) {
        return "Internal Server issues while saving password, try again later!!!";
      }
    }

    public String updateBus(long id, AdminAboutBus aboutBus, String email, String tokenValue) {
        if (authenticationService.authenticationAdmin(email,tokenValue)){
            AdminAboutBus aboutBus1= iAboutBusRepo.findById(id).orElseThrow();
            aboutBus1.setName(aboutBus1.getName());
           iAboutBusRepo.save(aboutBus1);
            return "nameBus updated";
        }
        return null;

    }

    public String deleteBus(Long id, String email, String tokenValue) {
        if(authenticationService.authenticationAdmin(email,tokenValue)){
            AdminAboutBus adminAboutBus=iAboutBusRepo.findById(id).orElseThrow();
            iAboutBusRepo.deleteById(id);
            return  "delete value";
        }
        return null;
    }
}
