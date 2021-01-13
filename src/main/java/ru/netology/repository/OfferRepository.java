package ru.netology.repository;

import ru.netology.domain.Offer;
import ru.netology.helpers.NotFoundException;

public class OfferRepository {
    private Offer[] offers = new Offer[0];

    public void save(Offer item) {
        int length = offers.length + 1;
        Offer[] tmp = new Offer[length];
        System.arraycopy(offers, 0, tmp, 0, offers.length);
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = item;
        offers = tmp;
    }

    public Offer[] findAll() {
        return offers;
    }

    public Offer findById(int id) {
        for (Offer item : offers) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Элемент с id: " + id + " не найден!");
        }
        int length = offers.length - 1;
        Offer[] tmp = new Offer[length];
        int index = 0;
        for (Offer item : offers) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        offers = tmp;
    }
}
