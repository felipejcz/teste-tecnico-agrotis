package agrotis.com.teste_backend_java.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_laboratorios")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "laboratorio")
    private List<Pessoa> pessoas;

    public Laboratorio() {
    }

    public Laboratorio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public enum Enum {

        LABORATORIO_1(null, "Laboratório Modelo 1"),
        LABORATORIO_2(null, "Laboratório Modelo 2"),
        LABORATORIO_3(null, "Laboratório Modelo 3"),
        LABORATORIO_4(null, "Laboratório Modelo 4"),
        LABORATORIO_5(null, "Laboratório Modelo 5"),
        LABORATORIO_6(null, "Laboratório Modelo 6");

        Enum(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        private Long id;
        private String nome;

        public Laboratorio get() {
            return new Laboratorio(id, nome);
        }
    }
}
