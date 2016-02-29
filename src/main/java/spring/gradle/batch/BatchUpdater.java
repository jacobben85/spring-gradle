package spring.gradle.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import spring.gradle.objects.Employee;
import spring.gradle.repository.EmployeeRepo;

import java.util.List;

/**
 * Batch updater
 */
public class BatchUpdater implements ItemWriter<Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);

    @Autowired
    private EmployeeRepo repo;

    @Override
    public void write(List<? extends Employee> items) throws Exception {

        for (Employee employee : items) {
            boolean updated = false;
            if (employee.getDisplayName() == null) {
                updated = employee.updateDisplayName();
            }

            if (updated) {
                repo.save(employee);
                LOGGER.info("Updated : " + employee.toString());
            }
        }
    }
}
