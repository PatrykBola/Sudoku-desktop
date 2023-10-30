package Model.Levels;

import Model.FileService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateLevel {
    private FileService fileService = new FileService();

    public int[][] generateBoard(int emptyCells, int[][] sudokuNet, int[][] reloadNet, int[][] compareBoard) {
        fillBoard(sudokuNet);
        fileService.save(sudokuNet, fileService.getCompareBoardPath());
        removeCells(emptyCells, sudokuNet);
        fileService.save(sudokuNet, fileService.getMainBoardPath());
        fileService.save(sudokuNet, fileService.getBoardToReload());
        return sudokuNet;

    }

    public void fillBoard(int[][] sudokuNet) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        generateBoardRek(0, 0,numbers, sudokuNet);
    }

    private boolean generateBoardRek(int x, int y,List<Integer> numbers, int[][] sudokuNet) {
        if (y == 9) {
            y = 0;
            x++;
            if (x == 9) {
                return true;
            }
        }

        int boxX = x / 3 * 3;
        int boxY = y / 3 * 3;
        for (int i = 0; i < 9; i++) {
            int number = numbers.get(i);
            if (existInBoard(number, x, y, boxX, boxY, sudokuNet)) {
                sudokuNet[x][y] = number;
                if (generateBoardRek(x, y + 1, numbers, sudokuNet)) {
                    return true;
                }
            }
        }
        sudokuNet[x][y] = 0;
        return false;
    }

    private boolean existInBoard(int number, int row, int col, int boxX, int boxY, int[][] sudokuNet) {
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

    private void removeCells(int toRemove, int[][] sudokuNet) {
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
