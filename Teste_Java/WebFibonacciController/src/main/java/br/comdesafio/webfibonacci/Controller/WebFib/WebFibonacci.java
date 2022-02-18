package br.comdesafio.webfibonacci.Controller.WebFib;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path="/fibonacci")
@ComponentScan
public class WebFibonacci {

    @GetMapping(value={"/numero/{numeroFibonacci}","posicao/{index"})
    public void resultado(@PathVariable(value="numeroFibonacci") int numeroFibonacci, @PathVariable(value="index") int index, HttpServletResponse response) throws IOException {
        response.getWriter().println(fibonacci(numeroFibonacci, index));
    }

    public Integer fibonacci(Integer numero, Integer index){

        List<Integer> fib = new  ArrayList<>();

        if (fib.get(index)==0){
            return 0;
        }else{
            if (fib.get(index)==1){
                return 1;
            }else{
             return  fib.get(index-1) + fib.get(index-2);
            }
        }
    }





}
