package agrotis.com.teste_backend_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agrotis.com.teste_backend_java.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    

}
