package com.sparta.back_office.repository;

import com.sparta.back_office.model.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,String> {
}
