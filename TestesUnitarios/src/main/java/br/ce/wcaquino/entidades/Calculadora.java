package br.ce.wcaquino.entidades;

import br.ce.wcaquino.Exception.NaoPodeDividirPorZero;

public class Calculadora {

    public int soma(int a, int b) {
        return a + b;
    }

    public int subtrair(int a, int b) {
        return a - b;
    }

    public int divisao(int a, int b) throws NaoPodeDividirPorZero {
        if(b == 0){
            throw new NaoPodeDividirPorZero("Erro! Impossível dividir por zero");
        }
        return a / b;
    }
}
