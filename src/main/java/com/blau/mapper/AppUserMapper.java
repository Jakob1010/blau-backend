package com.blau.mapper;

import com.blau.dto.AppUserDto;
import com.blau.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    AppUser map(AppUserDto dto);
}
