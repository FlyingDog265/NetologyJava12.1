package ru.netology.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Offer;
import ru.netology.repository.OfferRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class OfferManagerTest {

    @Mock
    private OfferRepository repository;

    @InjectMocks
    private OfferManager manager;

    private final Offer first = new Offer(456, 6900, "LED", "OMS", 180);
    private final Offer second = new Offer(486, 2700, "NSK", "OMS", 90);
    private final Offer third = new Offer(490, 5700, "LED", "OMS", 200);
    private final Offer fourth = new Offer(367, 7800, "LED", "NSK", 220);
    private final Offer fifth = new Offer(567, 6900, "LED", "OMS", 210);


    @Test()
    @DisplayName("Поиск предложений LED-OMS начиная с самого дешевого")
    public void shouldReturnAllOfLedOms() {
        Offer[] returned = new Offer[]{first, second, third, fourth};
        Offer[] excepted = new Offer[]{third, first};

        doReturn(returned)
                .when(repository)
                .findAll();

        Offer[] actual = manager.findAll("LED", "OMS");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }

    @Test()
    @DisplayName("Поиск предложений LED-OMS с одинаковой стоимостью")
    public void shouldReturnAllOfEqualsAmounts() {
        Offer[] returned = new Offer[]{first, second, third, fourth, fifth};
        Offer[] excepted = new Offer[]{third, first, fifth};

        doReturn(returned)
                .when(repository)
                .findAll();

        Offer[] actual = manager.findAll("LED", "OMS");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }
}