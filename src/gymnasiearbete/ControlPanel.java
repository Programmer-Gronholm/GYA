package gymnasiearbete;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ControlPanel {

    public static void main(String[] args) {
        new ControlPanel();
    }

    public ControlPanel() {
        JFrame frame = new JFrame("Blackjack");
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        SimulatePanel simulatePanel = new SimulatePanel();
        frame.add(simulatePanel);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(200,400));
        panel.setBackground(new Color(0x154107));
        panel.setBorder(new LineBorder(new Color(0x856A05),8));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JLabel label = new JLabel("Welcome!");
        label.setFont(new Font("Italic", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        JButton startButton = new JButton("START");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(startButton);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
