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
public String accountRBalance(String accNr){
    String sql = "SELECT balance FROM bank WHERE accNr = :a1";
    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("a1", accNr);
    String tagastus = jdbcTemplate.queryForObject(sql, paramMap, String.class);
    return "Konto seis on : "+ tagastus + " EUR!";
}
public String depositR(String accNr, double sum){

}
}
