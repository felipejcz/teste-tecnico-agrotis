package agrotis.com.teste_backend_java.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import agrotis.com.teste_backend_java.entity.Laboratorio;
import agrotis.com.teste_backend_java.entity.Propriedade;
import agrotis.com.teste_backend_java.repository.LaboratorioRepository;
import agrotis.com.teste_backend_java.repository.PropriedadeRepository;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final PropriedadeRepository propriedadeRepository;
    private final LaboratorioRepository laboratorioRepository;

    public DataLoader(PropriedadeRepository propriedadeRepository, LaboratorioRepository laboratorioRepository) {
        this.propriedadeRepository = propriedadeRepository;
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (propriedadeRepository.count() == 0) {
            Arrays.stream(Propriedade.Enum.values())
                    .forEach(propriedade -> propriedadeRepository.save(propriedade.get()));
        }
        if (laboratorioRepository.count() == 0) {
            Arrays.stream(Laboratorio.Enum.values())
                    .forEach(laboratorio -> laboratorioRepository.save(laboratorio.get()));
        }
    }

}
