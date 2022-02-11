package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Journal;
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.entity.Users;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal,Integer> {

    List<Journal> findByObjectsOrderByDateDescJournalIdDesc(Objects object);

    List<Journal> findByObjectsAndUsersOrderByDateDescJournalIdDesc(Objects object, Users user);

    @Query(nativeQuery = true, value="SELECT * FROM journal WHERE worker_id=:id ORDER BY journal_id DESC LIMIT 1")
    Journal findLastJournalByUsers(@Param("id") Integer workerId);
}
