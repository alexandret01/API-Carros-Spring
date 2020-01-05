package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepositorio rep;

    public List<CarroDTO> getCarros(){
        return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Optional<Carro> getCarrosById(long id) {
        return rep.findById(id);
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public void save(Carro carro) {
        rep.save(carro);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        Optional<Carro> optional = getCarrosById(id);
        if (optional.isPresent()){
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());

            rep.save(db);

            return(db);
        } else {
            throw new RuntimeException("Não foi possivel para atualizar o registro");
        }
    }

    public void delete(long id) {
        Assert.notNull(id,"Não foi possível deleter o registro");

        Optional<Carro> optional = getCarrosById(id);
        if (optional.isPresent()){
            rep.deleteById(id);
        } else {
            throw new RuntimeException("Não foi possivel para deletar o registro");
        }

    }
}
