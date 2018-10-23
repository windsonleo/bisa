package com.tecsoluction.bisa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.bisa.entidade.Sorteio;
import com.tecsoluction.bisa.entidade.Role;

import java.util.UUID;

@Repository
public interface ISorteioDAO extends JpaRepository<Sorteio, UUID> {
}
