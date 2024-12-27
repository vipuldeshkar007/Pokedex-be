package com.pokedex.service;

import com.pokedex.dto.ResponseDto;

public interface PokemonService {
    ResponseDto<Object> getPokemon(String name);
}
