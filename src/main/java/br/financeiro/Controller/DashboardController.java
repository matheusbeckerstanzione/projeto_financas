package br.financeiro.Controller;

import br.financeiro.DTO.DashboardVisaoGeralDTO;
import br.financeiro.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/visao-geral")
    public ResponseEntity<DashboardVisaoGeralDTO> obterVisaoGeral() {
        return ResponseEntity.ok(service.obterVisaoGeral());
    }
}