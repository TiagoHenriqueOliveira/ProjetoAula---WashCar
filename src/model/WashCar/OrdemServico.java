package model.WashCar;

import java.time.LocalDate;

public class OrdemServico {
	
	private Integer idOrdemServico;
	private LocalDate dataAgendamento;
	private LocalDate dataAlteracao;
	private Double valorTotal;
	private Boolean ordemServicoCancelada;
	private Cliente cliente;
	private Carro carro;
	
	//construtor com atributos
	public OrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}
	
	public OrdemServico() {

	}

	//gets e sets das variaveis
	public Integer getIdOrdemServico() {
		return idOrdemServico;
	}

	public void setIdOrdemServico(Integer idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Boolean isOrdemServicoCancelada() {
		return ordemServicoCancelada;
	}

	public void setOrdemServicoCancelada(Boolean ordemServicoCancelada) {
		this.ordemServicoCancelada = ordemServicoCancelada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
}