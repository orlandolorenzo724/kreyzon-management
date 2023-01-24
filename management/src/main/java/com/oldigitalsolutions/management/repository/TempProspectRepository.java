package com.oldigitalsolutions.management.repository;

import com.oldigitalsolutions.management.model.TempProspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempProspectRepository extends JpaRepository<TempProspect, SetupRepository> {
}
