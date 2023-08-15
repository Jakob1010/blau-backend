package com.blau.mapper;

import com.blau.dto.LogDto;
import com.blau.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = {AppUserMapper.class, DrinkMapper.class}
)
public interface LogMapper{

    LogDto logEntityToLogDto(Log log);

    @Mappings({
            @Mapping(target = "appUser", source = "appUser"),
            @Mapping(target = "drink", source = "drink")
    })
    Log logDtoToLogEntity(LogDto logDto);
}
