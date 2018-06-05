package org.Example_SpringMVC.Full.controller;

import java.util.Arrays;
import java.util.List;

import org.Example_SpringMVC.Full.model.Produto;
import org.Example_SpringMVC.Full.model.Role;
import org.Example_SpringMVC.Full.model.Usuario;
import org.Example_SpringMVC.Full.repository.ProdutoDAO;
import org.Example_SpringMVC.Full.repository.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private UsuarioDAO usuarioDao;

	@RequestMapping("/")
	@Cacheable("produtosHome")
	public ModelAndView home() {
		System.out.println("Home CDC");
		
		List<Produto> produtos = produtoDAO.listar();
		ModelAndView mv = new ModelAndView("home");
		
		mv.addObject("produtos", produtos);
		
		return mv;
	}

	@Transactional
	@ResponseBody
	@RequestMapping("/url-magica")
	public String urlMagicaMaluca() {
		Usuario usuario = new Usuario();
		usuario.setNome("Admin");
		usuario.setEmail("admin");
		usuario.setSenha("$2a$10$lt7pS7Kxxe5JfP.vjLNSyOXP11eHgh7RoPxo5fvvbMCZkCUss2DGu");
		usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

		usuarioDao.gravar(usuario);

		return "Url Mágica executada";
	}

}
