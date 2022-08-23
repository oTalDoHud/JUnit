package br.ce.wcaquinho.servicos;

import br.ce.wcaquino.Exception.NaoPodeDividirPorZero;
import br.ce.wcaquino.entidades.Calculadora;
import org.junit.Assert;
import org.junit.Before;

public class CalculadoraTest {

    private Calculadora calc;

    @Before
    public void setUp(){
        calc = new Calculadora();
    }

//    @Test
    public void deveSomarDoisValores(){
        //cenario
        int a = 5;
        int b = 3;

        //ação
        int result = calc.soma(a, b);

        //verificação
        Assert.assertEquals(8, result);
    }

//    @Test
    public void deverSubtrairDoisValores(){
        //cenario
        int a = 10;
        int b = 5;

        //ação
        int result = calc.subtrair(a, b);

        //verificação
        Assert.assertEquals(5, result);
    }

//    @Test
    public void deveDividirDoisValores() throws NaoPodeDividirPorZero {
        //cenario
        int a = 10;
        int b = 2;

        //ação
        int result = calc.divisao(a, b);

        //verificação
        Assert.assertEquals(5, result);
    }

//    @Test(expected = NaoPodeDividirPorZero.class)
    public void deveLançarExecaoAoDividirPorZero() throws NaoPodeDividirPorZero {
        //cenario
        int a = 10;
        int b = 0;

        //ação
        int result = calc.divisao(a, b);

        //verificação
        Assert.assertEquals(5, result);
    }


}
