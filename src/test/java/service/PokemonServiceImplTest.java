package service;

import com.pokedex.dto.ResponseDto;
import com.pokedex.exception.PokemonNotFoundException;
import com.pokedex.service.PokemonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;


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
        String pokemonName = "pikachu";
        Object mockResponse = new Object();  // Mocked response object from API

        lenient().when(restTemplate.getForObject(POKEMON_API_URL + pokemonName, Object.class)).thenReturn(mockResponse);

        ResponseDto<Object> response = pokemonService.getPokemon(pokemonName);

        assertNotNull(response);
        assertEquals(200, response.getHttpStatus());
        assertEquals("Response from V2 pokeapi for Pokémon: pikachu", response.getMessage());
    }

    @Test
    void testGetPokemonList_Fail_PokemonNotFoundException() {
        // Arrange
        String pokemonName = "nonexistentPokemon";

        lenient().when(restTemplate.getForObject(POKEMON_API_URL + pokemonName, Object.class)).thenThrow(new RuntimeException("API failure"));

        PokemonNotFoundException exception = assertThrows(PokemonNotFoundException.class, () ->
            pokemonService.getPokemon(pokemonName));

        assertEquals("Pokemon not found with the given name: nonexistentPokemon", exception.getMessage());
    }

}
