package com.tecsoluction.bisa.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.bisa.entidade.Cliente;
import com.tecsoluction.bisa.entidade.Premio;
import com.tecsoluction.bisa.entidade.Produto;
import com.tecsoluction.bisa.framework.AbstractController;
import com.tecsoluction.bisa.framework.AbstractEntityService;
import com.tecsoluction.bisa.servico.ClienteServicoImpl;
import com.tecsoluction.bisa.servico.EnderecoServicoImpl;
import com.tecsoluction.bisa.servico.ProdutoServicoImpl;
import com.tecsoluction.bisa.servico.UsuarioServicoImpl;
import com.tecsoluction.bisa.util.Categoria;
import com.tecsoluction.bisa.util.UnidadeMedida;

@Controller
@RequestMapping("produto/")
public class ProdutoController extends AbstractController<Produto> {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	
	 private final ProdutoServicoImpl produtoservice;
	 
	 private Produto produto;
	 
	 private UnidadeMedida[] un_medida;
	 
	 private String filename="avatar_pac.jpg";
	 
	 private Categoria[] categoria;
	
	
    public ProdutoController(ProdutoServicoImpl usuimpl) {
		super("produto");
	
		this.produtoservice = usuimpl;

		
	}
    
    @ModelAttribute
    public void addAttributes(Model model) {

    	logger.info("Welcome add atribute produto Controller !");

    	
    
    		
    	produto = new Produto();
    		
    	un_medida = UnidadeMedida.values();
    	
    	categoria =Categoria.values();
    

        model.addAttribute("produto", produto);
        model.addAttribute("un_medida", un_medida);
        model.addAttribute("filename", filename);
        model.addAttribute("categoria", categoria);

        

    }
    
    
    @RequestMapping(value = "salvarfotoproduto", method = RequestMethod.POST)
    public ModelAndView SalvarFotoProduto(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
                             Model model,@ModelAttribute Produto clienter) {
    	
    	
    	logger.info("Welcome salvar foto produto  Controller !");
    	
//    	Cliente cliente = new Cliente();
    	
//    	cliente = clienter;

        String sucesso = "Sucesso ao salvar foto";
        
        String erros = "Falha ao salvar foto";
        
//        ModelAndView cadastro = new ModelAndView("/private/produto/cadastro/cadastroproduto");

        String path = session.getServletContext().getRealPath("/WEB-INF/classes/static/img/produto/");
        
        this.filename = file.getOriginalFilename();
        
//        String caminho = path + "\\" + filename;
        
        String caminho = path  + filename;
        


        System.out.println(" path = "  + path );

        System.out.println(" caminho" + caminho);
//        
//        System.out.println("request D" + d);

        try {

            byte barr[] = file.getBytes();

            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(caminho));
            bout.write(barr);
            bout.flush();
            bout.close();

            model.addAttribute("sucesso", sucesso);
            model.addAttribute("filename", filename);
            model.addAttribute("acao", "add");
//            cliente.setFoto(filename);
            
            System.out.println(" salvou file : " + filename);

        } catch (Exception e) {

            System.out.println(e);

            model.addAttribute("erros", erros + e);
            model.addAttribute("acao", "add");
            
            System.out.println(" não salvou file : " + e);

        }

//     Cliente cliente = new Cliente();
        this.produto.setFoto(filename);
        
       return new ModelAndView("redirect:/produto/cadastro").addObject("produto", produto);

    }


	@Override
	protected ProdutoServicoImpl getservice() {
		// TODO Auto-generated method stub
		return produtoservice;
	}

}
