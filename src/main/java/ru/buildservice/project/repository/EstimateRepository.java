package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.Estimates;
import ru.buildservice.project.entity.Projects;

import java.util.List;

public interface EstimateRepository extends JpaRepository<Estimates,Integer> {


    List<Estimates> findByObjects(Object object);


}
