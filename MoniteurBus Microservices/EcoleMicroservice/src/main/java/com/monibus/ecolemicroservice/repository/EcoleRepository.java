package com.monibus.ecolemicroservice.repository;

import com.monibus.ecolemicroservice.entity.Ecole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoleRepository extends JpaRepository<Ecole,Long> {
}
