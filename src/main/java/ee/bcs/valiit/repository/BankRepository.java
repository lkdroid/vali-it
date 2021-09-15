package ee.bcs.valiit.repository;

import ee.bcs.valiit.controller.model.AccountOverview;
import ee.bcs.valiit.controller.model.AccountStatement;
import ee.bcs.valiit.controller.model.LockBalance;
import ee.bcs.valiit.service.Bank.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BankRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Integer createClientK(String firstName, String lastName, String clientAddress) {
        String sql = "INSERT INTO clients (name, lastname, address) VALUES (:firstName, :lastName, :clientAddress)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("firstName", firstName);
        paramMap.put("lastName", lastName);
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
        jdbcTemplate.update(sql, paramMap);
    }

    public void createRAccount(String accNr, int clientId) {
        String sql = "INSERT INTO accounts (clientid, accnr, balance, lock) " + "VALUES (:clientId, :accNr, 0, false)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNr", accNr);
        paramMap.put("clientId", clientId);
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

    public class BankRowMapper implements RowMapper<AccountOverview> {
        @Override
        public AccountOverview mapRow(ResultSet resultSet, int i) throws SQLException {
            AccountOverview overview = new AccountOverview();
            overview.setAccnr(resultSet.getString("accnr"));
            overview.setBalance(resultSet.getString("balance"));
            overview.setFirstName(resultSet.getString("name"));
            overview.setLastName(resultSet.getString("lastname"));
            overview.setAddress(resultSet.getString("address"));
            return overview;
        }
    }

    public List<AccountOverview> accountROverview(int id) {
        String sql = "SELECT accnr, balance, name, lastname, address FROM accounts a JOIN clients c ON a.clientid=c.id WHERE a.clientid = :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        return jdbcTemplate.query(sql, paramMap, new BankRepository.BankRowMapper());
    }

    public List<AccountOverview> returnRallaccounts() {
        String sql = "SELECT accnr, balance, name, lastname, address FROM accounts a JOIN clients c ON a.clientid=c.id";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new BankRepository.BankRowMapper());
    }

    public class LockBalanceMapper implements RowMapper<LockBalance> {
        @Override
        public LockBalance mapRow(ResultSet resultSet, int i) throws SQLException {
            LockBalance lBStatus = new LockBalance();
            lBStatus.setLock(resultSet.getBoolean("lock"));
            lBStatus.setBalance(resultSet.getDouble("balance"));
            lBStatus.setClientid(resultSet.getInt("clientid"));
            return lBStatus;
        }
    }

    public LockBalance returnRlockbalance(String accNr) {
        String sql = "SELECT lock, balance, clientid FROM accounts WHERE accnr = :accNr";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accNr", accNr);
        return jdbcTemplate.queryForObject(sql, paramMap, new LockBalanceMapper());
    }

    //See peaks olema nüüd statement baasi kirje loomine väljavõtte jaoks
    public void insertRStatement(String accNr, String actionType, double actionSum, double newBalance, int clientId) {
        String sql = "INSERT INTO statement (actionaccnr, actiontype, sum, newbalance, clientid, actiondate) " + "VALUES (:accNr, :actionType, :actionSum, :newBalance, :clientId, :actionDate )";
        Map<String, Object> paramMap = new HashMap<>();
        LocalDateTime actionDatetime = java.time.LocalDateTime.now();
        paramMap.put("accNr", accNr);
        paramMap.put("actionType", actionType);
        paramMap.put("actionSum", actionSum);
        paramMap.put("newBalance", newBalance);
        paramMap.put("clientId", clientId);
        paramMap.put("actionDate", actionDatetime);
        jdbcTemplate.update(sql, paramMap);

        //statement kirje loomise lõpp

    }

    public List accountRStatement(String accNr) {
        String sql = "SELECT actionaccnr, actiontype, sum, newbalance, clientid, actiondate FROM statement WHERE actionaccnr = :a1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", accNr);
        return jdbcTemplate.queryForList(sql, paramMap);

    }
    public void deleteRClient(int id) {
        String sqlsts = "DELETE FROM statement WHERE clientid = :statementid";
        Map<String, Object> paramMapSts = new HashMap<>();
        paramMapSts.put("statementid", id);
        jdbcTemplate.update(sqlsts, paramMapSts);

        String sqlaccounts = "DELETE FROM accounts WHERE clientid = :accountid";
        Map<String, Object> paramMapAccounts = new HashMap<>();
        paramMapAccounts.put("accountid", id);
        jdbcTemplate.update(sqlaccounts, paramMapAccounts);
        String sql = "DELETE FROM clients WHERE id = :clientid";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("clientid", id);
        jdbcTemplate.update(sql, paramMap);
    }

}