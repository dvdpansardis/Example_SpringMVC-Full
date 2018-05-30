package org.Example_SpringMVC.Full.controller;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.Example_SpringMVC.Full.infra.FileSaver;
import org.Example_SpringMVC.Full.model.Produto;
import org.Example_SpringMVC.Full.model.TipoPreco;
import org.Example_SpringMVC.Full.repository.ProdutoDAO;
import org.Example_SpringMVC.Full.validation.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private FileSaver fileSaver;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("/produtos/detalhe");
		Produto produto = produtoDAO.find(id);
		mv.addObject("produto", produto);
		return mv;
	}
	
//	@RequestMapping("/{id}")
//	@ResponseBody //Indica que tudo vai retornar vai ser no corpo da resposta
//	public Produto detalheToJson(@PathVariable("id") Long id) {
//		return produtoDAO.find(id);
//	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/form");
		mv.addObject("tipos", TipoPreco.values());
		return mv;
	}

	@CacheEvict(value = "produtosHome", allEntries = true)
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult br,
			RedirectAttributes redirectAttributes) {
		System.out.println(sumario.getOriginalFilename());

		System.out.println(produto);

		if (br.hasErrors()) {
			return form(produto);
		}

		String pathServer = fileSaver.write("arquivos_sumario", sumario);

		produto.setSumarioPath(pathServer);

		produtoDAO.gravar(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		// Always redirect after post
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("produtos/lista");
		List<Produto> produtos = produtoDAO.listar();
		mv.addObject("produtos", produtos);
		return mv;
	}

}
