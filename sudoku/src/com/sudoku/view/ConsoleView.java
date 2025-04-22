package com.sudoku.view;

import com.sudoku.model.Board;
import com.sudoku.model.Cell;

public class ConsoleView {
    
    public void displayWelcome() {
        System.out.println("Bem-vindo ao Sudoku!");
        System.out.println("====================");
    }
    
    public void displayDifficultyMenu() {
        System.out.println("Selecione a dificuldade:");
        System.out.println("1 - Fácil (35 células reveladas)");
        System.out.println("2 - Médio (30 células reveladas)");
        System.out.println("3 - Difícil (25 células reveladas)");
        System.out.print("Escolha (1-3): ");
    }
    
    public void displayBoard(Board board) {
        int size = board.getSize();
        
        System.out.println("\n  | 1 2 3 | 4 5 6 | 7 8 9 |");
        System.out.println("--+-------+-------+-------+");
        
        for (int row = 0; row < size; row++) {
            System.out.print((row + 1) + " | ");
            
            for (int col = 0; col < size; col++) {
                Cell cell = board.getCell(row, col);
                
                if (cell.isEmpty()) {
                    System.out.print("  ");
                } else {
                    System.out.print(cell.getValue() + " ");
                }
                
                if ((col + 1) % 3 == 0) {
                    System.out.print("| ");
                }
            }
            
            System.out.println();
            
            if ((row + 1) % 3 == 0) {
                System.out.println("--+-------+-------+-------+");
            }
        }
    }
    
    public void displayCongratulations() {
        System.out.println("Parabéns! Você completou o Sudoku corretamente!");
    }
    
    public void displayInvalidInput(String message) {
        System.out.println(message);
    }
    
    public void displayInvalidPosition() {
        System.out.println("Posição inválida! Digite valores entre 1 e 9.");
    }
    
    public void displayFixedCellError() {
        System.out.println("Essa posição não pode ser modificada!");
    }
    
    public void displayInvalidNumber() {
        System.out.println("Número inválido! Digite valores entre 0 e 9.");
    }
    
    public void displayInvalidMove() {
        System.out.println("Jogada inválida! Este número não pode ser colocado aqui.");
    }
    
    public void promptForRow() {
        System.out.print("Digite a linha (1-9): ");
    }
    
    public void promptForColumn() {
        System.out.print("Digite a coluna (1-9): ");
    }
    
    public void promptForNumber() {
        System.out.print("Digite o número (1-9, 0 para apagar): ");
    }
}