package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {

  Users findByUsername(String username);

}
