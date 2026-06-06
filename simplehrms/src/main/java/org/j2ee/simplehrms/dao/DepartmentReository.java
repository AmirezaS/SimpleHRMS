package org.j2ee.simplehrms.dao;

import java.util.List;
import java.util.Optional;

import org.j2ee.simplehrms.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentReository extends JpaRepository<Department, Long>{

    Optional<Department> findByName(String name);

    Optional<Department> findByCode(String code);

    boolean existsByName(String name);

    boolean existsByCode(String code);

    List<Department> findByActiveTrue();

    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.employees e " +
           "WHERE d.isActive = true AND e.status = 'ACTIVE'")
    List<Department> findAllWithActiveEmployees();
    

}
