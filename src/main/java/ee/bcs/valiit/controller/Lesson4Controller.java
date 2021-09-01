package ee.bcs.valiit.controller;

import ee.bcs.valiit.controller.model.Accounts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson4Controller {
    Map<String, Double> pangaandmed = new HashMap<>();

    @GetMapping("Lesson4/pank")
    public String valitegevus() {
        return "Vali ning sisesta järgneva tegevuse number aadressireale: 1 - Sisesta loodav konto number 2 - Sisesta konto balansi vaatamiseks 3 - Sisesta konto ning summa (eralda /-ga) raha lisamiseks  4 - Sisesta konto ja väljavõtmise summa (eralda /-ga) 5 - Sisesta /konto kust raha võetakse/konto kuhu raha kantakse/summa ";
    }

    @GetMapping("Lesson4/pank/vaata")
    public Map vaata() {
        return pangaandmed;
    }

    @GetMapping("Lesson4/pank/1/{accNr}")
    public String createAccount(@PathVariable("accNr") String accNr) {
        pangaandmed.put(accNr, (double) 0);
        return ("Konto numbriga " + accNr + " loodud.");
    }

    @GetMapping("Lesson4/pank/2/{accNr}")
    public String getBalance(@PathVariable("accNr") String accNr) {
        return "Arvel on hetkel: " + pangaandmed.get(accNr) + " eurot.";
    }

    @GetMapping("Lesson4/pank/3/{accNr}/{sum}")
    public String deposit(@PathVariable("accNr") String accNr, @PathVariable("sum") double sum) {
        double oldSum = pangaandmed.get(accNr);
        pangaandmed.put(accNr, oldSum + sum);
        return "Arvele number" + accNr + " lisatud " + sum + " eurot.";
    }

    @GetMapping("Lesson4/pank/4/{accNr}/{sum}")
    public String withdraw(@PathVariable("accNr") String accNr, @PathVariable("sum") double sum) {
        Double newAmount = pangaandmed.get(accNr) - sum;
        if (newAmount < 0) {
            return "Arvel ei ole nii suurt summat, alusta palun uuesti!";
        }
        pangaandmed.put(accNr, newAmount);
        return "Pangaarvelt number " + accNr + "maha võetud " + sum + " eurot.";
    }

    @GetMapping("Lesson4/pank/5/{accNr1}/{accNr2}/{sum}")
    public String transfer(@PathVariable("accNr1") String accNr1, @PathVariable("accNr2") String accNr2, @PathVariable("sum") double sum) {
        double minusAmount = pangaandmed.get(accNr1) - sum;
        if (minusAmount < 0) {
            return "Nii suurt summat kontol ei ole, alusta palun uuesti!";
        }
        double totalAmount = pangaandmed.get(accNr2) + sum;
        pangaandmed.put(accNr1, minusAmount);
        pangaandmed.put(accNr2, totalAmount);
        return "Ülekanne arvelt " + accNr1 + " arvele " + accNr2 + "summas " + sum + " sooritatud.";
    }
}


