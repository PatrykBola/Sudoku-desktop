package Model.Levels;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GenerateLevel {

    private static int[][] sudokuNet = new int[9][9];

    public static void generateBoard(int emptyCells) {
        fillBoard();
        removeCells(emptyCells);
    }


    private static void fillBoard() {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        generateBoardRek(0, 0, random, numbers);
    }

    private static boolean generateBoardRek(int x, int y, Random random, List<Integer> numbers) {
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
            int number = numbers.get(i);
            if (existInBoard(number, x, y, boxX, boxY)) {
                sudokuNet[x][y] = number;
                if (generateBoardRek(x, y + 1, random, numbers)) {
                    return true;
                }
            }
        }

        sudokuNet[x][y] = 0; // cofnięcie wyboru liczby
        return false;
    }

    private static boolean existInBoard(int number, int row, int col, int boxX, int boxY) {
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

    private static void removeCells(int toRemove) {
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

    public static void main(String[] args) {
        generateBoard(25);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuNet[i][j] + " ");
            }
            System.out.println();
        }
    }

}
