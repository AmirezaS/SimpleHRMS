package org.j2ee.simplehrms.dao;

import java.util.List;

import org.j2ee.simplehrms.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long>{

    List<Position> findByDepartmentId(Long departmentId);

    List<Position> findByIsActiveTrue();

    boolean existsByTitleAndDepartmentIdd(String title, Long departmentId);

    

}
