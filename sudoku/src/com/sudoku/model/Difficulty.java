package com.sudoku.model;

public enum Difficulty {
    EASY(35),
    MEDIUM(30),
    HARD(25);

    private final int cellsToReveal;

    Difficulty(int cellsToReveal) {
        this.cellsToReveal = cellsToReveal;
    }

    public int getCellsToReveal() {
        return cellsToReveal;
    }
}