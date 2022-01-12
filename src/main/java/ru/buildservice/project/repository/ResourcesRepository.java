package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.buildservice.project.entity.Resources;

public interface ResourcesRepository extends JpaRepository<Resources,Integer> {

  Resources findByUsername(String username);

}
