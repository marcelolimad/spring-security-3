package one.projeto.gof.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    private int produtoId;
    private String name;
    private int qtd;
    private double preco;
}
