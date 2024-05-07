package com.deepak.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.capstone.entity.Credentials;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByEmail(String email);
    void deleteByEmail(String email);
}
