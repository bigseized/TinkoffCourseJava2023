package edu.hw3.task6;

import java.util.PriorityQueue;

public class PriorityQueueStock implements StockMarket {
    private final PriorityQueue<Stock> stockQueue = new PriorityQueue<>(new StockComparator());

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Добавляемая акция не может быть null");
        }
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Удаляемая акция не может быть null");
        }
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}
