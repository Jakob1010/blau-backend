package com.blau.service;

import com.blau.entity.Drink;
import com.blau.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DrinkService {
    Drink save(Drink drink);
    Drink getDrinkById(Long id);
    Page<Drink> finAllDrinks(Pageable page);
}
