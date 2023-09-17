package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Controller {
    private Model model = new Model();
    private View view = new View();
    private int[][] sudokuNet;
    private int[][] reloadNet;
    private int[][] compareNet;
    private JTextField[][] sudokuFields = new JTextField[9][9];

    public void play() throws InterruptedException {
        this.sudokuNet = model.readSudokuNetFromFile();
        this.reloadNet = model.readReloadBoard();
        this.compareNet = model.readCompareBoard();
        view.creatingFields(sudokuFields);
        model.loadIntArrayToJTextFieldArray(sudokuNet, sudokuFields);
        view.creatingGui();
        view.changingDefaultFieldsColor(reloadNet, sudokuFields);


        System.out.println(reloadNet[0][0]);
        System.out.println(reloadNet[8][8]);

        attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet);



        view.addReloadButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.reloadBoard(sudokuNet, reloadNet);
                view.updateSudokuFields(sudokuNet,sudokuFields);
                attachListenersToSudokuFields(sudokuFields,reloadNet,compareNet);
            }
        });
//
//        view.addNewGameButtonActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              model.newGameButton();`
//            }
//        });
    }

    public void attachListenersToSudokuFields(JTextField[][] sudokuFields, int[][] reloadNet, int[][] compareNet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int row = i;
                int col = j;

                if (reloadNet[i][j] == 0) {

                    sudokuFields[i][j].addFocusListener(new FocusAdapter() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            JTextField color = (JTextField) e.getSource();
                            color.setBackground(view.getSelectedBackgroundColor());
                            color.getCaret().setVisible(false);
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            JTextField source = (JTextField) e.getSource();
                            String input = source.getText();
//                            System.out.println("a");
                            try {
//                                System.out.println("b");
                                int value = Integer.parseInt(input);

                                        if (value == 1){
                                            view.shortInfo("Wrong");
                                        }

                                model.setValue(row, col, value);
//                                System.out.println("c");
                                view.printNet(model.getSudokuNet());
                            } catch (NumberFormatException ex) {
                            } catch (InterruptedException ex) {
                                throw new RuntimeException(ex);
                            }
                            source.setBackground(view.getDefaultBackgroundColor());
                        }
                    });
//                    sudokuFields[i][j].addFocusListener(new FocusAdapter() {
//                        public void focusGained(FocusEvent e) {
//                            for (int k = 0; k < 9; k++) {
//                                for (int l = 0; l < 9; l++) {
//                                    JTextField color = (JTextField) e.getSource();
//                                    System.out.println(reloadNet[k][l]);
//                                    color.setBackground(view.getSelectedBackgroundColor());
//                    }
//
//                                );
//
                    sudokuFields[i][j].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            JTextField source = (JTextField) e.getSource();
                            char typedChar = e.getKeyChar();

                            if (Character.isDigit(typedChar)) {
                                source.setText(String.valueOf(typedChar));
                                source.setBackground(view.getSelectedBackgroundColor());
                                source.getCaret().setVisible(false);
                                e.consume();

                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
                                source.setBackground(view.getDefaultBackgroundColor());
                                source.setEditable(false);
                                Component nextComponent = getNextComponent(sudokuFields, reloadNet);
                                if (nextComponent != null) {
                                    nextComponent.requestFocusInWindow();
                                }

                            }

                        }
                    });

                } else {
                    sudokuFields[i][j].setEditable(false);
                    sudokuFields[i][j].setBackground(view.getDefaultFieldColor());
                }

            }
        }
    }
    private Component getNextComponent(JTextField[][] sudokuFields, int[][] reloadNet) {
        //TODO: Na komponent ktory jest nullem !
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (reloadNet[i][j] != 0) {
                    return sudokuFields[i][j];
                }
            }
        }
        return null;

    }
}








