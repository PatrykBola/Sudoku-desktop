package Controller;

import Model.Levels.GenerateLevel;
import Model.Model;
import View.View;
import Model.FileService;

import java.io.IOException;
import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;
    private FileService fileService = new FileService();
    private GenerateLevel generateLevel = new GenerateLevel();

    //    Scanner scanner = new Scanner(System.in);
    public Controller() {
        model = new Model();
        view = new View(model);
        fileService = new FileService();

    }

    //    public int[][] loadSudokuFromFile() {
//        return fileService.read(fileService.getMainBoardPath());
//    }
    public void play() {
//        model = new Model();
        fileService.read(model.getMainPath());
        int[][] sudokuNet = fileService.read(model.getMainPath());
        generateLevel.generateBoard(40, fileService.getSudokuNet());
        model.setSudokuNet(sudokuNet);
    }
//        model = new Model();
//        generateLevel.generateBoard(80, model.getSudokuNet(),model.getMainPath());
////        GenerateLevel.generateBoard(80, model.sudokuNet, model.getMainPath());
//
//        // Wczytaj nową planszę do modelu
//        int[][] sudokuNet = fileService.read(fileService.getMainBoardPath());
//        model.setSudokuNet(sudokuNet);

//        if (!model.isSolved(sudokuNet)) {
//            while (!model.isSolved(sudokuNet)) {
//                fileService.read(fileService.getMainBoardPath());
////                view.printNet(sudokuNet);
//                System.out.print("Enter X: ");
//                int y = scanner.nextInt() - 1;
//                System.out.print("Enter Y: ");
//                int x = scanner.nextInt() - 1;
//                System.out.print("Enter Digit: ");
//                int digit = scanner.nextInt();
//                model.setValue(x, y, digit, sudokuNet);
//                fileService.save(sudokuNet, fileService.getMainBoardPath());
//            }
//        }
}


