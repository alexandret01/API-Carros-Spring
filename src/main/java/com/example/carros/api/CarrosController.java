package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public List<CarroDTO> get(){

        return service.getCarros();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id){
        Optional<CarroDTO> carro = service.getCarrosById(id);

        return carro.isPresent() ? ResponseEntity.ok(carro.get()) : ResponseEntity.notFound().build();

    }

    @GetMapping("/tipo/{tipo}")
    public List<CarroDTO> getCarrosByTipo(@PathVariable("tipo") String tipo){
        return service.getCarrosByTipo(tipo);
    }

    @PostMapping()
    public String post(@RequestBody Carro carro){
        service.save(carro);
        return "Carro Salvo com sucesso";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") long id, @RequestBody Carro carro){
        service.update(carro, id);
        return "Carro Atualizado com sucesso";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        service.delete(id);
        return "Carro Deletado com Sucesso";
    }

}