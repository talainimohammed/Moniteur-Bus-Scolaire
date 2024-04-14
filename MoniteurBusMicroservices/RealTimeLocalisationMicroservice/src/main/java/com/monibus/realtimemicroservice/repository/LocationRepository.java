package com.monibus.realtimemicroservice.repository;

import com.monibus.realtimemicroservice.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location,Long> {
    List<Location> findAllByIdbus(long id);
    Location findByIdbus(long idbus);
}
