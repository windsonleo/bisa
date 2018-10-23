package com.tecsoluction.bisa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.bisa.entidade.Premio;
import com.tecsoluction.bisa.entidade.Role;

import java.util.UUID;

@Repository
public interface IPremioDAO extends JpaRepository<Premio, UUID> {
}
