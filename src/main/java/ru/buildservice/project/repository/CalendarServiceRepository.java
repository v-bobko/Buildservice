package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.CalendarService;

public interface CalendarServiceRepository extends JpaRepository<CalendarService,Integer> {
}
