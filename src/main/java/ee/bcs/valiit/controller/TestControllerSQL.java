package ee.bcs.valiit.controller;

import ee.bcs.valiit.controller.model.SampleEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestControllerSQL {
    @Autowired
    private NamedParameterJdbcTemplate jbdcTemplate;


    @PostMapping("SQL/employee")
    public void addEmployeesql(@RequestBody SampleEmployeeDto x) {
        String sql = "INSERT INTO employees (name, address) " + "VALUES (:employeeName, :employeeAddress)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("employeeName", x.getName());
        paramMap.put("employeeAddress", x.getAddress());
        jbdcTemplate.update(sql, paramMap);
    }

    @PutMapping("SQL/employee/{id}")
    public void updateEmployeesql(@PathVariable("id") int id, @RequestBody SampleEmployeeDto employeeDto) {
        String sql = "UPDATE employees SET name = :a1, address = :a2 WHERE id = :a3";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", employeeDto.getName());
        paramMap.put("a2", employeeDto.getAddress());
        paramMap.put("a3", id);
        jbdcTemplate.update(sql, paramMap);
    }

    @GetMapping("SQL/employee/{id}")
    public String getEmployee(@PathVariable("id") int id) {
        String sql = "SELECT name FROM employees WHERE id = :a1";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("a1", id);
        return jbdcTemplate.queryForObject(sql, paramMap, String.class);
    }

}
