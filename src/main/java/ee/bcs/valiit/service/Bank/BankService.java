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

    public String createBAccount(String accNr, String accName)
    {
        bankRepository.createRAccount(accNr,accName);
        return "Konto numbriga " +  accNr + " on loodud!";
    }
public String accountBBalance(String accNr){
    return bankRepository.accountRBalance(accNr);
}
public String depositB (String accNr, double sum) {
    String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
    Map<String, Object> paramMapCheckLock = new HashMap<>();
    paramMapCheckLock.put("a1", accNr);
    Boolean checkLock = jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
    if (checkLock) {return "Konto on lukus!";}
    String sqlOldSum = "SELECT balance FROM bank WHERE accnr = :a1";
    Map<String, Object> paramMapOldSum = new HashMap<>();
    paramMapOldSum.put("a1", accNr);
    double newSum = jdbcTemplate.queryForObject(sqlOldSum, paramMapOldSum, Double.class) + sum;
    String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
    Map<String, Object> paramMapUpdateSum = new HashMap<>();
    paramMapUpdateSum.put("a2", newSum);
    paramMapUpdateSum.put("a3", accNr);
    jdbcTemplate.update(sqlSum, paramMapUpdateSum);
    String s1 = String.valueOf(newSum);
    String s2 = String.valueOf(sum);
    return "Lisatud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
}

public String withdrawB(String accNr, Double sum ) {
    String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
    Map<String, Object> paramMapCheckLock = new HashMap<>();
    paramMapCheckLock.put("a1", accNr);
    Boolean checkLock = jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
    if (checkLock) {return "Konto on lukus!";}
    String sqlOldSum = "SELECT balance FROM bank WHERE accnr = :a1";
    Map<String, Object> paramMapOldSum = new HashMap<>();
    paramMapOldSum.put("a1", accNr);
    double newSum = jdbcTemplate.queryForObject(sqlOldSum, paramMapOldSum, Double.class) - sum;
    if (newSum < 0) {
        return "Arvel ei ole nii suurt summat, proovi uuesti!";
    }
    String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
    Map<String, Object> paramMapUpdateSum = new HashMap<>();
    paramMapUpdateSum.put("a2", newSum);
    paramMapUpdateSum.put("a3", accNr);
    jdbcTemplate.update(sqlSum, paramMapUpdateSum);
    String s1 = String.valueOf(newSum);
    String s2 = String.valueOf(sum);
    return "Välja võetud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
}
public String transferB(String accNr1, String accNr2, double sum){
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accNr1);
        Boolean checkLock = jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {return "Konto on lukus!";}
        paramMapCheckLock.put("a1", accNr2);
        checkLock = jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {return "Konto on lukus!";}
        String sqlOld = "SELECT balance FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapOld = new HashMap<>();
        paramMapOld.put("a1", accNr1);
        double sum1 = jdbcTemplate.queryForObject(sqlOld, paramMapOld, Double.class) - sum;
        if (sum1 < 0) {
            return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
        Map<String, Object> paramMapUpdate = new HashMap<>();
        paramMapUpdate.put("a2", sum1);
        paramMapUpdate.put("a3", accNr1);
        jdbcTemplate.update(sqlSum, paramMapUpdate);

        String sqlNew = "SELECT balance FROM bank WHERE accnr = :a2";
        Map<String, Object> paramMapNew = new HashMap<>();
        paramMapNew.put("a2", accNr2);
        double sum2 = jdbcTemplate.queryForObject(sqlNew, paramMapNew, Double.class) + sum;
        String sqlSum2 = "UPDATE bank SET balance = :a4 WHERE accnr = :a5";
        Map<String, Object> paramMapUpdate2 = new HashMap<>();
        paramMapUpdate2.put("a4", sum2);
        paramMapUpdate2.put("a5", accNr2);
        jdbcTemplate.update(sqlSum2, paramMapUpdate2);
        String s1 = String.valueOf(sum2);
        String s2 = String.valueOf(sum);
        String s3 = String.valueOf(sum1);
        return "Arvelt " + accNr1 + " (jääk nüüd " + s3+ " EUR ) on lisatud " + s2 + " EUR arvele " + accNr2 + " mille uus balanss on " + s1 + " EUR.";
    }

    public String accLockB(String accNr){
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accNr);
        Boolean checkLock = jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {
            String sqlUnlock = "UPDATE bank SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapLock = new HashMap<>();
            Boolean unlock = false;
            paramMapLock.put("a4", unlock);
            paramMapLock.put("a5", accNr);
            jdbcTemplate.update(sqlUnlock, paramMapLock);
            return "Konto " + accNr + " on nüüd avatud!";

        } else {String sqlLock = "UPDATE bank SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapUnlock = new HashMap<>();
            Boolean lock = true;
            paramMapUnlock.put("a4", lock);
            paramMapUnlock.put("a5", accNr);
            jdbcTemplate.update(sqlLock, paramMapUnlock);
            return "Konto " + accNr + " on nüüd suletud!";
        }
    }
}
