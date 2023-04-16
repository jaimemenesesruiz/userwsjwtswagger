package com.nisum.userws.repositories;

import com.nisum.userws.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
