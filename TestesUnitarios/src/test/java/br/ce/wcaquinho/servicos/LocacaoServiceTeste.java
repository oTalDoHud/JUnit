package br.ce.wcaquinho.servicos;


import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTeste {

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
        if(cont != 1){
            System.out.println(cont + " testes foram feitos");
        }else {
            System.out.println(cont + " teste foi feito");
        }
    }

    @BeforeClass
    public static void setupClass() {
        cont = 0;
    }

    @Test
    public void testeLocacao() throws Exception {
        //cenario
        Usuario usuario = new Usuario("Hudson user");
        Filme filme = new Filme("Senhor dos aneis", 2, 5.0);

        //ação
        Locacao locacao = null;
        locacao = ls.alugarFilme(usuario, filme);


        //verificação
        Assert.assertTrue(locacao.getUsuario().getNome().equals(usuario.getNome()));
        Assert.assertEquals(locacao.getUsuario().getNome(), usuario.getNome());

        Assert.assertEquals(5.0, locacao.getValor(), 0.1);
        errorCollector.checkThat(locacao.getValor(), is(5.0));

        errorCollector.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.dataHoje()), is(true));
        errorCollector.checkThat(DataUtils.isMesmaData(
                DataUtils.adicionarDias(locacao.getDataLocacao(), 1), DataUtils.adicionarDias(DataUtils.dataHoje(), 1)), is(true));


    }

    //Nesse vc informa qual exceção está esperando, bem elegante
    @Test(expected = FilmeSemEstoqueException.class)
    public void testLocacao_filmeSemEstoque() throws Exception{
        //cenario
        Usuario usuario = new Usuario("Usuario 1");
        Filme filme = new Filme("Filme 2", 0, 4.0);

        //acao
        ls.alugarFilme(usuario, filme);
        //ELEGANTE
    }

    @Test
    public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{
        //cenario
        Filme filme = new Filme("Filme 2", 1, 4.0);

        //acao
        try {
            ls.alugarFilme(null, filme);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
        //ROBUSTA
    }

    @Test
    public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
        //cenario
        Usuario usuario = new Usuario("Usuario 1");

        expectedException.expect(LocadoraException.class);
        expectedException.expectMessage("Filme vazio");

        //acao
        ls.alugarFilme(usuario, null);
        //NOVA
    }
}
