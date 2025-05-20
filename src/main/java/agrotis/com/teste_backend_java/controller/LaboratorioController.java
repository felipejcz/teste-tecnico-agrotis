package agrotis.com.teste_backend_java.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import agrotis.com.teste_backend_java.controller.dto.BuscarLaboratorioDto;
import agrotis.com.teste_backend_java.entity.Pessoa;
import agrotis.com.teste_backend_java.service.LaboratorioService;

@Controller
@RequestMapping("/laboratorios")
public class LaboratorioController {
    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<BuscarLaboratorioDto>> buscarLaboratorios(
            @RequestParam(required = false) String dataInicialInicio,
            @RequestParam(required = false) String dataInicialFim,
            @RequestParam(required = false) String dataFinalInicio,
            @RequestParam(required = false) String dataFinalFim,
            @RequestParam(required = false) String observacoes,
            @RequestParam Long quantidadeMinima) {
        var laboratorios = laboratorioService.buscarLaboratoriosComFiltro(
                Pessoa.convertToLocalDate(dataInicialInicio),
                Pessoa.convertToLocalDate(dataInicialFim),
                Pessoa.convertToLocalDate(dataFinalInicio),
                Pessoa.convertToLocalDate(dataFinalFim),
                observacoes,
                quantidadeMinima);
        return ResponseEntity.ok(laboratorios);
    }
}
