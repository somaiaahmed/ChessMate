import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class is responsible for the window that appears when
// a user wants to access their previous games data

public class PreviousGames extends JFrame implements ActionListener {
    Color chessGreen = new Color(118, 150, 86);
    Color chessWhite = new Color(238, 238, 210);
    ImageIcon logo = new ImageIcon("resources/pieces/black king.png");
    JButton backButton;
    JFrame previousGames= new JFrame();
    JPanel tablePanel= new JPanel();

    PreviousGames() {
        User.ReadDataFromFile(); // Reads all the game data from the logged-in user account
        String[] header = {"White", "Black", "Winner", "Time Elapsed"};
        String[][] data = User.allGamesDataSeparated;
        // 2D array that stores all game data (an array of arrays where each inner array contains one game data)
        backButton = new JButton("Back");
        previousGames.add(backButton);
        backButton.setBounds(190, 450, 100, 30);
        backButton.setFocusable(false);
        MyJTable demoTable = new MyJTable(data, header);

        tablePanel.setBounds(0,0,500,600);
        JScrollPane sp= new JScrollPane(demoTable);
        tablePanel.add(sp);
        previousGames.add(tablePanel);
        previousGames.setSize(500, 530);
        previousGames.setVisible(true);
        previousGames.setResizable(false);
        previousGames.setDefaultCloseOperation(EXIT_ON_CLOSE);
        previousGames.setIconImage(logo.getImage());
        previousGames.setLocationRelativeTo(null);
        previousGames.setTitle("Previous Games");
        backButton.addActionListener(this);
        demoTable.setEnabled(false); // Prevents user from editing table data
    }

    class MyJTable extends JTable {
        MyJTable(String[][] data, String[] header) {
            super(data, header);
        }
        public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
            Component c = super.prepareRenderer(renderer, row, col);
            if (row % 2 == 0 && !isRowSelected(row)) {
                c.setBackground(chessGreen);
            } else if (!isRowSelected(row))
                c.setBackground(chessWhite);
            return c;
        }
    }
// Class that inherits from JTable
// Responsible for alternating row colors
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            previousGames.dispose();
            new LoggedIn();
        }
    }
// sets action for backButton to leave previous games window and allow
// user to either start a new game or go back to launchPage
// i.e. Goes back to LoggedIn page
}
