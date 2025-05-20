package agrotis.com.teste_backend_java.repository;

import java.time.LocalDate;
import java.util.List;

public interface LaboratorioRepositoryCustom {
    List<?> buscarLaboratoriosComFiltro(
            LocalDate dataInicialInicio,
            LocalDate dataInicialFim,
            LocalDate dataFinalInicio,
            LocalDate dataFinalFim,
            String observacoes,
            Long quantidadeMinima);
}
