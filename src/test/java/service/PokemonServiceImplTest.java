package service;

import com.pokedex.config.CacheConfig;
import com.pokedex.dto.ResponseDto;
import com.pokedex.exception.PokemonNotFoundException;
import com.pokedex.service.PokemonServiceImpl;
import org.ehcache.CacheManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PokemonServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    private static final String POKEMON_API_URL = "https://pokeapi.co/api/v2/pokemon/";
    @BeforeEach
    void setUp() {
        // Set the URL for the mock service
        pokemonService.POKEMON_API_URL = POKEMON_API_URL;
    }
    @Test
    void testGetPokemonList_Success() {
        // Arrange
        String pokemonName = "pikachu";
        Object mockResponse = new Object();  // Mocked response object from API

        // Mock the behavior of RestTemplate
        lenient().when(restTemplate.getForObject(POKEMON_API_URL + pokemonName, Object.class)).thenReturn(mockResponse);

        // Act
        ResponseDto<Object> response = pokemonService.getPokemonList(pokemonName);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getHttpStatus());
        assertEquals("Response from V2 pokeapi for Pokémon: pikachu", response.getMessage());
    }

    @Test
    void testGetPokemonList_Fail_PokemonNotFoundException() {
        // Arrange
        String pokemonName = "nonexistentPokemon";

        // Mock the behavior of RestTemplate to throw an exception
        lenient().when(restTemplate.getForObject(POKEMON_API_URL + pokemonName, Object.class)).thenThrow(new RuntimeException("API failure"));

        // Act & Assert
        PokemonNotFoundException exception = assertThrows(PokemonNotFoundException.class, () -> {
            pokemonService.getPokemonList(pokemonName);
        });

        assertEquals("Pokemon not found with the given name: nonexistentPokemon", exception.getMessage());
    }

}
