package spring.gradle.repository;

import org.springframework.data.repository.CrudRepository;
import spring.gradle.objects.Employee;

/**
 * Employee repo
 */
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    // there are a set of default jpa methods - for instance is findAll()

    // this is used by the spring batch library
    Employee findFirstByDisplayNameIsNull();
}
