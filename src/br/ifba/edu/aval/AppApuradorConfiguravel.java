package br.ifba.edu.aval;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Apurador;
import br.ifba.edu.aval.model.BoletimProva;
import br.ifba.edu.aval.model.RegraApuracao;
import br.ifba.edu.aval.model.RegraRegistroChegada;
import br.ifba.edu.aval.model.RegraRegistroCompleto;
import br.ifba.edu.aval.model.RegraAtrasoPartida;
import br.ifba.edu.aval.model.RegraOrdemPrismas;
import br.ifba.edu.aval.model.RegraTempoMaximo;

public class AppApuradorConfiguravel extends AppAvaliacaoBase {

    public AppApuradorConfiguravel() {
        super();
    }

    @Override
    public void aval() throws AtividadeNaoPermitidaException {
        this.demonstrarConfiguracoes();
    }

    public void demonstrarConfiguracoes() {
        System.out.println("=== DEMONSTRAÇÃO DE APURADOR CONFIGURÁVEL ===");
        
        this.makeBoletinsProva();
        
        try {
            this.runAtleta4Aval3();
            
            // Configuração 1: Apurador Completo (todas as regras)
            this.testarApuradorCompleto();
            
            // Configuração 2: Prova sem limite de tempo
            this.testarProvaSemTempoLimite();
            
            // Configuração 3: Prova sem ordem obrigatória de prismas
            this.testarProvaSemOrdem();
            
            // Configuração 4: Prova sem penalização por atraso
            this.testarProvaSemPenalizacao();
            
        } catch (AtividadeNaoPermitidaException e) {
            System.err.println("Erro na configuração: " + e.getMessage());
        }
    }
    
    private void testarApuradorCompleto() {
        System.out.println("\n1. APURADOR COMPLETO (todas as regras):");
        
        Apurador apuradorCompleto = new Apurador(Duration.ofMinutes(120));
        
        this.mostrarRegrasAtivas(apuradorCompleto);
        this.testarApurador(apuradorCompleto, "Completo");
    }
    
    private void testarProvaSemTempoLimite() {
        System.out.println("\n2. PROVA SEM TEMPO LIMITE:");
        
        List<RegraApuracao> regras = Arrays.asList(
            new RegraRegistroChegada(),
            // Sem RegraTempoMaximo
            new RegraOrdemPrismas(),
            new RegraRegistroCompleto(),
            new RegraAtrasoPartida()
        );
        
        Apurador apuradorSemTempo = new Apurador(regras);
        
        this.mostrarRegrasAtivas(apuradorSemTempo);
        this.testarApurador(apuradorSemTempo, "Sem Tempo Limite");
    }
    
    private void testarProvaSemOrdem() {
        System.out.println("\n3. PROVA SEM ORDEM OBRIGATÓRIA:");
        
        List<RegraApuracao> regras = Arrays.asList(
            new RegraRegistroChegada(),
            new RegraTempoMaximo(Duration.ofMinutes(120)),
            // Sem RegraOrdemPrismas
            new RegraRegistroCompleto(),
            new RegraAtrasoPartida()
        );
        
        Apurador apuradorSemOrdem = new Apurador(regras);
        
        this.mostrarRegrasAtivas(apuradorSemOrdem);
        this.testarApurador(apuradorSemOrdem, "Sem Ordem");
    }
    
    private void testarProvaSemPenalizacao() {
        System.out.println("\n4. PROVA SEM PENALIZAÇÃO POR ATRASO:");
        
        List<RegraApuracao> regras = Arrays.asList(
            new RegraRegistroChegada(),
            new RegraTempoMaximo(Duration.ofMinutes(120)),
            new RegraOrdemPrismas(),
            new RegraRegistroCompleto()
            // Sem RegraAtrasoPartida
        );
        
        Apurador apuradorSemPenalizacao = new Apurador(regras);
        
        this.mostrarRegrasAtivas(apuradorSemPenalizacao);
        this.testarApurador(apuradorSemPenalizacao, "Sem Penalização");
    }
    
    private void mostrarRegrasAtivas(Apurador apurador) {
        System.out.println("   Regras ativas:");
        for (RegraApuracao regra : apurador.getRegras()) {
            System.out.println("   - " + regra.getNomeRegra());
        }
    }
    
    private void testarApurador(Apurador apurador, String tipo) {
        try {
            Duration resultado = apurador.apurar(this.atleta4);
            System.out.println("   Resultado (" + tipo + "): " + resultado);
        } catch (DNFException e) {
            System.out.println("   DNF (" + tipo + "): " + e.getMessage());
        } catch (AtividadeNaoPermitidaException e) {
            System.out.println("   Erro (" + tipo + "): " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new AppApuradorConfiguravel().demonstrarConfiguracoes();
    }
}