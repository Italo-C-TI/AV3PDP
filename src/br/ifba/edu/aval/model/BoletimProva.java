package br.ifba.edu.aval.model;

import java.time.Duration;
import java.util.List;

import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval1.prototype.ListaPassagens;

public class BoletimProva {

	public static final Integer PRE_PROVA = 0;
	public static final Integer MOMENTO_LARGADA = 1;
	public static final Integer PISTA = 2;
	public static final Integer POS_PROVA = 3;
	
	public String cboNumero;
	public ListaPassagens passagens;
	public Long minutoPartidaPrevisto;
	public Long minutoPartidaEfetivo;
	
	private EstadoProva estado;
	
	
	public BoletimProva(String cboNumero, Long minutoPartidaPrevisto, ListaPassagens passagens) {
		super();
		this.cboNumero = cboNumero;
		this.passagens = passagens;
		this.minutoPartidaEfetivo = this.minutoPartidaPrevisto = minutoPartidaPrevisto;
		this.estado = new PreProvaEstado();
	}
	

	public List<Integer> getOrdemPrismas() {
		return this.passagens.getOrdemPassagem();
	}
	
	public String cboNumero() {
		return this.cboNumero;
	}
	
	public void setEstado(EstadoProva estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "BoletimProva [cboNumero=" + cboNumero + ", passagens=" + passagens + "]";
	}	
	
	public Duration getTempo(Integer prismaID) {
		return this.passagens.getTempo(prismaID);
	}
	
	public void registrar(Integer prismaID, Duration tempo) throws AtividadeNaoPermitidaException {
		this.estado.registrarPassagem(this, prismaID, tempo);
	}

	public void registrarAtrasoPartida(Long minutoPartidaEfetivo) throws AtividadeNaoPermitidaException {
		this.estado.registrarAtrasoPartida(this, minutoPartidaEfetivo);
	}
	
	public Long getMinutosAtraso() throws AtividadeNaoPermitidaException {
		return this.estado.getMinutosAtraso(this);
	}	
	
	public void apresentarPraLargada() throws AtividadeNaoPermitidaException {
		this.estado.apresentarPraLargada(this);
	}
	
	public void registrarLargada() throws AtividadeNaoPermitidaException {
		this.estado.registrarLargada(this);
	}

	public void registrarChegada(Duration tempo) throws AtividadeNaoPermitidaException {
		this.estado.registrarChegada(this, tempo);
	}	
	
	

}
