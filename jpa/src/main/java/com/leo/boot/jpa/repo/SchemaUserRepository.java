package com.leo.boot.jpa.repo;

import com.leo.boot.jpa.domain.SchemaUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchemaUserRepository extends JpaRepository<SchemaUser, String> {
}
