SIMULADOR SIC/XE

OBSERVAÇÃO: ACESSEM A MEMÓRIA E OS REGISTRADORES PELA INSTÂNCIA DA MAIN!
OBS2: A CLASSE MAIN É O LIGADOR!

entrada_frontEnd (Artur)
É a interface. (No pdf sic sim tem uma interface de exemplo).

Maquina (2 pessoas)
É a execução do programa. Nesse momento a memória já está preenchida com a tabela de simbolos, e logo depois as instrucoes codificadas, portanto deve-se: ler a memoria e executar as instrucoes.
Deve-se ler a memória encontrando os opcodes (nesse ponto sabe-se quando bytes precisa-se ler). E classe de execucao de instrucoes.

Instrucoes (2 pessoa)
Classe de modelagem das instrucoes. E classe de enderecamento de instrucoes.

Memory (Vitor)
    Classe de modelagem da memoria principal e registradores.
    
    Registradores
        getRegName(String) = busca o registrador pelo nome e retorna o valor.
        getRegIndex(int) =  busca o registrador pelo index e retorna o valor.
        setRegs(String, int) = busca o registrador pelo nome, seta o valor int, e retorna true se conseguiu.

        Obs: para o registrador de numeros flutuantes, as chamadas de funcoes sao diferentes, mas fazem a mesma coisa.
        getRegNameF(String), getRegIndexF(int) e setRegsF(String, int).



Montador(Vitor, Lucas)
Recebe o arquivo .asm, monta tabelas, e preenche a memoria com a tabela.

Tradutor (Vini)
classe para conversão: decimal para binario, binario pra hexa, etc... (nao esqueça dos sinais)
