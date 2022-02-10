package ru.buildservice.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.buildservice.project.entity.Cameras;


import java.util.List;

public interface CameraRepository extends JpaRepository<Cameras,Integer> {

    List<Cameras> findByObjectsOrderByCameraIdAsc(Object object);


}
