package spring.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.gradle.objects.Employee;
import spring.gradle.repository.EmployeeRepo;

import java.util.ArrayList;

/**
 * Display controller
 */
@Controller
public class DisplayController {

    @Autowired
    private EmployeeRepo repo;

    @RequestMapping("/")
    String index() {
        return "default";
    }

    @RequestMapping(value="/employee", method= RequestMethod.GET)
    String employee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    @RequestMapping(value="/employee", method= RequestMethod.POST)
    String employeeResult(@ModelAttribute Employee employee, Model model) {
        repo.save(employee);
        model.addAttribute("employee", employee);
        return "result";
    }

    @RequestMapping("/employees")
    String employeeResult(Model model) {
        Iterable<Employee> list = repo.findAll();

        ArrayList<Employee> employees = new ArrayList<>();

        if (repo.count() > 0) {
            for (Employee employee : list) {
                employees.add(employee);
            }
        }

        model.addAttribute("employees", employees);
        return "list";
    }
}
