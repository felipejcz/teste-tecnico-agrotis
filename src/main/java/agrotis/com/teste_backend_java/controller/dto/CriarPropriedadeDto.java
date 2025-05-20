package agrotis.com.teste_backend_java.controller.dto;

import agrotis.com.teste_backend_java.entity.Propriedade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarPropriedadeDto(@NotNull Long id, @NotBlank String nome) {
    public Propriedade toEntity() {
        return new Propriedade(id, nome);
    }

}
