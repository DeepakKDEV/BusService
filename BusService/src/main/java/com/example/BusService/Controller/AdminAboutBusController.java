package com.example.BusService.Controller;

import com.example.BusService.Model.AdminAboutBus;
import com.example.BusService.Service.AdminAboutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@Validated
public class AdminAboutBusController {

         @Autowired
         AdminAboutService adminAboutService;

//         @PostMapping("Admin/sign/Up")
//         public String  AdminSign(@RequestBody AdminAboutBus adminAboutBus , @RequestBody AdminDto adminDto){
//            return adminAboutService.AdminSign(adminAboutBus, adminDto);
//         }

      @PostMapping("Admin/sign/up")
      public String   AdminSignUp(@RequestBody   @Valid AdminAboutBus  aboutBus){
          return  adminAboutService.AdminSignUp(aboutBus);
      }
      @PostMapping("Admin/sign/in")
       public String AdminSignIn( @RequestParam  @Valid  String email, @RequestParam @Valid String password){
          return   adminAboutService. AdminSignIn(email,password);
     }


    @PutMapping("update/order/id/{id}")
    public String updateBus(@PathVariable long  id, @RequestBody AdminAboutBus aboutBus, @RequestParam String email , @RequestParam String tokenValue){
        return  adminAboutService. updateBus(id, aboutBus, email,tokenValue);
    }

    @DeleteMapping("Delete/by/id/{id}")
    public String  deleteBus(@PathVariable long id, @RequestParam String email, @RequestParam String tokenValue){
        return  adminAboutService.deleteBus(id, email,tokenValue);
    }


}
