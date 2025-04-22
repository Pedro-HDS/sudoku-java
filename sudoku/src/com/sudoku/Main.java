package com.sudoku;

import com.sudoku.controller.GameController;
import com.sudoku.model.Board;
import com.sudoku.model.Cell;
import com.sudoku.service.GameInitializer;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            Board board = GameInitializer.initializeFromArgs(args);
            GameController controller = new GameController(board);
            controller.startGame();
        } else {
            GameController controller = new GameController();
            controller.startGame();
        }
    }
}