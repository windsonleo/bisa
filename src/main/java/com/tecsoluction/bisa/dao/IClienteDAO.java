package com.tecsoluction.bisa.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tecsoluction.bisa.entidade.Cliente;

@Repository
public interface IClienteDAO extends JpaRepository<Cliente, UUID> {


	@Query("SELECT p FROM Cliente p where p.cpf=:cpf")
    Cliente findByCpf(@Param("cpf") String cpf);

}
