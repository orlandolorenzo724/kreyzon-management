package com.oldigitalsolutions.management.repository;

import com.oldigitalsolutions.management.model.Prospect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProspectRepository extends JpaRepository<Prospect, Long>, JpaSpecificationExecutor<Prospect> {
    Optional<Prospect> findFirstByOrderByIdDesc();

    @Query(value = "SELECT * FROM prospects WHERE stage = ?1 AND contactable = 'YES'", nativeQuery = true)
    List<Prospect> findProspectListByStage(String stage);

    @Query(value = "SELECT * FROM prospects ORDER BY id DESC", nativeQuery = true)
    List<Prospect> findAllOrderByIdDesc();
    @Query(value = "SELECT * FROM prospects ORDER BY id DESC LIMIT ?1", nativeQuery = true)
    List<Prospect> findLastProspects(Integer numberOfReturnedProspects);
}