package suites;

import br.ce.wcaquinho.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquinho.servicos.LocacaoServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculoValorLocacaoTest.class,
        LocacaoServiceTest.class
})
public class SuitesExecucao {

}
