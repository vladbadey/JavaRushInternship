package com.space.controller;

import com.space.model.Ship;
import com.space.model.ShipType;
import com.space.service.ShipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class ShipsController {

    private ShipsService shipsService;

    @Autowired
    public void setShipsService(ShipsService shipsService) {
        this.shipsService = shipsService;
    }

    //ALL TESTS PASS!
    @GetMapping("/ships")
    @ResponseStatus(HttpStatus.OK)
    public List<Ship> getShipsList(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "planet", required = false) String planet,
            @RequestParam(value = "shipType", required = false) ShipType shipType,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating,
            @RequestParam(value = "order", required = false, defaultValue = "ID") ShipOrder order,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize)
    {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName()));
        Specification<Ship> specification = Specification.where(
                shipsService.filterByName(name)
                        .and(shipsService.filterByPlanet(planet))
                        .and(shipsService.filterByShipType(shipType))
                        .and(shipsService.filterByProdDate(after, before))
                        .and(shipsService.filterByIsUsed(isUsed))
                        .and(shipsService.filterBySpeed(minSpeed, maxSpeed))
                        .and(shipsService.filterByCrewSize(minCrewSize, maxCrewSize))
                        .and(shipsService.filterByRating (minRating, maxRating))
        );
        return shipsService.findAll(specification, pageable).getContent();
    }

    //ALL TESTS PASS!
    @GetMapping("/ships/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer shipsCount(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "planet", required = false) String planet,
            @RequestParam(value = "shipType", required = false) ShipType shipType,
            @RequestParam(value = "after", required = false) Long after,
            @RequestParam(value = "before", required = false) Long before,
            @RequestParam(value = "isUsed", required = false) Boolean isUsed,
            @RequestParam(value = "minSpeed", required = false) Double minSpeed,
            @RequestParam(value = "maxSpeed", required = false) Double maxSpeed,
            @RequestParam(value = "minCrewSize", required = false) Integer minCrewSize,
            @RequestParam(value = "maxCrewSize", required = false) Integer maxCrewSize,
            @RequestParam(value = "minRating", required = false) Double minRating,
            @RequestParam(value = "maxRating", required = false) Double maxRating)
    {

        Specification<Ship> specification = Specification.where(
                shipsService.filterByName(name)
                        .and(shipsService.filterByPlanet(planet))
                        .and(shipsService.filterByShipType(shipType))
                        .and(shipsService.filterByProdDate(after, before))
                        .and(shipsService.filterByIsUsed(isUsed))
                        .and(shipsService.filterBySpeed(minSpeed, maxSpeed))
                        .and(shipsService.filterByCrewSize(minCrewSize, maxCrewSize))
                        .and(shipsService.filterByRating (minRating, maxRating))
        );
        return shipsService.findAll(specification).size();
    }

    //ALL TESTS PASS!
    @PostMapping("/ships")
    @ResponseStatus(HttpStatus.OK)
    public Ship createShip(@RequestBody Ship ship){
        return shipsService.createShip(ship);
    }

    //ALL TESTS PASS!
    @GetMapping("/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship getShip(@PathVariable Long id){
        return shipsService.findById(id);
    }

    //ALL TESTS PASS!
    @PostMapping("/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ship updateShip(@PathVariable Long id, @RequestBody Ship ship){
        return shipsService.updateById(id, ship);
    }

    //ALL TESTS PASS!
    @DeleteMapping("/ships/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteShip(@PathVariable Long id){
        shipsService.deleteById(id);
    }

}