package spring.gradle.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import spring.gradle.objects.Employee;

/**
 *
 */
public class BatchProcessor implements ItemProcessor<Employee, Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchProcessor.class);

    @Override
    public Employee process(Employee employee) throws Exception {

        LOGGER.info("Processing : " + employee.toString());

        return employee;
    }
}
