package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.buildservice.project.entity.CalendarService;
import ru.buildservice.project.entity.Objects;
import ru.buildservice.project.entity.Users;

import java.util.List;


public interface ObjectRepository extends JpaRepository<Objects,Integer> {


    @Query(nativeQuery = true, value= "select * from objects inner join users u on u.user_id = objects.user_id" )
    List<Objects> findUsernameWithObjects();

    List<Objects> findByUsers(Users user);

    Objects findByObjectId(Integer objectId);

}
