package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.entity.Users;

import java.util.ArrayList;

import java.util.List;

public interface CalendarServiceRepository extends JpaRepository<CalendarService, Integer> {

    List<CalendarService> findByObjects(Objects object);
    List<CalendarService> findByObjectsAndMonthAndYearOrderByCalendarIdAsc(Objects objects, String month, Integer year);
//    List<CalendarService> findByObjectsAndMonthAndYear(Objects objects, String month, Integer year);



    @Query(nativeQuery = true, value = "select distinct year from calendar_service;")
    ArrayList<Integer> findYears();

    CalendarService findByCalendarIdOrderByCalendarIdAsc(Integer task);



//
//    @Query(nativeQuery = true, value = "select * from calendar_service where date_start= '2021-01-01'")
//    List<CalendarService> findByYearsAndMonth();



}
