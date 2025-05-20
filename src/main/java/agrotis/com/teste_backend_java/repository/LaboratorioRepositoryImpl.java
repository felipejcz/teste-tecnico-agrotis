package agrotis.com.teste_backend_java.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import agrotis.com.teste_backend_java.controller.dto.BuscarLaboratorioDto;
import agrotis.com.teste_backend_java.entity.Laboratorio;
import agrotis.com.teste_backend_java.entity.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LaboratorioRepositoryImpl implements LaboratorioRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<BuscarLaboratorioDto> buscarLaboratoriosComFiltro(
        LocalDate dataInicialInicio,
        LocalDate dataInicialFim,
        LocalDate dataFinalInicio,
        LocalDate dataFinalFim,
        String observacoes,
        Long quantidadeMinima
    ) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<BuscarLaboratorioDto> cq = cb.createQuery(BuscarLaboratorioDto.class);
        Root<Laboratorio> lab = cq.from(Laboratorio.class);
        Join<Laboratorio, Pessoa> pessoas = lab.join("pessoas", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();

        // Filtros dinâmicos
        if (dataInicialInicio != null && dataInicialFim != null) {
            predicates.add(cb.between(pessoas.get("dataInicial"), dataInicialInicio, dataInicialFim));
        }

        if (dataFinalInicio != null && dataFinalFim != null) {
            predicates.add(cb.between(pessoas.get("dataFinal"), dataFinalInicio, dataFinalFim));
        }

        if (observacoes != null && !observacoes.isEmpty()) {
            predicates.add(cb.like(cb.lower(pessoas.get("observacoes")), "%" + observacoes.toLowerCase() + "%"));
        }

        // Agrupamento e seleção
        Expression<Long> count = cb.count(pessoas.get("id"));

        cq.select(cb.construct(
            BuscarLaboratorioDto.class,
            lab.get("id"),
            cb.upper(lab.get("nome")),
            count
        ));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.groupBy(lab.get("id"), lab.get("nome"));
        cq.having(cb.ge(count, quantidadeMinima));

        // Ordenação
        List<Order> orders = new ArrayList<>();
        orders.add(cb.desc(count));
        if (dataInicialInicio != null && dataInicialFim != null) {
            orders.add(cb.asc(cb.min(pessoas.get("dataInicial"))));
        }

        cq.orderBy(orders);

        return em.createQuery(cq).getResultList();
    }

}
