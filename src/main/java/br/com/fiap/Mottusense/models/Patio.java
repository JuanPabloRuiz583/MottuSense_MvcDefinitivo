package br.com.fiap.Mottusense.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    @Size(max = 100, message = "Nome não pode exceder 100 caracteres")
    private String nome;

    @NotBlank(message = "Endereço não pode estar em branco")
    @Size(max = 255, message = "Endereço não pode exceder 255 caracteres")
    private String endereco;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Moto> motos;


    @Override
    public String toString() {
        return "Patio{id=" + id + ", nome='" + nome + "'}";
    }
}
