package ee.bcs.valiit.controller;

import ee.bcs.valiit.controller.model.Accounts;
import ee.bcs.valiit.controller.model.SampleEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson5SQLController {
    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;


    @GetMapping("Lesson5/bank")
    public String valitegevus() {
        return "Vali ning sisesta järgneva tegevuse number aadressireale: 1 - Sisesta loodav konto ja omaniku nimi number 2 - Sisesta konto balansi vaatamiseks 3 - Sisesta konto ning summa (eralda /-ga) raha lisamiseks  4 - Sisesta konto ja väljavõtmise summa (eralda /-ga) 5 - Sisesta /konto kust raha võetakse/konto kuhu raha kantakse/summa 6 - Sisesta konto number mis lukustada või avada.";
    }

    @GetMapping("Lesson5/bank/1/{accNr}/{accName}")
    public void create(@PathVariable("accNr") String accNr, @PathVariable("accName") String accName) {
        String sql = "INSERT INTO bank (accnr, name, balance, lock) " + "VALUES (:accNr, :accName, 0, false)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accNr", accNr);
        paramMap.put("accName", accName);
        jbdcTemplate.update(sql, paramMap);
    }

    @GetMapping("Lesson5/bank/2/{accNr}")
    public String accbalance(@PathVariable("accNr") int id) {
        String sql = "SELECT balance FROM bank WHERE id = :a1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", id);
        return jbdcTemplate.queryForObject(sql, paramMap, String.class);
    }

    @GetMapping("Lesson5/bank/3/{accnr}/{sum}")
    public String deposit(@PathVariable("accnr") String accnr, @PathVariable("sum") double sum) {
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accnr);
        Boolean checkLock = jbdcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {return "Konto on lukus!";}
        String sqlOldSum = "SELECT balance FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapOldSum = new HashMap<>();
        paramMapOldSum.put("a1", accnr);
        double newSum = jbdcTemplate.queryForObject(sqlOldSum, paramMapOldSum, Double.class) + sum;
        String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
        Map<String, Object> paramMapUpdateSum = new HashMap<>();
        paramMapUpdateSum.put("a2", newSum);
        paramMapUpdateSum.put("a3", accnr);
        jbdcTemplate.update(sqlSum, paramMapUpdateSum);
        String s1 = String.valueOf(newSum);
        String s2 = String.valueOf(sum);
        return "Lisatud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
    }

    @GetMapping("Lesson5/bank/4/{accnr}/{sum}")
    public String withdraw(@PathVariable("accnr") String accnr, @PathVariable("sum") double sum) {
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accnr);
        Boolean checkLock = jbdcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {return "Konto on lukus!";}
        String sqlOldSum = "SELECT balance FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapOldSum = new HashMap<>();
        paramMapOldSum.put("a1", accnr);
        double newSum = jbdcTemplate.queryForObject(sqlOldSum, paramMapOldSum, Double.class) - sum;
        if (newSum < 0) {
            return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
        Map<String, Object> paramMapUpdateSum = new HashMap<>();
        paramMapUpdateSum.put("a2", newSum);
        paramMapUpdateSum.put("a3", accnr);
        jbdcTemplate.update(sqlSum, paramMapUpdateSum);
        String s1 = String.valueOf(newSum);
        String s2 = String.valueOf(sum);
        return "Välja võetud " + s2 + " EUR ja uus balanss on " + s1 + " EUR.";
    }

    @GetMapping("Lesson5/bank/5/{accNr1}/{accNr2}/{sum}")
    public String transfer(@PathVariable("accNr1") String accNr1, @PathVariable("accNr2") String accNr2, @PathVariable("sum") double sum) {
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accNr1);
        Boolean checkLock = jbdcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {return "Konto on lukus!";}
        paramMapCheckLock.put("a1", accNr2);
        checkLock = jbdcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {return "Konto on lukus!";}
        String sqlOld = "SELECT balance FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapOld = new HashMap<>();
        paramMapOld.put("a1", accNr1);
        double sum1 = jbdcTemplate.queryForObject(sqlOld, paramMapOld, Double.class) - sum;
        if (sum1 < 0) {
        return "Arvel ei ole nii suurt summat, proovi uuesti!";
        }
        String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
        Map<String, Object> paramMapUpdate = new HashMap<>();
        paramMapUpdate.put("a2", sum1);
        paramMapUpdate.put("a3", accNr1);
        jbdcTemplate.update(sqlSum, paramMapUpdate);

        String sqlNew = "SELECT balance FROM bank WHERE accnr = :a2";
        Map<String, Object> paramMapNew = new HashMap<>();
        paramMapNew.put("a2", accNr2);
        double sum2 = jbdcTemplate.queryForObject(sqlNew, paramMapNew, Double.class) + sum;
        String sqlSum2 = "UPDATE bank SET balance = :a4 WHERE accnr = :a5";
        Map<String, Object> paramMapUpdate2 = new HashMap<>();
        paramMapUpdate2.put("a4", sum2);
        paramMapUpdate2.put("a5", accNr2);
        jbdcTemplate.update(sqlSum2, paramMapUpdate2);
        String s1 = String.valueOf(sum2);
        String s2 = String.valueOf(sum);
        String s3 = String.valueOf(sum1);
        return "Arvelt " + accNr1 + " (jääk nüüd " + s3+ " EUR ) on lisatud " + s2 + " EUR arvele " + accNr2 + " mille uus balanss on " + s1 + " EUR.";

    }
    @GetMapping("Lesson5/bank/6/{accnr}")
    public String acclock(@PathVariable("accnr") String accnr) {
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accnr);
        Boolean checkLock = jbdcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
        if (checkLock) {
            String sqlUnlock = "UPDATE bank SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapLock = new HashMap<>();
            Boolean unlock = false;
            paramMapLock.put("a4", unlock);
            paramMapLock.put("a5", accnr);
            jbdcTemplate.update(sqlUnlock, paramMapLock);
            return "Konto " + accnr + " on nüüd avatud!";

        } else {String sqlLock = "UPDATE bank SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapUnlock = new HashMap<>();
            Boolean lock = true;
            paramMapUnlock.put("a4", lock);
            paramMapUnlock.put("a5", accnr);
            jbdcTemplate.update(sqlLock, paramMapUnlock);
            return "Konto " + accnr + " on nüüd suletud!";
        }
    }
    }

