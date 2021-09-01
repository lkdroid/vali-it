package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController

public class Lesson3HardController {
    Random random = new Random();
    int i = random.nextInt(100);

    int count = 0;

    @GetMapping("Lesson3HardController/random/{a}")
    public String random(@PathVariable("a") int a) {
        count++;

        int sisendnr = a;
        if (sisendnr < i) {
            return ("VALE! Otsitav arv on SUUREM!");
        } else if (sisendnr > i) {
            return ("VALE! Otsitav arv on VÄIKSEM!");
        } else {
            String countString = String.valueOf(count);
            count=0;
            i = random.nextInt(100);
            return "Õieti arvasid" + countString;

        }


    }


}
