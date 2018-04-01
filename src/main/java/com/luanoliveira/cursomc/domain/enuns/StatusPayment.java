package com.luanoliveira.cursomc.domain.enuns;

public enum StatusPayment {
	
	PENDENTE(1, "Pendente"), 
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descripton;
	
	private StatusPayment(int cod, String descripton) {
		this.cod = cod;
		this.descripton = descripton;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescripton() {
		return this.descripton;
	}
	
	public static StatusPayment toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(StatusPayment x : StatusPayment.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
