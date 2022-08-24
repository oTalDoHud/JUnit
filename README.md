# O que é JUnit?

JUnit é uma ferramenta muito poderosa de grande abrangência no mercado que pode verificar se cada unidade de código funciona da forma esperada;
Facilita a criação, execução automática de testes e a apresentação dos resultados.
É Orientado a Objeto, gratuita e pode ser baixado em: *www.junit.org*.

# Sobre o projeto

JUnit locadora de filmes é uma aplicação back end focado em aprender o princípios do Test-driven development (Desenvolvimento Orientada a Testes) e utilizar o JUnit da forma forma possível garantindo uma aplicação que entrega o que promete.

A aplicação consiste em diversos cenários de aluguel de filmes que o JUnit deve garantir que o funcionamento do código atende as regras própostas.

## Primeiro Cenário

### COMPORTAMENTE ESPERADO
Deve haver descontos progressivos da seguinte forma:
 - O __3º__ filme deve ter desconto de __25%__ em seu valor
 - O __4º__ filme deve ter desconto de __50%__ em seu valor
 - O __5º__ filme deve ter desconto de __75%__ em seu valor
 - O __6º__ filme deve ter desconto de __100%__ em seu valor
 - O __7º__ filme não tera desconto

As regras acima foi testada utilizando o JUnit da seguinte forma:
 
![Funcionamento do código - Primeiro Cenário](https://github.com/oTalDoHud/Junit/blob/main/assets/CalculoValorLocacao.png)

## Segundo Cenário

### COMPORTAMENTE ESPERADO
Nesse segundo cenário outras regras foram colocadas há teste:
 - __DEVE__ solicitar devolução do filme alugado na segunda e não aos domingos
 - __DEVE__ permiter alugar filme
 - __DEVE__ lançar exceção a alugar filme sem estoque
 - __NÃO DEVE__ permiter aluguel quando o filme for nulo
 - __NÃO DEVE__ permiter aluguel quando o usuario for nulo
 - __NÃO DEVE__ permiter aluguel quando o filme for nulo
 - __NÃO DEVE__ solicitar devolução aos domingos

As regras acima foi testada utilizando o JUnit da seguinte forma:

![Funcionamento do código - Primeiro Cenário](https://github.com/oTalDoHud/Junit/blob/main/assets/SegundoCenario.png)


# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- Maven
- JUnit 5

# Autor

Hudson Lucas Teles Vieira

www.linkedin.com/in/otaldohud

hudson.lucas.vieira@gmail.com
