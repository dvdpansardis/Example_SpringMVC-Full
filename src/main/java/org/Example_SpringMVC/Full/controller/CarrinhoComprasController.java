package org.Example_SpringMVC.Full.controller;

import org.Example_SpringMVC.Full.model.CarrinhoCompras;
import org.Example_SpringMVC.Full.model.CarrinhoItem;
import org.Example_SpringMVC.Full.model.Produto;
import org.Example_SpringMVC.Full.model.TipoPreco;
import org.Example_SpringMVC.Full.repository.ProdutoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/carrinho")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
/**
 * Session Scope, cria um controller a cada requisição e é destruido no final.
 * 
 * @author David
 *
 */
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping(value = "/remover", method = RequestMethod.POST)
	public ModelAndView remover(Long produtoId, TipoPreco tipoPreco) {
		carrinho.remover(produtoId, tipoPreco);
		return new ModelAndView("redirect:/carrinho/itens");
	}

	@RequestMapping(value = "/itens", method = RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(Long produtoId, TipoPreco tipoPreco) {
		ModelAndView mv = new ModelAndView("redirect:/carrinho/itens");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);

		carrinho.add(carrinhoItem);

		return mv;
	}

	private CarrinhoItem criaItem(long produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDAO.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}

}
