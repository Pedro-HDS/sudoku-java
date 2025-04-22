package com.sudoku.service;

import com.sudoku.model.Board;
import com.sudoku.model.Cell;

public class GameValidator {
    
    public static boolean isValidMove(Board board, int row, int col, int value) {
        if (!board.isValidPosition(row, col)) {
            return false;
        }
        
        Cell cell = board.getCell(row, col);
        if (cell.isFixed()) {
            return false;
        }
        
        if (!board.isValidValue(value)) {
            return false;
        }
        
        return board.isValidPlacement(row, col, value);
    }
    
    public static boolean isGameComplete(Board board) {
        return board.isFull() && board.isValid();
    }
}
