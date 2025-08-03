package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;

public interface EstadoProva {
    
    void registrarPassagem(BoletimProva boletimProva, Integer prismaID, Duration tempo) throws AtividadeNaoPermitidaException;
    
    void registrarAtrasoPartida(BoletimProva boletimProva, Long minutoPartidaEfetivo) throws AtividadeNaoPermitidaException;
    
    Long getMinutosAtraso(BoletimProva boletimProva) throws AtividadeNaoPermitidaException;
    
    void apresentarPraLargada(BoletimProva boletimProva) throws AtividadeNaoPermitidaException;
    
    void registrarLargada(BoletimProva boletimProva) throws AtividadeNaoPermitidaException;
    
    void registrarChegada(BoletimProva boletimProva, Duration tempo) throws AtividadeNaoPermitidaException;
    
}