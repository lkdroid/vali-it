package ee.bcs.valiit.service.Bank;

import ee.bcs.valiit.controller.model.AccountOverview;
import ee.bcs.valiit.controller.model.AccountStatement;
import ee.bcs.valiit.controller.model.Accounts;
import ee.bcs.valiit.controller.model.LockBalance;
import ee.bcs.valiit.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankService {

    //See pole veel päris õig koht?, aga hetkel siia
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private BankRepository bankRepository;

    public String createSClient(String firstName, String lastName, String clientAddress) {
        int IDN = bankRepository.createClientK(firstName, lastName, clientAddress);
        String IDNS = String.valueOf(IDN);
        return "Panga kasutaja kliendile " + firstName + lastName + " ja ID on " + IDNS + " on loodud!";
    }

    public String createSAccount(String accNr, int clientId) {
        bankRepository.createRAccount(accNr, clientId);
        return "Panga konto " + accNr + " on loodud!";
    }

    public String accountBBalance(String accNr) {
        String balanss = String.valueOf(bankRepository.accountRBalance(accNr));
        return "Konto numbriga " + accNr + " seis on " + balanss + " EUR.";
    }

    public String depositB(String accNr, double sum) {
        LockBalance lockbalance = bankRepository.returnRlockbalance(accNr);
        if (lockbalance.getLock()) {
            return "Konto on lukus!";
        }
        double oldSum = lockbalance.getBalance();
        double newSum = oldSum + sum;
        bankRepository.changeAccountBalance(accNr, newSum);
        String s1 = String.valueOf(newSum);
        String s2 = String.valueOf(sum);
        String actiontype = "Deposit";
        //See osa kirjutab statement andmebaasi kirjed väljavõtte jaoks
        bankRepository.insertRStatement(accNr, actiontype, sum, newSum, lockbalance.getClientid());
        //väljavõtte osa lõpp
        return "Lisatud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
    }

    public String withdrawB(String accNr, Double sum) {
        LockBalance lockbalance = bankRepository.returnRlockbalance(accNr);
        if (lockbalance.getLock()) {
            return "Konto on lukus!";
        }
        double newSum = lockbalance.getBalance() - sum;
        if (newSum < 0) {
            return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        bankRepository.changeAccountBalance(accNr, newSum);
        String s1 = String.valueOf(newSum);
        String s2 = String.valueOf(sum);
        String actiontype = "Withdrawal";
        //See osa kirjutab statement andmebaasi kirjed väljavõtte jaoks
        bankRepository.insertRStatement(accNr, actiontype, sum, newSum, lockbalance.getClientid());
        //väljavõtte osa lõpp
        return "Välja võetud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
    }

    public String transferB(String accNr1, String accNr2, double sum) {
        LockBalance lockbalance1 = bankRepository.returnRlockbalance(accNr1);
        LockBalance lockbalance2 = bankRepository.returnRlockbalance(accNr2);
        if (lockbalance1.getLock()) {
            return "Konto on lukus!";
        }
        if (lockbalance2.getLock()) {
            return "Konto on lukus!";
        }
        double sum1 = lockbalance1.getBalance() - sum;
        if (sum1 < 0) {
            return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        bankRepository.changeAccountBalance(accNr1, sum1);
        double sum2 = lockbalance2.getBalance() + sum;
        bankRepository.changeAccountBalance(accNr2, sum2);
        String s1 = String.valueOf(sum2);
        String s2 = String.valueOf(sum);
        String s3 = String.valueOf(sum1);
        String actiontype1 = "Outgoing transfer";
        String actiontype2 = "Incoming transfer";
        //See osa kirjutab statement andmebaasi kirjed väljavõtte jaoks
        bankRepository.insertRStatement(accNr1, actiontype1, sum, sum1, lockbalance1.getClientid());
        bankRepository.insertRStatement(accNr2, actiontype2, sum, sum2, lockbalance2.getClientid());
        //väljavõtte osa lõpp
        return "Arvelt " + accNr1 + " (jääk nüüd " + s3 + " EUR ) on lisatud " + s2 + " EUR arvele " + accNr2 + " mille uus balanss on " + s1 + " EUR.";
    }

    public String accLockB(String accNr) {
        LockBalance lockbalance = bankRepository.returnRlockbalance(accNr);
        Boolean check = bankRepository.checkRLock(accNr);
        bankRepository.changeRLock(accNr, check);
        if (check) {
            //See osa kirjutab statement andmebaasi kirjed väljavõtte jaoks
            String actiontype = "Avamine lukust";
            bankRepository.insertRStatement(accNr, actiontype, 0, 0, lockbalance.getClientid());
            //väljavõtte osa lõpp
            return "Konto " + accNr + " on nüüd avatud!";

        } else {
            String actiontype = "Lukustamine";
            bankRepository.insertRStatement(accNr, actiontype, 0, 0, lockbalance.getClientid());
            return "Konto " + accNr + " on nüüd suletud!";
        }
    }


    public List<AccountOverview> accountSOverview(int id) {
        return bankRepository.accountROverview(id);
    }

    public List<AccountOverview> returnSallaccounts() {
        return bankRepository.returnRallaccounts();
    }

    public List accountSStatement(String accNr) {
        return bankRepository.accountRStatement(accNr);
    }
}