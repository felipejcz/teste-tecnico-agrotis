package agrotis.com.teste_backend_java.controller.dto;

import agrotis.com.teste_backend_java.entity.Pessoa;

public record AtualizarPessoaDto(String nome, String dataInicial, String dataFinal,
        String observacoes, AtualizarPropriedadeDto propriedade, AtualizarLaboratorioDto laboratorio) {

    public Pessoa toEntity() {
        return new Pessoa(nome, dataInicial, dataFinal, observacoes, propriedade.toEntity(), laboratorio.toEntity());
    }

}
