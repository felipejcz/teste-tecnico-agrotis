package agrotis.com.teste_backend_java;

import agrotis.com.teste_backend_java.controller.dto.CriarPessoaDto;
import agrotis.com.teste_backend_java.controller.dto.AtualizarLaboratorioDto;
import agrotis.com.teste_backend_java.controller.dto.AtualizarPessoaDto;
import agrotis.com.teste_backend_java.controller.dto.AtualizarPropriedadeDto;
import agrotis.com.teste_backend_java.entity.*;
import agrotis.com.teste_backend_java.exception.AgrotisException;
import agrotis.com.teste_backend_java.repository.PessoaRepository;
import agrotis.com.teste_backend_java.service.PessoaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João da Silva");
        pessoa.setDataInicial(Pessoa.convertToLocalDate("2022-02-02T17:41:44Z"));
        pessoa.setDataFinal(Pessoa.convertToLocalDate("2022-05-02T17:41:44Z"));
        pessoa.setObservacoes("teste");
        pessoa.setPropriedade(new Propriedade(1L, "Propriedade 1"));
        pessoa.setLaboratorio(new Laboratorio(1L, "Laboratório 1"));
    }

    @Test
    void deveBuscarTodasAsPessoas() {
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));
        var resultado = pessoaService.buscarPessoas();
        assertEquals(1, resultado.size());
        verify(pessoaRepository).findAll();
    }

    @Test
    void deveRetornarPessoaPorId() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        var resultado = pessoaService.buscarPessoa(1L);
        assertEquals("João da Silva", resultado.getNome());
    }

    @Test
    void deveLancarExcecao_SePessoaNaoForEncontrada() {
        when(pessoaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(AgrotisException.class, () -> pessoaService.buscarPessoa(99L));
    }

    @Test
    void deveCriarPessoa() {
        CriarPessoaDto dto = mock(CriarPessoaDto.class);
        when(dto.toEntity()).thenReturn(pessoa);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        Pessoa criada = pessoaService.criarPessoa(dto);
        assertNotNull(criada);
        assertEquals("João da Silva", criada.getNome());
        verify(pessoaRepository).save(pessoa);
    }

    @Test
    void deveAtualizarPessoa() {
        AtualizarPessoaDto dto = mock(AtualizarPessoaDto.class);
        AtualizarPropriedadeDto propriedadeDto = mock(AtualizarPropriedadeDto.class);
        AtualizarLaboratorioDto laboratorioDto = mock(AtualizarLaboratorioDto.class);
        when(dto.nome()).thenReturn("Maria");
        when(dto.dataInicial()).thenReturn("2022-02-02T17:41:44Z");
        when(dto.dataFinal()).thenReturn("2022-05-02T17:41:44Z");
        when(dto.observacoes()).thenReturn("nova observação");
        when(dto.propriedade()).thenReturn(propriedadeDto);
        when(propriedadeDto.toEntity()).thenReturn(new Propriedade(2L, "Prop 2"));

        when(dto.laboratorio()).thenReturn(laboratorioDto);
        when(laboratorioDto.toEntity()).thenReturn(new Laboratorio(2L, "Lab 2"));

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any())).thenReturn(pessoa);

        Pessoa atualizada = pessoaService.atualizarPessoa(1L, dto);
        assertEquals("Maria", atualizada.getNome());
    }

    @Test
    void deveDeletarPessoa() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        doNothing().when(pessoaRepository).deleteById(1L);

        pessoaService.deletarPessoa(1L);
        verify(pessoaRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecao_SeDeletarPessoaNaoExistente() {
        when(pessoaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(AgrotisException.class, () -> pessoaService.deletarPessoa(99L));
    }
}
