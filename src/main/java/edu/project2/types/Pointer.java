package edu.project2.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pointer {
    int col;
    int row;
    Pointer parent;
}
