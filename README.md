# Relatório de Implementação - Padrões de Projeto

**Disciplina:** Padrões de Projetos  
**Sistema:** Gerenciamento de Competições de Corrida de Orientação

---

## 📋 Resumo

Este relatório apresenta a implementação de dois padrões de projeto para resolver problemas específicos identificados no sistema de corrida de orientação:

- **Questão I:** State Pattern para gerenciar comportamentos dependentes de estado no `BoletimProva`
- **Questão II:** Strategy Pattern para configurabilidade de regras no `Apurador`

---

## 🎯 Questão I - State Pattern

### Problema Identificado

A classe `BoletimProva` utilizava condicionais hardcoded (`if/else`) baseadas numa variável `fase` para controlar comportamentos específicos das quatro fases da prova, violando princípios SOLID.

### Solução: State Pattern

#### Justificativa Técnica (State)

1. **Comportamento dependente de estado:** O objeto precisa alterar comportamento conforme seu estado interno
2. **Eliminação de condicionais complexas:** Substitui estruturas `if/else` por polimorfismo
3. **Extensibilidade:** Novos estados podem ser adicionados sem modificar código existente

#### Estrutura do State Pattern

**Participantes:**

- **Context:** `BoletimProva` - mantém referência ao estado atual
- **State:** `EstadoProva` (interface) - define operações dependentes de estado  
- **ConcreteStates:** `PreProvaEstado`, `MomentoLargadaEstado`, `PistaEstado`, `PosProvaEstado`

**Estrutura de Transições:**

```text
PreProvaEstado → MomentoLargadaEstado → PistaEstado → PosProvaEstado
```

#### Resultados Obtidos

- ✅ Eliminação de 6 métodos com lógica condicional complexa
- ✅ Encapsulamento de regras por estado
- ✅ Validação correta: Atleta3 rejeitado por tentar registrar prismas em PRE_PROVA

**Código Refatorado (Exemplo):**

```java
// ANTES
public void registrar(Integer prismaID, Duration tempo) throws AtividadeNaoPermitidaException {
    if(this.fase != BoletimProva.PISTA)
        throw new AtividadeNaoPermitidaException("Não pode registrar prisma");
    this.passagens.registrarPassagem(prismaID, tempo);
}

// DEPOIS  
public void registrar(Integer prismaID, Duration tempo) throws AtividadeNaoPermitidaException {
    this.estado.registrarPassagem(this, prismaID, tempo);
}
```

---

## 🎯 Questão II - Strategy Pattern

### Problema Identificado

A classe `Apurador` possuía regras de validação hardcoded, impossibilitando configuração para diferentes tipos de prova (com/sem tempo limite, ordem obrigatória, etc.).

### Solução: Strategy Pattern

#### Justificativa Técnica (Strategy)

1. **Algoritmos intercambiáveis:** Diferentes regras podem ser aplicadas conforme o contexto
2. **Configurabilidade runtime:** Regras podem ser adicionadas/removidas dinamicamente
3. **Single Responsibility:** Cada regra encapsula uma única responsabilidade

#### Estrutura do Strategy Pattern

**Participantes:**

- **Strategy:** `RegraApuracao` (interface) - define algoritmo de validação
- **ConcreteStrategies:** 5 regras implementadas
- **Context:** `Apurador` - aplica conjunto configurável de regras

**Regras Implementadas:**

1. `RegraRegistroChegada` - Validação obrigatória de chegada
2. `RegraTempoMaximo` - Verificação de tempo limite (configurável)
3. `RegraOrdemPrismas` - Validação de ordem cronológica (configurável)  
4. `RegraRegistroCompleto` - Verificação de prismas registrados
5. `RegraAtrasoPartida` - Penalização por atraso (configurável)

#### Resultados Obtidos

**Demonstração de Configurabilidade:**

| Configuração | Resultado | Diferença |
|--------------|-----------|-----------|
| Todas as regras | PT7M5S | Tempo completo |
| Sem penalização | PT6M5S | Sem atraso |
| Sem tempo limite | PT7M5S | Sem verificação |
| Sem ordem | PT7M5S | Ordem flexível |

**Código Refatorado (Exemplo):**

```java
// ANTES - Método monolítico com 25+ linhas
public Duration apurar(BoletimProva boletim) throws DNFException {
    // 5 regras hardcoded em sequência...
}

// DEPOIS - Configurável e extensível
public Duration apurar(BoletimProva boletim) throws DNFException {
    Duration tempoFinal = Duration.ZERO;
    for (RegraApuracao regra : regras) {
        tempoFinal = regra.aplicarRegra(boletim, tempoFinal);
    }
    return tempoFinal;
}
```

---

## 📊 Arquivos Modificados/Criados

### Questão I (State Pattern)

**Criados:**

- `EstadoProva.java` (interface)
- `PreProvaEstado.java`, `MomentoLargadaEstado.java`, `PistaEstado.java`, `PosProvaEstado.java`

**Modificados:**

- `BoletimProva.java` - substituição de lógica condicional por delegação

### Questão II (Strategy Pattern)

**Criados:**

- `RegraApuracao.java` (interface)
- `RegraRegistroChegada.java`, `RegraTempoMaximo.java`, `RegraOrdemPrismas.java`, `RegraRegistroCompleto.java`, `RegraAtrasoPartida.java`
- `AppApuradorConfiguravel.java` (demonstração)

**Modificados:**

- `Apurador.java` - refatoração para uso de regras configuráveis

---

## 🏆 Benefícios Alcançados

### Técnicos

- **Manutenibilidade:** Código mais limpo e organizado
- **Extensibilidade:** Novos estados/regras facilmente adicionáveis  
- **Testabilidade:** Componentes testáveis independentemente
- **Flexibilidade:** Sistema adaptável para diferentes modalidades

### Conformidade SOLID

- **SRP:** Cada classe/regra tem responsabilidade única
- **OCP:** Aberto para extensão, fechado para modificação
- **DIP:** Dependência de abstrações, não implementações

---

## ✅ Validação dos Resultados

- ✅ **Compatibilidade:** Comportamento original mantido
- ✅ **Configurabilidade:** Demonstrada com 4 configurações diferentes
