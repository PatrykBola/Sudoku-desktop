package Model;

import Model.Levels.GenerateLevel;

import javax.swing.*;

public class Model {
    public FileService fileService = new FileService();
    public GenerateLevel generateLevel = new GenerateLevel();
    public int[][] sudokuNet;
    JTextField[][] sudokuFields = new JTextField[9][9];

    public int[][] readSudokuNetFromFile(){
        this.sudokuNet = fileService.read(fileService.getMainBoardPath());
        return sudokuNet;
    }


//    public JTextField sudokuField() {
//        JTextField field = new JTextField();
//        field.setFont(new Font("Times New Roman", Font.BOLD, 14));
//        field.setHorizontalAlignment(JTextField.CENTER);
//        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//
//        return field;
//    }

//    public void creatingField(){
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                sudokuFields[i][j] = sudokuField();
//            }
//        }
//    }
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
    public void loadIntArrayToJTextFieldArray(int[][] sudokunet,JTextField[][] sudokuFields) {
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
       public void newGameButton(){
//        creatingFields();
//        loadIntArrayToJTextFieldArray(sudokuNet);
           generateLevel.generateBoard(30,sudokuNet);
           updateSudokuFields(sudokuNet);
           System.out.println(sudokuNet[1][1]);

           for (int i = 0; i < 9; i++) {
               for (int j = 0; j < 9; j++) {
                   System.out.print(sudokuNet[i][j] + " ");
               }
               System.out.println();
           }
           System.out.println(getValue(0,0));
       }

        public int[][] getSudokuNet () {
            return sudokuNet;
        }

        public void setSudokuNet ( int[][] sudokuArr){
            this.sudokuNet = sudokuArr;
        }

        public int getValue ( int x, int y){
            return sudokuNet[x][y];
        }

        public boolean setValue ( int x, int y, int digit){
            sudokuNet[x][y] = digit;
//        if (isValid(x, y, digit)) {
//            this.sudokuNet[x][y] = digit;
//            fileService.save(this.sudokuNet, fileService.getMainBoardPath()); // Zapis do pliku po ustawieniu wartości
//            return true;
//        }
            return true;
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
//        public boolean isSolved ( int[][] sudokuNet){
//            for (int x = 0; x < 9; x++) {
//                for (int y = 0; y < 9; y++) {
//                    if (this.sudokuNet[x][y] == 0) {
//                        return false;
//                    }
//                }
//            }
//            return true;
//        }



}
