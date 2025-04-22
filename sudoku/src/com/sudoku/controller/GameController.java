package com.sudoku.controller;

import com.sudoku.model.Board;
import com.sudoku.model.Difficulty;
import com.sudoku.service.BoardGenerator;
import com.sudoku.service.GameValidator;
import com.sudoku.view.ConsoleView;

import java.util.Scanner;

public class GameController {
    private Board board;
    private final ConsoleView view;
    private final Scanner scanner;
    
    public GameController() {
        this.view = new ConsoleView();
        this.scanner = new Scanner(System.in);
    }
    
    public GameController(Board initialBoard) {
        this();
        this.board = initialBoard;
    }
    
    public void startGame() {
        if (board == null) {
            Difficulty difficulty = selectDifficulty();
            board = BoardGenerator.generateBoard(difficulty);
        }
        
        view.displayWelcome();
        
        while (!GameValidator.isGameComplete(board)) {
            view.displayBoard(board);
            makeMove();
        }
        
        view.displayBoard(board);
        view.displayCongratulations();
        scanner.close();
    }
    
    private Difficulty selectDifficulty() {
        view.displayDifficultyMenu();
        
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        return Difficulty.EASY;
                    case 2:
                        return Difficulty.MEDIUM;
                    case 3:
                        return Difficulty.HARD;
                    default:
                        view.displayInvalidInput("Por favor, digite um número entre 1 e 3.");
                }
            } catch (NumberFormatException e) {
                view.displayInvalidInput("Entrada inválida. Digite um número.");
            }
        }
    }
    
    private void makeMove() {
        int row, col, value;
        
        while (true) {
            try {
                view.promptForRow();
                row = Integer.parseInt(scanner.nextLine()) - 1; // Ajuste para base-0
                
                view.promptForColumn();
                col = Integer.parseInt(scanner.nextLine()) - 1; // Ajuste para base-0
                
                if (!board.isValidPosition(row, col)) {
                    view.displayInvalidPosition();
                    continue;
                }
                
                if (board.getCell(row, col).isFixed()) {
                    view.displayFixedCellError();
                    continue;
                }
                
                view.promptForNumber();
                value = Integer.parseInt(scanner.nextLine());
                
                if (!board.isValidValue(value)) {
                    view.displayInvalidNumber();
                    continue;
                }
                
                if (value != 0 && !GameValidator.isValidMove(board, row, col, value)) {
                    view.displayInvalidMove();
                    continue;
                }
                
                board.setCell(row, col, value, false);
                break;
                
            } catch (NumberFormatException e) {
                view.displayInvalidInput("Entrada inválida. Digite um número.");
            }
        }
    }
}
