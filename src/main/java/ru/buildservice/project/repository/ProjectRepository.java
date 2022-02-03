package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Projects;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Projects,Integer> {


    List<Projects> findByObjects(Object object);


}
