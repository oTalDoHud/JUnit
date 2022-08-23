package br.ce.wcaquinho.servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

    private LocacaoService ls;
    @Parameterized.Parameter
    public List<Filme> filmeList;
    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;
    @Parameterized.Parameter(value = 2)
    public String nomeDaLista;

    @Before
    public void setUp() {
        ls = new LocacaoService();
    }

    private static Filme filme1 = new Filme("Filme 1", 2, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
    private static Filme filme7 = new Filme("Filme 7", 2, 4.0);


    @Parameterized.Parameters(name = "{2} -  valor total R$:{1}.00")
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme1, filme2), 8.00, "2 filmes: sem desconto"},
                {Arrays.asList(filme1, filme2, filme3), 11.00, "3 filmes: 25% de desconto no 3º"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.00, "4 filmes: 50% de desconto no 4º"},
                {Arrays.asList(filme1, filme2, filme3, filme3, filme5), 14.00, "5 filmes: 75% de desconto no 5º"},
                {Arrays.asList(filme1, filme2, filme3, filme3, filme5, filme6), 14.00, "6 filmes: 100% de desconto no 6º"},
                {Arrays.asList(filme1, filme2, filme3, filme3, filme5, filme6, filme7), 18.00, "7 filmes: 100% sem desconto no  no 7º"},
        });
    }

    @Test
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");

        //ação
        Locacao resultado = ls.alugarFilme(usuario, filmeList);

        //verificação
        assertThat(resultado.getValor(), is(valorLocacao));

        System.out.println("!");
    }
}
