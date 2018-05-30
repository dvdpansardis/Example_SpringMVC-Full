package org.Example_SpringMVC.Full.controller;

import java.util.List;

import org.Example_SpringMVC.Full.model.Produto;
import org.Example_SpringMVC.Full.repository.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	private ProdutoDAO produtoDAO;
	
	@RequestMapping("/")
	@Cacheable("produtosHome")
	public ModelAndView home() {
		System.out.println("Home CDC");
		
		List<Produto> produtos = produtoDAO.listar();
		ModelAndView mv = new ModelAndView("home");
		
		mv.addObject("produtos", produtos);
		
		return mv;
	}
	
}
