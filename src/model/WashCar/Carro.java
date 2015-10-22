package model.WashCar;

import java.time.LocalDate;

public class Carro implements Entidade{
	
	private Integer idCarro;
	private String nome;
	private String placa;
	private Cliente cliente;
	private Modelo modelo;
	private LocalDate dataAltercacao;
	private LocalDate dataInclusao;
	
	//construtor com atributos
	public Carro(Integer idCarro, String nome, String placa, Cliente cliente,
			Modelo modelo, LocalDate dataAltercacao, LocalDate dataInclusao) {
		super();
		this.idCarro = idCarro;
		this.nome = nome;
		this.placa = placa;
		this.cliente = cliente;
		this.modelo = modelo;
		this.dataAltercacao = dataAltercacao;
		this.dataInclusao = dataInclusao;
	}
	
	//gets e sets das variaveis
	public Integer getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public LocalDate getDataAltercacao() {
		return dataAltercacao;
	}

	public void setDataAltercacao(LocalDate dataAltercacao) {
		this.dataAltercacao = dataAltercacao;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
}