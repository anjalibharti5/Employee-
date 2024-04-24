package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.*;

@Repository

public interface userRepo extends JpaRepository<user,Long>{

}