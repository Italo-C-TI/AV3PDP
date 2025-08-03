package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public class RegraAtrasoPartida implements RegraApuracao {

    @Override
    public Duration aplicarRegra(BoletimProva boletim, Duration tempoAtual) throws DNFException, AtividadeNaoPermitidaException {
        Long minutosAtraso = boletim.getMinutosAtraso();

        return tempoAtual.plus(Duration.ofMinutes(minutosAtraso));
    }

    @Override
    public String getNomeRegra() {
        return "Penalização por Atraso na Partida";
    }
    
}