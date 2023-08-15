package com.blau.mapper;

import com.blau.dto.DrinkDto;
import com.blau.entity.Drink;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DrinkMapper {
    Drink map(DrinkDto drinkDto);

    DrinkDto map(Drink drink);


}
