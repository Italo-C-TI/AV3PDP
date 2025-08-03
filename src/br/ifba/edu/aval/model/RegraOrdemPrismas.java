package br.ifba.edu.aval.model;

import java.time.Duration;
import java.util.List;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public class RegraOrdemPrismas implements RegraApuracao {

    @Override
    public Duration aplicarRegra(BoletimProva boletim, Duration tempoAtual) throws DNFException, AtividadeNaoPermitidaException {
        List<Integer> ordemPrismas = boletim.getOrdemPrismas();

        for(int i = 0; i < ordemPrismas.size() - 1; i++) {
            Duration tempoAnterior = boletim.getTempo(ordemPrismas.get(i));
            Duration tempoAtualPrisma = boletim.getTempo(ordemPrismas.get(i + 1));
            
            if(tempoAnterior != null && tempoAtualPrisma != null) {
                if(tempoAnterior.compareTo(tempoAtualPrisma) > 0) {
                    throw new DNFException("Atleta registrou prisma fora da ordem");
                }
            }
        }
        return tempoAtual;
    }

    @Override
    public String getNomeRegra() {
        return "Ordem dos Prismas";
    }
    
}