package agrotis.com.teste_backend_java.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import agrotis.com.teste_backend_java.controller.dto.BuscarLaboratorioDto;
import agrotis.com.teste_backend_java.repository.LaboratorioRepositoryImpl;

@Service
public class LaboratorioService {
    private final LaboratorioRepositoryImpl laboratorioRepositoryImpl;

    public LaboratorioService(LaboratorioRepositoryImpl laboratorioRepositoryImpl) {
        this.laboratorioRepositoryImpl = laboratorioRepositoryImpl;
    }

    public List<BuscarLaboratorioDto> buscarLaboratoriosComFiltro(
            LocalDate dataInicialInicio,
            LocalDate dataInicialFim,
            LocalDate dataFinalInicio,
            LocalDate dataFinalFim,
            String observacoes,
            Long quantidadeMinima) {
        return laboratorioRepositoryImpl.buscarLaboratoriosComFiltro(
                dataInicialInicio,
                dataInicialFim,
                dataFinalInicio,
                dataFinalFim,
                observacoes,
                quantidadeMinima);
    }

}