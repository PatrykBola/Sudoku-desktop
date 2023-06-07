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
    private int choice;
    public void play() {


//        model.chooseLevelEasy();
        int[][] sudokuNet = fileService.read(fileService.getFilePath());
//        model.sudokuNetToPlay();
        view.printNet(sudokuNet);

//        if (model.isSolved(sudokuNet)){
//            choice = scanner.nextInt();
//            System.out.println("Choose Level:\n 1-EASY \n 2-Medium \n 3-HARD");
//            switch (choice){
//                case 1: model.chooseLevelEasy(22);
//                    fileService.save(sudokuNet);
//                case 2: model.chooseLevelMedium();
//                    fileService.save(sudokuNet);
//                case 3: model.chooseLevelHard();
//                    fileService.save(sudokuNet);
//            }
//        }
//         if (!model.isSolved(sudokuNet)){
//            while (!model.isSolved(sudokuNet)) {
//                view.printNet(sudokuNet);
//                System.out.print("Enter X: ");
//                int y = scanner.nextInt() - 1;
//                System.out.print("Enter Y: ");
//                int x = scanner.nextInt() - 1;
//                System.out.print("Enter Digit: ");
//                int digit = scanner.nextInt();
//                model.setValue(x, y, digit, sudokuNet);
//                fileService.save(sudokuNet);
//            }
//        }


//    fileService.save(sudokuNet);
//        fileService.read();


    }
}

