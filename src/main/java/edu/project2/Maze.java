package edu.project2;

import edu.project2.types.Cell;
import lombok.Getter;

public class Maze {
    private final int height;
    private final int width;
    @Getter
    private final Cell[][] grid;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }
}
