package br.ifba.edu.aval.model;

import java.time.Duration;
import java.util.List;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public class RegraRegistroCompleto implements RegraApuracao {

    @Override
    public Duration aplicarRegra(BoletimProva boletim, Duration tempoAtual) throws DNFException, AtividadeNaoPermitidaException {
        List<Integer> ordemPrismas = boletim.getOrdemPrismas();

        for(int i = 0; i < ordemPrismas.size() - 1; i++) {
            Duration tempo = boletim.getTempo(ordemPrismas.get(i));
            
            if(ordemPrismas.get(i) != Prisma.CHEGADA && tempo == null) {
                throw new DNFException("Atleta não registrou um dos prismas.");
            }
        }
        return tempoAtual;
    }

    @Override
    public String getNomeRegra() {
        return "Registro Completo dos Prismas";
    }
    
}