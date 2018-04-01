package com.luanoliveira.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.luanoliveira.cursomc.domain.enuns.TypeClient;
import com.luanoliveira.cursomc.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 a 120 caracteres")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpfOrCnpj;
	
	private Integer typeClient;
	private Integer typeAdress;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String address;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String number;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String complement;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String district;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String zipCode;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String phone1;
	private String phone2;
	private String phone3;
	
	private Integer cityId;
	
	public ClientNewDTO() {
		
	}

	public ClientNewDTO(Integer id, String name, String email, String cpfOrCnpj, TypeClient typeClient) {
		this.name = name;
		this.email = email;
		this.cpfOrCnpj = cpfOrCnpj;
		this.typeClient = (typeClient==null) ? null : typeClient.getCod();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getTypeClient() {
		return typeClient;
	}

	public void setTypeClient(Integer typeClient) {
		this.typeClient = typeClient;
	}

	public Integer getTypeAdress() {
		return typeAdress;
	}

	public void setTypeAdress(Integer typeAdress) {
		this.typeAdress = typeAdress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
}
