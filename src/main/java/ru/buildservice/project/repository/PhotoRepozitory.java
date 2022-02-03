package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Photo;

import java.util.List;

public interface PhotoRepozitory extends JpaRepository<Photo,Integer> {



    List<Photo>  findByObjects(Object object);
}
