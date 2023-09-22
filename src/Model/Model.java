package Model;

import Model.Levels.GenerateLevel;

import javax.swing.*;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

public class Model {
    public FileService fileService = new FileService();
    public GenerateLevel generateLevel = new GenerateLevel();
    private int[][] sudokuNet = new int[9][9];
    private int[][] reloadNet = new int[9][9];
    private int[][] compareNet = new int[9][9];
    private int[][] defaultColorField = new int[9][9];
    private JTextField[][] sudokuFields = new JTextField[9][9];


    public boolean digitBetweenOneAndNine(char typedChar){
        String val = Character.toString(typedChar);
        int value = Integer.parseInt(val);
        for (int i = 1; i <=9; i++) {
            if (value == i ){
                return true;
            }
        }
        return false;
    }

    public void removeListenerFromOneField(JTextField[][] sudokuFields,int row, int col){
        JTextField field =  sudokuFields[row][col];
        removeFocusListener(field);
        removeKeyListener(field);
    }
    public void removeListenersFromSudokuFields(JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField field = sudokuFields[i][j];
                removeFocusListener(field);
                removeKeyListener(field);
            }
        }
    }

    private void removeFocusListener(JTextField field) {
        for (FocusListener listener : field.getFocusListeners()) {
            field.removeFocusListener(listener);
        }
    }

    private void removeKeyListener(JTextField field) {
        for (KeyListener listener : field.getKeyListeners()) {
            field.removeKeyListener(listener);
        }
    }

    public int[][] newArray(int[][] reloadNet) {
        int[][] tempArray = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (reloadNet[i][j] == 0) {
                    tempArray[i][j] = 1;
                } else {
                    tempArray[i][j] = 0;
                }
            }
        }

        return tempArray;

    }

    public JTextField[][] cleanFields(JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j].setText(" ");
            }
        }
        return sudokuFields;
    }

    public void cleanBoards(int[][] sudokuNet, int[][] reloadNet, int[][] compareNet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuNet[i][j] = 0;
                reloadNet[i][j] = 0;
                compareNet[i][j] = 0;
            }
        }
    }

    public int[][] generateNewBoard(int[][] sudokuNet, int emptyCells) {
        generateLevel.generateBoard(emptyCells, sudokuNet, reloadNet, compareNet);
        return sudokuNet;
    }

    public int[][] readReloadBoard() {
        this.reloadNet = fileService.read(reloadNet, fileService.getBoardToReload());
        return reloadNet;
    }

    public int[][] getReloadNet() {
        return reloadNet;
    }

    public int[][] readCompareBoard() {
        this.compareNet = fileService.read(compareNet, fileService.getCompareBoardPath());
        return compareNet;
    }

    public int[][] reloadBoard(int[][] oldBoard, int[][] newBoard) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                oldBoard[i][j] = newBoard[i][j];
            }
        }
        return newBoard;
    }


    public int[][] readSudokuNetFromFile() {
        this.sudokuNet = fileService.read(sudokuNet, fileService.getMainBoardPath());
        return sudokuNet;
    }

    public void updateSudokuFields(int[][] sudokuNet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuNet[i][j] != 0) {
                    sudokuFields[i][j].setText(String.valueOf(sudokuNet[i][j]));
                } else {
                    sudokuFields[i][j].setText(" ");
                }
            }
        }
    }

    public void loadIntArrayToJTextFieldArray(int[][] sudokunet, JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokunet[i][j] != 0) {
                    sudokuFields[i][j].setText(String.valueOf(sudokunet[i][j]));
                } else {
                    sudokuFields[i][j].setText(" ");
                }
            }
        }
    }

    public int[][] getSudokuNet() {
        return sudokuNet;
    }


    public void setValue(int x, int y, int digit) {
        sudokuNet[x][y] = digit;
//        if (isValid(x, y, digit)) {
//            this.sudokuNet[x][y] = digit;
//            fileService.save(this.sudokuNet, fileService.getMainBoardPath()); // Zapis do pliku po ustawieniu wartości
//            return true;
//        }
//            return true;
    }

    public boolean isValid(int[][] sudokuNet, int[][] compareNet, int x, int y) {
        if (compareNet[x][y] == sudokuNet[x][y]) {
            return true;
        }
        return false;
    }

    //        public boolean isValid ( int x, int y, int digit){
//            for (int i = 0; i < 9; i++) {
//                if (sudokuNet[x][i] == digit) {             //Sprawdza X
//                    return false;
//                }
//            }
//
//            for (int i = 0; i < 9; i++) {
//                if (sudokuNet[i][y] == digit) {             //Sprawdza Y
//                    return false;
//                }
//            }
//            int boxX = x - x % 3;
//            int boxY = y - y % 3;
//            for (int i = boxX; i < boxX + 3; i++) {     // Sprawdza box 3x3
//                for (int j = boxY; j < boxY + 3; j++) {
//                    if (sudokuNet[i][j] == digit) {
//                        return false;
//                    }
//                }
//            }
//            return true;
//        }
//
    public boolean isSolved(int[][] sudokuNet) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (sudokuNet[x][y] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


}
