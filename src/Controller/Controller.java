package Controller;

import Model.Model;
import View.View;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller {
    private Model model = new Model();
    private View view = new View();
    private int[][] sudokuNet;
    private int[][] reloadNet;
    //    private int[][] colorNet;
    private JTextField[][] sudokuFields = new JTextField[9][9];

//    int[][] colorNet = model.colorFields(reloadNet,colorNet) ;

    public void play() {
        this.sudokuNet = model.readSudokuNetFromFile();
        this.reloadNet = model.readReloadBoard();
        view.creatingFields(sudokuFields);
        model.loadIntArrayToJTextFieldArray(sudokuNet, sudokuFields);
        view.creatingGui();
        view.changingDefaultFieldsColor(reloadNet, sudokuFields);

//        colorNet = model.readReloadBoard();
        System.out.println(reloadNet[0][0]);
        System.out.println(reloadNet[8][8]);

        attachListenersToSudokuFields(sudokuFields, reloadNet);


//        view.printNet(sudokuNet);
        //TODO:Naprawic generowanie !
        //TODO:1.wygenerowanie zapisanie do tymczasowej tablicy i dopiero wygenerowana zapisac do pliku a w modelu czytac z pliku do oryg. sudokuNet
        //TODO:Naprawic generowanie !
        //TODO:Naprawic generowanie !
//        System.out.println("********************");

//        view.printNet(model.getSudokuNet());
//        attachListenersToSudokuFields();

//        view.addReloadButtonActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                model.fileService.read(model.fileService.getBoardToReload());
//
//            }
//        });
//
//        view.addNewGameButtonActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//              model.newGameButton();`
//            }
//        });
    }

    public void attachListenersToSudokuFields(JTextField[][] sudokuFields, int[][] reloadNet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final int row = i;
                final int col = j;
                if (reloadNet[i][j] != 0) {
                    sudokuFields[i][j].addFocusListener(new FocusAdapter() {

                        @Override
                        public void focusGained(FocusEvent e) {
                            for (int k = 0; k < 9; k++) {
                                for (int l = 0; l < 9; l++) {
                                    JTextField color = (JTextField) e.getSource();
                                    color.setBackground(view.getSelectedBackgroundColor());
                                    color.getCaret().setVisible(false);
                                    sudokuFields[k][l].addKeyListener(new KeyAdapter() {

                                        @Override
                                        public void keyTyped(KeyEvent e) {
                                            JTextField source = (JTextField) e.getSource();
                                            char typedChar = e.getKeyChar();

                                            if (Character.isDigit(typedChar)) {
                                                source.setText(String.valueOf(typedChar));
                                                source.setBackground(view.getSelectedBackgroundColor());
                                                source.getCaret().setVisible(false);
                                                e.consume();
                                                source.setBackground(view.getDefaultBackgroundColor());
                                                // Konsumuj zdarzenie, aby uniknąć dodatkowego wprowadzania tekstu
//                                            handleInput(row, col, typedChar, source); // Obsługa wprowadzonej cyfry
                                            }
                                        }
                                    });
                                }

                            }

                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            JTextField source = (JTextField) e.getSource();
                            String input = source.getText();
                            System.out.println("a");
                            try {
                                System.out.println("b");
                                int value = Integer.parseInt(input);
                                model.setValue(row, col, value);
                                System.out.println("c");
                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
                                view.printNet(model.getSudokuNet());
                            } catch (NumberFormatException ex) {
                                // Obsłuż niepoprawny format wprowadzonych danych
                            }
                            source.setBackground(view.getDefaultBackgroundColor());
                            source.getCaret().setVisible(false);

                        }
                    });
//                sudokuFields[i][j].addKeyListener(new KeyAdapter() {
//
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        JTextField source = (JTextField) e.getSource();
//                        char typedChar = e.getKeyChar();
//
//                        if (Character.isDigit(typedChar)) {
//                            source.setText(String.valueOf(typedChar));
//                            source.setBackground(view.getSelectedBackgroundColor());
//                            source.getCaret().setVisible(false);
//                            e.consume(); // Konsumuj zdarzenie, aby uniknąć dodatkowego wprowadzania tekstu
//                            handleInput(row, col, typedChar,source); // Obsługa wprowadzonej cyfry
//                        }
//                    }
//                });
                }
            }
        }
    }

    private void handleInput(int row, int col, char typedChar, JTextField source) {
        try {
            source.setBackground(view.getDefaultBackgroundColor());
            int value = Integer.parseInt(String.valueOf(typedChar));
            model.setValue(row, col, value);
            model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
            view.printNet(model.getSudokuNet());
        } catch (NumberFormatException ex) {
            // Obsłuż niepoprawny format wprowadzonych danych
        }
    }

//    public void attachListenersToSudokuFields(JTextField[][] sudokuFields) {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                final int row = i;
//                final int col = j;
//                sudokuFields[i][j].addFocusListener(new FocusAdapter() {
//                                                        @Override
//                                                        public void focusGained(FocusEvent e) {
//                                                            JTextField source = (JTextField) e.getSource();
//                                                            source.setBackground(view.getSelectedBackgroundColor());
//                                                            view.setSelectedRow(row);
//                                                            view.setSelectedCol(col);
//                                                            System.out.println(row);
//                                                            source.getCaret().setVisible(false);
//                                                            source.setText("");
//
//                                                            if (!source.getText().equals("")) {
//
//                                                            }
//                                                        }
//
//                                                        @Override
//                                                        public void focusLost(FocusEvent e) {
//                                                            JTextField source = (JTextField) e.getSource();
//                                                            String input = source.getText();
//                                                            System.out.println("a");
//                                                            try {
//                                                                System.out.println("b");
//                                                                int value = Integer.parseInt(input);
//                                                                model.setValue(row, col, value);
//                                                                System.out.println("c");
//                                                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
//                                                                view.printNet(model.getSudokuNet());
//                                                            } catch (NumberFormatException ex) {
//                                                                // Obsłuż niepoprawny format wprowadzonych danych
//                                                            }
//                                                            source.setBackground(view.getDefaultBackgroundColor());
//                                                            source.getCaret().setVisible(false);
//
//                                                        }
//                                                    }
//
//                );
//
//            }
//        }
//    }
}







