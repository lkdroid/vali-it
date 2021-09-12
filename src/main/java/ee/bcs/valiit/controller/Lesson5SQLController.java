package ee.bcs.valiit.controller;

import ch.qos.logback.core.net.server.Client;
import ee.bcs.valiit.controller.model.*;
import ee.bcs.valiit.service.Bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Lesson5SQLController {
    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;
    @Autowired
    private BankService bankService;

    @GetMapping("Lesson5/bank")
    public String valitegevus() {
        return "Vali ning sisesta järgneva tegevuse number aadressireale: 1 - Sisesta loodav konto ja omaniku nimi number 2 - Sisesta konto balansi vaatamiseks 3 - Sisesta konto ning summa (eralda /-ga) raha lisamiseks  4 - Sisesta konto ja väljavõtmise summa (eralda /-ga) 5 - Sisesta /konto kust raha võetakse/konto kuhu raha kantakse/summa 6 - Sisesta konto number mis lukustada või avada.";
    }

    @PostMapping("Lesson5/bank/createClient/")
    public String createSClient(@RequestBody ClientDto clientin) {
        return bankService.createSClient(clientin.getFirstName(), clientin.getLastName(), clientin.getAddress());

    }

    @GetMapping("Lesson5/bank/createAccount/{accNr}/{clientId}")
    public String createAccount(@PathVariable("accNr") String accNr, @PathVariable("clientId") int clientId) {
        return bankService.createSAccount(accNr, clientId);

    }

    @GetMapping("Lesson5/bank/2/{accNr}")
    public String accbalance(@PathVariable("accNr") String accNr) {
        return bankService.accountBBalance(accNr);

    }

    @GetMapping("Lesson5/bank/3/{accnr}/{sum}")
    public String deposit(@PathVariable("accnr") String accnr, @PathVariable("sum") double sum) {
        return bankService.depositB(accnr, sum);
    }

    @GetMapping("Lesson5/bank/4/{accnr}/{sum}")
    public String withdraw(@PathVariable("accnr") String accnr, @PathVariable("sum") double sum) {
        return bankService.withdrawB(accnr, sum);
    }

    @GetMapping("Lesson5/bank/5/{accNr1}/{accNr2}/{sum}")
    public String transfer(@PathVariable("accNr1") String accNr1, @PathVariable("accNr2") String accNr2, @PathVariable("sum") double sum) {
        return bankService.transferB(accNr1, accNr2, sum);
    }

    @GetMapping("Lesson5/bank/6/{accnr}")
    public String acclock(@PathVariable("accnr") String accnr) {
        return bankService.accLockB(accnr);
    }


    @GetMapping("Lesson5/accountoverview/{id}")
    public List<AccountOverview> returnaccountoverview(@PathVariable("id") int id) {
        return bankService.accountSOverview(id);

    }
    @GetMapping("Lesson5/allaccounts")
        public List<AccountOverview> returnallaccounts() {
        return bankService.returnSallaccounts();
    }

    @GetMapping("Lesson5/bank/statement/{a}")
    public List accountStatement(@PathVariable("a") String accNr) {
        return bankService.accountSStatement(accNr);
    }
}
