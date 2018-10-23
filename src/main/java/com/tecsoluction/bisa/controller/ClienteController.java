package com.tecsoluction.bisa.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
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

import com.tecsoluction.bisa.entidade.Cliente;
import com.tecsoluction.bisa.entidade.Endereco;
import com.tecsoluction.bisa.entidade.Cliente;
import com.tecsoluction.bisa.entidade.Usuario;
import com.tecsoluction.bisa.framework.AbstractController;
import com.tecsoluction.bisa.framework.AbstractEditor;
import com.tecsoluction.bisa.servico.ClienteServicoImpl;
import com.tecsoluction.bisa.servico.EnderecoServicoImpl;
import com.tecsoluction.bisa.servico.UsuarioServicoImpl;
import com.tecsoluction.bisa.util.Genero;






@Controller
@RequestMapping("cliente/")
public class ClienteController extends AbstractController<Cliente> {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

		 
	 private final ClienteServicoImpl ususervice;
	 
	
	 private Cliente cliente;
	 
	 private EnderecoServicoImpl enderecoservico;
	 
	 private UsuarioServicoImpl usuarioServico;
	 
	 
	 private String filename="avatar_pac.jpg";
	 
	
	 
	  private List<Cliente> clientes = new ArrayList<Cliente>();
	  
	  private Genero[] genero;
	 
	
	
    public ClienteController(ClienteServicoImpl usuimpl,EnderecoServicoImpl end,UsuarioServicoImpl usuarioser) {
		super("cliente");
	
		this.ususervice = usuimpl;
		this.enderecoservico = end;
		this.usuarioServico = usuarioser;
		
	}


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Cliente.class, new AbstractEditor<Cliente>(this.ususervice) {
        });

    }
	
    @ModelAttribute
    public void addAttributes(Model model) {

    	
    	logger.info("Welcome add atribute Cliente Controller !" + cliente);
    	

    	

    		
    	cliente = new Cliente();
    	
    	
    	 clientes = getservice().findAll();
    	 
    	 genero = Genero.values();
    	 
    	
    	
        model.addAttribute("cliente", cliente);
        model.addAttribute("filename", filename);
        model.addAttribute("genero", genero);
        

    }
    
    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public ModelAndView profileCliente(HttpServletRequest request) {

        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView profilecliente = new ModelAndView("/private/cliente/perfil");

        Cliente cliente = getservice().findOne(idf);
        
        
        Date datanow = new Date();

        profilecliente.addObject("cliente", cliente);
        profilecliente.addObject("datanow", datanow);

        return profilecliente;
    }
    
    
//    @RequestMapping(value = "/addevolucao", method = RequestMethod.POST)
//    public ModelAndView AddEvolucaoCliente(HttpServletRequest request) {
//
//        UUID idf = UUID.fromString(request.getParameter("id"));
//
//        ModelAndView profilecliente = new ModelAndView("/private/cliente/perfil");
//
//        this.cliente = getservice().findOne(idf);
//        
//	   	 Usuario usuario;
//	 	
//	   	 String mail =SecurityContextHolder.getContext().getAuthentication().getName();
//	        
//	   	 usuario = usuarioServico.findByEmail(mail);
//                
//
//        
//        getservice().edit(cliente);
//        
//        
//        
//        Date datanow = new Date();
//
//        profilecliente.addObject("cliente", cliente);
//        profilecliente.addObject("datanow", datanow);
//  
//
//        return profilecliente;
//    }
    
    
//    @RequestMapping(value = "/removeEvolucao", method = RequestMethod.GET)
//    public ModelAndView rEMOVEEvolucaoCliente(HttpServletRequest request) {
//
//        UUID idf = UUID.fromString(request.getParameter("id"));
//    	
//    	String idff = request.getParameter("idevolucao");
//
//        ModelAndView profilecliente = new ModelAndView("/private/cliente/perfil");
//
//        this.cliente = getservice().findOne(idf);
        
//	   	 Usuario usuario;
//	 	
//	   	 String mail =SecurityContextHolder.getContext().getAuthentication().getName();
//	        
//	   	 usuario = usuarioServico.findByEmail(mail);
//        
//        Evolucao evolucao = new Evolucao();
//        
//        evolucao.setUsuario(usuario);
//        evolucao.setData(new Date());
//        evolucao.setDescricao(request.getParameter("descricao"));
        
