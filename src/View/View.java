package View;

import Model.Model;
import Model.FileService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class View extends JFrame {

    private Model model;
    private FileService fileService = new FileService();
    private JTextField[][] sudokuFields;
    private final int SUBGRID_SIZE = 3;
    private final int CELL_SIZE = 40;
    private final int MARGIN_TOP = 70;
    private final int MARGIN_LEFT = 50;
    private final int MARGIN_RIGHT = 50;
    private final int MARGIN_BOTTOM = 70;
    public View(Model model) {
        this.model = model;
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(600, 700);

        int windowWidth = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_LEFT + MARGIN_RIGHT;
        int windowHeight = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_TOP + MARGIN_BOTTOM;
        setPreferredSize(new Dimension(windowWidth,windowHeight));
//        setLocationRelativeTo(null);
        setLayout(null);

        JButton newGameButton = new JButton("New Game");
        JButton reloadButton = new JButton("Reload");

        newGameButton.setBounds(MARGIN_LEFT, MARGIN_TOP - 50, 100, 30);
        reloadButton.setBounds(MARGIN_LEFT + 260, MARGIN_TOP - 50, 100, 30);
        newGameButton.setSize(100,35);
        reloadButton.setSize(100,35);

        //TODO: adctionListeners !

        add(newGameButton);
        add(reloadButton);

        sudokuFields = new JTextField[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j] = sudokuField();
                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                add(sudokuFields[i][j]);
            }
        }

        String filePath = "D:/git/SudokuApp/src/gameBoard.txt";
        int[][] sudokuNet = fileService.read(filePath);
        loadArray(sudokuNet);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JTextField sudokuField(){
        JTextField field = new JTextField();
        field.setFont(new Font("Times New Roman",Font.BOLD,14));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return field;
    }

    public void loadArray(int[][] sudokunet) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j].setText(String.valueOf(sudokunet[i][j]));
            }

        }
    }


//    public void printNet(int[][] sudokuNet) {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(sudokuNet[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }

}
