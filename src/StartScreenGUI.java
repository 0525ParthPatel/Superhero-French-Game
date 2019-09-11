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

public class StartScreenGUI extends JFrame implements ActionListener {
   JPanel main = new JPanel();
   JLabel logo = new JLabel("Invaders Français");
   JLabel player = new JLabel("Choisissez Un Joueur:");
   JButton Instr = new JButton("Instructions");
   JButton play = new JButton("Sélection de Héros:");
   public static String playerNum = "Player";

   public static String getPlayer() {
      return playerNum;
   }

   public void setTheme(String player) {
      playerNum = player;
   }

   public StartScreenGUI() {
      this.setSize(570, 570);
      this.setResizable(false);
      this.setLocationRelativeTo((Component)null);
      this.setLayout((LayoutManager)null);
      this.setTitle("Introduction Screen");
      this.setContentPane(new JLabel(new ImageIcon("images/MainMenu.jpg")));
      this.setVisible(true);
      this.logo.setBounds(170, 10, 400, 80);
      this.logo.setFont(new Font("Serif", 0, 36));
      this.logo.setForeground(Color.WHITE);
      this.player.setBounds(185, 100, 400, 80);
      this.player.setFont(new Font("Serif", 1, 22));
      this.player.setForeground(Color.WHITE);
      this.Instr.setBounds(190, 230, 200, 80);
      this.Instr.setFont(new Font("Serif", 1, 26));
      this.Instr.setForeground(Color.white);
      this.Instr.setOpaque(false);
      this.Instr.setContentAreaFilled(false);
      this.Instr.setBorderPainted(false);
      this.play.setBounds(140, 280, 300, 80);
      this.play.setFont(new Font("Serif", 1, 26));
      this.play.setForeground(Color.white);
      this.play.setOpaque(false);
      this.play.setContentAreaFilled(false);
      this.play.setBorderPainted(false);
      this.Instr.addActionListener(this);
      this.play.addActionListener(this);
      this.add(this.Instr);
      this.add(this.logo);
      this.add(this.play);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.Instr) {
         this.setVisible(false);
         new InstructionsGUI();
      }

      if (e.getSource() == this.play) {
         this.setVisible(false);
         new SuperheroSelection();
      }

   }
}