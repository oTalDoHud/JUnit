package br.ce.wcaquinho.servicos;


import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTeste {
	
	@Test
	public void teste() {
		//cenario 
		LocacaoService ls = new LocacaoService();
		Usuario usuario = new Usuario("Hudson user");
		Filme filme = new Filme("Senhor dos aneis", 2, 5.0);
		
		
		//ação
		Locacao locacao = ls.alugarFilme(usuario, filme);
		
		//verificação
		Assert.assertTrue(locacao.getUsuario().getNome().equals(usuario.getNome()));
		Assert.assertTrue(locacao.getValor().equals(5.0));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.dataHoje()) );
		Assert.assertTrue(DataUtils.isMesmaData(
				DataUtils.adicionarDias(locacao.getDataLocacao(), 1), DataUtils.adicionarDias(DataUtils.dataHoje(), 1)));

		
	}

}
