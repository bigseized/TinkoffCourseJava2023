package edu.project2.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Cell {
    private int row;
    private int col;
    @Setter
    private Type type;

    public enum Type { WALL, PASSAGE }
}
