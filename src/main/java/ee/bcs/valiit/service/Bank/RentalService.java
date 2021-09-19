package ee.bcs.valiit.service.Bank;

import ee.bcs.valiit.controller.model.*;
import ee.bcs.valiit.repository.BankRepository;
import ee.bcs.valiit.repository.RentalRepository;
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
import java.util.Random;

@Service

public class RentalService {
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

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private RentalRepository rentalRepository;

    public List<OneRental> returnSallrentals() {
        return rentalRepository.returnRallrentals();
    }

    public String returnSrandomrentals(int randomnumber) {
        for (int i = 1; i <= randomnumber; i++) {
            int rand1 = (int)Math.floor(Math.random() * (10 - 3 + 1) + 3);
            int rand2 = (int)Math.floor(Math.random() * (10 - 3 + 1) + 3);
            String firstname = getAlphaNumericString(rand1);
            String lastname = getAlphaNumericString(rand2);
                       for (int j = 1; j <= 3; j++) {
                int key = rentalRepository.newcar();
                int randomXrentals = (int)Math.floor(Math.random() * (10 - 1 + 1) + 1);
                for (int k = 1; k <= randomXrentals; k++) {
                    rentalRepository.newrental(firstname, lastname, key);
                }
            }

        }
        return "Loodud " + randomnumber + " klienti ning kirjed nendega!";
    }
}
