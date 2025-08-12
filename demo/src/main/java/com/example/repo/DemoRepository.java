package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.DemoEntity;


public interface DemoRepository extends JpaRepository<DemoEntity,Long> {
}


