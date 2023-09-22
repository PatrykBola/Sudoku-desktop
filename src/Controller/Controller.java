package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Controller {
    Random random = new Random();
    private Model model = new Model();
    private View view = new View();
    private int emptyCells;
    private int[][] sudokuNet;
    private int[][] reloadNet;
    private int[][] compareNet;
    //    private int emptyCells = 50;
    private JTextField[][] sudokuFields = new JTextField[9][9];
    int count = 0;

    public void play() throws InterruptedException {
        this.sudokuNet = model.readSudokuNetFromFile();
        this.reloadNet = model.readReloadBoard();
        this.compareNet = model.readCompareBoard();
        view.creatingFields(sudokuFields);
        model.loadIntArrayToJTextFieldArray(sudokuNet, sudokuFields);
        view.creatingGui();
        view.setBoxBorders(sudokuFields);
        view.changingDefaultFieldsColor(reloadNet, sudokuFields);
        view.infoLabel(true);

        attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet);

        view.addReloadButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.reloadBoard(sudokuNet, reloadNet);
                view.updateSudokuFields(sudokuNet, sudokuFields);
                attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet);
            }
        });

//        newGameListener();
//
        view.addNewGameButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameListener();
//                view.getBottomLabel().setSize(0,0);
//                view.bottomEMHButtons(true);
//
//                view.addEasyButtonActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        view.bottomEMHButtons(false);
//                        emptyCells = random.nextInt(26, 31);
//                        System.out.println(emptyCells);
//                        creatingNewGame(sudokuFields, reloadNet, sudokuNet, compareNet, emptyCells);
//                        view.getBottomLabel().setSize(200,50);
//
//                    }
//                });
//                view.addMediumButtonActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        emptyCells = random.nextInt(32,39);
//                        System.out.println(emptyCells);
//                        creatingNewGame(sudokuFields,reloadNet,sudokuNet,compareNet,emptyCells);
//                        view.bottomEMHButtons(false);
//                        view.getBottomLabel().setSize(200,50);
//                    }
//                });
//                view.addHardButtonActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        emptyCells = random.nextInt(40,47);
//                        System.out.println(emptyCells);
//                        creatingNewGame(sudokuFields,reloadNet,sudokuNet,compareNet,emptyCells);
//                        view.bottomEMHButtons(false);
//                        view.getBottomLabel().setSize(200,50);
//                    }
//                });

            }
        });
//
    }

    public void newGameListener(){

                view.getBottomLabel().setSize(0,0);
                view.bottomEMHButtons(true);

                view.addEasyButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        view.bottomEMHButtons(false);
//                        emptyCells = random.nextInt(26, 31);
                        emptyCells = 1;
                        System.out.println(emptyCells);
                        creatingNewGame(sudokuFields, reloadNet, sudokuNet, compareNet, emptyCells);
                        view.getBottomLabel().setSize(200,50);

                    }
                });
                view.addMediumButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        emptyCells = random.nextInt(32,39);
                        System.out.println(emptyCells);
                        creatingNewGame(sudokuFields,reloadNet,sudokuNet,compareNet,emptyCells);
                        view.bottomEMHButtons(false);
                        view.getBottomLabel().setSize(200,50);
                    }
                });
                view.addHardButtonActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        emptyCells = random.nextInt(40,47);
                        System.out.println(emptyCells);
                        creatingNewGame(sudokuFields,reloadNet,sudokuNet,compareNet,emptyCells);
                        view.bottomEMHButtons(false);
                        view.getBottomLabel().setSize(200,50);
                    }
                });


    }

    public void creatingNewGame(JTextField[][] sudokuFields, int[][] reloadNet, int[][] sudokuNet, int[][] compareNet, int emptyCells) {
        model.cleanBoards(sudokuNet, reloadNet, compareNet);
        model.generateNewBoard(sudokuNet, emptyCells);
        model.readReloadBoard();
        model.readCompareBoard();
        model.loadIntArrayToJTextFieldArray(sudokuNet, sudokuFields);
        view.updateSudokuFields(sudokuNet, sudokuFields);
        view.reloadingDefaultFieldsColor(reloadNet, sudokuFields);
        view.changingDefaultFieldsColor(reloadNet, sudokuFields);
        model.removeListenersFromSudokuFields(sudokuFields);
        attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet);
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
                            source.setBackground(view.getDefaultBackgroundColor());

                        }
                    });

                    sudokuFields[i][j].addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyTyped(KeyEvent e) {
                            JTextField source = (JTextField) e.getSource();
                            char typedChar = e.getKeyChar();

                            if (model.digitBetweenOneAndNine(typedChar)) {

                                source.setText(String.valueOf(typedChar));
                                source.setBackground(view.getSelectedBackgroundColor());
                                source.getCaret().setVisible(false);

                                e.consume();

                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
                                source.setBackground(view.getDefaultBackgroundColor());
                                source.setEditable(false);

                                String val = Character.toString(typedChar);
                                int value = Integer.parseInt(val);

                                model.setValue(row, col, value);

                                Component nextComponent = getNextComponent(sudokuFields, reloadNet);
                                if (nextComponent != null) {
                                    nextComponent.requestFocusInWindow();
                                }

                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
                                try {
                                    view.shortInfo("Good !");
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
                                model.removeListenerFromOneField(sudokuFields,row,col);
                                if (model.isSolved(sudokuNet)){
                                    newGameListener();
                                }

                            }
                            else {
                                try {
                                    view.shortInfo("Wrong !");
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
                                }
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
        System.out.println(count);
    }

    private Component getNextComponent(JTextField[][] sudokuFields, int[][] reloadNet) {
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