//        int index = Integer.valueOf(idff);
//        
//        cliente.removeEvolucao(index);
//        
//        logger.info("Welcome Remove Evolucao Cliente Controller index: !" + idff);
//        
//        getservice().edit(cliente);
        
        
        
//        Date datanow = new Date();

//        profilecliente.addObject("cliente", cliente);
//        profilecliente.addObject("datanow", datanow);
//        profilecliente.addObject("evolucao", new Evolucao());

//        return new ModelAndView("redirect:/cliente/perfil?id=" + cliente.getId());
//    }
    
    
    
//    
//    
//    
//    
//    
    @RequestMapping(value = "/addendereco", method = RequestMethod.GET)
    public ModelAndView AddEndereco(HttpServletRequest request, Model model) {
       
    	logger.info("Welcome Add eNDEREECO PACIENTE !");
    	
    	 UUID id = UUID.fromString(request.getParameter("id"));
    	 
    	 Usuario usuario;
    	
    	 String mail =SecurityContextHolder.getContext().getAuthentication().getName();
         
    	 usuario = usuarioServico.findByEmail(mail);
    	 
    	 this.cliente = getservice().findOne(id);
    	 
    	 if(cliente.getEndereco() == null){
    	 
    	 Endereco endereco = new Endereco();
    	 cliente.setEndereco(endereco);
    	 
    	 }
    	 
//    	 if(request.getParameter("id") == null){
//    		 
//    		 ModelAndView cadastroenderecoerro = new ModelAndView("/public/error/erro");
//    		 cadastroenderecoerro.addObject("erro", "Id Cliente Nulo");
//    		 cadastroenderecoerro.addObject("usuarioAtt", usuario);
//    		 
//    		 
//    		 logger.info("if add endereco cliente!");
//    		 
//    		 return cadastroenderecoerro;
//    		 
//    	 }else {
//
//         this.cliente = getservice().findOne(id);
//
//        ModelAndView cadastroendereco = new ModelAndView("/private/endereco/cadastro/cadastroendereco");
//        
//        cadastroendereco.addObject("cliente", cliente);
//        cadastroendereco.addObject("acao", "add");
//        cadastroendereco.addObject("usuarioAtt", usuario);
//        
//        logger.info("else add endereco cliente!");
//        
//
//
//        return cadastroendereco;
//        
//    	 }
    	 
    	 
       ModelAndView cadastroendereco = new ModelAndView("/private/endereco/cadastro/cadastroendereco");
       
       
       
       cadastroendereco.addObject("cliente", cliente);
       cadastroendereco.addObject("acao", "add");
       cadastroendereco.addObject("endereco", cliente.getEndereco());
       
//       logger.info("else add endereco cliente!");
       


       return cadastroendereco; 
    	 
    	 
    }
    
    
    @RequestMapping(value = "addendereco", method = RequestMethod.POST)
    public ModelAndView addEnderecoCliente(HttpServletRequest request, Model model) {


    	 UUID id = UUID.fromString(request.getParameter("id"));

         Endereco endereco = new Endereco();

         endereco.setLogradouro(request.getParameter("logradouro"));
         endereco.setNumero(request.getParameter("numero"));
         endereco.setBairro(request.getParameter("bairro"));
         endereco.setCidade(request.getParameter("cidade"));
         endereco.setUf(request.getParameter("uf"));
         endereco.setCep(request.getParameter("cep"));
         endereco.setPontoreferencia(request.getParameter("pontoreferencia"));
         endereco.setComplemento(request.getParameter("complemento"));
         endereco.setAtivo(true);

//   		String datanascimento = request.getParameter("datanascimento");

//   		SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
//   		
//   		Date data = null;
//   		
//   		try {
//   			data = df.parse(datanascimento);
//   		} catch (ParseException e) {
//   			// TODO Auto-generated catch block
//   			e.printStackTrace();
//   		}

         endereco = enderecoservico.save(endereco);


         this.cliente = getservice().findOne(id);


//   			cliente.setNome(request.getParameter("nome"));
//   			cliente.setTelefone(request.getParameter("telefone"));
////   			cliente.setDatanascimento(data);
//   			cliente.setEmail(request.getParameter("email"));
//   			cliente.setFoto(request.getParameter("foto"));
//   			cliente.setGenero(request.getParameter("genero"));
//   			cliente.setativo(true);

         cliente.setEndereco(endereco);


        getservice().edit(cliente);
        
//         endereco.setCliente(cliente);
         
//         enderecoService.edit(endereco);

   	ModelAndView cadastroendereco= new ModelAndView("/private/endereco/cadastro/cadastroendereco");
//   		
//   		
   	cadastroendereco.addObject("cliente",cliente);
   	cadastroendereco.addObject("endereco",endereco);
   	cadastroendereco.addObject("acao", "editar");


//         return new ModelAndView("redirect:/cliente/addendereco?id=" + cliente.getId());
   	
   	return cadastroendereco;
     }
    
    
    
    
    @RequestMapping(value = "/AddPatologia", method = RequestMethod.GET)
    public ModelAndView AddPatologiaCliente(HttpServletRequest request) {

        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView profilecliente = new ModelAndView("/private/cliente/patologia");

        this.cliente = getservice().findOne(idf);
        
//	   	 Usuario usuario;
//	 	
//	   	 String mail =SecurityContextHolder.getContext().getAuthentication().getName();
//	        
//	   	 usuario = usuarioServico.findByEmail(mail);
//        
//        Evolucao evolucao = new Evolucao();
//        
//        evolucao.setUsuario(usuario);
//        evolucao.setData(new Date());
//        evolucao.setDescricao(request.getParameter("descricao"));
//        
//        cliente.addEvolucao(evolucao);
//        
//        getservice().edit(cliente);
//        
//        
//        
//        Date datanow = new Date();
        
//        List<Patologia> patologias = patologiaServico.findAll();

        profilecliente.addObject("cliente", cliente);
//        profilecliente.addObject("patologias", patologias);
        
        
//        profilecliente.addObject("datanow", datanow);
//        profilecliente.addObject("evolucao", new Evolucao());

        return profilecliente;
    }
    
    
    
    @RequestMapping(value = "/AddPatologia", method = RequestMethod.POST)
    public ModelAndView AddPatologiaClientePost(HttpServletRequest request,@ModelAttribute Cliente model) {

        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView profilecliente = new ModelAndView("/private/cliente/perfil");

        this.cliente = getservice().findOne(idf);
        
//	   	 Usuario usuario;
//	 	
//	   	 String mail =SecurityContextHolder.getContext().getAuthentication().getName();
//	        
//	   	 usuario = usuarioServico.findByEmail(mail);
//        
//        Evolucao evolucao = new Evolucao();
//        
//        evolucao.setUsuario(usuario);
//        evolucao.setData(new Date());
//        evolucao.setDescricao(request.getParameter("descricao"));
//        
//        cliente.addEvolucao(evolucao);
//        
        
//        cliente.setPatologias(model.getPatologias());
        getservice().edit(cliente);
//        
//        
//        
//        Date datanow = new Date();
        
//        List<Patologia> patologias = patologiaServico.findAll();

        profilecliente.addObject("cliente", cliente);
//        profilecliente.addObject("patologias", patologias);
//        profilecliente.addObject("evolucao", new Evolucao());
        
//        profilecliente.addObject("datanow", datanow);
//        profilecliente.addObject("evolucao", new Evolucao());

        return profilecliente;
    }
    
    
    
