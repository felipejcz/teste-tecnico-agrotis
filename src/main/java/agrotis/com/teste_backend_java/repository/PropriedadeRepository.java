package agrotis.com.teste_backend_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import agrotis.com.teste_backend_java.entity.Propriedade;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    

}
