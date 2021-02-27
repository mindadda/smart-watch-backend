package com.htlabs.smartwatch.repository;

import com.htlabs.smartwatch.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , String> {

    @Query(value = "SELECT * FROM department WHERE department_name LIKE %:departmentName%", nativeQuery = true)
    public String findDepartmentName(String departmentName);
}
