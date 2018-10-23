package com.tecsoluction.bisa.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tecsoluction.bisa.framework.BaseEntity;
import com.tecsoluction.bisa.util.Genero;
import com.tecsoluction.bisa.util.UnidadeMedida;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente  extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	
	
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date datanascimento;
    
    private String foto;
	
    
	private String cpf;
	
	 @JsonIgnore
	@OneToOne(targetEntity=Endereco.class)
	private Endereco endereco;
	
	
	private String telefone;
	
     
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//	private Date datainicio;
//    
//    
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//	private Date datafim;
	
    @Column(name = "GENERO")
    @Enumerated(EnumType.STRING)
    private Genero genero;
    
    
    private String email;
    
    
    private String obs;
    
//    @ElementCollection(fetch=FetchType.EAGER)
//    @CollectionTable(name = "evolucao_paciente", joinColumns = @JoinColumn(name = "id"))
//    @JsonManagedReference
//    private List<Evolucao> evolucoes;
//    
//    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "paciente_patologia",
//            joinColumns = @JoinColumn(name = "idpaciente"),
//            inverseJoinColumns = @JoinColumn(name = "idpatologia"))
//    private Set<Patologia> patologias;
//    
//    
    @OneToMany(mappedBy="cliente",fetch=FetchType.EAGER)
    private Set<Sorteio> sorteios;
    
    
	

//    @JsonIgnore
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "usuario_role",
//            joinColumns = @JoinColumn(name = "idusuario"),
//            inverseJoinColumns = @JoinColumn(name = "idrole"))
//	 private Set<Role> roles;
    
//    @OneToOne
//    @JoinColumn(name="perfil_id")
//    private Perfil perfil;

//    @OneToOne
//    @JoinColumn(name="telefone_id")
//    private Telefone telefone;
    
    
	public Cliente() {
		
		
	}
	
	
	
	
    public void addSorteios(Sorteio item){
    	
    	
    	this.getSorteios().add(item);
    	
    	
    	
    }
//    
//    
    public void removeSorteios(int index){
    	
    	
    	this.getSorteios().remove(index);
	
    	
    }
//    
//    
//    public void addPatologia(Patologia item){
//    	
//    	
//    	this.getPatologias().add(item);
//    	
//    	
//    	
//    }
//    
//    
//    public void removePatologia(int index){
//    	
//    	
//    	this.getPatologias().remove(index);
//	
//    	
//    }
	
	    
	    
		@Override
		public String toString() {
			return nome;
		}
		
		
		
		
//		public int DiasTotais() {
//			
//			
//			UtilsDate utildate = new UtilsDate();
//			
//			
//			
//			
//			return utildate.toQtdDiasEntreDatas(getDatainicio(), getDatafim());
//		}
//		
//		
//		
//		public int DiasRestantes() {
//			
//
//			UtilsDate utildate = new UtilsDate();
//			
//			Date datahoje = new Date();
//			
//			
//			
//			return utildate.toQtdDiasEntreDatas(datahoje, getDatafim());
//			
//		}
//		
//		
//		
//		public int CalcularIdade() {
//			
//
//			UtilsDate utildate = new UtilsDate();
//			
//			Date datahoje = new Date();
//			
//			
//			
//			return utildate.toQtdAnosDataAtual(datahoje, getDatanascimento());
//			
//		}
	
	
}
