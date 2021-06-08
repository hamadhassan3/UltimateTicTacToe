/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import ultimatetictactoe.GameBoard;
import ultimatetictactoe.HoldMove;

/**
 *
 * @author hamad
 */
public class Board extends javax.swing.JFrame {

    /**
     * Creates new form Board
     */
    private GameBoard gameBoard;

    private JPanel[][] jp;
    private Button[][][][] buttons;
    JFrame curr;

    public Board(GameBoard gameBoard) {
        initComponents();
        curr = this;

        JPanel[][] jp = new JPanel[3][3];
        buttons = new Button[3][3][][];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                jp[i][j] = new JPanel(new GridLayout(3, 3, 5, 5));
                int h = jPanel13.getHeight() / 3;
                int w = jPanel13.getWidth() / 3;
                jp[i][j].setBounds(j * w, i * h, w, h);
                jp[i][j].setBackground(Color.gray);
                jp[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.green));
                buttons[i][j] = new Button[3][3];
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {

                        //The following class handles the click of the mouse
                        class MouseClickHandle implements MouseListener {

                            int row;
                            int col;
                            int parentRow;
                            int parentCol;

                            public MouseClickHandle(int i, int j, int row, int col) {
                                parentRow = i;
                                parentCol = j;
                                this.row = row;
                                this.col = col;
                            }

                            @Override
                            public void mouseClicked(MouseEvent me) {

                            }

                            @Override
                            public void mousePressed(MouseEvent me) {

                                String symbol = gameBoard.getCurrentMove();

                                Button b = buttons[parentRow][parentCol][row][col];

                                b.setFont(new Font("TimesRoman", Font.BOLD, 20));

                                b.setLabel(symbol);

                                int score = gameBoard.setMove(parentRow, parentCol, row, col);
                                gameBoard.getOpponent().score += score;
                                jLabel3.setText(gameBoard.getOpponent().getName() + ": " + gameBoard.getOpponent().score);

                                b.setEnabled(false);

                                if (symbol.equals("X")) {
                                    b.setBackground(Color.yellow);
                                } else {
                                    b.setBackground(Color.blue);
                                }

                                try {
                                    TimeUnit.MILLISECONDS.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {

                                        //jp[i][j].setEnabled(false);
                                        //Disabling the rest of the board
                                        if (gameBoard.canMakeMove(i, j, row, col)) {
                                            jp[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));
                                            jp[i][j].setEnabled(true);
                                            for (int k = 0; k < 3; k++) {
                                                for (int l = 0; l < 3; l++) {
                                                    buttons[i][j][k][l].setFocusable(false);
                                                    if (buttons[i][j][k][l].getLabel().isEmpty()) {
                                                        buttons[i][j][k][l].setEnabled(true);
                                                    }
                                                }
                                            }
                                        } else {
                                            jp[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
                                            jp[i][j].setEnabled(false);
                                            for (java.awt.Component c : jp[i][j].getComponents()) {
                                                c.setEnabled(false);
                                                c.setFocusable(false);
                                            }
                                        }

                                    }
                                }

                                try {
                                    TimeUnit.MILLISECONDS.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                int state = gameBoard.checkGameWin();
                                if (state == 3) {
                                    //it is a draw
                                    String textToAppend = gameBoard.getOpponent().getName() + " " + gameBoard.getOpponent().score;

                                    BufferedWriter writer = null;
                                    try {
                                        writer = new BufferedWriter(new FileWriter("scoresDB.txt", true));
                                        writer.write(textToAppend);
                                        writer.newLine();
                                        writer.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    String message = "The Game was a Draw";

                                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                            JOptionPane.ERROR_MESSAGE);

                                    Home h = new Home();
                                    h.setVisible(true);
                                    curr.setVisible(false);
                                    return;
                                } else if (state == 1) {
                                    String message = gameBoard.getWinMessage();
                                    String textToAppend = gameBoard.getOpponent().getName() + " " + gameBoard.getOpponent().score;

                                    BufferedWriter writer = null;
                                    try {
                                        writer = new BufferedWriter(new FileWriter("scoresDB.txt", true));
                                        writer.write(textToAppend);
                                        writer.newLine();
                                        writer.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                            JOptionPane.ERROR_MESSAGE);
                                    Home h = new Home();
                                    h.setVisible(true);
                                    curr.setVisible(false);
                                    return;
                                } else if (state == 2) {
                                    String message = gameBoard.getWinMessage();
                                    String textToAppend = gameBoard.getOpponent().getName() + " " + gameBoard.getOpponent().score;

                                    BufferedWriter writer = null;
                                    try {
                                        writer = new BufferedWriter(new FileWriter("scoresDB.txt", true));
                                        writer.write(textToAppend);
                                        writer.newLine();
                                        writer.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                            JOptionPane.ERROR_MESSAGE);

                                    Home h = new Home();
                                    h.setVisible(true);
                                    curr.setVisible(false);
                                    return;
                                }

                                //Setting computer's move
                                String symbComp = gameBoard.getCurrentMove();
                                HoldMove hm = new HoldMove(0, 0);
                                int ind1;
                                int ind2;
                                int score2 = 0;
                                boolean found = false;
                                for (int i = 0; i < 3 && !found; i++) {
                                    for (int j = 0; j < 3 && !found; j++) {
                                        if (gameBoard.canMakeMove(i, j, row, col)) {
                                            hm = gameBoard.getCompMove(gameBoard.getBoard(i, j), gameBoard.getCurrentPlayer());
                                            score2 = gameBoard.setMove(i, j, hm.i, hm.j);
                                            b = buttons[i][j][hm.i][hm.j];
                                            ind1 = i;
                                            ind2 = j;
                                            found = true;
                                        }
                                    }
                                }

                                gameBoard.getOpponent().score += score2;
                                jLabel4.setText(gameBoard.getOpponent().getName() + ": " + gameBoard.getOpponent().score);

                                b.setFont(new Font("TimesRoman", Font.BOLD, 20));

                                b.setLabel(symbComp);

                                b.setEnabled(false);

                                if (symbComp.equals("X")) {
                                    b.setBackground(Color.yellow);
                                } else {
                                    b.setBackground(Color.blue);
                                }

                                try {
                                    TimeUnit.MILLISECONDS.sleep(200);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                for (int i = 0; i < 3; i++) {
                                    for (int j = 0; j < 3; j++) {

                                        if (gameBoard.canMakeMove(i, j, hm.i, hm.j)) {
                                            jp[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.GREEN));
                                            jp[i][j].setEnabled(true);
                                            for (int k = 0; k < 3; k++) {
                                                for (int l = 0; l < 3; l++) {
                                                    buttons[i][j][k][l].setFocusable(false);
                                                    if (buttons[i][j][k][l].getLabel().isEmpty()) {
                                                        buttons[i][j][k][l].setEnabled(true);
                                                    }
                                                }
                                            }
                                        } else {
                                            jp[i][j].setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.RED));
                                            jp[i][j].setEnabled(false);
                                            for (java.awt.Component c : jp[i][j].getComponents()) {
                                                c.setEnabled(false);
                                                c.setFocusable(false);
                                            }
                                        }

                                    }
                                }
                                state = gameBoard.checkGameWin();
                                if (state == 3) {
                                    //it is a draw

                                    String message = "The Game was a Draw";
                                    String textToAppend = gameBoard.getCurrentPlayer().getName() + " " + gameBoard.getCurrentPlayer().score;

                                    BufferedWriter writer = null;
                                    try {
                                        writer = new BufferedWriter(new FileWriter("scoresDB.txt", true));
                                        writer.write(textToAppend);
                                        writer.newLine();
                                        writer.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                            JOptionPane.ERROR_MESSAGE);

                                    Home h = new Home();
                                    h.setVisible(true);
                                    curr.setVisible(false);
                                } else if (state == 1) {
                                    String message = gameBoard.getWinMessage();
                                    String textToAppend = gameBoard.getOpponent().getName() + " " + gameBoard.getOpponent().score;

                                    BufferedWriter writer = null;
                                    try {
                                        writer = new BufferedWriter(new FileWriter("scoresDB.txt", true));
                                        writer.write(textToAppend);
                                        writer.newLine();
                                        writer.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                            JOptionPane.ERROR_MESSAGE);
                                    Home h = new Home();
                                    h.setVisible(true);
                                    curr.setVisible(false);

                                } else if (state == 2) {
                                    String message = gameBoard.getWinMessage();
                                    String textToAppend = gameBoard.getOpponent().getName() + " " + gameBoard.getOpponent().score;

                                    BufferedWriter writer = null;
                                    try {
                                        writer = new BufferedWriter(new FileWriter("scoresDB.txt", true));
                                        writer.write(textToAppend);
                                        writer.newLine();
                                        writer.close();
                                    } catch (IOException ex) {
                                        Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                                            JOptionPane.ERROR_MESSAGE);

                                    Home h = new Home();
                                    h.setVisible(true);
                                    curr.setVisible(false);
                                }

                            }

                            @Override
                            public void mouseReleased(MouseEvent me) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void mouseEntered(MouseEvent me) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }

                            @Override
                            public void mouseExited(MouseEvent me) {
                                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        }

                        buttons[i][j][k][l] = new Button();
                        int hh = jp[i][j].getHeight() / 3;
                        int ww = jp[i][j].getWidth() / 3;
                        //buttons[k][l].setMinimumSize(new Dimension(hh, ww));
                        buttons[i][j][k][l].setPreferredSize(new Dimension(ww - 5, hh - 5));
                        buttons[i][j][k][l].setFocusable(false);
                        buttons[i][j][k][l].addMouseListener(new MouseClickHandle(i, j, k, l));
                        jp[i][j].add(buttons[i][j][k][l]);
                        //buttons[k][l].setBounds(k*ww, l*hh, ww, hh);

                    }
                }

                jPanel13.add(jp[i][j]);
            }
        }

        this.gameBoard = gameBoard;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 676, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );

        jLabel3.setText("0");

        jLabel4.setText("0");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
 /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
    //</editor-fold>

    /* Create and display the form */
 /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board(null).setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    // End of variables declaration//GEN-END:variables
}
