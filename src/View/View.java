package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class View extends JFrame {
    Label bottomLabel = new Label();
    private String defaultInfo = "Sudoku APP";
    private JButton newGameButton, reloadButton, easyButton, mediumButton, hardButton;
    private Timer timer;
    private final String otherDataPath = "D:/git/SudokuApp/src/otherData.txt";

    public String getCurrentTime() {
        return currentTime;
    }

    private final int SUBGRID_SIZE = 3;
    private final int CELL_SIZE = 40;
    private final int MARGIN_TOP = 70;
    private final int MARGIN_LEFT = 50;
    private final int MARGIN_RIGHT = 50;
    private final int MARGIN_BOTTOM = 110;
    private final int TOTAL_WIDTH = (CELL_SIZE * 9) + MARGIN_LEFT + MARGIN_RIGHT + (SUBGRID_SIZE * 2);

    private Color defaultBackgroundColor = Color.WHITE;
    private Color selectedBackgroundColor = new Color(120, 198, 118);
    private Color defaultFieldColor = new Color(34, 198, 32);
    private Color doneNumbersColor = new Color(6, 86, 6);
    private Color backlightColor = new Color(195, 233, 195, 255);
    private String currentTime = readCurrentTime();
    public JLabel getTimeLabel() {
        return timeLabel;
    }
    private JLabel timeLabel = new JLabel();

    public JLabel timerLabel(String initialTime, JLabel timeLabel) {
        timeLabel.setBounds(194, 28, 100, 40);
        timeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        String[] timeParts = initialTime.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);

        int delay = 1000;
        timer = new Timer(delay, new ActionListener() {
            int count = hours * 3600 + minutes * 60 + seconds;
            @Override
            public void actionPerformed(ActionEvent e) {

                count ++;

                int hours = count / 3600;
                int remainingSeconds = count % 3600;
                int minutes = remainingSeconds / 60;
                int seconds = remainingSeconds % 60;

                String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timeLabel.setText(formattedTime);
                currentTime = formattedTime;
                saveCurrentTime(formattedTime);
                System.out.println(formattedTime);
            }
        });
        return timeLabel;

    }

