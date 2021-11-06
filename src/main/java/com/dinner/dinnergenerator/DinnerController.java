package com.dinner.dinnergenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
public class DinnerController {
MenuService menuService;

public DinnerController(MenuService menuService){
    this.menuService = menuService;
}
@ResponseStatus(HttpStatus.OK)
@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
MenuProperties whatsForDinner() throws Exception {

    MenuProperties builder = MenuProperties.builder()
    .meat(this.menuService.getMeat())
    .vegetable(this.menuService.getVegetable())
    .starch(this.menuService.getStarch())
    .alternative(this.menuService.getAlternative())
    .build();

    return builder;
}
}