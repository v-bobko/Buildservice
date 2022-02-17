package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.buildservice.project.entity.CalendarComment;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Objects;

import java.util.ArrayList;
import java.util.List;

public interface CalendarCommentRepository extends JpaRepository<CalendarComment, Integer> {

    List<CalendarComment> findByObjects(Objects object);

    List<CalendarComment> findByObjectsAndMonthAndYearOrderByCommentIdAsc(Objects objects, String month, Integer year);

    CalendarComment findByCommentId(Integer id);

}
