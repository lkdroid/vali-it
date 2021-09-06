package ee.bcs.valiit.service.Bank;

import ee.bcs.valiit.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankService {

    //See pole veel päris õig koht?, aga hetkel siia
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private BankRepository bankRepository;

    public String createBAccount(String accNr, String accName) {
        bankRepository.createRAccount(accNr, accName);
        return "Konto numbriga " + accNr + " on loodud!";
    }

    public String accountBBalance(String accNr) {
        String balanss = String.valueOf(bankRepository.accountRBalance(accNr));
        return "Konto numbriga " + accNr + " seis on " + balanss + " EUR.";
    }

    public String depositB(String accNr, double sum) {
        if (bankRepository.checkRLock(accNr)) {
            return "Konto on lukus!";
        }
        double oldSum = bankRepository.accountRBalance(accNr);
        double newSum = oldSum + sum;
        bankRepository.changeAccountBalance(accNr, newSum);
        String s1 = String.valueOf(newSum);
        String s2 = String.valueOf(sum);
        return "Lisatud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
    }

    public String withdrawB(String accNr, Double sum) {
        if (bankRepository.checkRLock(accNr)) {
            return "Konto on lukus!";
        }
        double newSum = bankRepository.accountRBalance(accNr) - sum;
        if (newSum < 0) {
            return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        bankRepository.changeAccountBalance(accNr, newSum);
        String s1 = String.valueOf(newSum);
        String s2 = String.valueOf(sum);
        return "Välja võetud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
    }

    public String transferB(String accNr1, String accNr2, double sum) {
        if (bankRepository.checkRLock(accNr1)) {
            return "Konto on lukus!";
        }
        if (bankRepository.checkRLock(accNr2)) {
            return "Konto on lukus!";
        }
        double sum1 = bankRepository.accountRBalance(accNr1) - sum;
        if (sum1 < 0) {
            return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        bankRepository.changeAccountBalance(accNr1, sum1);
        double sum2 = bankRepository.accountRBalance(accNr2) + sum;
        bankRepository.changeAccountBalance(accNr2, sum2);
        String s1 = String.valueOf(sum2);
        String s2 = String.valueOf(sum);
        String s3 = String.valueOf(sum1);
        return "Arvelt " + accNr1 + " (jääk nüüd " + s3 + " EUR ) on lisatud " + s2 + " EUR arvele " + accNr2 + " mille uus balanss on " + s1 + " EUR.";
    }

    public String accLockB(String accNr) {
        Boolean check = bankRepository.checkRLock(accNr);
        bankRepository.changeRLock(accNr, check);
        if (check) {
            return "Konto " + accNr + " on nüüd avatud!";

        } else {

            return "Konto " + accNr + " on nüüd suletud!";
        }
    }
}
