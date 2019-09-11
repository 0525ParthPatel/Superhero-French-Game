import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ThemeSelectionGUI extends JFrame implements ActionListener {
   private static Clip clip;
   private static int theme;
   JPanel main = new JPanel();
   JLabel logo = new JLabel("Sélection De Thème");
   JButton one = new JButton("Champ Brûlant");
   JButton two = new JButton("Navire Pirate");
   JButton three = new JButton("Montagne Orageuse");
   File burningMusic = new File("sounds/BurningMusic.wav");
   File pirateMusic = new File("sounds/PirateMusic.wav");
   File dungeonMusic = new File("sounds/DungeonMusic.wav");

   public ThemeSelectionGUI() {
      this.setSize(600, 600);
      this.setResizable(false);
      this.setLocationRelativeTo((Component)null);
      this.setLayout((LayoutManager)null);
      this.setTitle("Game Mode Selection");
      this.setContentPane(new JLabel(new ImageIcon("images/Theme.png")));
      this.setVisible(true);
      this.logo.setBounds(150, -30, 300, 150);
      this.logo.setFont(new Font("Serif", 0, 36));
      this.logo.setForeground(Color.WHITE);
      this.one.setBounds(0, 80, 300, 150);
      this.one.setFont(new Font("Serif", 1, 26));
      this.one.setForeground(Color.white);
      this.one.setOpaque(false);
      this.one.setContentAreaFilled(false);
      this.one.setBorderPainted(false);
      this.two.setBounds(0, 280, 300, 150);
      this.two.setFont(new Font("Serif", 1, 26));
      this.two.setForeground(Color.white);
      this.two.setOpaque(false);
      this.two.setContentAreaFilled(false);
      this.two.setBorderPainted(false);
      this.three.setBounds(0, 430, 300, 150);
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

   public static int getTheme() {
      return theme;
   }

   public static void setTheme(int theme) {
      theme = theme;
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.one) {
         theme = 1;
         playMusic(this.burningMusic);
         this.setVisible(false);
         new GameScreenGUI();
      }

      if (e.getSource() == this.two) {
         theme = 2;
         playMusic(this.pirateMusic);
         this.setVisible(false);
         new GameScreenGUI();
      }

      if (e.getSource() == this.three) {
         theme = 3;
         playMusic(this.dungeonMusic);
         this.setVisible(false);
         new GameScreenGUI();
      }

   }

   static void playMusic(File Sound) {
      try {
         clip = AudioSystem.getClip();
         clip.open(AudioSystem.getAudioInputStream(Sound));
         clip.start();
         clip.loop(1000000000);
         //FloatControl gainControl = (FloatControl)clip.getControl(Type.MASTER_GAIN);
        // gainControl.setValue(-15.0F);
      } catch (Exception var2) {
         ;
      }

   }

   public static void stopMusic() {
      clip.stop();
   }
}