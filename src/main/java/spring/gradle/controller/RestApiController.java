package spring.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.gradle.objects.Employee;
import spring.gradle.repository.EmployeeRepo;

import java.util.Map;
import java.util.HashMap;

/**
 * Rest controller
 *
 * I am sticking with json for the demo
 * produces=MediaType.APPLICATION_XML_VALUE could generate xml
 */
@RestController
@RequestMapping(value = "/rest", produces = {"application/json"})
public class RestApiController {

    // auto wiring spring magic
    @Autowired
    private EmployeeRepo repo;

    @RequestMapping("")
    public Map<String, Object> home() {

        Map<String, Object> model = new HashMap<>();
        model.put("title", "Spring gradle");

        return model;
    }

    @RequestMapping("/employees")
    public HashMap<Long, Employee> employeeResult() {
        Iterable<Employee> list = repo.findAll();

        HashMap<Long, Employee> employeeList = new HashMap<>();

        if (repo.count() > 0) {
            for (Employee employee : list) {
                employeeList.put(employee.getId(), employee);
            }
        }

        return employeeList;
    }

    @RequestMapping("/test")
    public Employee employeeTest() {
        Employee employee = repo.findFirstByDisplayNameIsNull();

        return employee;
    }
}
