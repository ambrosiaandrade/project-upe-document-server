package br.edu.upe.document.adapter.output;

import br.edu.upe.document.domain.port.output.OutputPortData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OutputAdapterData implements OutputPortData {

    // Mocked
    @Override
    public Map<String, String> getInsurancePolicyData(String campus) {
        System.out.println("getInsurancePolicyData: " + campus);
        Map<String, String> map = new HashMap<>();
        map.put("AS_NOME", "PEGAR DO BANCO");
        map.put("AS_NUMERO", "PEGAR DO BANCO");
        return map;
    }

}
