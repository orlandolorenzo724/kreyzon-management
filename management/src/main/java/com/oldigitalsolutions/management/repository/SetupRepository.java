package com.oldigitalsolutions.management.repository;

import com.oldigitalsolutions.management.model.Setup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetupRepository extends JpaRepository<Setup, String> {
}