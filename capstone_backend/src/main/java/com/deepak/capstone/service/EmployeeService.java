package com.deepak.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.capstone.entity.Credentials;
import com.deepak.capstone.entity.Employee;
import com.deepak.capstone.repository.CredentialsRepository;
import com.deepak.capstone.repository.EmployeeRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CredentialsRepository credentialsRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<Credentials> getAllCredentials() {
        return credentialsRepository.findAll();
    }

    public Credentials getCredentialsByEmail(String email) {
        Optional<Credentials> credOptional = credentialsRepository.findByEmail(email);
        return credOptional.orElse(null);
    }

    public void registerEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void registerCred(Credentials cred) {
        String hashedPassword = passwordEncoder.encode(cred.getPassword());
        cred.setPassword(hashedPassword);
        credentialsRepository.save(cred);
    }
    
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void saveAllEmployees(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    public void updateEmployee(Long id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            employeeRepository.save(employee);
        }
    }

   

    public List<Employee> searchEmployees(String fName, String lName, String email, String grade) {
        // Prepare search parameters
        fName = prepareSearchParameter(fName);
        lName = prepareSearchParameter(lName);
        email = prepareSearchParameter(email);
        grade = prepareSearchParameter(grade);

        System.out.println(fName+lName+email+grade);

        // Perform wildcard search using LIKE operator in JPQL
        return employeeRepository.findByParameters(fName, lName, email, grade);
    }

    private String prepareSearchParameter(String parameter) {
        if (parameter == null || parameter.equals("*")) {
            return "%"; // Match any value
        } else {
            return "%" + parameter + "%"; // Match parameter partially
        }
    }

    public void deleteCredential(String email) {
        credentialsRepository.deleteByEmail(email);
    }
}


