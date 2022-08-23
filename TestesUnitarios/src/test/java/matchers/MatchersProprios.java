package matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static ComparacaoComNumeroDeDiasMatcher ehHoje(){
        return new ComparacaoComNumeroDeDiasMatcher(0);
    }

    public static ComparacaoComNumeroDeDiasMatcher ehHojeComDiferencaDias(Integer quantidadeDIas){
        return new ComparacaoComNumeroDeDiasMatcher(quantidadeDIas);
    }


}
