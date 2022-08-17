package br.ce.wcaquino.servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;
import static br.ce.wcaquino.utils.DataUtils.verificarDiaSemana;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
        if (usuario == null) {
            throw new LocadoraException("Usuario vazio");
        }

        if (filmes == null || filmes.isEmpty()) {
            throw new LocadoraException("Filme vazio");
        }

        Double valorTotal = 0.0;

        for (Filme x : filmes) {
            if (x.getEstoque() == 0) {
                throw new FilmeSemEstoqueException();
            }
        }

        for (int i = 0; i < filmes.size(); i++) {
            Filme filme = filmes.get(i);
            double valorFilme = filme.getPrecoLocacao();

            switch (i) {
                case 2:
                    valorFilme = valorFilme * 0.75;
                    break;
                case 3:
                    valorFilme = valorFilme * 0.5;
                    break;
                case 4:
                    valorFilme = valorFilme * 0.25;
                    break;
                case 5:
                    valorFilme = 0d;
                    break;
            }

            valorTotal += valorFilme;
        }

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if (verificarDiaSemana(dataEntrega, Calendar.SUNDAY)){
            dataEntrega = adicionarDias(dataEntrega, 1);
        }

        Locacao locacao = new Locacao(usuario, filmes, new Date(), dataEntrega, valorTotal);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }
}