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

public class SuperheroSelection extends JFrame implements ActionListener {
   JPanel main = new JPanel();
   JLabel label = new JLabel("Chaque héros a ses propres capacités! Choisissez-en un:");
   JButton theme1 = new JButton("Batman");
   JButton theme2 = new JButton("Flash");
   JButton theme3 = new JButton("Captain America");
   JButton theme4 = new JButton("Iron Man");
   JLabel batman = new JLabel(new ImageIcon((new ImageIcon("images/BatmanIdle.gif")).getImage().getScaledInstance(100, 100, 0)));
   JLabel flash = new JLabel(new ImageIcon((new ImageIcon("images/FlashIdle.gif")).getImage().getScaledInstance(100, 100, 0)));
   JLabel captain = new JLabel(new ImageIcon((new ImageIcon("images/CaptainIdle.gif")).getImage().getScaledInstance(100, 100, 0)));
   JLabel ironman = new JLabel(new ImageIcon((new ImageIcon("images/IronmanIdle.gif")).getImage().getScaledInstance(100, 100, 0)));
   public static int heroNum;

   public int getTheme() {
      return heroNum;
   }

   public void setTheme(int theme) {
      heroNum = theme;
   }

   SuperheroSelection() {
      this.setSize(650, 650);
      this.setResizable(false);
      this.setLocationRelativeTo((Component)null);
      this.setLayout((LayoutManager)null);
      this.setContentPane(new JLabel(new ImageIcon("images/heroBackground.jpg")));
      this.setVisible(true);
      this.theme1.setBounds(200, 100, 300, 80);
      this.theme1.setFont(new Font("Serif", 0, 26));
      this.theme1.setForeground(new Color(0, 0, 0));
      this.theme1.setOpaque(false);
      this.theme1.setContentAreaFilled(false);
      this.theme1.setBorderPainted(false);
      this.theme2.setBounds(200, 200, 300, 80);
      this.theme2.setFont(new Font("Serif", 0, 26));
      this.theme2.setForeground(new Color(255, 69, 0));
      this.theme2.setOpaque(false);
      this.theme2.setContentAreaFilled(false);
      this.theme2.setBorderPainted(false);
      this.theme3.setBounds(200, 300, 300, 80);
      this.theme3.setFont(new Font("Serif", 0, 26));
      this.theme3.setForeground(new Color(65, 105, 225));
      this.theme3.setOpaque(false);
      this.theme3.setContentAreaFilled(false);
      this.theme3.setBorderPainted(false);
      this.theme4.setBounds(200, 400, 300, 80);
      this.theme4.setFont(new Font("Serif", 0, 26));
      this.theme4.setForeground(new Color(255, 223, 0));
      this.theme4.setOpaque(false);
      this.theme4.setContentAreaFilled(false);
      this.theme4.setBorderPainted(false);
      this.label.setBounds(10, 10, 800, 80);
      this.label.setFont(new Font("Serif", 0, 28));
      this.label.setForeground(Color.white);
      this.theme1.addActionListener(this);
      this.theme2.addActionListener(this);
      this.theme3.addActionListener(this);
      this.theme4.addActionListener(this);
      this.batman.setBounds(50, 85, 100, 100);
      this.add(this.batman);
      this.flash.setBounds(50, 200, 100, 100);
      this.add(this.flash);
      this.captain.setBounds(50, 315, 100, 100);
      this.add(this.captain);
      this.ironman.setBounds(50, 430, 100, 100);
      this.add(this.ironman);
      this.add(this.theme1);
      this.add(this.theme2);
      this.add(this.theme3);
      this.add(this.theme4);
      this.add(this.label);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.theme1) {
         this.setVisible(false);
         heroNum = 1;
         new GameModeSelectionGUI();
      }

      if (e.getSource() == this.theme2) {
         this.setVisible(false);
         heroNum = 2;
         new GameModeSelectionGUI();
      }

      if (e.getSource() == this.theme3) {
         this.setVisible(false);
         heroNum = 3;
         new GameModeSelectionGUI();
      }

      if (e.getSource() == this.theme4) {
         this.setVisible(false);
         heroNum = 4;
         new GameModeSelectionGUI();
      }

   }
}