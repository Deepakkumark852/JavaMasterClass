package com.deepak.capstone.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.deepak.capstone.entity.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE (:fName IS NULL OR e.fName LIKE :fName) " +
            "AND (:lName IS NULL OR e.lName LIKE :lName) " +
            "AND (:email IS NULL OR e.email LIKE :email) " +
            "AND (:grade IS NULL OR e.grade LIKE :grade)")
    List<Employee> findByParameters(@Param("fName") String fName,
                                    @Param("lName") String lName,
                                    @Param("email") String email,
                                    @Param("grade") String grade);
}
