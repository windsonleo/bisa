package com.tecsoluction.bisa.servico;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsoluction.bisa.dao.IEnderecoDAO;
import com.tecsoluction.bisa.entidade.Endereco;
import com.tecsoluction.bisa.framework.AbstractEntityService;


/*  criar validacaoes para que o servico as chamem caso nao haja erros execute a acao  */


@Service("enderecoService")
@Transactional
//@PersistenceContext
public class EnderecoServicoImpl extends AbstractEntityService<Endereco> {


    @Autowired
    private IEnderecoDAO dao;


    public EnderecoServicoImpl() {

        super(Endereco.class, "endereco");


    }
    
    @Override
    public Endereco save(Endereco user) {

//		user.setRoles(new HashSet<>());
//		userRepository.save(user);
        dao.saveAndFlush(user);

        return user;

    }


//    public Endereco findByEmail(String email) {
//
//        return dao.findByEmail(email);
//    }


    @Override
    protected JpaRepository<Endereco, UUID> getDao() {

        return dao;
    }


    @Override
    protected void validateSave(Endereco post) {
        // TODO Auto-generated method stub

    }

    @Override
    protected String getIdEntity(Endereco endereco) {
        return endereco.getId().toString();
    }


    @Override
    protected void validateEdit(Endereco post) {
        // TODO Auto-generated method stub

    }


    @Override
    protected void validateDelete(UUID id) {
        // TODO Auto-generated method stub

    }


}
