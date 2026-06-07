package org.j2ee.simplehrms.dao;

import java.time.LocalDate;
import java.util.List;

import org.j2ee.simplehrms.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee>{

    List<Employee> findByEmail(String email);

    List<Employee> findByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);

    boolean existsByEmployeeCode(String employeeCode);

    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByPositionId(Long positionId);

    List<Employee> findByManagerId(Long managerId);

    List<Employee> findByEmploymentStatus(Employee.EmploymentStatus status);

    Page<Employee> findByDepartmentId(Long departmentId, Pageable pagable);

//     @Query("SELECT e FROM Employee e WHERE e.status = 'ACTIVE' " +
//            "AND (LOWER(e.firstName) LIKE LOWER(CONCAT('%', :search, '%')) " +
//            "OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :search, '%')) " +
//            "OR LOWER(e.email) LIKE LOWER(CONCAT('%', :search, '%')) " +
//            "OR LOWER(e.employeeCode) LIKE LOWER(CONCAT('%', :search, '%')))")
//     Page<Employee> searchEmployees(@Param("search") String search, Pageable pageable);
 
//     @Query("SELECT e FROM Employee e WHERE e.department.id = :deptId AND e.status = 'ACTIVE'")
//     List<Employee> findActiveEmployeesByDepartment(@Param("deptId") Long deptId);
 
//     @Query("SELECT COUNT(e) FROM Employee e WHERE e.department.id = :deptId AND e.status = 'ACTIVE'")
//     Long countActiveByDepartment(@Param("deptId") Long deptId);
 
//     @Query("SELECT COUNT(e) FROM Employee e WHERE e.status = :status")
//     Long countByStatus(@Param("status") Employee.EmploymentStatus status);
 
//     @Query("SELECT e FROM Employee e WHERE e.hireDate BETWEEN :startDate AND :endDate")
//     List<Employee> findByHireDateBetween(@Param("startDate") LocalDate startDate,
//                                          @Param("endDate") LocalDate endDate);
 
//     @Query("SELECT e FROM Employee e WHERE MONTH(e.dateOfBirth) = :month AND DAY(e.dateOfBirth) = DAY(CURRENT_DATE)")
//     List<Employee> findBirthdaysToday(@Param("month") int month);
 
//     @Query("SELECT e FROM Employee e WHERE e.hireDate <= :date AND " +
//            "FUNCTION('TIMESTAMPDIFF', YEAR, e.hireDate, :date) = :years")
//     List<Employee> findByWorkAnniversary(@Param("date") LocalDate date,
//                                           @Param("years") int years);
 
//     @Query("SELECT DISTINCT e.department.name, COUNT(e) FROM Employee e " +
//            "WHERE e.status = 'ACTIVE' GROUP BY e.department.name")
//     List<Object[]> countEmployeesByDepartment();


}
