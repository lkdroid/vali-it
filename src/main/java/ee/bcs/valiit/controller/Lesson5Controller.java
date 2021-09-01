package ee.bcs.valiit.controller;

import ee.bcs.valiit.controller.model.Accounts;
import ee.bcs.valiit.controller.model.SampleEmployeeDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Lesson5Controller {
    Map<String, Accounts> accounts = new HashMap<>();

    @GetMapping("Lesson5/pank")
    public String valitegevus() {
        return "Vali ning sisesta järgneva tegevuse number aadressireale: 1 - Sisesta loodav konto ja omaniku nimi number 2 - Sisesta konto balansi vaatamiseks 3 - Sisesta konto ning summa (eralda /-ga) raha lisamiseks  4 - Sisesta konto ja väljavõtmise summa (eralda /-ga) 5 - Sisesta /konto kust raha võetakse/konto kuhu raha kantakse/summa 6 - Sisesta konto number mis lukustada või avada.";
    }

    @GetMapping("Lesson5/pank/vaata")
    public Map vaata() {
        return accounts;
    }

    @GetMapping("Lesson5/pank/1/{accNr}/{name}")
    public String createAccount(@PathVariable("accNr") String accNr, @PathVariable("name") String name) {

        Accounts account = new Accounts();
        account.setAcc(accNr);
        account.setName(name);
        account.setSum(0);
        account.setLock(false);
        accounts.put(accNr, account);
        return ("Konto numbriga " + accNr + " isikule " + name + " loodud.");
    }

    @GetMapping("Lesson5/pank/2/{accNr}")
    public String getBalance(@PathVariable("accNr") String accNr) {
        Accounts acc1 = accounts.get(accNr);

        return "Kontol on summa: " + acc1.getSum();
    }

    @GetMapping("Lesson5/pank/3/{accNr}/{sum}")
    public String deposit(@PathVariable("accNr") String accNr, @PathVariable("sum") Double sum) {
        Accounts acc1 = accounts.get(accNr);
        if (acc1.isLock()) {return "Konto on suletud!";}
        double oldSum = acc1.getSum();
        acc1.setSum(oldSum + sum);
        accounts.put(accNr, acc1);
        return "Kontole " + acc1.getAcc() + " lisati summa " + sum + " eur.";
    }

    @GetMapping("Lesson5/pank/4/{accNr}/{sum}")
    public String withdraw(@PathVariable("accNr") String accNr, @PathVariable("sum") double sum) {
        Accounts acc1 = accounts.get(accNr);
        if (acc1.isLock()) {return "Konto on suletud!";}
        double oldSum = acc1.getSum();
        if (oldSum - sum < 0) {
            return "Kontol ei ole nii palju raha. Kontol on hetkel " + oldSum + " eur. Palun proovi uuesti.";
        }
        acc1.setSum(oldSum - sum);
        accounts.put(accNr, acc1);
        return "Kontolt " + accNr + "on välja võetud " + sum + " eur.";
    }

    @GetMapping("Lesson5/pank/5/{accNr1}/{accNr2}/{sum}")
    public String transfer(@PathVariable("accNr1") String accNr1, @PathVariable("accNr2") String accNr2, @PathVariable("sum") double sum) {
        Accounts acc1 = accounts.get(accNr1);
        Accounts acc2 = accounts.get(accNr2);
        if (acc1.isLock()) {return "Konto "+ accNr1 + " on suletud!";}
        if (acc2.isLock()) {return "Konto " + accNr2 + " on suletud!";}
        double newSum1 = acc1.getSum() - sum;
        double newSum2 = acc2.getSum() + sum;
        if (newSum1 < 0) {
            return "Kontol ei ole nii palju raha. Kontol on hetkel " + acc1 + " eur. Palun proovi uuesti.";
        }
        acc1.setSum(newSum1);
        acc2.setSum(newSum2);
        accounts.put(accNr1, acc1);
        accounts.put(accNr2, acc2);
        return "Kontolt " + accNr1 + " tõsteti kontole " + accNr2 + " " + sum + " eur.";
    }
    @GetMapping("Lesson5/pank/6/{accNr}")
    public String lockunlock(@PathVariable("accNr") String accNr){
        Accounts acc1 = accounts.get(accNr);
        if (acc1.isLock()) {acc1.setLock(false);
        return "Konto numbriga " + accNr + " on nüüd avatud!";
        }
        if (!acc1.isLock()) {acc1.setLock(true);
        }
        return "Konto numbriga " + accNr + " on nüüd suletud!";
    }
}

