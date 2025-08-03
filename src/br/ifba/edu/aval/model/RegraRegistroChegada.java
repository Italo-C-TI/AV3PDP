package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public class RegraRegistroChegada implements RegraApuracao {

    @Override
    public Duration aplicarRegra(BoletimProva boletim, Duration tempoAtual) throws DNFException, AtividadeNaoPermitidaException {
        Duration tempoChegada = boletim.getTempo(Prisma.CHEGADA);
        
        if (tempoChegada == null) {
            throw new DNFException("Atleta não registrou chegada");
        }
        

        return tempoChegada;
    }

    @Override
    public String getNomeRegra() {
        return "Registro de Chegada";
    }
    
}