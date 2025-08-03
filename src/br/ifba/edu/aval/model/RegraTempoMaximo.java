package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public class RegraTempoMaximo implements RegraApuracao {
    
    private Duration tempoMaximo;
    
    public RegraTempoMaximo(Duration tempoMaximo) {
        this.tempoMaximo = tempoMaximo;
    }

    @Override
    public Duration aplicarRegra(BoletimProva boletim, Duration tempoAtual) throws DNFException, AtividadeNaoPermitidaException {
        if (tempoAtual.compareTo(this.tempoMaximo) > 0) {
            throw new DNFException("O atleta finalizou a prova, após o tempo limite");
        }

        return tempoAtual;
    }

    @Override
    public String getNomeRegra() {
        return "Tempo Máximo (" + tempoMaximo + ")";
    }
    
}