package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;

public class PosProvaEstado implements EstadoProva {

    @Override
    public void registrarPassagem(BoletimProva boletimProva, Integer prismaID, Duration tempo) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Não pode registrar prisma");
    }

    @Override
    public void registrarAtrasoPartida(BoletimProva boletimProva, Long minutoPartidaEfetivo) throws AtividadeNaoPermitidaException {
        boletimProva.minutoPartidaEfetivo = minutoPartidaEfetivo;
    }

    @Override
    public Long getMinutosAtraso(BoletimProva boletimProva) throws AtividadeNaoPermitidaException {
        return boletimProva.minutoPartidaEfetivo - boletimProva.minutoPartidaPrevisto;
    }

    @Override
    public void apresentarPraLargada(BoletimProva boletimProva) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Fase não permite se apresentar pra largada.");
    }

    @Override
    public void registrarLargada(BoletimProva boletimProva) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Fase não permite largar.");
    }

    @Override
    public void registrarChegada(BoletimProva boletimProva, Duration tempo) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Fase não permite registro de chegada.");
    }
    
}