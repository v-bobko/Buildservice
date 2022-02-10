package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo,Integer> {



    List<Photo> findByObjectsAndMonthAndYearOrderByPhotoIdDesc(Object object, String month, Integer year);
}
