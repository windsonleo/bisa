package com.tecsoluction.bisa.entidade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecsoluction.bisa.framework.BaseEntity;
import com.tecsoluction.bisa.util.Categoria;
import com.tecsoluction.bisa.util.UnidadeMedida;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Produto extends BaseEntity implements Serializable {

	
    private static final long serialVersionUID = -5401174413867896341L;

    @Column(name = "foto")
    private String foto;
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "codebar")
    private String codebar;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "un_medida")
    @Enumerated(EnumType.STRING)
    private UnidadeMedida un_medida;
    
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(name = "preco_custo")
    private BigDecimal precocusto;

    @Column(name = "preco_venda")
    private BigDecimal precovenda;
    
    @OneToMany(mappedBy = "produto")
    private List<Premio> premios;



    public Produto(UUID id, String foto, String nome, String codebar, String descricao,
                   UnidadeMedida un, BigDecimal precocusto, BigDecimal precovenda, boolean ativo) {
        super();
        this.id = id;
        this.foto = foto;
        this.nome = nome;
        this.codebar = codebar;
        this.descricao = descricao;
        this.un_medida = un;
        this.precocusto = precocusto;
        this.precovenda = precovenda;
        this.ativo = ativo;
       
    }

    public Produto() {
      

    }



    @Override
    public String toString() {
        return nome.toUpperCase();
    }

}
