package agrotis.com.teste_backend_java.service;

import org.springframework.stereotype.Service;

import agrotis.com.teste_backend_java.repository.LaboratorioRepository;

@Service
public class LaboratorioService {
    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

}