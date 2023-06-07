package Model;

import Controller.Controller;

import java.io.*;

public class FileService {
    private String filePath;
    private String tempFilePath;

    public FileService() {
        this.filePath = "D:/git/SudokuApp/src/data.txt";
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void save(int[][] sudokuNet) {
        try {
            PrintWriter writer = new PrintWriter("data.txt");
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
        int[][] sudokuNet = new int[9][9];
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

    public void printNet(int[][] sudokuNet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuNet[i][j] + " ");
            }
            System.out.println();
        }
    }

}


