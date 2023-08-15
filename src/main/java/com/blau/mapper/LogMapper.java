package com.blau.mapper;

import com.blau.dto.LogDto;
import com.blau.entity.Log;
import org.antlr.v4.runtime.misc.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = "spring",
        uses = {AppUserMapper.class, DrinkMapper.class}
)
public interface LogMapper{

    @Mappings({
            @Mapping(target = "appUser", source = "appUser"),
            @Mapping(target = "drink", source = "drink")
    })
    LogDto logEntityToLogDto(Log log);

    @Mappings({
            @Mapping(target = "appUser", source = "appUser"),
            @Mapping(target = "drink", source = "drink")
    })
    Log logDtoToLogEntity(LogDto logDto);

    default Page<LogDto> toDtoPage(@NotNull Page<Log> page) {
        return page.map(this::logEntityToLogDto);
    }
}
