package br.com.register.api.dto;

import br.com.register.api.entity.Pessoa;
import br.com.register.api.entity.PessoaFisica;
import br.com.register.api.entity.PessoaJuridica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PessoaDTO {

    private static ModelMapper MAPPER = new ModelMapper();

    @JsonIgnore
    private Long id;

    @NotNull
    @NotEmpty
    @Length(min = 2, max = 200)
    private String endereco;

    @NotNull
    private String telefone;

    @NotNull
    private String tipo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sexo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cnpj;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String razaoSocial;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String inscricaoEstadual;


    public static PessoaDTO buildDTO(Pessoa pessoa) {
        return MAPPER.map(pessoa, PessoaDTO.class);
    }

    public Pessoa buildToEntity() {
        if (this.tipo.equals("F")) {
            return new PessoaFisica(this.cpf, this.idade, this.sexo, this.endereco, this.telefone, this.tipo);
        }
        return new PessoaJuridica(this.cnpj, this.razaoSocial, this.inscricaoEstadual, this.endereco, this.telefone, this.tipo);
    }
}



