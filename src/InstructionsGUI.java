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
import javax.swing.JTextArea;

public class InstructionsGUI extends JFrame implements ActionListener {
   JPanel main = new JPanel();
   JLabel label = new JLabel("Instructions");
   JLabel luck = new JLabel("Bonne Chance!");
   JButton Hero = new JButton("Choisissez Un Héro");
   JTextArea area = new JTextArea("-Utilisez les touches fléchées pour se déplacer à gauche et à droite.\n-Utilisez la barre d'espace pour tirer avec votre projectile.\n-Après avoir répondu correctement, tirez sur le reste des objets pour obtenir des points supplémentaires. \n-Assurez-vous de frapper l'objet avec le texte correspondant à l'anglais.\n-N'oubliez pas d'attraper votre projectile après après le tir ou vous perdrez tous vos munitions.\n-Vous avez un nombre limité de vies.\n");

   public InstructionsGUI() {
      this.setSize(600, 650);
      this.setResizable(false);
      this.setLocationRelativeTo((Component)null);
      this.setLayout((LayoutManager)null);
      this.setTitle("Instructions");
      this.setContentPane(new JLabel(new ImageIcon("images/Instructions.jpg")));
      this.setVisible(true);
      this.Hero.setBounds(100, 500, 400, 80);
      this.Hero.setFont(new Font("Serif", 0, 30));
      this.Hero.setForeground(Color.white);
      this.Hero.setOpaque(false);
      this.Hero.setContentAreaFilled(false);
      this.Hero.setBorderPainted(false);
      this.luck.setBounds(213, 450, 200, 80);
      this.luck.setFont(new Font("Serif", 0, 28));
      this.luck.setForeground(Color.white);
      this.label.setBounds(213, 30, 200, 80);
      this.label.setFont(new Font("Serif", 0, 36));
      this.label.setForeground(Color.white);
      this.area.setBounds(50, 115, 500, 350);
      this.area.setFont(new Font("Serif", 1, 24));
      this.area.setForeground(Color.white);
      this.area.setOpaque(false);
      this.area.setLineWrap(true);
      this.area.setWrapStyleWord(true);
      this.area.setEditable(false);
      this.Hero.addActionListener(this);
      this.add(this.Hero);
      this.add(this.label);
      this.add(this.luck);
      this.add(this.area);
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.Hero) {
         this.setVisible(false);
         new SuperheroSelection();
      }

   }
}