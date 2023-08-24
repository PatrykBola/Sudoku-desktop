package Model;

import Model.Levels.GenerateLevel;
import View.View;

import javax.swing.*;
import java.io.File;

public class Model {
    public FileService fileService = new FileService();
    private int[][] sudokuNet = fileService.read(fileService.getMainBoardPath());
    public Model(){
        FileService fileService = new FileService();
         int[][] sudokuNet = fileService.read(fileService.getMainBoardPath());
    }
//    private FileService fileService = new FileService();

//    private JTextField[][] sudokuFields =

    public int[][] getSudokuNet() {
        return sudokuNet;
    }

    public void setSudokuNet(int[][] sudokuArr) {
        this.sudokuNet = sudokuArr;
    }

    public int getValue(int x, int y) {
        return sudokuNet[x][y];
    }

    public boolean setValue(int x, int y, int digit) {
        this.sudokuNet[x][y] = digit;
//        if (isValid(x, y, digit)) {
//            this.sudokuNet[x][y] = digit;
//            fileService.save(this.sudokuNet, fileService.getMainBoardPath()); // Zapis do pliku po ustawieniu wartości
//            return true;
//        }
        return true;
    }

    public boolean isValid(int x, int y, int digit) {
        for (int i = 0; i < 9; i++) {
            if (sudokuNet[x][i] == digit) {             //Sprawdza X
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudokuNet[i][y] == digit) {             //Sprawdza Y
                return false;
            }
        }
        int boxX = x - x % 3;
        int boxY = y - y % 3;
        for (int i = boxX; i < boxX + 3; i++) {     // Sprawdza box 3x3
            for (int j = boxY; j < boxY + 3; j++) {
                if (sudokuNet[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolved(int[][] sudokuNet) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (this.sudokuNet[x][y] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
