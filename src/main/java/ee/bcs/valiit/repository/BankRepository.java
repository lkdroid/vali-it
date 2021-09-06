package ee.bcs.valiit.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createRAccount(String accNr, String accName) {
        String sql = "INSERT INTO bank (accnr, name, balance, lock) " + "VALUES (:accNr, :accName, 0, false)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accNr", accNr);
        paramMap.put("accName", accName);
        jdbcTemplate.update(sql, paramMap);
    }

    public Double accountRBalance(String accNr) {
        String sql = "SELECT balance FROM bank WHERE accNr = :a1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", accNr);
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);

    }

    public void changeAccountBalance(String accNr, double newSum) {
        String sqlSum = "UPDATE bank SET balance = :a2 WHERE accnr = :a3";
        Map<String, Object> paramMapUpdateSum = new HashMap<>();
        paramMapUpdateSum.put("a2", newSum);
        paramMapUpdateSum.put("a3", accNr);
        jdbcTemplate.update(sqlSum, paramMapUpdateSum);
    }

    public Boolean checkRLock(String accNr) {
        String sqlCheckLock = "SELECT lock FROM bank WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accNr);
        return jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
    }

    public void changeRLock(String accNr, Boolean check) {

        if (check) {
            String sqlUnlock = "UPDATE bank SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapLock = new HashMap<>();
            Boolean unlock = false;
            paramMapLock.put("a4", unlock);
            paramMapLock.put("a5", accNr);
            jdbcTemplate.update(sqlUnlock, paramMapLock);
        } else {
            String sqlLock = "UPDATE bank SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapUnlock = new HashMap<>();
            Boolean lock = true;
            paramMapUnlock.put("a4", lock);
            paramMapUnlock.put("a5", accNr);
            jdbcTemplate.update(sqlLock, paramMapUnlock);
        }
    }
}
