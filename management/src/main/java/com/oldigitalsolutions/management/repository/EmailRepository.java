package com.oldigitalsolutions.management.repository;

import com.oldigitalsolutions.management.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByProspectId(Long prospectId);

    Optional<Email> findFirstByOrderByIdDesc();
}