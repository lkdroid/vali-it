package ee.bcs.valiit.controller;

import ee.bcs.valiit.service.Bank.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import ee.bcs.valiit.controller.model.*;
import ee.bcs.valiit.service.Bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentalController {
    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;
    @Autowired
    private RentalService rentalService;

    @GetMapping("rental")
    public List<OneRental> allRentalList()
    {return rentalService.returnSallrentals();}

    @GetMapping("rentalrandom/{randomnumber}")
    public String randomrentals(@PathVariable("randomnumber") int randomNumber){

        return rentalService.returnSrandomrentals(randomNumber);
    }


}
