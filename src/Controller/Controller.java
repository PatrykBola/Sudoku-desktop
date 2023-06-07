package Controller;

import Model.Levels.GenerateLevel;
import Model.Model;
import View.View;
import Model.FileService;

import java.util.Scanner;

public class Controller {
    private Model model = new Model();
    private View view = new View();
    private FileService fileService = new FileService();
    Scanner scanner = new Scanner(System.in);
    public void play() {

        GenerateLevel.generateBoard(25);
        int[][] sudokuNet = fileService.read(fileService.getFilePath());
        model.setSudokuNet(sudokuNet);

        if (!model.isSolved(sudokuNet)) {
            while (!model.isSolved(sudokuNet)) {
                fileService.read(fileService.getFilePath());
                view.printNet(sudokuNet);
                System.out.print("Enter X: ");
                int y = scanner.nextInt() - 1;
                System.out.print("Enter Y: ");
                int x = scanner.nextInt() - 1;
                System.out.print("Enter Digit: ");
                int digit = scanner.nextInt();
                model.setValue(x, y, digit, sudokuNet);
                fileService.save(sudokuNet);
            }
        }
    }
}

