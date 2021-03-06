package it.albertus.spring.ioc.model;

import org.springframework.beans.factory.InitializingBean;

public class Geometra implements InitializingBean {

	private static final int EURO_PER_MQ = 20;

	private String nome;

	private Calcolatrice calcolatrice;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calcolatrice getCalcolatrice() {
		return calcolatrice;
	}

	public void setCalcolatrice(Calcolatrice calcolatrice) {
		this.calcolatrice = calcolatrice;
	}

	public double calcolaParcella(int metriQuadrati) {
		return calcolatrice.moltiplicazione(metriQuadrati, EURO_PER_MQ);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (calcolatrice == null) {
			throw new IllegalStateException("La calcolatrice non puo' essere null!");
		}
	}

}