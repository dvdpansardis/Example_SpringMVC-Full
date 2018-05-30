package org.Example_SpringMVC.Full.controller;

import java.util.concurrent.Callable;

import org.Example_SpringMVC.Full.model.CarrinhoCompras;
import org.Example_SpringMVC.Full.model.DadosDePagamento;
import org.Example_SpringMVC.Full.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamentos")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MailSender mailSender;

	@SuppressWarnings("finally")
	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
		return () -> {
			System.out.println(carrinho.getTotal());

			String uri = "http://book-payment.herokuapp.com/payment";
			DadosDePagamento dadosDePagamento = new DadosDePagamento(carrinho.getTotal());

			String response = "";
			try {
				response = restTemplate.postForObject(uri, dadosDePagamento, String.class);
				System.out.println(response);

				enviaEmailCompraProduto(usuario);

				model.addFlashAttribute("sucesso", response);
			} catch (HttpClientErrorException e) {
				System.out.println(e.getMessage());
				model.addFlashAttribute("falha", e.getResponseBodyAsString());
			} finally {
				return new ModelAndView("redirect:/produtos");
			}
		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("Compra finalizada com sucesso");
        //email.setTo(usuario.getEmail());
        email.setTo("dvd.pansardis@gmail.com");
        email.setText("Compra finaliza com o valor de " + carrinho.getTotal());
        email.setFrom("casadocodigo@vendas.com.br");

        mailSender.send(email);
    }

}
