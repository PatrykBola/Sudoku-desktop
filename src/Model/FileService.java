package Model;

import java.io.*;

public class FileService {
    private final String mainBoardPath= "D:/git/SudokuApp/src/gameBoard.txt";
    private final String compareBoardPath= "D:/git/SudokuApp/src/boardToCompare.txt";
    private final String boardToReload= "D:/git/SudokuApp/src/boardToReload.txt";
    private final String otherDataPath = "D:/git/SudokuApp/src/otherDate.txt";
    public String getMainBoardPath() {
        return this.mainBoardPath;
    }
    public String getBoardToReload() {
        return boardToReload;
    }
    public String getCompareBoardPath() {return this.compareBoardPath;}

    public void save(int[][] sudokuNet, String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < sudokuNet[i].length; j++) {
                    writer.print(sudokuNet[i][j]);
                }
                writer.println();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public void saveWrongAnswers(int wrongs){
        try {
            PrintWriter pw = new PrintWriter(otherDataPath);
            pw.print(wrongs);
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int readWrongAnswers(){
         int wrongs = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(otherDataPath))) {
            String line = br.readLine(); 
            wrongs = Integer.parseInt(String.valueOf(line));
            
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }
        return wrongs;
    }
    public int[][] read(int[][] arrayBoard, String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            int x = 0;
            while (line != null && x < 9) {
                String[] parts = line.split("");
                for (int i = 0; i < parts.length; i++) {
                    arrayBoard[x][i] = Integer.parseInt(parts[i]);
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
        return arrayBoard;
    }
}


