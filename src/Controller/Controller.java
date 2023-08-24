package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Controller {
    private Model model = new Model();
    private View view = new View(model);


//    private FileService fileService = new FileService();
//    private GenerateLevel generateLevel = new GenerateLevel();
//    JTextField[][] sudokuFields = new JTextField[9][9];
//    int[][] sudokuNet = fileService.read(fileService.getMainBoardPath());

    public void play() {
//        int[][] sudokuNet = model.getSudokuNet();
//        view.printNet(sudokuNet);
        attachListenersToSudokuFields();

    }
//    public Controller() {
//        this.model = new Model();
//        this.view = new View(model);
//        this.sudokuFields = view.getSudokuFields();
////        attachListenersToSudokuFields();
////        model = new Model();
////        view = new View(model);
////        fileService = new FileService();

////        JTextField[][] sudokuFields = view.getSudokuFields();
//
//    }
    private void attachListenersToSudokuFields() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i;
                final int col = j;

                JTextField[][] sudokuFields = view.getSudokuFields();
                sudokuFields[i][j].addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        sudokuFields[row][col].setBackground(view.getSelectedBackgroundColor());
                        view.setSelectedRow(row);
                        view.setSelectedCol(col);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        JTextField source = (JTextField) e.getSource();
                        String input = source.getText();

                        try {
                            int value = Integer.parseInt(input);
                            model.setValue(row, col, value);
                            model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
//                            view.printNet(model.getSudokuNet());
                        } catch (NumberFormatException ex) {
                            // Obsłuż niepoprawny format wprowadzonych danych
                        }
                        sudokuFields[row][col].setBackground(view.getDefaultBackgroundColor());
                    }
                });
            }
        }
    }
//    public void play() {
////        String filePath = "D:/git/SudokuApp/src/gameBoard.txt";
//
////        fileService.loadArray(sudokuNet);
//        int[][] sudokuNet = model.getSudokuNet();
//        view.printNet(sudokuNet);
//        view.printFields();
//        attachListenersToSudokuFields();
//        if (!model.isSolved(model.getSudokuNet())){
//            generateLevel.generateBoard(40, fileService.getSudokuNet());
//        }


//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                sudokuFields[i][j] = view.sudokuField();
//                sudokuFields[i][j].setBounds(view.getMARGIN_LEFT() + j * view.getCELL_SIZE(), view.getMARGIN_TOP() + i * view.getCELL_SIZE(), view.getCELL_SIZE(), view.getCELL_SIZE());
//                add(sudokuFields[i][j]);
//
//                final int row = i;
//                final int col = j;
//                sudokuFields[i][j].addFocusListener(new FocusAdapter() {
//                    @Override
//                    public void focusGained(FocusEvent e) {
//                        view.getSudokuFields()[row][col].setBackground(view.getSelectedBackgroundColor());
//                        view.selectedRow = row;
//                        view.selectedCol = col;
//                        System.out.println(row);
//                        System.out.println(col);
//                    }
//                    @Override
//                    public void focusLost(FocusEvent e) {
//                        JTextField source = (JTextField) e.getSource();
//                        String input = source.getText();
//
//                        try {
//                            int value = Integer.parseInt(input);
//                            System.out.println(input);
//                            model.setValue(row, col, value);
//                            fileService.save(model.getSudokuNet(), fileService.getMainBoardPath());
//                            view.printNet(model.getSudokuNet());
//                        } catch (NumberFormatException ex) {
//                            //TODO:na if`ach
//                        }
//                        sudokuFields[row][col].setBackground(view.getDefaultBackgroundColor());
//                    }
//                });
//            }
//        }


//        fileService.read(model.getMainPath());
//        int[][] sudokuNet = fileService.read(model.getMainPath());
//        if (model.isSolved(sudokuNet)) {

//            model.setSudokuNet(sudokuNet);
//        }


//        if (!model.isSolved(sudokuNet)) {
//            while (!model.isSolved(sudokuNet)) {
//                fileService.read(fileService.getMainBoardPath());
////                view.printNet(sudokuNet);
////                System.out.print("Enter X: ");
////                int y = scanner.nextInt() - 1;
////                System.out.print("Enter Y: ");
////                int x = scanner.nextInt() - 1;
////                System.out.print("Enter Digit: ");
////                int digit = scanner.nextInt();
////                model.setValue(x, y, digit, sudokuNet);
//                fileService.save(sudokuNet, fileService.getMainBoardPath());
//            }
//        }
//    }
    }



