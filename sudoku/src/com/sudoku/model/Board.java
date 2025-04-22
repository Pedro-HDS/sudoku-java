package com.sudoku.model;

public class Board {
    private final Cell[][] cells;
    private final int SIZE = 9;
    private final int SUBGRID_SIZE = 3;
    private final int EMPTY = 0;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        initializeEmptyCells();
    }

    private void initializeEmptyCells() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cells[row][col] = new Cell(row, col);
            }
        }
    }

    public Cell getCell(int row, int col) {
        if (isValidPosition(row, col)) {
            return cells[row][col];
        }
        throw new IllegalArgumentException("Posição inválida: " + row + "," + col);
    }

    public void setCell(int row, int col, int value, boolean fixed) {
        if (isValidPosition(row, col)) {
            cells[row][col] = new Cell(row, col, value, fixed);
        } else {
            throw new IllegalArgumentException("Posição inválida: " + row + "," + col);
        }
    }

    public void setCell(Cell cell) {
        int row = cell.getRow();
        int col = cell.getColumn();
        if (isValidPosition(row, col)) {
            cells[row][col] = cell;
        } else {
            throw new IllegalArgumentException("Posição inválida: " + row + "," + col);
        }
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    public boolean isValidValue(int value) {
        return value >= 0 && value <= SIZE;
    }

    public boolean isValidPlacement(int row, int col, int num) {
        if (num == EMPTY) {
            return true; // Sempre podemos colocar uma célula vazia
        }

        // Verificar se o número já está presente na linha
        for (int i = 0; i < SIZE; i++) {
            if (i != col && cells[row][i].getValue() == num) {
                return false;
            }
        }

        // Verificar se o número já está presente na coluna
        for (int i = 0; i < SIZE; i++) {
            if (i != row && cells[i][col].getValue() == num) {
                return false;
            }
        }

        // Verificar a subgrade 3x3
        int boxRowStart = row - row % SUBGRID_SIZE;
        int boxColStart = col - col % SUBGRID_SIZE;

        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                int currentRow = boxRowStart + i;
                int currentCol = boxColStart + j;
                if ((currentRow != row || currentCol != col) && 
                    cells[currentRow][currentCol].getValue() == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (cells[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid() {
        // Verificar linhas
        for (int row = 0; row < SIZE; row++) {
            boolean[] used = new boolean[SIZE + 1];
            for (int col = 0; col < SIZE; col++) {
                int num = cells[row][col].getValue();
                if (num == EMPTY || used[num]) {
                    return false;
                }
                used[num] = true;
            }
        }

        // Verificar colunas
        for (int col = 0; col < SIZE; col++) {
            boolean[] used = new boolean[SIZE + 1];
            for (int row = 0; row < SIZE; row++) {
                int num = cells[row][col].getValue();
                if (num == EMPTY || used[num]) {
                    return false;
                }
                used[num] = true;
            }
        }

        // Verificar subgrades 3x3
        for (int boxRow = 0; boxRow < SUBGRID_SIZE; boxRow++) {
            for (int boxCol = 0; boxCol < SUBGRID_SIZE; boxCol++) {
                boolean[] used = new boolean[SIZE + 1];
                for (int i = 0; i < SUBGRID_SIZE; i++) {
                    for (int j = 0; j < SUBGRID_SIZE; j++) {
                        int row = boxRow * SUBGRID_SIZE + i;
                        int col = boxCol * SUBGRID_SIZE + j;
                        int num = cells[row][col].getValue();
                        if (num == EMPTY || used[num]) {
                            return false;
                        }
                        used[num] = true;
                    }
                }
            }
        }

        return true;
    }

    public int getSize() {
        return SIZE;
    }

    public String getBoardState() {
        StringBuilder state = new StringBuilder();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (col > 0) state.append(" ");
                state.append(cells[row][col].toString());
            }
        }
        return state.toString();
    }

    public void clearCell(int row, int col) {
        if (!cells[row][col].isFixed()) {
            cells[row][col].setValue(EMPTY);
        }
    }
}