package agrotis.com.teste_backend_java.service;

import java.util.List;

import org.springframework.stereotype.Service;

import agrotis.com.teste_backend_java.controller.dto.AtualizarPessoaDto;
import agrotis.com.teste_backend_java.controller.dto.CriarPessoaDto;
import agrotis.com.teste_backend_java.entity.Pessoa;
import agrotis.com.teste_backend_java.exception.AgrotisException;
import agrotis.com.teste_backend_java.repository.PessoaRepository;

@Service
public class PessoaService {
    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> buscarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoa(Long pessoaId) {
        var pessoa = pessoaRepository.findById(pessoaId).orElse(null);
        if(pessoa == null) {
            throw new AgrotisException("Pessoa não encontrada");
        }
        return pessoa;
    }

    public Pessoa criarPessoa(CriarPessoaDto dto) {
        var pessoa = pessoaRepository.save(dto.toEntity());
        if(pessoa == null) {
            throw new AgrotisException("Erro ao criar pessoa");
        }
        return pessoa;
    }

    public Pessoa atualizarPessoa(Long pessoaId, AtualizarPessoaDto dto) {
        var pessoa = pessoaRepository.findById(pessoaId).orElse(null);
        if(pessoa == null) {
            throw new AgrotisException("Pessoa não encontrada");
        }
        pessoa.setNome(dto.nome());
        pessoa.setDataInicial(Pessoa.convertToLocalDate(dto.dataInicial()));
        pessoa.setDataFinal(Pessoa.convertToLocalDate(dto.dataFinal()));
        pessoa.setObservacoes(dto.observacoes());
        pessoa.setPropriedade(dto.propriedade().toEntity());
        pessoa.setLaboratorio(dto.laboratorio().toEntity());
        return pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Long pessoaId) {
        var pessoa = buscarPessoa(pessoaId);
        if(pessoa == null) {
            throw new AgrotisException("Pessoa não encontrada");
        }
        pessoaRepository.deleteById(pessoa.getId());
    }
}
