package br.ce.wcaquinho.servicos;


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

public class LocacaoServiceTeste {

	//Usando o ErrorCollector ele faz todos os teste mesmo que o primeir falhe
	@Rule
	public ErrorCollector errorCollector = new ErrorCollector();
	
	@Test
	public void testeLocacao() {
		//cenario 
		LocacaoService ls = new LocacaoService();
		Usuario usuario = new Usuario("Hudson user");
		Filme filme = new Filme("Senhor dos aneis", 2, 5.0);
		
		
		//ação
		Locacao locacao = ls.alugarFilme(usuario, filme);
		
		//verificação

		Assert.assertTrue(locacao.getUsuario().getNome().equals(usuario.getNome()));
		Assert.assertEquals(locacao.getUsuario().getNome(), usuario.getNome());

		Assert.assertEquals(5.0, locacao.getValor(), 0.1);
		errorCollector.checkThat(locacao.getValor(), CoreMatchers.is(5.0));

		errorCollector.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.dataHoje()), CoreMatchers.is(true) );
		errorCollector.checkThat(DataUtils.isMesmaData(
				DataUtils.adicionarDias(locacao.getDataLocacao(), 1), DataUtils.adicionarDias(DataUtils.dataHoje(), 1)), CoreMatchers.is(true));

		
	}

}
