package edu.hw3.task6;

import java.util.Comparator;

public class StockComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock stock1, Stock stock2) {
        return Double.compare(stock2.price(), stock1.price());
    }
}
