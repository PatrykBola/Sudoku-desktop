//@Override
//public void keyTyped(KeyEvent e) {
//        JTextField source = (JTextField) e.getSource();
//        char typedChar = e.getKeyChar();
//
//        if (Character.isDigit(typedChar)) {
//        int typedValue = Character.getNumericValue(typedChar);
//
//        if (typedValue >= 1 && typedValue <= 9) {
//        // Poprawna cyfra wpisana
//        source.setText(String.valueOf(typedChar));
//        source.setBackground(view.getSelectedBackgroundColor());
//        source.getCaret().setVisible(false);
//        e.consume();
//
//        // Reszta istniejącej logiki...
//
//        if (model.compareToSecondBoard(compareNet, typedValue, row, col)) {
//        // Reszta istniejącej logiki...
//
//        } else {
//        // Reszta istniejącej logiki...
//        }
//        } else {
//        // Wyświetl "Wrong!" tylko dla niepoprawnych cyfr
//        try {
//        source.setText("");
//        source.setBackground(view.getSelectedBackgroundColor());
//        view.shortInfo("Wrong");
//        } catch (InterruptedException ex) {
//        throw new RuntimeException(ex);
//        }
//        }
//        } else {
//        // Wyświetl "Wrong!" dla znaków inne niż cyfry
//        try {
//        source.setText("");
//        source.setBackground(view.getSelectedBackgroundColor());
//        view.shortInfo("Wrong");
//        } catch (InterruptedException ex) {
//        throw new RuntimeException(ex);
//        }
//        }
//        }