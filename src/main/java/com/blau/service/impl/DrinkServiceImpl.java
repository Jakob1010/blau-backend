package com.blau.service.impl;

import com.blau.entity.Drink;
import com.blau.repository.DrinkRepository;
import com.blau.service.DrinkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DrinkServiceImpl implements DrinkService {
    private DrinkRepository repository;

    public DrinkServiceImpl(DrinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Drink save(Drink drink) {
        return repository.save(drink);
    }

    @Override
    public Drink getDrinkById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Page<Drink> finAllDrinks(Pageable page) {
        return repository.findAll(page);
    }
}
