package Model;

import java.io.*;

public class FileService {
    private final String mainBoardPath= "D:/git/SudokuApp/src/gameBoard.txt";
    private final String compareBoardPath= "D:/git/SudokuApp/src/boardToCompare.txt";
    private int[][] sudokuNet = new int[9][9];

    public int[][] getSudokuNet() {
        return sudokuNet;
    }

    public String getMainBoardPath() {
        return this.mainBoardPath;
    }
    public String getCompareBoardPath() {return this.compareBoardPath;}
    public void save(int[][] sudokuNet, String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (int i = 0; i < sudokuNet.length; i++) {
                for (int j = 0; j < sudokuNet[i].length; j++) {
                    writer.print(sudokuNet[i][j]);
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
        //truncate
    }

    public int[][] read(String filePath) {
//        int[][] sudokuNet = new int[9][9];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            int x = 0;
            while (line != null && x < 9) {
                String[] parts = line.split("");
                for (int i = 0; i < parts.length; i++) {
                    sudokuNet[x][i] = Integer.parseInt(parts[i]);
                }
                x++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sudokuNet;
    }
}


