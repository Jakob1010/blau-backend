package com.blau.mapper;

import com.blau.dto.DrinkDto;
import com.blau.dto.LogDto;
import com.blau.entity.Drink;
import com.blau.entity.Log;
import org.antlr.v4.runtime.misc.NotNull;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface DrinkMapper {
    Drink map(DrinkDto drinkDto);

    DrinkDto map(Drink drink);

    default Page<DrinkDto> toDtoPage(@NotNull Page<Drink> page) {
        return page.map(this::map);
    }
}
