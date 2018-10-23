package com.tecsoluction.bisa.entidade;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tecsoluction.bisa.framework.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario  extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	private String senha;
	
	private String email;
	
    private String foto;
		

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_role",
            joinColumns = @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrole"))
	 private Set<Role> roles;
    
//    @JsonIgnore
//    @OneToMany(mappedBy="usuario",fetch=FetchType.EAGER)
//    private Set<Atividade> atividades;
//    
//    
//    @JsonIgnore
//    @OneToMany(mappedBy="usuario",fetch=FetchType.EAGER)
//    private Set<Atendimento> atendimentos;
    
    
    
//    @OneToOne
//    @JoinColumn(name="perfil_id")
//    private Perfil perfil;

//    @OneToOne
//    @JoinColumn(name="telefone_id")
//    private Telefone telefone;
    
    
	public Usuario() {
		
		
	}
	
	    
	    
		@Override
		public String toString() {
			return email;
		}
	
	
	
}
