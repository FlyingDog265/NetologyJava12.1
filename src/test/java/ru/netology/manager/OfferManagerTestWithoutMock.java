package ru.netology.manager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Offer;
import ru.netology.helpers.NotFoundException;
import ru.netology.repository.OfferRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OfferManagerTestWithoutMock {
    OfferRepository repository = new OfferRepository();
    OfferManager manager = new OfferManager(repository);

    private final Offer first = new Offer(456, 6900, "LED", "OMS", 180);
    private final Offer second = new Offer(486, 2700, "NSK", "OMS", 90);
    private final Offer third = new Offer(490, 5700, "LED", "OMS", 200);
    private final Offer fourth = new Offer(367, 7800, "LED", "NSK", 220);

    @Test
    @DisplayName("Добавление предложений в репозиторий")
    public void shouldAddSomeStuffInRepo() {
        Offer[] excepted = new Offer[]{third, first};

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);

        Offer[] actual = manager.findAll("LED", "OMS");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }

    @Test
    @DisplayName("Удаление предложений из репозитория")
    public void shouldRemoveSomeStuffFromRepo() {
        Offer[] excepted = new Offer[]{third};

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.removeById(456);

        Offer[] actual = manager.findAll("LED", "OMS");
        assertArrayEquals(excepted, actual, "Найденные направления не соответствуют ожидаемому результату!");
    }

    @Test
    @DisplayName("Удаление предложений из репозитория по несущечтвующему ID")
    public void shouldShowNotFoundExceptionFromRemove() {
        manager.add(first);
        manager.add(second);
        assertThrows(NotFoundException.class, () -> manager.removeById(228),
                "Ошибка удаления несуществующего предложения из репо поломалась");
    }


    @Test
    @DisplayName("Поиск предложений по несуществующему направлению")
    public void shouldShowNotFoundExceptionFromSearch() {
        manager.add(first);
        manager.add(second);
        assertThrows(NotFoundException.class, () -> manager.findAll("PIT", "BUL"),
                "Ошибка удаления несуществующего предложения из репо поломалась");
    }
}
