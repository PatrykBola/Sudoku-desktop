package View;

import Model.Model;
import Model.FileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    JTextField[][] sudokuFields = new JTextField[9][9];
    private JButton newGameButton, reloadButton;

    private final int SUBGRID_SIZE = 3;
    private final int CELL_SIZE = 40;
    private final int MARGIN_TOP = 70;
    private final int MARGIN_LEFT = 50;
    private final int MARGIN_RIGHT = 50;
    private final int MARGIN_BOTTOM = 70;

    public void creatingGui(){
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int windowWidth = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_LEFT + MARGIN_RIGHT;
        int windowHeight = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_TOP + MARGIN_BOTTOM;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setLayout(null);
        newGameButton = new JButton("New Game");
        reloadButton = new JButton("Reload");
        newGameButton.setBounds(MARGIN_LEFT, MARGIN_TOP - 50, 100, 30);
        reloadButton.setBounds(MARGIN_LEFT + 260, MARGIN_TOP - 50, 100, 30);
        newGameButton.setSize(100, 35);
        reloadButton.setSize(100, 35);
//        loadArray(sudokuNet);
        add(newGameButton);
        add(reloadButton);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        //TODO: here we need to creating fields with parameters in controller


    }

    public void changingDefaultFieldsColor(int[][] reloadNet, JTextField[][] sudokuFields){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (reloadNet[i][j] != 0){
                    sudokuFields[i][j].setBackground(defaultFieldColor);
                }
            }
        }
    }

    public void creatingFields(JTextField[][] sudokuFields){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j] = sudokuField();
                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                add(sudokuFields[i][j]);
            }
        }
    }


//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                sudokuFields[i][j] = sudokuField();
//                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//                add(sudokuFields[i][j]);
//            }
//        }
//        loadArray(model.getSudokuNet());
//        newGameButton.setBounds(MARGIN_LEFT, MARGIN_TOP - 50, 100, 30);
//        reloadButton.setBounds(MARGIN_LEFT + 260, MARGIN_TOP - 50, 100, 30);
//        newGameButton.setSize(100, 35);
//        reloadButton.setSize(100, 35);
//        loadArray(sudokuNet);
//        add(newGameButton);
//        add(reloadButton);
//        setResizable(false);
//        pack();
//        setLocationRelativeTo(null);
//        setVisible(true);
//
        // Dodac sudokuFields Jako komponent
        // KOlejny problem komponent jest pusty

//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                sudokuFields[i][j] = sudokuField();
//                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//                add(sudokuFields[i][j]);
//
//                final int row = i;
//                final int col = j;
//                sudokuFields[i][j].addFocusListener(new FocusAdapter() {
//                    @Override
//                    public void focusGained(FocusEvent e) {
//                        sudokuFields[row][col].setBackground(selectedBackgroundColor);
//                        selectedRow = row;
//                        selectedCol = col;
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
//                            printNet(model.getSudokuNet());
//                        } catch (NumberFormatException ex) {
//                            //TODO:na if`ach
//                        }
//                        sudokuFields[row][col].setBackground(defaultBackgroundColor);
//                    }
//                });
//            }
//        }

//        String filePath = "D:/git/SudokuApp/src/gameBoard.txt";
//        int[][] sudokuNet = fileService.read(filePath);
//        loadArray(sudokuNet);


//    public void updateSudokuFields(int[][] sudokuNet) {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                if (sudokuNet[i][j] != 0) {
//                    sudokuFields[i][j].setText(String.valueOf(sudokuNet[i][j]));
//                } else {
//                    sudokuFields[i][j].setText(" ");
//                }
//            }
//        }
//    }
    public JTextField sudokuField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Times New Roman", Font.BOLD, 14));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return field;
    }

    public void printFields() {
        int windowWidth = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_LEFT + MARGIN_RIGHT;
        int windowHeight = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_TOP + MARGIN_BOTTOM;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setLayout(null);
    }

    public void printNet(int[][] sudokuNet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudokuNet[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    public Color getDefaultFieldColor() {return defaultFieldColor;}

    public Color getSelectedBackgroundColor() {
        return selectedBackgroundColor;
    }

    public int selectedRow = -1;
    public int selectedCol = -1;

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }
    public void setSelectedCol(int selectedCol) {
        this.selectedCol = selectedCol;
    }

    private Color defaultBackgroundColor = Color.WHITE; // Domyślny kolor tła
    private Color selectedBackgroundColor = new Color(152, 188, 151);
    private Color defaultFieldColor = new Color(34, 198, 32);// Kolor tła dla zaznaczonego pola

    public void addReloadButtonActionListener(ActionListener listener) {
        reloadButton.addActionListener(listener);
    }
    public void addNewGameButtonActionListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }


}
