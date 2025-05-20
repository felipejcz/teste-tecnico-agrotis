package agrotis.com.teste_backend_java.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_propriedades")
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    public Propriedade() {
    }

    public Propriedade(Long id, String nome) {
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
        PROPRIEDADE_1(null, "Fazenda Modelo 1"),
        PROPRIEDADE_2(null, "Fazenda Modelo 2"),
        PROPRIEDADE_3(null, "Fazenda Modelo 3"),
        PROPRIEDADE_4(null, "Fazenda Modelo 4"),
        PROPRIEDADE_5(null, "Fazenda Modelo 5"),
        PROPRIEDADE_6(null, "Fazenda Modelo 6");

        Enum(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        private Long id;
        private String nome;

        public Propriedade get() {
            return new Propriedade(id, nome);
        }
    }
}
