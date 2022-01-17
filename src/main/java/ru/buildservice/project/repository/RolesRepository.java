package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
