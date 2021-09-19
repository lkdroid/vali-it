package ee.bcs.valiit.repository;

import ee.bcs.valiit.controller.model.AccountOverview;
import ee.bcs.valiit.controller.model.AccountStatement;
import ee.bcs.valiit.controller.model.LockBalance;
import ee.bcs.valiit.controller.model.OneRental;
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
import java.util.*;

@Repository

public class RentalRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<OneRental> returnRallrentals() {
        String sql = "SELECT carsid, firstname, lastname, rentfrom, rentto, number FROM rentals r JOIN cars c ON r.rentalid=c.carid";
        Map<String, Object> paramMap = new HashMap<>();
        return jdbcTemplate.query(sql, paramMap, new RentalRepository.RentRowMapper());
    }

    static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public Integer newcar() {
        String carnumber = getAlphaNumericString(6);
        String make = "BatMobile Model " + getAlphaNumericString(3);
        String sqlCar = "INSERT INTO cars (number, make)" + "VALUES (:Number, :Make)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("Number", carnumber);
        paramMap.put("Make", make);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sqlCar, new MapSqlParameterSource(paramMap), keyHolder);
        return (Integer) keyHolder.getKeys().get("carid");
    }

    public void newrental(String firstName, String lastName, int carid) {
        Date rentFrom = new Date();
        Random plus = new Random();
        Date rentTo = rentFrom;
        String sqlRental = "INSERT INTO rentals (carsid, firstname, lastname, rentfrom, rentto)" + "VALUES (:carsId, :firstName, :lastName, :rentFrom, :rentTo)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("carsId", carid);
        paramMap.put("firstName", firstName);
        paramMap.put("lastName", lastName);
        paramMap.put("rentFrom", rentFrom);
        paramMap.put("rentTo", rentTo);
        jdbcTemplate.update(sqlRental, paramMap);

    }

    public class RentRowMapper implements RowMapper<OneRental> {
        @Override
        public OneRental mapRow(ResultSet resultSet, int i) throws SQLException {
            OneRental overview = new OneRental();
            overview.setCarNumber(resultSet.getString("number"));
            overview.setClientFullname(resultSet.getString("firstname") + " " + resultSet.getString("lastname"));
            overview.setRentFrom(resultSet.getString("rentfrom"));
            overview.setRentTo(resultSet.getString("rentto"));
            return overview;
        }
    }
}

