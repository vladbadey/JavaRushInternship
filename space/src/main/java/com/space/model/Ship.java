package com.space.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "ship")
public class Ship implements Serializable {

    private static final long serialVersionUID = -9893205227711419L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //ID корабля

    @Column(name = "name", length = 50)
    private String name; //Название корабля (до 50 знаков включительно)

    @Column(name = "planet", length = 50)
    private String planet; //Планета пребывания (до 50 знаков включительно)

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    private ShipType shipType; //Тип корабля

    @Column(name = "prodDate")
    private Date prodDate; //Дата выпуска. Диапазон значений года 2800..3019 включительно

    @Column(name = "isUsed")
    private Boolean isUsed; //Использованный / новый

    @Column(name = "speed")
    private Double speed; //Максимальная скорость корабля. Диапазон значений 0,01..0,99 включительно. Используй математическое округление до сотых.

    @Column(name = "crewSize", nullable = true)
    private Integer crewSize; //Количество членов экипажа. Диапазон значений 1..9999 включительно.

    @Column(name = "rating")
    private Double rating; //Рейтинг корабля. Используй математическое округление до сотых.

    public Double rating(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(prodDate);
        double v = speed;
        double k = isUsed ? 0.5d : 1d;
        long y0 = 3019;
        long y1 = calendar.get(Calendar.YEAR);
        return Math.round(((80 * v * k) / (y0 - y1 + 1)) * 100d) / 100d;
    }

    /*----------------getters and setters and no arg constructor because no lombok---------------*/

    public Ship() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}