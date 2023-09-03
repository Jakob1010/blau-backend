package com.blau.rest;

import com.blau.dto.DrinkDto;
import com.blau.dto.LogDto;
import com.blau.mapper.DrinkMapper;
import com.blau.mapper.LogMapper;
import com.blau.service.DrinkService;
import com.blau.service.impl.LogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DrinkEndpoint.BASE_URL)
@Slf4j
public class DrinkEndpoint {
    public static final String BASE_URL =  "/api/v1/drinks";
    private final DrinkService drinkService;
    private final DrinkMapper drinkMapper;

    public DrinkEndpoint(DrinkService drinkService, DrinkMapper drinkMapper) {
        this.drinkService = drinkService;
        this.drinkMapper = drinkMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<DrinkDto> getAllDrinks(Pageable pageable) {
        log.info("GET {}", BASE_URL);
        return drinkMapper.toDtoPage(drinkService.finAllDrinks(pageable));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DrinkDto getDrink(@PathVariable Long id){
        log.info("GET {}/{}", BASE_URL, id);
        return drinkMapper.map(drinkService.getDrinkById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createDrink(@RequestBody DrinkDto drinkDto) {
        log.info("POST {}", BASE_URL);
        drinkService.save(drinkMapper.map(drinkDto));
        return ResponseEntity.status(201).body("Drink created successfully.");
    }

}
