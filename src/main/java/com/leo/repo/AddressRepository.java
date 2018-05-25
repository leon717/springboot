package com.leo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.domain.Address;

public interface AddressRepository extends JpaRepository<Address, String> {

}
