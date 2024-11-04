package br.com.RestApi.development.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="clientes")
public class Cliente {
	
	@Id // Chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	@NotBlank(message= "O nome não pode estar em branco")
	private String nome;
	
	@Column (nullable = false)
	@NotBlank(message = "A idade não pode estar em branco")
	private String idade;

}
