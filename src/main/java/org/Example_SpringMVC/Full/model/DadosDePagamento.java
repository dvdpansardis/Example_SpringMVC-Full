package org.Example_SpringMVC.Full.model;

import java.math.BigDecimal;

public class DadosDePagamento {

	private BigDecimal value;

	public DadosDePagamento() {
	}
	
	public DadosDePagamento(BigDecimal total) {
		this.value = total;
	}
	
	public BigDecimal getValue() {
		return value;
	}

}
