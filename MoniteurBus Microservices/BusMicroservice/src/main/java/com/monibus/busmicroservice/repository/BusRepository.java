package com.monibus.busmicroservice.repository;

import com.monibus.busmicroservice.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {

    List<Bus> findByDeletedFalse();
    List<Bus> findAllByIdecoleAndDeletedFalse(long idEcole);
    Bus findByIdchauffeur(long id);
}
