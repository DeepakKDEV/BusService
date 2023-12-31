package com.example.BusService.Controller;

import com.example.BusService.Model.Customer;
import com.example.BusService.Model.TicketBooking;
import com.example.BusService.Service.CustomerService;
import com.example.BusService.Service.TicketService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/booking")
@Validated
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    TicketService ticketService;

    @PostMapping("/signUp/customer") // Add a forward slash at the beginning of the path
    public String customerSignUp(@RequestBody @Valid Customer newCustomer) {
        return customerService.customerSignUp(newCustomer);
    }

    @PostMapping("signIn/Customer")
      public String customerSignIn(@RequestParam @Valid  String email, @RequestParam @Valid  String  password){
           return  customerService.customerSignIn(email,password);
      }

     @DeleteMapping("user/signOut")
     public String customerSignOut(@RequestParam String email, @RequestParam String tokenVal) {
        return customerService. customerSignOut(email, tokenVal);
     }

     @GetMapping("/Get/customer/data")
     public List<Customer>  getCustomer(String email, String tokenValue){
        return customerService. getCustomer(email,tokenValue);
     }
    @GetMapping("get/Customer/ticket/id/{id}")
    public List<TicketBooking> customerTicket(
            @PathVariable @Positive(message = "Id must be a positive integer") Integer id,
            @RequestParam @NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") String email,
            @RequestParam @NotBlank(message = "Token value cannot be blank") String tokenValue) {
        // Call the service method
        return ticketService.customerTicket(id, email, tokenValue);
     }



    @PutMapping("Customer/by/Change/id/{id}")
      public  String  updateTicket(@PathVariable Integer  id, @RequestParam String email, @RequestParam String tokenValue){
            return  ticketService. updateTicket(id,email,tokenValue);
      }

    @DeleteMapping("Cancel/ticket/by/id/{id}")
    public String Cancel(@PathVariable Integer id, @RequestParam String email, @RequestParam String tokenValue){
        return  ticketService.Cancel(id, email,tokenValue);
    }


}
