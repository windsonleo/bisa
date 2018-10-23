package com.tecsoluction.bisa.servico;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsoluction.bisa.dao.IProdutoDAO;
import com.tecsoluction.bisa.entidade.Produto;
import com.tecsoluction.bisa.framework.AbstractEntityService;



@Service("produtoService")
@Transactional
public class ProdutoServicoImpl extends AbstractEntityService<Produto> {


    @Autowired
    private
    IProdutoDAO produtodao;

//	private Entity entityClass;


    public ProdutoServicoImpl() {
        super(Produto.class, "produto");
    }

    @Override
    protected JpaRepository<Produto, UUID> getDao() {

        return produtodao;
    }

	@Override
	protected void validateSave(Produto post) {
		// TODO Auto-generated method stub
		
	}

    @Override
    protected String getIdEntity(Produto produto) {
        return produto.getId().toString();
    }

    @Override
	protected void validateEdit(Produto post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public List<Produto> findAllNew() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
