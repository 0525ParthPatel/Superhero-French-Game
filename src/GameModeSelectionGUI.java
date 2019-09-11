import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameModeSelectionGUI extends JFrame implements ActionListener {
   JPanel main = new JPanel();
   JLabel logo = new JLabel("Sélection Du Mode");
   JButton one = new JButton("Vocabulaire");
   JButton two = new JButton("Temps Des Verbs");
   JButton three = new JButton("Comparatif & Superlatif");
   private static int mode;

   public GameModeSelectionGUI() {
      this.setSize(600, 600);
      this.setResizable(false);
      this.setLocationRelativeTo((Component)null);
      this.setLayout((LayoutManager)null);
      this.setTitle("Game Mode Selection");
      this.setContentPane(new JLabel(new ImageIcon("images/GameSelection.jpg")));
      this.setVisible(true);
      this.logo.setBounds(150, 10, 400, 80);
      this.logo.setFont(new Font("Serif", 0, 36));
      this.logo.setForeground(Color.WHITE);
      this.one.setBounds(-25, 60, 300, 150);
      this.one.setFont(new Font("Serif", 1, 26));
      this.one.setForeground(Color.white);
      this.one.setOpaque(false);
      this.one.setContentAreaFilled(false);
      this.one.setBorderPainted(false);
      this.two.setBounds(0, 160, 300, 150);
      this.two.setFont(new Font("Serif", 1, 26));
      this.two.setForeground(Color.white);
      this.two.setOpaque(false);
      this.two.setContentAreaFilled(false);
      this.two.setBorderPainted(false);
      this.three.setBounds(0, 260, 400, 150);
      this.three.setFont(new Font("Serif", 1, 26));
      this.three.setForeground(Color.white);
      this.three.setOpaque(false);
      this.three.setContentAreaFilled(false);
      this.three.setBorderPainted(false);
      this.one.addActionListener(this);
      this.two.addActionListener(this);
      this.three.addActionListener(this);
      this.add(this.one);
      this.add(this.two);
      this.add(this.three);
      this.add(this.logo);
   }

   public static int getMode() {
      return mode;
   }

   public static void setMode(int mode) {
      mode = mode;
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.one) {
         mode = 1;
         this.setVisible(false);
         new ThemeSelectionGUI();
      }

      if (e.getSource() == this.two) {
         mode = 2;
         this.setVisible(false);
         new ThemeSelectionGUI();
      }

      if (e.getSource() == this.three) {
         mode = 3;
         this.setVisible(false);
         new ThemeSelectionGUI();
      }

   }
}