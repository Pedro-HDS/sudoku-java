package com.sudoku.model;

public class Cell {
    private int value;
    private boolean fixed;
    private final int row;
    private final int column;

    public Cell(int row, int column, int value, boolean fixed) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.fixed = fixed;
    }

    public Cell(int row, int column) {
        this(row, column, 0, false);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public String toString() {
        return String.format("%d,%d;%d,%b", row, column, value, fixed);
    }
}