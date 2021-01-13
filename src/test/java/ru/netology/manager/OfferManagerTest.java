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

    private final Offer first = new Offer(456, 6900, "LED", "OMS", 180);//3
    private final Offer second = new Offer(486, 2700, "NSK", "OMS", 90);
    private final Offer third = new Offer(490, 5700, "LED", "OMS", 200);//2
    private final Offer fourth = new Offer(367, 7800, "LED", "NSK", 220);
    private final Offer fifth = new Offer(567, 6900, "LED", "OMS", 250);
    private final Offer sixth = new Offer(876, 8900, "LED", "OMS", 210);//4
    private final Offer seventh = new Offer(945, 6900, "LED", "SVO", 90);
    private final Offer eighth = new Offer(684, 3099, "LED", "OMS", 210);//1


    @Test()
    @DisplayName("Поиск предложений LED-OMS начиная с самого дешевого")
    public void shouldReturnAllOfLedOms() {
        Offer[] returned = new Offer[]{first, second, third, fourth, sixth, seventh, eighth};
        Offer[] excepted = new Offer[]{eighth, third, first, sixth};

        doReturn(returned)
                .when(repository)
                .findAll();

        Offer[] actual = manager.findAll("LED", "OMS");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }

    @Test()
    @DisplayName("Поиск предложений LED-OMS с одинаковой стоимостью")
    public void shouldReturnAllOfEqualsAmounts() {
        Offer[] returned = new Offer[]{first, second, third, fourth, fifth, sixth, seventh, eighth};
        Offer[] excepted = new Offer[]{eighth, third, first, fifth, sixth};

        doReturn(returned)
                .when(repository)
                .findAll();

        Offer[] actual = manager.findAll("LED", "OMS");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }

    @Test()
    @DisplayName("Поиск единственного предложения LED-OMS с одинаковой стоимостью")
    public void shouldReturnOnlyOneOffer() {
        Offer[] returned = new Offer[]{first, second, third, fourth, fifth, sixth, seventh, eighth};
        Offer[] excepted = new Offer[]{seventh};

        doReturn(returned)
                .when(repository)
                .findAll();

        Offer[] actual = manager.findAll("LED", "SVO");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }
}