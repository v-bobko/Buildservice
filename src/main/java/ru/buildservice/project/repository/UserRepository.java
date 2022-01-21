package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.buildservice.project.entity.Roles;
import ru.buildservice.project.entity.Users;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);


  @Query(nativeQuery = true, value="select * from users inner join roles r on r.role_id = users.role_id where role_name = :role")
  List<Users> findRoleUser(@Param("role") String role);




}

//@Transactional(readOnly = true)
//@Query(nativeQuery = true, value = "select * from post p where p.user_id=:id and p.title like :title")