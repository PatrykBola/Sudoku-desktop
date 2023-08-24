package View;

import Model.Model;
import Model.FileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class View extends JFrame {

    Model model = new Model();
    FileService fileService = new FileService();
    JTextField[][] sudokuFields = new JTextField[9][9];
//    int[][] sudokuNet = model.getSudokuNet();
    private final int SUBGRID_SIZE = 3;
    private final int CELL_SIZE = 40;
    private final int MARGIN_TOP = 70;
    private final int MARGIN_LEFT = 50;
    private final int MARGIN_RIGHT = 50;
    private final int MARGIN_BOTTOM = 70;
//    JTextField[][] sudokuFields = sudokuFields();


    public View(Model model) {
        this.model = model;
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int[][] sudokuNet = model.getSudokuNet();
        int windowWidth = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_LEFT + MARGIN_RIGHT;
        int windowHeight = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_TOP + MARGIN_BOTTOM;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setLayout(null);

        JButton newGameButton = new JButton("New Game");
        JButton reloadButton = new JButton("Reload");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j] = sudokuField();
                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                add(sudokuFields[i][j]);
            }
        }
        loadArray(model.getSudokuNet());
        newGameButton.setBounds(MARGIN_LEFT, MARGIN_TOP - 50, 100, 30);
        reloadButton.setBounds(MARGIN_LEFT + 260, MARGIN_TOP - 50, 100, 30);
        newGameButton.setSize(100, 35);
        reloadButton.setSize(100, 35);
        loadArray(sudokuNet);
        add(newGameButton);
        add(reloadButton);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
//
        // Dodac sudokuFields Jako komponent
        // KOlejny problem komponent jest pusty


/** Action Listen
 *
 */
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
        /** Action Listenr
         *
         */

//        String filePath = "D:/git/SudokuApp/src/gameBoard.txt";
//        int[][] sudokuNet = fileService.read(filePath);
//        loadArray(sudokuNet);

    }

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

    public void loadArray(int[][] sudokunet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j].setText(String.valueOf(sudokunet[i][j]));
            }
        }

    }

    public JTextField[][] getSudokuFields() {
        return sudokuFields;
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

    public Color getSelectedBackgroundColor() {
        return selectedBackgroundColor;
    }

    public int getCELL_SIZE() {
        return CELL_SIZE;
    }

    public int getMARGIN_TOP() {
        return MARGIN_TOP;
    }

    public int getMARGIN_LEFT() {
        return MARGIN_LEFT;
    }


    public int selectedRow = -1;
    public int selectedCol = -1;

    public void setSelectedRow(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    public void setSelectedCol(int selectedCol) {
        this.selectedCol = selectedCol;
    }

    public int getSelectedRow() {
        return selectedRow;
    }

    public int getSelectedCol() {
        return selectedCol;
    }

    private Color defaultBackgroundColor = Color.WHITE; // Domyślny kolor tła
    private Color selectedBackgroundColor = new Color(43, 140, 215); // Kolor tła dla zaznaczonego pola


//    public void setSudokuFields(JTextField[][] sudokuFields) {
//        this.sudokuFields = sudokuFields;
//    }
}
