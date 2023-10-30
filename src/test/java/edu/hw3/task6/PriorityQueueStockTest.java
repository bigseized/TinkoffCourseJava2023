package edu.hw3.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


public class PriorityQueueStockTest {
    private PriorityQueueStock priorityQueueStock;
    @BeforeEach
    public void setUp() {
        priorityQueueStock = getDefaultStockMarket();
    }

    @Test
    @DisplayName("Проверка работы методы добавления акции")
    public void add_shouldAddStock_whenCorrectInput() {
        Stock mostValuableStock = priorityQueueStock.mostValuableStock();
        Stock newStock = new Stock("Hakich", mostValuableStock.price() + 10);
        priorityQueueStock.add(newStock);
        assertThat(priorityQueueStock.mostValuableStock()).isNotEqualTo(mostValuableStock);
    }

    @Test
    @DisplayName("Проверка работы методы удаления акции")
    public void remove_shouldRemoveStock_whenCorrectInput() {
        Stock mostValuableStock = priorityQueueStock.mostValuableStock();
        priorityQueueStock.remove(mostValuableStock);
        assertThat(priorityQueueStock.mostValuableStock()).isNotEqualTo(mostValuableStock);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Проверка на обработку null")
    public void stockAddRemove_shouldThrowException_whenStockIsNull(Stock stock) {
        assertAll(
            () -> assertThatThrownBy(() -> priorityQueueStock.add(stock)).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> priorityQueueStock.remove(stock)).isInstanceOf(IllegalArgumentException.class)
        );
    }
     private PriorityQueueStock getDefaultStockMarket() {
        PriorityQueueStock priorityQueueStock = new PriorityQueueStock();
        priorityQueueStock.add(new Stock("Aeroflot", 2000.7));
        priorityQueueStock.add(new Stock("X5 Group", 1245.3));
        priorityQueueStock.add(new Stock("Tinkoff", 11111.2));
        return priorityQueueStock;
    }
}
