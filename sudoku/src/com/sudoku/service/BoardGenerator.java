
package com.sudoku.service;

import com.sudoku.model.Board;
import com.sudoku.model.Difficulty;

import java.util.Random;

public class BoardGenerator {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private static final Random random = new Random();

    public static Board generateBoard(Difficulty difficulty) {
        int[][] solution = new int[SIZE][SIZE];
        generateSolution(solution, 0, 0);
        
        Board board = new Board();
        
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board.setCell(row, col, solution[row][col], true);
            }
        }
        
        int cellsToReveal = difficulty.getCellsToReveal();
        int cellsToHide = SIZE * SIZE - cellsToReveal;
        
        while (cellsToHide > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            
            if (board.getCell(row, col).getValue() != EMPTY) {
                board.setCell(row, col, EMPTY, false);
                cellsToHide--;
            }
        }
        
        return board;
    }

    private static boolean generateSolution(int[][] board, int row, int col) {
        if (row == SIZE) {
            row = 0;
            if (++col == SIZE) {
                return true; // Tabuleiro completo
            }
        }
        
        if (board[row][col] != EMPTY) {
            return generateSolution(board, row + 1, col);
        }
        
        int[] nums = getShuffledNumbers();
        
        for (int num : nums) {
            if (isValidPlacement(board, row, col, num)) {
                board[row][col] = num;
                if (generateSolution(board, row + 1, col)) {
                    return true;
                }
                board[row][col] = EMPTY; // Retroceder (backtrack)
            }
        }
        
        return false;
    }

    private static int[] getShuffledNumbers() {
        int[] nums = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            nums[i] = i + 1;
        }
        
        for (int i = 0; i < SIZE; i++) {
            int r = random.nextInt(SIZE);
            int temp = nums[i];
            nums[i] = nums[r];
            nums[r] = temp;
        }
        
        return nums;
    }

    private static boolean isValidPlacement(int[][] board, int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[boxRowStart + i][boxColStart + j] == num) {
                    return false;
                }
            }
        }
        
        return true;
    }
}