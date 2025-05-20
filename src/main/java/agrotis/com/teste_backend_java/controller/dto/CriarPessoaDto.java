package agrotis.com.teste_backend_java.controller.dto;

import agrotis.com.teste_backend_java.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarPessoaDto(@NotBlank String nome, @NotBlank String dataInicial, @NotBlank String dataFinal,
        String observacoes, @NotNull CriarPropriedadeDto infosPropriedade, @NotNull CriarLaboratorioDto laboratorio) {

    public Pessoa toEntity() {
        return new Pessoa(nome, dataInicial, dataFinal, observacoes, infosPropriedade.toEntity(), laboratorio.toEntity());
    }

}
