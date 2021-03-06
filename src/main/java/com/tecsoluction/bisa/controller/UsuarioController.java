package com.tecsoluction.bisa.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.tecsoluction.bisa.entidade.Role;
import com.tecsoluction.bisa.entidade.Usuario;
import com.tecsoluction.bisa.framework.AbstractController;
import com.tecsoluction.bisa.framework.AbstractEditor;
import com.tecsoluction.bisa.servico.RoleServicoImpl;
import com.tecsoluction.bisa.servico.UsuarioServicoImpl;





@Controller
@RequestMapping("usuario/")
public class UsuarioController extends AbstractController<Usuario> {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

	@Autowired
	 private final RoleServicoImpl roleservico;
	@Autowired
	 private final UsuarioServicoImpl ususervice;
	 
	 private Usuario usuario ;
	 
	 private String filename="avatar_usu.jpg";
	 
	
	@Autowired
    public UsuarioController(RoleServicoImpl roleimpl,UsuarioServicoImpl usuimpl) {
		super("usuario");
		this.roleservico = roleimpl;
		this.ususervice = usuimpl;
		
	}


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Role.class, new AbstractEditor<Role>(this.roleservico) {
        });

    }
	
    @ModelAttribute
    public void addAttributes(Model model) {

    	logger.info("Welcome add atribute Usuario Controller !" + usuario);

    	
    	List<Role> roles = roleservico.findAll();
    	
    	
    
    		
    		usuario = new Usuario();
    		
    	
    
    	
//    	Genero[] generos = Genero.values();

//        usuario = new Usuario();
//        usuario.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        usuario = ususervice.findByUsername(usuario.getUsername());

                
//        model.addAttribute("usuarioAtt", usuario);
//        model.addAttribute("generos", generos);
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);
        model.addAttribute("filename", filename);

        

    }
    
    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public ModelAndView profileUsuario(HttpServletRequest request) {

        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView profileusuario = new ModelAndView("/private/usuario/perfil");

        Usuario usuario = getservice().findOne(idf);

        profileusuario.addObject("usuario", usuario);

        return profileusuario;
    }
    
    
    
    
    
    @RequestMapping(value = "/registro", method = RequestMethod.GET)
    public ModelAndView Registro(Locale locale, Model model) {
       
    	logger.info("Welcome registro ! The client locale is {}.", locale);

        ModelAndView home = new ModelAndView("/public/registro");


        return home;
    }
    
    @RequestMapping(value = "/registro", method = RequestMethod.POST)
    public ModelAndView RegistroPost(Locale locale, Model model, HttpServletRequest request) {
       
    	logger.info("Welcome registro ! The client locale is {}.", locale);

        ModelAndView home = new ModelAndView("/public/registro");
        
        Usuario usuario = new Usuario();
        
//        usuario.setUsername(request.getParameter("username"));
//        usuario.setEmail(request.getParameter("email"));
//        usuario.setSenha(request.getParameter("senha"));
//        usuario.setRoles(new HashMap().put(arg0, arg1));
       
        getservice().save(usuario);


        return new ModelAndView("forward:/login");
    }
    
    
    // verificar tmanho do arquivo e se o arquivo ja existe
    @RequestMapping(value = "salvarfotousuario", method = RequestMethod.POST)
    public ModelAndView SalvarFotoProduto2(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
                             Model model, @ModelAttribute Usuario usuarior) {
    	
//    	Usuario usuario = new Usuario();

        String sucesso = "Sucesso ao salvar foto";
        
        String erros = "Falha ao salvar foto";
        
//        ModelAndView cadastro = new ModelAndView("/private/produto/cadastro/cadastroproduto");

        String path = session.getServletContext().getRealPath("/WEB-INF/classes/static/img/usuario/");
        
        this.filename = file.getOriginalFilename();
        
        
//        heroku não funfa com essas barras
//        String caminho = path + "\\" + filename;
        
        String caminho = path + filename;
        


        System.out.println(" path = "  + path );

//        System.out.println(" caminho" + caminho);
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
            
            System.out.println(" salvou file : " + filename);
            
           
//           usuario.setFoto(filename);

        } catch (Exception e) {

            System.out.println(e);

            model.addAttribute("erros", erros + e);
            model.addAttribute("acao", "add");
            
            System.out.println(" não salvou file : " + e);

        }

//       Usuario usuario =  new Usuario();
     
        this.usuario.setFoto(filename);
        
       return new ModelAndView("redirect:/usuario/cadastro").addObject("usuario", usuario);

    }
    

	@Override
	protected UsuarioServicoImpl getservice() {

		return ususervice;
	}
    
    

}
