package br.ce.wcaquinho.servicos;


import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LocacaoServiceTest {

    private LocacaoService ls;
    private static Integer cont;
    //Usando o ErrorCollector ele faz todos os teste mesmo que o primeir falhe
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        ls = new LocacaoService();

        cont++;
        if (cont != 1) {
            System.out.println(cont + " testes foram feitos");
        } else {
            System.out.println(cont + " teste foi feito");
        }
    }

    @BeforeClass
    public static void setupClass() {
        cont = 0;
    }

    @Test
    public void deveAlugarFilme() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Hudson user");
        List<Filme> filmes = listSenhorDosAneis(3);

        //ação
        Locacao locacao = null;
        locacao = ls.alugarFilme(usuario, filmes);


        //verificação
        Assert.assertTrue(locacao.getUsuario().getNome().equals(usuario.getNome()));
        Assert.assertEquals(locacao.getUsuario().getNome(), usuario.getNome());

        Assert.assertEquals(12.25, locacao.getValor(), 0.1);
        errorCollector.checkThat(locacao.getValor(), is(12.25));

        errorCollector.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.dataHoje()), is(true));
        errorCollector.checkThat(DataUtils.isMesmaData(
                DataUtils.adicionarDias(locacao.getDataLocacao(), 1), DataUtils.adicionarDias(DataUtils.dataHoje(), 1)), is(true));
    }

    //Nesse vc informa qual exceção está esperando, bem elegante
    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLançarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 2", 0, 4.0);
        List<Filme> filmes = new ArrayList<Filme>();

        filmes.add(filme);

        //acao
        ls.alugarFilme(usuario, filmes);

        //ELEGANTE
    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
        //cenario
        Filme filme = new Filme("Filme 2", 1, 4.0);
        List<Filme> filmes = new ArrayList<Filme>();
        filmes.add(filme);

        //acao
        try {
            ls.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
        //ROBUSTA
    }

    @Test
    public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");

        expectedException.expect(LocadoraException.class);
        expectedException.expectMessage("Filme vazio");

        //acao
        ls.alugarFilme(usuario, null);
        //NOVA
    }

    @Test
    public void devePagar75pctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = listSenhorDosAneis(3);

        //ação
        for (Filme x : filmes) {
            x.setPrecoLocacao(4.0);
        }
        Locacao resultado = ls.alugarFilme(usuario, filmes);

        //verificação
        assertThat(resultado.getValor(), is(11.0));
    }

    @Test
    public void devePagar50pctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = listSenhorDosAneis(4);

        //ação
        for (Filme x : filmes) {
            x.setPrecoLocacao(4.0);
        }
        Locacao resultado = ls.alugarFilme(usuario, filmes);

        //verificação
        assertThat(resultado.getValor(), is(13.0));
    }

    @Test
    public void devePagar25pctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = listSenhorDosAneis(5);

        //ação
        for (Filme x : filmes) {
            x.setPrecoLocacao(4.0);
        }
        Locacao resultado = ls.alugarFilme(usuario, filmes);

        //verificação
        assertThat(resultado.getValor(), is(14.0));
    }

    @Test
    public void devePagar0pctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> filmes = listSenhorDosAneis(6);

        //ação
        for (Filme x : filmes) {
            x.setPrecoLocacao(4.0);
        }
        Locacao resultado = ls.alugarFilme(usuario, filmes);

        //verificação
        assertThat(resultado.getValor(), is(14.0));
    }

    public List<Filme> listSenhorDosAneis(int quantosFilmesQuer) {

        List<Filme> todosOsFilmes = new ArrayList<Filme>();
        Filme filme1 = new Filme("Senhor dos aneis - A sociedade do anel", 2, 5.0);
        Filme filme2 = new Filme("Senhor dos aneis - As duas torres", 3, 2.0);
        Filme filme3 = new Filme("Senhor dos aneis - O retorno", 5, 7.0);
        Filme filme4 = new Filme("O Hobbit", 23, 3.0);
        Filme filme5 = new Filme("O Hobbit - A desolação de Smaug", 12, 18.0);
        Filme filme6 = new Filme("O Hobbit - a batalha dos cinco exercitos", 13, 23.0);
        todosOsFilmes.add(filme1);
        todosOsFilmes.add(filme2);
        todosOsFilmes.add(filme3);
        todosOsFilmes.add(filme4);
        todosOsFilmes.add(filme5);
        todosOsFilmes.add(filme6);

        List<Filme> filmesQuantidadePedida = new ArrayList<Filme>();

        for (int i = 0; i < quantosFilmesQuer; i++) {
            filmesQuantidadePedida.add(todosOsFilmes.get(i));
        }

        return filmesQuantidadePedida;
    }
}
