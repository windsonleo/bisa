package com.tecsoluction.bisa.servico;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsoluction.bisa.dao.IClienteDAO;
import com.tecsoluction.bisa.entidade.Cliente;
import com.tecsoluction.bisa.framework.AbstractEntityService;


/*  criar validacaoes para que o servico as chamem caso nao haja erros execute a acao  */


@Service("clienteService")
@Transactional
//@PersistenceContext
public class ClienteServicoImpl extends AbstractEntityService<Cliente> {


    @Autowired
    private IClienteDAO dao;


    public ClienteServicoImpl() {

        super(Cliente.class, "paciente");


    }
    
    @Override
    public Cliente save(Cliente user) {

//		user.setRoles(new HashSet<>());
//		userRepository.save(user);
        dao.saveAndFlush(user);

        return user;

    }


    public Cliente findByCpf(String cpf) {

        return dao.findByCpf(cpf);
    }


    @Override
    protected JpaRepository<Cliente, UUID> getDao() {

        return dao;
    }


    @Override
    protected void validateSave(Cliente post) {
        // TODO Auto-generated method stub

    }

    @Override
    protected String getIdEntity(Cliente paciente) {
        return paciente.getId().toString();
    }


    @Override
    protected void validateEdit(Cliente post) {
        // TODO Auto-generated method stub

    }


    @Override
    protected void validateDelete(UUID id) {
        // TODO Auto-generated method stub

    }


}
