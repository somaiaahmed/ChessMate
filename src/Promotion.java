import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;


public class Promotion {
    Piece piece;

    private final JPanel Pgui = new JPanel(new BorderLayout());//This is the BASE Panel
    public static boolean done = false;
    private final JPanel pick = new JPanel( new GridLayout(2,2));
    public static JButton[][] promoteChoice= new JButton[2][2];//2x2 matrix of choices
    private final Image[][] promoteImage = new Image[2][2];


    Promotion() {
        if(!Pawn.canPromote){
            initializePromote();
        }
    }
    public static Character selectedP;
    public static boolean dispose = false;

    Action promoteA = new AbstractAction("promote") {
        @Override
        public void actionPerformed(ActionEvent e) {
            done = true;
            dispose = true;

        }
    };




    public final void initializePromote() {
        Pgui.setBorder(new EmptyBorder(5, 5, 5, 5));
        Pgui.setPreferredSize(new Dimension(600, 600));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        Pgui.add(tools, BorderLayout.PAGE_START);
        // choices buttons

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                JButton p = new JButton();
                p.setPreferredSize(new Dimension(30,30));
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64,
                        BufferedImage.TYPE_INT_ARGB));
                p.setIcon(icon);
                p.setBackground(new Color(238, 238, 210));
                promoteChoice[i][j] = p;
                if (Pawn.pColor) {
                    promoteImage[0][0] = new ImageIcon("resources/pieces/black queen.png").getImage();
                    promoteImage[0][1] = new ImageIcon("resources/pieces/black bishop.png").getImage();
                    promoteImage[1][0] = new ImageIcon("resources/pieces/black rook.png").getImage();
                    promoteImage[1][1] = new ImageIcon("resources/pieces/black knight.png").getImage();
                } else {
                    promoteImage[0][0] = new ImageIcon("resources/pieces/white queen.png").getImage();
                    promoteImage[0][1] = new ImageIcon("resources/pieces/white bishop.png").getImage();
                    promoteImage[1][0] = new ImageIcon("resources/pieces/white rook.png").getImage();
                    promoteImage[1][1] = new ImageIcon("resources/pieces/white knight.png").getImage();

                }
                promoteChoice[i][j].setIcon(new ImageIcon(promoteImage[i][j]));
                promoteChoice[i][j].addActionListener(promoteA);
                if (i == 0) {
                    if(j==0){
                        selectedP='Q';
                    }else{
                        selectedP='B';
                    }
                } else if (j==0) {
                    selectedP='R';
                }else {
                    selectedP='K';
                }
                pick.add(promoteChoice[i][j]);

            }
        }
        Pgui.add(pick);
    }
    public final void initializePromote(JFrame frame) {
        // ...
        frame.add(Pgui);
    }
    public final JComponent getPgui() {
        return Pgui;
    }

    public static void runPromote() {
        JFrame frame = new JFrame("Well Done! Choose Your Promotion!");
        Promotion test = new Promotion();
        test.initializePromote(frame);
        frame.setPreferredSize(new Dimension(500, 500));
        ImageIcon logo = new ImageIcon("resources/pieces/black king.png");
        frame.setIconImage(logo.getImage());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
       // frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setBounds(10, 10, 450, 450);
        frame.setResizable(false);
        while(!dispose) {
            // wait for dispose to be true
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.dispose();
    }
    public static void main(String[] args){

            runPromote();

    }
}