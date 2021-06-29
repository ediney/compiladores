# compiladores

###### *Atividades desenvolvidas e apresentadas no dia 02 de abril de 2015 para a disciplina Compiladores II.*



### Primeira atividade de Compiladores II

Implementar um analisador léxico para reconhecer/identificar os tokens da LALG.

Entrada: um programa escrito na linguagem LALG (programa fonte)

Saída:

a) uma lista contendo os pares <token, classe a qual pertence>, na sequência que aparecem no programa fonte;

b) já deve ter sido projetada a tabela de símbolos e nela inseridos os identificadores encontrados pelo léxico.



### Segunda atividade de Compiladores II

Implementar um analisador sintático descendente recursivo para a LALG.

**Entrada:** a lista de pares montada em 1.

**Saída:**

a) a análise sintática concluída ou erros, se houverem, nas respectivas linhas, indicando o/os ponto/s nos quais foram identificados problemas.

b) a tabela de símbolos acrescida com os tipos dos identificadores, os escopos aos quais estão vinculados, a quantidade e os tipos de parâmetros vinculados a cada subrotina, além da ordem nas quais apareceram na declaração dos parâmetros. Obs.: identificadores são variáveis, nomes de subrotinas e parâmetros. Você deve diferenciá-los de acordo com sua categoria na tabela de símbolos, e trata-los como tal. Por exemplo, nome de subrotina não tem tipo, uma vez que a LALG só trabalha com procedimentos, parâmetros, são sempre locais às suas subrotinas.



### Terceira atividade de Compiladores II

1. Implementar um verificador de tipos para a LALG, seguindo o esquema de tradução dirigida pela sintaxe.

Na verificação de tipos deverá ser checado se tipos usados em expressões, atribuições são compatíveis entre si (compatíveis, aqui nosso caso é se são iguais). Essa verificação é estática e é feita através de consulta à tabela de símbolos procurando pelos tipos das variáveis armazenadas naquele escopo ou globais. Sempre lembrando que o escopo local tem prioridade sobre o global no caso de dupla declaração. Deverá ser feito também a checagem da quantidade e compatibilidade de tipos entre parâmetros formais e reais.

2. Apresentar a tabela de símbolos com todas as informações sobre identificadores.



### Quarta atividade de Compiladores II

Gerar um código intermediário baseado na MEPA para o programa fonte baseado na tradução dirigida pela sintaxe que você propôs.



### Quinta atividade de Compiladores II

Implementar a maquina de execução hipotética para o código MEPA gerado.



### Dependências

- Java JDK 1.6 

### Código para a LALG

program teste
var a,b:real;

procedure teste(a:real, b:real)
	var lb:real;
	var la: real;
        la=a;
	lb=b;
	write ((la+lb));
end;

begin
   teste(1,2)
end.

program exemplo2
	{exemplo2}
	var a: real;
	var b:integer;
	procedure nomep(x:real)
	  var a,c:integer;
	  begin
	    read(c,a);
	    if a < x + c then
	       a:= c+x;
               write(a)
	    else c:=a+x
	    $
end
begin{programa principal}
	read(b);
	nomep(b)
end.
