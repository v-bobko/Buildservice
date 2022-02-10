package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Journal;
import ru.buildservice.project.entity.Objects;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal,Integer> {

    List<Journal> findByObjects(Objects object);
}
