package ee.bcs.valiit.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BankRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Integer createClientK(String clientName, String clientAddress){
        String sql = "INSERT INTO clients (name, address) VALUES (:clientName, :clientAddress)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("clientName", clientName);
        paramMap.put("clientAddress", clientAddress);

        //tagastab id tabelist clients
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("id");

    }

    public void createRClient(String clientName, String clientAddress) {
        String sql = "INSERT INTO clients (name, address) " + "VALUES (:clientName, :clientAddress)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("clientName", clientName);
        paramMap.put("clientAddress", clientAddress);
        jdbcTemplate.update(sql, paramMap);}

    public void createRAccount(String accNr, int clientId) {
        String sql = "INSERT INTO accounts (accnr, clientid, balance, lock) " + "VALUES (:accNr, :clientid 0, false)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("accNr", accNr);
        String idstring = String.valueOf(clientId);
        paramMap.put("clientid", idstring);
        jdbcTemplate.update(sql, paramMap);
    }

    public Double accountRBalance(String accNr) {
        String sql = "SELECT balance FROM accounts WHERE accNr = :a1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", accNr);
        return jdbcTemplate.queryForObject(sql, paramMap, Double.class);

    }

    public void changeAccountBalance(String accNr, double newSum) {
        String sqlSum = "UPDATE accounts SET balance = :a2 WHERE accnr = :a3";
        Map<String, Object> paramMapUpdateSum = new HashMap<>();
        paramMapUpdateSum.put("a2", newSum);
        paramMapUpdateSum.put("a3", accNr);
        jdbcTemplate.update(sqlSum, paramMapUpdateSum);
    }

    public Boolean checkRLock(String accNr) {
        String sqlCheckLock = "SELECT lock FROM accounts WHERE accnr = :a1";
        Map<String, Object> paramMapCheckLock = new HashMap<>();
        paramMapCheckLock.put("a1", accNr);
        return jdbcTemplate.queryForObject(sqlCheckLock, paramMapCheckLock, Boolean.class);
    }

    public void changeRLock(String accNr, Boolean check) {

        if (check) {
            String sqlUnlock = "UPDATE accounts SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapLock = new HashMap<>();
            Boolean unlock = false;
            paramMapLock.put("a4", unlock);
            paramMapLock.put("a5", accNr);
            jdbcTemplate.update(sqlUnlock, paramMapLock);
        } else {
            String sqlLock = "UPDATE accounts SET lock = :a4 WHERE accnr = :a5";
            Map<String, Object> paramMapUnlock = new HashMap<>();
            Boolean lock = true;
            paramMapUnlock.put("a4", lock);
            paramMapUnlock.put("a5", accNr);
            jdbcTemplate.update(sqlLock, paramMapUnlock);
        }
    }
}
