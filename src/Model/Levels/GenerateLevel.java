package Model.Levels;

import Model.FileService;
import Model.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateLevel {
    FileService fileService = new FileService();
    Model model = new Model();
    String mainPath = fileService.getMainBoardPath();
    String comparePath = fileService.getCompareBoardPath();
//    private String mainPath = fileService.getMainBoardPath();
//
//    public String getMainPath() {
//        return mainPath;
//    }

//    int[][] sudokuNet = model.getSudokuNet();

    public void generateBoard(int emptyCells, int[][] sudokuNet) {
        fillBoard(sudokuNet, mainPath);
        fileService.save(sudokuNet,comparePath);
        removeCells(emptyCells, sudokuNet);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuNet[i][j] + " ");
            }
            System.out.println();
        }
        fileService.save(sudokuNet,mainPath);
    }

    private int[][] fillBoard(int[][] sudokuNet, String mainPath) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        generateBoardRek(0, 0, random, numbers, sudokuNet);
//        fileService.save(sudokuNet, mainPath);
        fileService.save(sudokuNet,mainPath);
        return sudokuNet;
    }

    private  boolean generateBoardRek(int x, int y, Random random, List<Integer> numbers, int[][] sudokuNet) {
        if (y == 9) {
            y = 0;
            x++;
            if (x == 9) {
                return true; // plansza jest wypełniona
            }
        }

        int boxX = x / 3 * 3;
        int boxY = y / 3 * 3;

        for (int i = 0; i < 9; i++) {
//            System.out.println(sudokuNet[i][i]);
            int number = numbers.get(i);
            if (existInBoard(number, x, y, boxX, boxY, sudokuNet)) {
                sudokuNet[x][y] = number;
                if (generateBoardRek(x, y + 1, random, numbers,sudokuNet)) {
                    return true;
                }
            }
        }



//
        sudokuNet[x][y] = 0; // cofnięcie wyboru liczby
        return false;
    }

    private  boolean existInBoard(int number, int row, int col, int boxX, int boxY, int[][] sudokuNet) {
        for (int i = 0; i < 9; i++) {
            if (sudokuNet[row][i] == number || sudokuNet[i][col] == number) {
                return false;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudokuNet[boxX + i][boxY + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private  void removeCells(int toRemove, int[][] sudokuNet) {
        Random random = new Random();
        for (int i = 0; i < toRemove; i++) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);
            if (sudokuNet[x][y] != 0) {
                sudokuNet[x][y] = 0;
            } else {
                i--;
            }
        }
    }


}
