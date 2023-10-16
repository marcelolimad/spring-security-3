package one.projeto.gof.controller;

import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import one.projeto.gof.dto.Produto;
import one.projeto.gof.entity.UsuarioInfo;
import one.projeto.gof.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/welcome")
    public String welcome() {
        return "Bem vindo esse endpoint não seguro";
    }

    @PostMapping("/new")
    public String ok(){
        return "Bem vindo";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String getText() {
       return "tudo bem get texto";	
    }
    

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String getServico() {
        return "Get Ultimo";
    }
}
