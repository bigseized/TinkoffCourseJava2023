package edu.project2.drawers;

import edu.project2.types.Cell;
import edu.project2.types.Coordinate;
import java.util.List;

public interface Drawer {
    void draw(Cell[][] maze);

    void drawWithPath(Cell[][] maze, List<Coordinate> path);
}
