package com.tecsoluction.bisa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tecsoluction.bisa.entidade.Produto;

import java.util.UUID;

@Repository
public interface IProdutoDAO extends JpaRepository<Produto, UUID> {
}
