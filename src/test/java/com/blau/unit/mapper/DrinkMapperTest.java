package com.blau.unit.mapper;

import com.blau.dto.DrinkDto;
import com.blau.entity.Drink;
import com.blau.mapper.DrinkMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DrinkMapperTest {

    private DrinkMapper drinkMapper;

    @BeforeEach
    public void init() {
        drinkMapper = Mappers.getMapper(DrinkMapper.class);
    }

    @Test
    public void testMapToDto() {
        Drink drink = new Drink();
        drink.setId(1L);
        drink.setName("Mojito");
        drink.setDescription("Refreshing cocktail");
        drink.setAlcoholic(true);

        DrinkDto drinkDto = drinkMapper.map(drink);

        assertEquals(drink.getId(), drinkDto.getId());
        assertEquals(drink.getName(), drinkDto.getName());
        assertEquals(drink.getDescription(), drinkDto.getDescription());
        assertEquals(drink.isAlcoholic(), drinkDto.isAlcoholic());
    }

    @Test
    public void testMapToEntity() {
        DrinkDto drinkDto = new DrinkDto();
        drinkDto.setId(2L);
        drinkDto.setName("Pina Colada");
        drinkDto.setDescription("Tropical cocktail");
        drinkDto.setAlcoholic(true);

        Drink drink = drinkMapper.map(drinkDto);

        assertEquals(drinkDto.getId(), drink.getId());
        assertEquals(drinkDto.getName(), drink.getName());
        assertEquals(drinkDto.getDescription(), drink.getDescription());
        assertEquals(drinkDto.isAlcoholic(), drink.isAlcoholic());
    }

    @Test
    public void testToDtoPage() {
        // Create a list of Drink entities
        List<Drink> drinkList = new ArrayList<>();
        drinkList.add(new Drink(1L, "Mojito", "Refreshing cocktail", true));
        drinkList.add(new Drink(2L, "Pina Colada", "Tropical cocktail", true));

        // Create a Page of Drink entities
        Page<Drink> drinkPage = new PageImpl<>(drinkList);

        // Call the toDtoPage method
        Page<DrinkDto> drinkDtoPage = drinkMapper.toDtoPage(drinkPage);

        // Assert that the resulting Page has the same number of elements
        assertEquals(drinkPage.getTotalElements(), drinkDtoPage.getTotalElements());

        // You can also assert individual elements if needed
        List<DrinkDto> drinkDtoList = drinkDtoPage.getContent();
        for (int i = 0; i < drinkList.size(); i++) {
            Drink drink = drinkList.get(i);
            DrinkDto drinkDto = drinkDtoList.get(i);

            assertEquals(drink.getId(), drinkDto.getId());
            assertEquals(drink.getName(), drinkDto.getName());
            assertEquals(drink.getDescription(), drinkDto.getDescription());
            assertEquals(drink.isAlcoholic(), drinkDto.isAlcoholic());
        }
    }
}

