package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;

public class View extends JFrame {
    JTextField[][] sudokuFields = new JTextField[9][9];
        Label bottomLabel = new Label();
        private String defaultInfo = "Sudoku APP";
    private JButton newGameButton, reloadButton, easyButton, mediumButton, hardButton;

    private final int SUBGRID_SIZE = 3;
    private final int CELL_SIZE = 40;
    private final int MARGIN_TOP = 70;
    private final int MARGIN_LEFT = 50;
    private final int MARGIN_RIGHT = 50;
    private final int MARGIN_BOTTOM = 110;
    private final int TOTAL_WIDTH = (CELL_SIZE * 9) + MARGIN_LEFT + MARGIN_RIGHT + (SUBGRID_SIZE * 2);

    public Label getBottomLabel() {
        return bottomLabel;
    }

    public void creatingGui() {
        setTitle("Sudoku Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int windowWidth = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_LEFT + MARGIN_RIGHT;
        int windowHeight = CELL_SIZE * 9 + (SUBGRID_SIZE * 2) + MARGIN_TOP + MARGIN_BOTTOM;
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setLayout(null);
        newGameButton = new JButton("New Game");
        reloadButton = new JButton("Reload");
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        newGameButton.setBounds(MARGIN_LEFT, MARGIN_TOP - 50, 100, 30);
        reloadButton.setBounds(MARGIN_LEFT + 260, MARGIN_TOP - 50, 100, 30);
        newGameButton.setSize(100, 35);
        reloadButton.setSize(100, 35);
        add(newGameButton);
        add(reloadButton);
        bottomLabel = infoLabel(true);
        add(bottomLabel);
//        bottomEMHButtons(false);
//        bottomLabel = infoLabel(false);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


        //TODO: here we need to creating fields with parameters in controller


    }
    public void bottomEMHButtons(boolean visible){
        easyButton.setBounds(MARGIN_LEFT+20,440,100,35);
        mediumButton.setBounds((TOTAL_WIDTH/2) - 50,440,100,35);
        hardButton.setBounds(MARGIN_LEFT + 240,440,100,35);
        add(easyButton);
        add(mediumButton);
        add(hardButton);
        easyButton.setVisible(visible);
        mediumButton.setVisible(visible);
        hardButton.setVisible(visible);
    }

    public void changingDefaultFieldsColor(int[][] reloadNet, JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (reloadNet[i][j] != 0) {
                    sudokuFields[i][j].setBackground(defaultFieldColor);
                }
            }
        }
    }
    public void reloadingDefaultFieldsColor(int[][] reloadNet,JTextField[][] sudokuFields){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j].setBackground(defaultBackgroundColor);
            }
        }
    }


    public void creatingFields(JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuFields[i][j] = sudokuField();
                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                add(sudokuFields[i][j]);

            }
        }

    }

    public JTextField[][] updateSudokuFields(int[][] sudokuNet, JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuNet[i][j] != 0) {
                    sudokuFields[i][j].setText(String.valueOf(sudokuNet[i][j]));
                } else {
                    sudokuFields[i][j].setText(" ");
                }
            }
        }
        return sudokuFields;
    }

    public Label shortInfo(String info) throws InterruptedException {
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomLabel.setText(defaultInfo);
            }
        });
        timer.setRepeats(false);
        timer.start();
        bottomLabel.setText(info);

        return bottomLabel;
    }

    public Label infoLabel(boolean visible) {

        Label label = new Label();
        label.setVisible(visible);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        label.setAlignment(Label.CENTER);
        label.setBounds((TOTAL_WIDTH / 2) - 100, 440, 200, 50);
        label.setText("Sudoku App");

        return label;
    }

    public JTextField sudokuField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Times New Roman", Font.BOLD, 14));
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return field;
    }

    public JTextField[][] setBoxBorders(JTextField[][] sudokuFields){
        for (int i = 0; i < 9; i++) {
            sudokuFields[2][i].setBorder(BorderFactory.createMatteBorder(1,1,3,1,Color.BLACK));
            sudokuFields[5][i].setBorder(BorderFactory.createMatteBorder(1,1,3,1,Color.BLACK));
            sudokuFields[i][2].setBorder(BorderFactory.createMatteBorder(1,1,1,3,Color.BLACK));
            sudokuFields[i][5].setBorder(BorderFactory.createMatteBorder(1,1,1,3,Color.BLACK));
        }
        sudokuFields[2][2].setBorder(BorderFactory.createMatteBorder(1,1,3,3,Color.BLACK));
        sudokuFields[2][5].setBorder(BorderFactory.createMatteBorder(1,1,3,3,Color.BLACK));
        sudokuFields[5][2].setBorder(BorderFactory.createMatteBorder(1,1,3,3,Color.BLACK));
        sudokuFields[5][5].setBorder(BorderFactory.createMatteBorder(1,1,3,3,Color.BLACK));
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

    public Color getDefaultFieldColor() {
        return defaultFieldColor;
    }

    public Color getSelectedBackgroundColor() {
        return selectedBackgroundColor;
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

   public void addEasyButtonActionListener(ActionListener listener){easyButton.addActionListener(listener);}
    public void addMediumButtonActionListener(ActionListener listener){mediumButton.addActionListener(listener);}
    public void addHardButtonActionListener(ActionListener listener){hardButton.addActionListener(listener);}
}
