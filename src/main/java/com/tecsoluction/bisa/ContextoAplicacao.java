package com.tecsoluction.bisa;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.bisa.entidade.Usuario;
import com.tecsoluction.bisa.exception.CustomGenericException;
import com.tecsoluction.bisa.servico.UsuarioServicoImpl;



/**
 * Created by clebr on 01/09/2016.
 */
@ControllerAdvice
public class ContextoAplicacao {

	 @Autowired
	 private
	 UsuarioServicoImpl usuarioService;

	// @Autowired
	// public ContextoAplicacao(UsuarioServicoImpl sevice) {
	//
	// this.userservice = sevice;
	// }

	@Autowired
	public ContextoAplicacao() {

		// this.userservice = sevice;
	}

	@ModelAttribute
	public void addAttributes(Model model) {

		// Usuario usuarioAtt = dao.PegarPorId(100L);
		//
		// model.addAttribute("usuarioAtt", usuarioAtt);
		
        //inicio de ususario logado
        
        Usuario usuario = new Usuario();
        usuario.setEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        usuario = usuarioService.findByEmail(usuario.getEmail());
        
        //verifica se há usuario cadastrado
        if(usuario == null) {
        	
        model.addAttribute("mensagem", "Carregado Usuario Padrão");
        
        usuario = new Usuario();
        UUID idf = UUID.fromString("4b71a569-c0bd-41a2-bffe-35e39e1a875a");
        usuario = usuarioService.findOne(idf);
        	
        	
        } else {
    	
	        model.addAttribute("mensagem", "Bem-Vindo " + usuario.getEmail());

        
        }

        
        model.addAttribute("usuarioAtt", usuario);
	}

	@ExceptionHandler(CustomGenericException.class)
	public ModelAndView handleCustomException(CustomGenericException ex) {

		ModelAndView model = new ModelAndView("/public/error/erro");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());

		return model;

	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("/public/error/erro");
		model.addObject("errMsg", ex.toString());

		return model;

	}

}
