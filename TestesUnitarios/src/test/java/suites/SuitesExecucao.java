package suites;

import br.ce.wcaquinho.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquinho.servicos.LocacaoServiceTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculoValorLocacaoTest.class,
        LocacaoServiceTest.class
})
public class SuitesExecucao {
    //remova se puder!

    @BeforeClass
    public static void before(){
        System.out.println("Before");
    }

    @AfterClass
    public static void after(){
        System.out.println("After");
    }
}
