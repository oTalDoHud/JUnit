package br.ce.wcaquino.servicos;

import br.ce.wcaquino.Exception.FilmeSemEstoqueException;
import br.ce.wcaquino.Exception.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

import java.util.Date;
import java.util.List;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException {
        if (usuario == null) {
            throw new LocadoraException("Usuario vazio");
        }

        if (filmes == null) {
            throw new LocadoraException("Filme vazio");
        }

        Double totalValor = 0.0;

        if (filmes != null) {
            for (Filme x : filmes) {
                if (x.getEstoque() == 0) {
                    System.out.println("Disparou a excessão");
                    throw new FilmeSemEstoqueException();
                }
            }

            for (Filme x : filmes) {
                totalValor += x.getPrecoLocacao();
            }
        }



        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);

        Locacao locacao = new Locacao(usuario, filmes, new Date(), dataEntrega, totalValor);

        //Salvando a locacao...
        //TODO adicionar método para salvar

        return locacao;
    }
}