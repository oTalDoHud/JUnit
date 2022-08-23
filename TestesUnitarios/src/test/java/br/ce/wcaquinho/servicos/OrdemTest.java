package br.ce.wcaquinho.servicos;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Ordem alfabetica
//@FixMethodOrder(MethodSorters.JVM) //Ordem dos métodos
public class OrdemTest {

    public static Integer cont = 0;

//    @Test
    public void inicia1() {
        cont++;
    }

//    @Test
    public void inicia2() {
        cont++;
    }

//    @Test
    public void inicia3() {
        cont++;
    }

//    @Test
    public void inicia4() {
        cont++;
    }

//    @Test
    public void verifica() {
        Assert.assertThat(cont, CoreMatchers.is(4));
    }

    //Primeira possível solução - não muito legal.
//    @Test
//    public void testGeral(){
//        inicia();
//        verifica();
//    }
}