//    
//    @RequestMapping(value = "/registro", method = RequestMethod.POST)
//    public ModelAndView RegistroPost(Locale locale, Model model, HttpServletRequest request) {
//       
//    	logger.info("Welcome registro ! The client locale is {}.", locale);
//
//        ModelAndView home = new ModelAndView("/public/registro");
//        
//        Cliente cliente = new Cliente();
//        
////        cliente.setUsername(request.getParameter("username"));
////        cliente.setEmail(request.getParameter("email"));
////        cliente.setSenha(request.getParameter("senha"));
////        cliente.setRoles(new HashMap().put(arg0, arg1));
//       
//        getservice().save(cliente);
//
//
//        return new ModelAndView("forward:/login");
//    }
    
    // verificar tmanho do arquivo e se o arquivo ja existe
    @RequestMapping(value = "salvarfotocliente", method = RequestMethod.POST)
    public ModelAndView SalvarFotoCliente(@RequestParam ("file") MultipartFile file, HttpSession session, HttpServletRequest request,
                             Model model,@ModelAttribute Cliente clienter) {
    	
    	
    	logger.info("Welcome salvar foto cliente Cliente Controller !");
    	
//    	Cliente cliente = new Cliente();
    	
//    	cliente = clienter;

        String sucesso = "Sucesso ao salvar foto";
        
        String erros = "Falha ao salvar foto";
        
//        ModelAndView cadastro = new ModelAndView("/private/produto/cadastro/cadastroproduto");

        String path = session.getServletContext().getRealPath("/WEB-INF/classes/static/img/cliente/");
        
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
        this.cliente.setFoto(filename);
        
       return new ModelAndView("redirect:/cliente/cadastro").addObject("cliente", cliente);

    }
    
    
    
