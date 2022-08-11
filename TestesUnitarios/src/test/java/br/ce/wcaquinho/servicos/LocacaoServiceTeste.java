package br.ce.wcaquinho.servicos;


import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

public class LocacaoServiceTeste {

    //Usando o ErrorCollector ele faz todos os teste mesmo que o primeir falhe
    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testeLocacao() throws Exception {
        //cenario
        LocacaoService ls = new LocacaoService();
        Usuario usuario = new Usuario("Hudson user");
        Filme filme = new Filme("Senhor dos aneis", 2, 5.0);

        //ação
        Locacao locacao = null;
        locacao = ls.alugarFilme(usuario, filme);


        //verificação
        Assert.assertTrue(locacao.getUsuario().getNome().equals(usuario.getNome()));
        Assert.assertEquals(locacao.getUsuario().getNome(), usuario.getNome());

        Assert.assertEquals(5.0, locacao.getValor(), 0.1);
        errorCollector.checkThat(locacao.getValor(), CoreMatchers.is(5.0));

        errorCollector.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.dataHoje()), CoreMatchers.is(true));
        errorCollector.checkThat(DataUtils.isMesmaData(
                DataUtils.adicionarDias(locacao.getDataLocacao(), 1), DataUtils.adicionarDias(DataUtils.dataHoje(), 1)), CoreMatchers.is(true));


    }

    //Nesse vc informa qual exceção está esperando, bem elegante
    @Test(expected = FilmeSemEstoqueException.class)
    public void testeLocacaoFilemSemEstoque() throws Exception {
        //cenario
        LocacaoService ls = new LocacaoService();
        Usuario usuario = new Usuario("Hudson user");
        Filme filme = new Filme("Senhor dos aneis", 0, 5.0);

        //ação
        Locacao locacao = null;
        locacao = ls.alugarFilme(usuario, filme);
        //forma elegante
    }

    @Test
    public void testeLocacaoFilemSemEstoque2() {
        //cenario
        LocacaoService ls = new LocacaoService();
        Usuario usuario = new Usuario("Hudson user");
        Filme filme = new Filme("Senhor dos aneis", 0, 5.0);

        //ação
        Locacao locacao = null;
        try {
            locacao = ls.alugarFilme(usuario, filme);
            Assert.fail("Deveria ter lançãdo a exceção");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Filme fora de estoque"));
        }
        //forma robusta
    }

    @Test
    public void testeLocacaoFilemSemEstoque3() throws Exception {
        //cenario
        LocacaoService ls = new LocacaoService();
        Usuario usuario = new Usuario("Hudson user");
        Filme filme = new Filme("Senhor dos aneis", 0, 5.0);
        expectedException.expect(Exception.class);

        //ação
        Locacao locacao = null;
        locacao = ls.alugarFilme(usuario, filme);

        //validação
        expectedException.expectMessage("Filme fora de estoque");
        //forma nova
    }


    @Test
    public void testeLocacaoUsuarioVazio() throws FilmeSemEstoqueException {
        //cenario
        LocacaoService ls = new LocacaoService();
        Filme filme = new Filme("Senhor dos aneis", 1, 5.0);

        //ação
        try {
            ls.alugarFilme(null, filme);
            Assert.fail();
        } catch (LocadoraException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Usuário nulo"));
        }
        //forma robusta
    }

    @Test
    public void testeLocacaoFilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
        //cenario
        LocacaoService ls = new LocacaoService();
        Usuario usuario = new Usuario("Hudson user");
        Filme filme = new Filme("Senhor dos aneis", 1, 5.0);
        expectedException.expect(LocadoraException.class);

        //ação
        ls.alugarFilme(usuario, null);

        expectedException.expectMessage("filme nulo");

        //forma nova
    }

}
