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
@Table(name = "moto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"placa"}),
        @UniqueConstraint(columnNames = {"numero_chassi"})
})
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Placa não pode estar em branco")
    @Size(max = 10, message = "Placa não pode exceder 10 caracteres")
    private String placa;

    @NotBlank(message = "Modelo não pode estar em branco")
    @Size(max = 50, message = "Modelo não pode exceder 50 caracteres")
    private String modelo;

    @NotBlank(message = "Número do Chassi não pode estar em branco")
    @Size(max = 17, message = "Número do Chassi não pode exceder 17 caracteres")
    private String numeroChassi;

    @NotNull(message = "Status não pode ser nulo")
    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    private Patio patio;


    @Override
    public String toString() {
        return "Moto{id=" + id + ", placa='" + placa + "', modelo='" + modelo + "'}";
    }
}
