package agrotis.com.teste_backend_java.controller.dto;

import agrotis.com.teste_backend_java.entity.Laboratorio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarLaboratorioDto(@NotNull Long id, @NotBlank String nome) {

    public Laboratorio toEntity() {
        return new Laboratorio(id, nome);
    }

}
