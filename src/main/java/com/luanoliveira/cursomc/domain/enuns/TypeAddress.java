package com.luanoliveira.cursomc.domain.enuns;

public enum TypeAddress {
	
	RESIDENCIAL(1, "Residencial"), 
	COMERCIAL(2, "Comercial");
	
	private int cod;
	private String descripton;
	
	private TypeAddress(int cod, String descripton) {
		this.cod = cod;
		this.descripton = descripton;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescripton() {
		return this.descripton;
	}
	
	public static TypeAddress toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TypeAddress x : TypeAddress.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
