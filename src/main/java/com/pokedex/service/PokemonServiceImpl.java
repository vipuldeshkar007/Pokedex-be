package com.pokedex.service;

import com.pokedex.dto.ResponseDto;
import com.pokedex.exception.PokemonNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.MessageFormat;

@Service
public class PokemonServiceImpl implements PokemonService{

    @Value("${pokeapi.base-uri}")
    public String POKEMON_API_URL;
    private static final Logger logger = LogManager.getLogger(PokemonServiceImpl.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    @Cacheable(value = "pokemonCache", key = "#type+#name.toLowerCase()")
    public ResponseDto<Object> getPokemon(String name) {
        String lowerCaseName = name.toLowerCase();
        logger.info("Request received for Pokémon: {}", name);
        try{
            logger.info("Fetching data from API for Pokémon: {}", lowerCaseName);
            Object response = restTemplate.getForObject(POKEMON_API_URL + lowerCaseName, Object.class);
            return new ResponseDto<>(
                    200,
                    response,
                    MessageFormat.format("Response from V2 pokeapi for Pokémon: {0}", name)
            );
        }catch(Exception e){
            logger.error("Failed to fetch Pokémon data for name: {}", lowerCaseName, e);
            throw new PokemonNotFoundException("Pokemon not found with the given name: " + name);
        }
    }
}