public void saveCurrentTime(String time){
    try {
        RandomAccessFile file = new RandomAccessFile(otherDataPath, "rwd");

        while (file.read() == 0) {

        }
        String line1 = file.readLine();
        String line2 = file.readLine();
        file.seek(line1.length() + System.lineSeparator().length());
        file.writeBytes("CurrentTime_" + time);
        file.close();

    } catch (IOException e) {
        System.err.println("Error : " + e.getMessage());
    }
}
    public String readCurrentTime(){
        try {
            RandomAccessFile file = new RandomAccessFile(otherDataPath, "r");
            while (file.read() == 0) {

            }
            String line1 = file.readLine();
            String line2 = file.readLine();
            String splittedline2 = line2.substring(12);

            file.close();
            return splittedline2;
        } catch (IOException e) {
            System.err.println("Error : " + e.getMessage());
        }
        return null;
    }
    public void setDefaultColorsOfFields(int[][] reloadNet,JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (reloadNet[i][j] != 0) {
                    sudokuFields[i][j].setBackground(defaultFieldColor);
                } else {
                    sudokuFields[i][j].setBackground(Color.white);
                }
            }
        }
    }

    public Label getBottomLabel() {
        return bottomLabel;
    }

    public JTextField[] cleatDoneNumbers(JTextField[] doneNumbers) {
        for (int i = 0; i < 9; i++) {
            doneNumbers[i].setBackground(Color.WHITE);
        }
        return doneNumbers;
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
        newGameButton.setBounds(MARGIN_LEFT, MARGIN_TOP - 55, 100, 30);
        reloadButton.setBounds(MARGIN_LEFT + 260, MARGIN_TOP - 55, 100, 30);
        newGameButton.setSize(100, 35);
        reloadButton.setSize(100, 35);
        add(newGameButton);
        add(reloadButton);
        System.out.println(currentTime);
        add(timerLabel(currentTime,timeLabel));
        bottomLabel = infoLabel(true);
        add(bottomLabel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
public JLabel timer(String initialTime) {
    JLabel label = new JLabel(initialTime);
    label.setBounds(194, 28, 100, 40);
    label.setFont(new Font("Times New Roman", Font.PLAIN, 20));

    String[] timeParts = initialTime.split(":");
    int hours = Integer.parseInt(timeParts[0]);
    int minutes = Integer.parseInt(timeParts[1]);
    int seconds = Integer.parseInt(timeParts[2]);

    int delay = 1000;
    timer = new Timer(delay, new ActionListener() {
        int count = hours * 3600 + minutes * 60 + seconds;

        @Override
        public void actionPerformed(ActionEvent e) {
            count ++;

            int hours = count / 3600;
            int remainingSeconds = count % 3600;
            int minutes = remainingSeconds / 60;
            int seconds = remainingSeconds % 60;

            String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            label.setText(formattedTime);
            currentTime = formattedTime;
            System.out.println(formattedTime);
        }
    });

    return label;
}

    public Timer getTimer() {
        return timer;
    }

    public void showAllRedCrosses(JLabel[] redCrosses) {
        for (int i = 0; i < 3; i++) {
            redCrosses[i].setVisible(true);
        }
    }

    public void hideAllRedCrosses(JLabel[] redCrosses) {
        for (int i = 0; i < 3; i++) {
            redCrosses[i].setVisible(false);
        }
    }

    public void createJLabelsWithCrosses(JLabel[] redCrosses) {
        String imagePath = "D:\\git\\SudokuApp\\src\\View\\red_cross.png";
        ImageIcon icon = new ImageIcon(imagePath);
        int startX = 175;
        int startY = 5;
        int crossSize = 33;
        for (int i = 0; i < 3; i++) {
            redCrosses[i] = new JLabel(icon);
            redCrosses[i].setBounds(startX + (i * (crossSize + 5)), startY, crossSize, crossSize);
            add(redCrosses[i]);
        }
        redCrosses[0].setVisible(false);
        redCrosses[1].setVisible(false);
        redCrosses[2].setVisible(false);
    }

    public void bottomEMHButtons(boolean visible) {
        easyButton.setBounds(MARGIN_LEFT + 20, 465, 100, 35);
        mediumButton.setBounds((TOTAL_WIDTH / 2) - 50, 465, 100, 35);
        hardButton.setBounds(MARGIN_LEFT + 240, 465, 100, 35);
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

    public void reloadingDefaultFieldsColor(int[][] reloadNet, JTextField[][] sudokuFields) {
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
                sudokuFields[i][j].setBounds(MARGIN_LEFT + j * CELL_SIZE, MARGIN_TOP + i * CELL_SIZE + 30, CELL_SIZE, CELL_SIZE);
                add(sudokuFields[i][j]);

            }
        }

    }

    public void doneNumbersFields(JTextField[] doneNumbers) {
        for (int i = 0; i < 9; i++) {
            doneNumbers[i] = sudokuField();
            doneNumbers[i].setBackground(Color.WHITE);
            doneNumbers[i].setBounds(MARGIN_LEFT + 15 + i * CELL_SIZE - 10, MARGIN_TOP - 10, CELL_SIZE - 10, CELL_SIZE - 10);
            add(doneNumbers[i]);
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
        Timer timer = new Timer(1000, new ActionListener() {
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
        label.setBounds((TOTAL_WIDTH / 2) - 100, 460, 200, 50);
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

    public JTextField[][] setBoxBorders(JTextField[][] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            sudokuFields[2][i].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
            sudokuFields[5][i].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
            sudokuFields[i][2].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
            sudokuFields[i][5].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
        }
        sudokuFields[2][2].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
        sudokuFields[2][5].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
        sudokuFields[5][2].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
        sudokuFields[5][5].setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
        return sudokuFields;
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

    public Color getBacklightColor() {
        return backlightColor;
    }

    public Color getDoneNumbersColor() {
        return doneNumbersColor;
    }

    public void addReloadButtonActionListener(ActionListener listener) {
        reloadButton.addActionListener(listener);
    }

    public void addNewGameButtonActionListener(ActionListener listener) {
        newGameButton.addActionListener(listener);
    }

    public void addEasyButtonActionListener(ActionListener listener) {
        easyButton.addActionListener(listener);
    }

    public void addMediumButtonActionListener(ActionListener listener) {
        mediumButton.addActionListener(listener);
    }

    public void addHardButtonActionListener(ActionListener listener) {
        hardButton.addActionListener(listener);
    }
}