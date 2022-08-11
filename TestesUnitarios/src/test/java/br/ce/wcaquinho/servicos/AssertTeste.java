package br.ce.wcaquinho.servicos;

import static org.junit.Assert.assertTrue;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTeste {

    @Test
    public void teste() {
        Assert.assertTrue(true);

        Assert.assertFalse(false);

        Assert.assertEquals(1, 1);

        //o Delta é necessario para que ele tenha uma margem de erro
        // e considere apenas a quantidade necessaria de número depois da virgula
        Assert.assertEquals(0.52, 0.52, 0.02);

        //Isso é para comprar tipos objeto com tipo primitivos
        int primitivo = 1;
        Integer objeto = 1;
        Assert.assertEquals(Integer.valueOf(primitivo), objeto);
        Assert.assertEquals(primitivo, objeto.intValue());

        //Métodos uteis com String
        Assert.assertEquals("Bola", "Bola");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("Bola".startsWith("Bo"));

        //Comparaç]ao de objetos
        //A comparação só vai dar certo se houver um Equals dentro do objeto
        Usuario user1 = new Usuario("Usuario 1");
        Usuario user2 = new Usuario("Usuario 1");
        Usuario user3 = user2;
        //Compara o Equals entre os objetos
        Assert.assertEquals(user1, user2);
        //Compara a instancia dos objetos
//		Assert.assertSame(user1, user2);
        Assert.assertSame(user3, user2);
		Assert.assertNotSame(user1, user2);

		//verificar se é nulo
		Usuario user4 = null;
		Assert.assertNotNull(user2);
		Assert.assertNull(user4);

		//Você pode passar uma String de declaração, ela só vai aparecer quando aquele teste falhar
//		Assert.assertEquals("Erro de comparação entra números inteiros",2,1);



	}
}
