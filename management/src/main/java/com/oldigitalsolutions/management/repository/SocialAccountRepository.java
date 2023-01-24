package com.oldigitalsolutions.management.repository;

import com.oldigitalsolutions.management.model.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, Long> {
    Optional<SocialAccount> findFirstByOrderByIdDesc();

    List<SocialAccount> findByProspectId(Long prospectId);
}