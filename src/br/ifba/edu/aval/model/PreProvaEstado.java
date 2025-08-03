package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;

public class PreProvaEstado implements EstadoProva {

    @Override
    public void registrarPassagem(BoletimProva boletimProva, Integer prismaID, Duration tempo) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Não pode registrar prisma");
    }

    @Override
    public void registrarAtrasoPartida(BoletimProva boletimProva, Long minutoPartidaEfetivo) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Não pode calcular minutos de atraso");
    }

    @Override
    public Long getMinutosAtraso(BoletimProva boletimProva) throws AtividadeNaoPermitidaException {
        throw new AtividadeNaoPermitidaException("Não pode calcular minutos de atraso");
    }

    @Override
    public void apresentarPraLargada(BoletimProva boletimProva) throws AtividadeNaoPermitidaException {
        boletimProva.setEstado(new MomentoLargadaEstado());
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