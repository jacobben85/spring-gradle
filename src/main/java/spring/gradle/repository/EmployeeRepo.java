package spring.gradle.repository;

import org.springframework.data.repository.CrudRepository;
import spring.gradle.objects.Employee;

/**
 * Employee repo
 */
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
