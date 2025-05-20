package agrotis.com.teste_backend_java.controller.dto;

public record BuscarLaboratorioDto(Long codigoLaboratorio, String nomeLaboratorio, Long quantidadePessoas) {

    public BuscarLaboratorioDto {
        nomeLaboratorio = nomeLaboratorio.toUpperCase();
    }

}
