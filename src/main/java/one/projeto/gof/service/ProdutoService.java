package one.projeto.gof.service;

import one.projeto.gof.dto.Produto;
import one.projeto.gof.entity.UsuarioInfo;
import one.projeto.gof.repository.UsuarioInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProdutoService {

    List<Produto> productList = null;

    @Autowired
    private UsuarioInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Produto.builder()
                        .produtoId(i)
                        .name("produto " + i)
                        .qtd(new Random().nextInt(10))
                        .preco(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }


    public List<Produto> getProducts() {
        return productList;
    }

    public Produto getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProdutoId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("produto " + id + " não encontrado"));
    }


    public String addUser(UsuarioInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "usuario add ";
    }
}
