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
    List<CalendarService> findByObjectsAndMonthAndYearAndUsersOrderByCalendarIdAsc(Objects objects, String month, Integer year, Users users);


//    List<CalendarService> findDistinctByUsers(Users users);
    @Query(nativeQuery = true, value = "select distinct object_id from calendar_service where user_id=:user")
    List<Integer>  findObjectbyUserId(@Param("user")Integer userId);




    CalendarService findByCalendarIdOrderByCalendarIdAsc(Integer task);







    @Query(nativeQuery = true, value = "select distinct year from calendar_service;")
    ArrayList<Integer> findYears();

    @Query(nativeQuery = true, value = "select distinct user_id from calendar_service where object_id=:object limit 1")
    Integer  findUserByObject(@Param("object")Integer objectId);
//
//    @Query(nativeQuery = true, value = "select * from calendar_service where date_start= '2021-01-01'")
//    List<CalendarService> findByYearsAndMonth();


}
