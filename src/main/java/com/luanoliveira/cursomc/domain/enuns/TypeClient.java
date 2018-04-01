package com.luanoliveira.cursomc.domain.enuns;

public enum TypeClient {
	
	PESSOAFISICA(1, "Pessoa Física"), 
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descripton;
	
	private TypeClient(int cod, String descripton) {
		this.cod = cod;
		this.descripton = descripton;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescripton() {
		return this.descripton;
	}
	
	public static TypeClient toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TypeClient x : TypeClient.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
