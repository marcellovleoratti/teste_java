package br.com.desafio.teste_java.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.desafio.teste_java.model.WebLoginModel;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path="/login")
@ComponentScan(basePackages = "br.comdesafio.webfibonacci.Controller")
public class WebLogin {
    @Autowired
    WebLoginRepository webRp;

    @PostMapping(value="/gravarUser")
    public WebLoginModel gravar(@RequestBody WebLoginModel webLoginModel){
        webRp.save(webLoginModel);
        return webLoginModel;
    }

    @GetMapping(value={"/buscar/login/{nome}","/buscar/login/{usuario}"})
    public ResponseEntity<WebLoginModel> getBynome(@RequestParam("nome")  String nome, @RequestParam("usuario") String usuario, HttpServletResponse response) throws IOException {
        Optional<WebLoginModel> webLog = webRp.findByNomeAndUser(nome, usuario);
        if (webLog.isPresent()) {
            response.sendRedirect("/fibonacci");
            return new ResponseEntity<>(webLog.get(), HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(webLog.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/apagar/{id}")
    public void apagarusuario(@RequestParam("id")Long id){
        WebLoginModel webLog1 = new WebLoginModel();
        webRp.deleteById(id);
    }

    @PutMapping("/atualizar/login/{id}")
    public ResponseEntity<WebLoginModel> atualizarLogin(@PathVariable(value = "id") Long id, @Validated @RequestBody WebLoginModel web2) throws ResourceNotFoundException {
        WebLoginModel web = webRp.findById(id).orElseThrow(() -> new ResourceNotFoundException("Produto Não encontrado:: " + web2.getNome()));
        final WebLoginModel atualiza = webRp.save(web);
        return ResponseEntity.ok(atualiza);
    }
}
