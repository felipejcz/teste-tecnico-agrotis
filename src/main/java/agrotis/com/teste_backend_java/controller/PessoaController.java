package agrotis.com.teste_backend_java.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agrotis.com.teste_backend_java.controller.dto.AtualizarPessoaDto;
import agrotis.com.teste_backend_java.controller.dto.CriarPessoaDto;
import agrotis.com.teste_backend_java.entity.Pessoa;
import agrotis.com.teste_backend_java.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> buscarPessoas() {
        var pessoas = pessoaService.buscarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> buscarPessoa(@PathVariable("pessoaId") Long pessoaId) {
        var pessoa = pessoaService.buscarPessoa(pessoaId);
        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping("/criar")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody @Valid CriarPessoaDto dto) {
        var pessoa = pessoaService.criarPessoa(dto);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/atualizar/{pessoaId}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable("pessoaId") Long pessoaId,
            @RequestBody @Valid AtualizarPessoaDto dto) {
        var pessoa = pessoaService.atualizarPessoa(pessoaId, dto);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/deletar/{pessoaId}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable("pessoaId") Long pessoaId) {
        pessoaService.deletarPessoa(pessoaId);
        return ResponseEntity.noContent().build();
    }
}
