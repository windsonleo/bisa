package com.tecsoluction.bisa.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tecsoluction.bisa.entidade.Role;
import com.tecsoluction.bisa.entidade.Endereco;
import com.tecsoluction.bisa.entidade.Cliente;
import com.tecsoluction.bisa.framework.AbstractController;
import com.tecsoluction.bisa.framework.AbstractEditor;
import com.tecsoluction.bisa.servico.RoleServicoImpl;
import com.tecsoluction.bisa.servico.EnderecoServicoImpl;
import com.tecsoluction.bisa.servico.ClienteServicoImpl;





@Controller
@RequestMapping("endereco/")
public class EnderecoController extends AbstractController<Endereco> {
	
	private static final Logger logger = LoggerFactory.getLogger(EnderecoController.class);

	
	 private final ClienteServicoImpl clienteservico;
	 
	 private final EnderecoServicoImpl ususervice;
	 
	  private Cliente cliente ;
	 
	
	
    public EnderecoController(ClienteServicoImpl roleimpl,EnderecoServicoImpl usuimpl) {
		super("endereco");
		this.clienteservico = roleimpl;
		this.ususervice = usuimpl;
		
	}


    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        binder.registerCustomEditor(Cliente.class, new AbstractEditor<Cliente>(this.clienteservico) {
        });

    }
	
    @ModelAttribute
    public void addAttributes(Model model) {

    	
//    	List<Role> roles = roleservico.findAll();
    	
//    	Genero[] generos = Genero.values();

        Endereco endereco = new Endereco();
        
        cliente = new Cliente();
//        endereco.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
//        endereco = ususervice.findByUsername(endereco.getUsername());

                
//        model.addAttribute("enderecoAtt", endereco);
//        model.addAttribute("generos", generos);
        model.addAttribute("endereco", endereco);
        model.addAttribute("cliente", cliente);

        

    }
    
//    @RequestMapping(value = "/profile", method = RequestMethod.GET)
//    public ModelAndView profileEndereco(HttpServletRequest request) {
//
//        UUID idf = UUID.fromString(request.getParameter("id"));
//
//        ModelAndView profileendereco = new ModelAndView("/public/profile");
//
//        Endereco endereco = getservice().findOne(idf);
//
//        profileendereco.addObject("endereco", endereco);
//
//        return profileendereco;
//    }
//    
//    
//    
//    
//    
//    @RequestMapping(value = "/registro", method = RequestMethod.GET)
//    public ModelAndView Registro(Locale locale, Model model) {
//       
//    	logger.info("Welcome registro ! The client locale is {}.", locale);
//
//        ModelAndView home = new ModelAndView("/public/registro");
//
//
//        return home;
//    }
//    
//    @RequestMapping(value = "/registro", method = RequestMethod.POST)
//    public ModelAndView RegistroPost(Locale locale, Model model, HttpServletRequest request) {
//       
//    	logger.info("Welcome registro ! The client locale is {}.", locale);
//
//        ModelAndView home = new ModelAndView("/public/registro");
//        
//        Endereco endereco = new Endereco();
//        
////        endereco.setUsername(request.getParameter("username"));
////        endereco.setEmail(request.getParameter("email"));
////        endereco.setSenha(request.getParameter("senha"));
////        endereco.setRoles(new HashMap().put(arg0, arg1));
//       
//        getservice().save(endereco);
//
//
//        return new ModelAndView("forward:/login");
//    }
    
  @RequestMapping(value = "/getLatLong", method = RequestMethod.GET)
  public ModelAndView PegarLatLong(Locale locale, Model model, HttpServletRequest request) {
     
  	logger.info("Welcome Pegar Latitude e Longitude ! ");

  UUID idf = UUID.fromString(request.getParameter("id"));
  
  UUID idfpac = UUID.fromString(request.getParameter("idcliente"));
  
  this.cliente = clienteservico.findOne(idfpac);


//  ModelAndView profileendereco = new ModelAndView("/public/profile");

  	Endereco endereco = getservice().findOne(idf);
  	
  	
  	
  	String urlString = "";
  	
  	
  	try{
  		
  		
    // Create a URL for the desired page  
    URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="+endereco.getLogradouro()+","+endereco.getBairro()+","+endereco.getUf()+"&sensor=false");  
    urlString ="http://maps.googleapis.com/maps/api/geocode/json?address=" + URLEncoder.encode(endereco.getLogradouro()+","+endereco.getBairro()+","+endereco.getUf(),"UTF-8") + "&sensor=false";
    
    
    // Read all the text returned by the server  
//    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),Charset.forName("UTF-8")));  
    BufferedReader in = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(urlString)).openConnection()).getInputStream(), Charset.forName("UTF-8")));

    
    String str;              

    String json = "";
    int counter = 0;
    while ((str = in.readLine()) != null) 
    {  
        json = json + str;
    }     


    in.close();  

    String location = json.substring(json.indexOf("\"location\" :"), json.indexOf("\"location_type\"")-13);
    String lat = location.substring((location.indexOf("\"lat\"")+8), location.indexOf(","));
    String lng = location.substring((location.indexOf("\"lng\"")+8), (location.indexOf("}")-12));
    
    endereco.setLatitude(lat);
    endereco.setLongetude(lng);
    
    getservice().edit(endereco);

    System.out.println(location);
    System.out.println(lat);
    System.out.println(lng);

} catch (MalformedURLException e) {  
    e.printStackTrace();

} catch (IOException e) {  
    e.printStackTrace();
}
  	
  	cliente.setEndereco(endereco);
//  	
  	clienteservico.edit(cliente);
  	

	logger.info("Welcome Pegar Latitude" + endereco.getLatitude());
  	logger.info("Welcome Pegar Longitude ! " +endereco.getLongetude());


      return new ModelAndView("redirect:/cliente/addendereco?id=" + cliente.getId()).addObject("endereco", endereco).addObject("cliente", cliente);
  } 
  
  
  @RequestMapping(value = "addendereco", method = RequestMethod.POST)
  public ModelAndView addEnderecoCliente(HttpServletRequest request, Model model) {


//	  	ModelAndView cadastroendereco= new ModelAndView("cadastroendereco");

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

//		String datanascimento = request.getParameter("datanascimento");

//		SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
//		
//		Date data = null;
//		
//		try {
//			data = df.parse(datanascimento);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

      endereco = getservice().save(endereco);


      this.cliente = clienteservico.findOne(id);


//			cliente.setNome(request.getParameter("nome"));
//			cliente.setTelefone(request.getParameter("telefone"));
////			cliente.setDatanascimento(data);
//			cliente.setEmail(request.getParameter("email"));
//			cliente.setFoto(request.getParameter("foto"));
//			cliente.setGenero(request.getParameter("genero"));
//			cliente.setativo(true);

      cliente.setEndereco(endereco);


     clienteservico.edit(cliente);
     
//      endereco.setCliente(cliente);
      
//      enderecoService.edit(endereco);

	ModelAndView cadastroendereco= new ModelAndView("/private/endereco/cadastro/cadastroendereco");
//		
//		
	cadastroendereco.addObject("cliente",cliente);
	cadastroendereco.addObject("endereco",endereco);
	cadastroendereco.addObject("acao", "add");


//      return new ModelAndView("redirect:/cliente/addendereco?id=" + cliente.getId());
	
	return cadastroendereco;
  }
    

	@Override
	protected EnderecoServicoImpl getservice() {

		return ususervice;
	}
    
    

}
