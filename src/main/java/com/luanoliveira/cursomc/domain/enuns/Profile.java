package com.luanoliveira.cursomc.domain.enuns;

public enum Profile {
	
	ADMIN(1, "ROLE_ADMIN"), 
	CLIENT(2, "ROLE_CLIENT");
	
	private int cod;
	private String descripton;
	
	private Profile(int cod, String descripton) {
		this.cod = cod;
		this.descripton = descripton;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDescripton() {
		return this.descripton;
	}
	
	public static Profile toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(Profile x : Profile.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
	
}
