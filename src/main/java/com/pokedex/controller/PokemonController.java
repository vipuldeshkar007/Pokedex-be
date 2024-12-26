package com.pokedex.controller;


import com.pokedex.dto.ResponseDto;
import com.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("api/v1")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/getPokemonList/{name}")
    public ResponseEntity<Object> getPokemonList(@PathVariable String name){
        return ResponseEntity.ok(pokemonService.getPokemonList(name));
    }
}
