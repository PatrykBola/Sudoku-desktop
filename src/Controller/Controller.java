package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Controller {
    Random random = new Random();
    private final Model model = new Model();
    private final View view = new View();
    private int emptyCells;
    private int[][] sudokuNet;
    private int[][] reloadNet;
    private int[][] compareNet;
    //    private int emptyCells = 50;
    private final JTextField[][] sudokuFields = new JTextField[9][9];
    private final JTextField[] doneNumbers = new JTextField[9];
    int count = 0;

    public void play() throws InterruptedException {
        this.sudokuNet = model.readSudokuNetFromFile();
        this.reloadNet = model.readReloadBoard();
        this.compareNet = model.readCompareBoard();
        view.doneNumbersFields(doneNumbers);
        model.fillFields(doneNumbers);
        view.creatingFields(sudokuFields);
        model.loadIntArrayToJTextFieldArray(sudokuNet, sudokuFields);
        view.creatingGui();
        view.setBoxBorders(sudokuFields);
        view.changingDefaultFieldsColor(reloadNet, sudokuFields);
        view.infoLabel(true);
        isNumberReady(doneNumbers,sudokuNet);

        attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet, doneNumbers);

        view.addReloadButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                model.reloadBoard(sudokuNet, reloadNet);
                view.updateSudokuFields(sudokuNet, sudokuFields);
//                view.updateSudokuFields(sudokuNet, sudokuFields);
                view.reloadingDefaultFieldsColor(reloadNet, sudokuFields);
                view.changingDefaultFieldsColor(reloadNet, sudokuFields);
                model.removeListenersFromSudokuFields(sudokuFields);
                view.cleatDoneNumbers(doneNumbers);
//                isNumberReady(doneNumbers,sudokuNet);
//                model.fillFields(doneNumbers);
                isNumberReady(doneNumbers,sudokuNet);
//                attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet, doneNumbers);

                attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet, doneNumbers);
            }
        });

//        newGameListener();
//
        view.addNewGameButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                view.cleatDoneNumbers(doneNumbers);
                newGameListener();
            }
        });
    }

    public void newGameListener() {
//        view.cleatDoneNumbers(this.doneNumbers);
//        model.fillFields(doneNumbers);
        view.getBottomLabel().setSize(0, 0);
        view.bottomEMHButtons(true);

        view.addEasyButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.cleatDoneNumbers(doneNumbers);
                view.bottomEMHButtons(false);
//                        emptyCells = random.nextInt(26, 31);
                emptyCells = 1;
//                System.out.println(emptyCells);
                creatingNewGame(sudokuFields, reloadNet, sudokuNet, compareNet, emptyCells);
                view.getBottomLabel().setSize(200, 50);

            }
        });
        view.addMediumButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.cleatDoneNumbers(doneNumbers);
                emptyCells = random.nextInt(32, 39);
//                System.out.println(emptyCells);
                creatingNewGame(sudokuFields, reloadNet, sudokuNet, compareNet, emptyCells);
                view.bottomEMHButtons(false);
                view.getBottomLabel().setSize(200, 50);
            }
        });
        view.addHardButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.cleatDoneNumbers(doneNumbers);
                emptyCells = random.nextInt(40, 47);
//                System.out.println(emptyCells);
                creatingNewGame(sudokuFields, reloadNet, sudokuNet, compareNet, emptyCells);
                view.bottomEMHButtons(false);
                view.getBottomLabel().setSize(200, 50);
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
        model.fillFields(doneNumbers);
        isNumberReady(doneNumbers,sudokuNet);
        attachListenersToSudokuFields(sudokuFields, reloadNet, compareNet, doneNumbers);
    }

    public void attachListenersToDoneNumbersFields(JTextField[] doneNumbers,JTextField[][] sudokuFields){
        for (int i = 0; i < 9; i++) {
            doneNumbers[i].addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {

                    JTextField doneColor = (JTextField) e.getSource();
                    if (doneColor.getBackground() != view.getDefaultFieldColor()){
                        doneColor.setBackground(view.getDoneNumbersColor());

                        doneColor.setForeground(Color.WHITE);
                        for (int j = 0; j < 9; j++) {
                            for (int k = 0; k < 9; k++) {
                                if (sudokuFields[j][k].getText().equals(doneColor.getText()) ){
                                    sudokuFields[j][k].setBackground(view.getDoneNumbersColor());
                                    sudokuFields[j][k].setForeground(Color.WHITE);
                                }
                            }
                        }
                    }

                }

                @Override
                public void focusLost(FocusEvent e) {
                    JTextField doneColor = (JTextField) e.getSource();
                    if (doneColor.getBackground() == view.getDoneNumbersColor()){
                        doneColor.setBackground(Color.WHITE);
                        doneColor.setForeground(Color.BLACK);
                        for (int j = 0; j < 9; j++) {
                            for (int k = 0; k < 9; k++) {
                                if (sudokuFields[j][k].getText().equals(doneColor.getText()) ){
                                    sudokuFields[j][k].setBackground(view.getDefaultFieldColor());
                                    sudokuFields[j][k].setForeground(Color.BLACK);
                                }
                            }
                        }
                    }
                }
            });
        }
    }

    public void attachListenersToSudokuFields(JTextField[][] sudokuFields, int[][] reloadNet, int[][] compareNet, JTextField[] doneNumbers) {
        attachListenersToDoneNumbersFields(doneNumbers, sudokuFields);
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

//                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
                                source.setBackground(view.getDefaultBackgroundColor());
                                source.setEditable(false);

                                String val = Character.toString(typedChar);
                                int value = Integer.parseInt(val);


                                if (model.compareToSecondBoard(compareNet, value, row, col)) {
                                    model.setValue(row, col, value);
                                    model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());

                                    Component nextComponent = getNextComponent(sudokuFields, reloadNet);
                                    if (nextComponent != null) {
                                        nextComponent.requestFocusInWindow();
                                    }
                                    model.removeListenerFromOneField(sudokuFields, row, col);
                                    try {
                                        view.shortInfo("Good !");
                                        isNumberReady(doneNumbers,sudokuNet);
                                    } catch (InterruptedException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    if (model.isSolved(sudokuNet)) {
//                                        view.cleatDoneNumbers(doneNumbers);
                                        newGameListener();

                                    }
                                } else {
                                    try {
                                        source.setText("");
                                        source.setBackground(view.getSelectedBackgroundColor());
                                        view.shortInfo("Wrong");
                                    } catch (InterruptedException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            } else if (!model.digitBetweenOneAndNine(typedChar)) {

                                source.setDisabledTextColor(view.getDefaultBackgroundColor());
                                try {
                                    source.setText("");
                                    source.setBackground(view.getSelectedBackgroundColor());
                                    view.shortInfo("Wrong");
                                    e.consume();
                                } catch (InterruptedException ex) {
                                    throw new RuntimeException(ex);
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
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (reloadNet[i][j] != 0) {
                    return sudokuFields[i][j];
                }
            }
        }
        return null;

    }
    public void isNumberReady (JTextField[] doneNumbers,int[][] sudokuNet){
//        doneNumbers[1].setBackground(view.getDefaultFieldColor());
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <9 ; j++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuNet[j][k] == i){
                        count++;
                    }
                    if (count == 9){
                        doneNumbers[i-1].setBackground(view.getDefaultFieldColor());
                        count = 0;
                    }
                    else if (count < 9 && j ==8  && k == 8){
                        count = 0;
                    }
                }
            }
//            System.out.println(count + " " +i);
        }
    }
}








