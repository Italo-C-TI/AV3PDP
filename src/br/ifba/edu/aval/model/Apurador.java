package br.ifba.edu.aval.model;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public class Apurador {
	
	private List<RegraApuracao> regras;
	
	public Apurador(List<RegraApuracao> regras) {
		this.regras = new ArrayList<>(regras);
	}
	
	public Apurador(Duration tempoMaximoProva) {
		this.regras = criarRegrasDefault(tempoMaximoProva);
	}
	
	private List<RegraApuracao> criarRegrasDefault(Duration tempoMaximo) {
		return Arrays.asList(
			new RegraRegistroChegada(),
			new RegraTempoMaximo(tempoMaximo),
			new RegraOrdemPrismas(),
			new RegraRegistroCompleto(),
			new RegraAtrasoPartida()
		);
	}
	
	public Duration apurar(BoletimProva boletim) throws DNFException, AtividadeNaoPermitidaException {
		Duration tempoFinal = Duration.ZERO;

		for (RegraApuracao regra : regras) {
			tempoFinal = regra.aplicarRegra(boletim, tempoFinal);
		}
		
		return tempoFinal;
	}
	
	public List<RegraApuracao> getRegras() {
		return new ArrayList<>(regras);
	}
	
	public void adicionarRegra(RegraApuracao regra) {
		this.regras.add(regra);
	}
	
	public boolean removerRegra(RegraApuracao regra) {
		return this.regras.remove(regra);
	}	
}
