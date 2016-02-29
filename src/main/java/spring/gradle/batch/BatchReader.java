package spring.gradle.batch;

import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import spring.gradle.objects.Employee;
import spring.gradle.repository.EmployeeRepo;

/**
 * Batch reader
 */
public class BatchReader implements ItemReader<Employee> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(BatchReader.class);

    @Autowired
    private EmployeeRepo repo;

    @Override
    public Employee read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (repo.count() > 0) {
            Employee employee = null;
            try {
                employee = repo.findFirstByDisplayNameIsNull();
            } catch (Exception e) {
                LOGGER.error("Exception ", e);
            }
            return employee;
        }
        return null;
    }
}
