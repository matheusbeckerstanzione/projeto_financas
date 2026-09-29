package br.financeiro.Service;

import br.financeiro.DTO.DashboardVisaoGeralDTO;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    public DashboardVisaoGeralDTO obterVisaoGeral() {
        DashboardVisaoGeralDTO dto = new DashboardVisaoGeralDTO();
        return dto;
    }
}