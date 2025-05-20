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

    public Pessoa atualizarPessoa(AtualizarPessoaDto dto) {
        var pessoaAtualizada = pessoaRepository.save(dto.toEntity());
        if(pessoaAtualizada == null) {
            throw new AgrotisException("Erro ao atualizar pessoa");
        }
        return pessoaAtualizada;
    }

    public void deletarPessoa(Long pessoaId) {
        var pessoa = buscarPessoa(pessoaId);
        if(pessoa == null) {
            throw new AgrotisException("Erro ao deletar pessoa");
        }
        pessoaRepository.delete(pessoa);
    }
}
