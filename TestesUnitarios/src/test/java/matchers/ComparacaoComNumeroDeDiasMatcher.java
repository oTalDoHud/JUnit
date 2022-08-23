package matchers;

import br.ce.wcaquino.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class ComparacaoComNumeroDeDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer quantidadeDias;

    public ComparacaoComNumeroDeDiasMatcher(Integer quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }

    @Override
    public void describeTo(Description description) {

    }

    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quantidadeDias));
    }
}
