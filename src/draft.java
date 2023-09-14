//public class draft {
//    private void attachListenersToSudokuFields() {
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                final int row = i;
//                final int col = j;
//
//                JTextField[][] sudokuFields = view.getSudokuFields();
//
//                sudokuFields[i][j].addFocusListener(new FocusAdapter() {
//                    @Override
//                    public void focusGained(FocusEvent e) {
//                        JTextField source = (JTextField) e.getSource();
//                        source.setBackground(view.getSelectedBackgroundColor());
//                        view.setSelectedRow(row);
//                        view.setSelectedCol(col);
//                        source.getCaret().setVisible(false);
//                    }
//
//                    @Override
//                    public void focusLost(FocusEvent e) {
//                        // Nie musisz nic robić w momencie utraty focusu
//                    }
//                });
//
//                sudokuFields[i][j].addKeyListener(new KeyAdapter() {
//                    @Override
//                    public void keyTyped(KeyEvent e) {
//                        JTextField source = (JTextField) e.getSource();
//                        String input = source.getText();
//                        if (input.length() == 1) {
//                            try {
//                                int value = Integer.parseInt(input);
//                                model.setValue(row, col, value);
//                                model.fileService.save(model.getSudokuNet(), model.fileService.getMainBoardPath());
//                                source.setBackground(view.getDefaultBackgroundColor());
//                                source.getCaret().setVisible(false);
//                                // Po wprowadzeniu nowej cyfry, przekazujemy focus do innego komponentu
//                                source.transferFocus();
//                            } catch (NumberFormatException ex) {
//                                System.out.println("Niepoprawny format wprowadzonych danych!");
//                            }
//                        }
//                    }
//                });
//            }
//        }
//    }
//
//}
