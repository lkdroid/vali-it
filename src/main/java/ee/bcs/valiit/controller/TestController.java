package ee.bcs.valiit.controller;

import ee.bcs.valiit.controller.model.SampleEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.NamedParameterJdbcOperationsDependsOnPostProcessor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class TestController {


    @GetMapping("test2/{name}")
    public String test2(@PathVariable("name") String nimi) {

        return "Hello " + nimi;

    }

    // http://localhost:8080/test3?name=John
    @GetMapping("test3")
    public String test3(@RequestParam("name") String nimi) {
        return "Hello " + nimi;
    }

    @GetMapping("test4")

    public String test4(String nimi) {
        return "Hello " + nimi;
    }

    @GetMapping("test5")
    public SampleEmployeeDto test5() {
        SampleEmployeeDto employee = new SampleEmployeeDto();
        employee.setName("Laur");
        employee.setAddress("Tallinn");
        return employee;
    }

    @PostMapping("test5")
    public SampleEmployeeDto test5Post() {
        SampleEmployeeDto employee = new SampleEmployeeDto();
        employee.setName("Juhan");
        employee.setAddress("Tartn");
        return employee;
    }

    List<SampleEmployeeDto> employees = new ArrayList<>();

    @GetMapping("employee")

    public List<SampleEmployeeDto> getAllEmployees() {
        return employees;
    }
    @GetMapping("employee/{id}")
    public SampleEmployeeDto getEmployee(@PathVariable("id") int id) {
        return employees.get(id);
    }
    @PostMapping("employee")
    public void addEmployee(@RequestBody SampleEmployeeDto employeeDto) {
        employees.add(employeeDto);
    }

    @PutMapping("employee/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody SampleEmployeeDto employeeDto){
        employees.set(id, employeeDto);
    }

    @DeleteMapping("employee/{id}")
    public void deleteEmployees(@PathVariable("id") int id) {
        employees.remove(id);
    }

    // @PostMapping("test6")
    // public SampleEmployeeDto test6Post(@RequestBody SampleEmployeeDto employee) {
     //   employee.getName();
       // return employee;
    // }

}
