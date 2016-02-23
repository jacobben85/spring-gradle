package spring.gradle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.gradle.objects.Employee;
import spring.gradle.repository.EmployeeRepo;

/**
 * Spring boot command line runner.
 */
@Component
public class CliRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CliRunner.class);

    @Autowired
    private EmployeeRepo repo;

    @Override
    public void run(String... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (String option : args) {
            sb.append(" ").append(option);
            if (option.startsWith("preload")) {
                for (int i=1; i <= 10; i++) {
                    Employee employee = new Employee();
                    employee.setFirstName("first name of :" + i);
                    employee.setLastName("last name of :" + i);
                    repo.save(employee);
                }
            }
        }
        sb = sb.length() == 0 ? sb.append("No Options Specified") : sb;
        LOGGER.warn(String.format("Application launched with options : %s", sb.toString()));
    }
}
