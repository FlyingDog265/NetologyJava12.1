package ru.netology.domain;

import java.util.Objects;

public class Offer implements Comparable<Offer> {
    private int id;
    private int amount; // Стоимость
    private String departureAirport; // Аэропорт отправки
    private String arrivalAirport; // Аэропорт прибытия
    private int travelTime; // Время в пути

    public Offer() {
    }

    public Offer(int id, int amount, String departure, String arrival, int travelTime) {
        this.id = id;
        this.amount = amount;
        this.departureAirport = departure;
        this.arrivalAirport = arrival;
        this.travelTime = travelTime;
    }


    @Override
    public int compareTo(Offer o) {
        return amount - o.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && amount == offer.amount && travelTime == offer.travelTime && Objects.equals(departureAirport, offer.departureAirport) && Objects.equals(arrivalAirport, offer.arrivalAirport);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }
}
