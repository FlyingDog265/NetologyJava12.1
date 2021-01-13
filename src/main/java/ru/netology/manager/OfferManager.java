package ru.netology.manager;

import ru.netology.domain.Offer;
import ru.netology.helpers.NotFoundException;
import ru.netology.repository.OfferRepository;

import java.util.Arrays;

public class OfferManager {
    private final OfferRepository repository;

    public OfferManager(OfferRepository repository) {
        this.repository = repository;
    }

    public void add(Offer item) {
        repository.save(item);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public Offer[] findAll(String from, String to) {
        Offer[] result = new Offer[0];
        for (Offer offer : repository.findAll()) {
            if (matches(offer, from, to)) {
                Offer[] tmp = new Offer[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = offer;
                result = tmp;
                Arrays.sort(result);
            }
        }
        if (result.length == 0) throw new NotFoundException("Предложения не найдены!");
        return result;
    }

    public boolean matches(Offer offer, String departure, String arrival) {
        return offer.getDepartureAirport().equalsIgnoreCase(departure)
                & offer.getArrivalAirport().equalsIgnoreCase(arrival);
    }
}
