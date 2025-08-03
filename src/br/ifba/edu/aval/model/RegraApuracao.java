package br.ifba.edu.aval.model;

import java.time.Duration;
import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;

public interface RegraApuracao {
    
    Duration aplicarRegra(BoletimProva boletim, Duration tempoAtual) throws DNFException, AtividadeNaoPermitidaException;
    
    String getNomeRegra();
    
}