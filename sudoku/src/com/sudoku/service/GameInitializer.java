package com.sudoku.service;

import com.sudoku.model.Board;
import com.sudoku.model.Cell;

public class GameInitializer {
    
    public static Board initializeFromArgs(String[] args) {
        Board board = new Board();
        
        if (args.length == 1 && args[0].contains(";")) {
            String[] cellsData = args[0].split(" ");
            for (String cellData : cellsData) {
                parseCellData(cellData, board);
            }
        } else {
            for (String cellData : args) {
                parseCellData(cellData, board);
            }
        }
        
        return board;
    }
    
    private static void parseCellData(String cellData, Board board) {
        try {
            String[] parts = cellData.split(";");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Formato inválido para célula: " + cellData);
            }
            
            String[] position = parts[0].split(",");
            String[] valueData = parts[1].split(",");
            
            if (position.length != 2 || valueData.length != 2) {
                throw new IllegalArgumentException("Formato inválido para célula: " + cellData);
            }
            
            int row = Integer.parseInt(position[0]);
            int col = Integer.parseInt(position[1]);
            int value = Integer.parseInt(valueData[0]);
            boolean fixed = Boolean.parseBoolean(valueData[1]);
            
            Cell cell = new Cell(row, col, value, fixed);
            board.setCell(cell);
            
        } catch (Exception e) {
            System.err.println("Erro ao processar célula: " + cellData);
            System.err.println("Erro: " + e.getMessage());
        }
    }
}