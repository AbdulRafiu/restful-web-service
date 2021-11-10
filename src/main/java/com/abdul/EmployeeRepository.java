package com.abdul;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class EmployeeRepository {

    private final AtomicLong id = new AtomicLong(0L);

    private final List<Employee> employees = new ArrayList<Employee>()
//    {
//        {
//            add(create("Abdul Rafiu", 55000));
//            add(create("Mohsin Metlo", 55000));
//            add(create("Haseeb Ansari", 55000));
//            add(create("Hasnain Malik", 55000));
//        }
//    }
    ;

    public Employee create(Employee employee) {
        employee.setEmpId(getNextEmpId());
        employees.add(employee);
        return employee;
    }

    public Employee create(String name, float salary) {
        Employee employee = new Employee();
        employee.setEmpId(getNextEmpId());
        employee.setEmpName(name);
        employee.setEmpSalary(salary);
        employees.add(employee);
        return employee;
    }

    public Optional<Employee> findEmployeeById(long id) {
        return  employees.stream().filter(e -> e.getEmpId().equals(id)).findFirst();
    }

    public List<Employee> findAll() {
        return employees;
    }

    public  int count() {
        return employees.size();
    }

    private long getNextEmpId() {
        return id.getAndIncrement();
    }

    public Employee update(long id) {
        String updatedName = "updatedName";
        long updatedSalary = 101010;

        for (Employee employee: employees) {
            if (employee.getEmpId().equals(id)) {
                employee.setEmpName(updatedName);
                employee.setEmpSalary(updatedSalary);
                return employee;
            }
        }
        return create(updatedName, updatedSalary);
    }

    public boolean delete(long id) {
        return employees.removeIf(employee -> employee.getEmpId().equals(id));
    }

}