//    @RequestMapping(value = "/progresso", method = RequestMethod.GET)
//    public ModelAndView ProgressoCliente(HttpServletRequest request) {
//
//        UUID idf = UUID.fromString(request.getParameter("id"));
//
//        ModelAndView profilecliente = new ModelAndView("/private/cliente/progresso");
//
//        this.cliente = getservice().findOne(idf);
        
//	   	 Usuario usuario;
//	 	
//	   	 String mail =SecurityContextHolder.getContext().getAuthentication().getName();
//	        
//	   	 usuario = usuarioServico.findByEmail(mail);
//        
//        Evolucao evolucao = new Evolucao();
//        
//        evolucao.setUsuario(usuario);
//        evolucao.setData(new Date());
//        evolucao.setDescricao(request.getParameter("descricao"));
//        
//        cliente.addEvolucao(evolucao);
//        
//        getservice().edit(cliente);
//        
//        
//        
//        Date datanow = new Date();
        
//        List<Patologia> patologias = patologiaServico.findAll();
//
//        profilecliente.addObject("cliente", cliente);
//        profilecliente.addObject("patologias", patologias);
//        profilecliente.addObject("evolucao", new Evolucao());
//        
        
//        profilecliente.addObject("datanow", datanow);
//        profilecliente.addObject("evolucao", new Evolucao());

//        return profilecliente;
//    }
    
//    private void VerificarAltas(List<Cliente> clientes2) {
//		
//    	
//    	clientesAltas = new ArrayList<Cliente>();
//    	
//
//    	for (Cliente cliente : clientes2) {
//    		
//    		
//    		if(cliente.isAlta()){
//    			
//    			clientesAltas.add(cliente);
//    			
//    			
//    		}
//    		
//			
//		}
//    	
//    	
//
//	}
    
    
//    private void VerificarInternacoes(List<Cliente> clientes2) {
//		
//    	
//    	clientesInternados = new ArrayList<Cliente>();
//    	
//   
//
//    	for (Cliente cliente : clientes2) {
//    		
//    		
//    		if(cliente.isInternacao()){
//    			
//    			clientesInternados.add(cliente);
//    			
//    			
//    		}
//    		
//			
//		}
//    	
//    	
//    		}
    
    
    
    
    @RequestMapping(value = "/altas", method = RequestMethod.GET)
    public ModelAndView AltaCliente(HttpServletRequest request) {

//        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView profilecliente = new ModelAndView("/private/cliente/alta");



        return profilecliente;
    } 
    
    
    @RequestMapping(value = "/internacao", method = RequestMethod.GET)
    public ModelAndView InternacaoCliente(HttpServletRequest request) {

//        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView profilecliente = new ModelAndView("/private/cliente/internacao");



        return profilecliente;
    } 
    
    @RequestMapping(value = "/maps", method = RequestMethod.GET)
    public ModelAndView MapsCliente(HttpServletRequest request) {

        UUID idf = UUID.fromString(request.getParameter("id"));

        ModelAndView mapscliente = new ModelAndView("/private/cliente/maps");
        
        cliente = getservice().findOne(idf);
        
        Endereco endereco = cliente.getEndereco();
        
        mapscliente.addObject("cliente", cliente);
        
        mapscliente.addObject("endereco", endereco);



        return mapscliente;
    } 
    
    
    
    
    

	@Override
	protected ClienteServicoImpl getservice() {

		return ususervice;
	}
    
    

}
