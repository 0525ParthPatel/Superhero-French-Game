import java.awt.event.ActionEvent;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Container;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class endScreen extends JFrame implements ActionListener
{
    JPanel main;
    JLabel logo;
    JLabel player;
    JLabel score;
    JButton play;
    
    public endScreen(final int newscore) throws IOException {
        this.main = new JPanel();
        this.logo = new JLabel("Merci Pour Jouer");
        this.player = new JLabel("Merci Pour Jouer");
        this.score = new JLabel("Meilleur Score");
        this.play = new JButton("Sortie");
        this.setSize(600, 600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setTitle("End Screen");
        this.setContentPane(new JLabel(new ImageIcon(new ImageIcon("images/endScreen.jpg").getImage().getScaledInstance(600, 600, 0))));
        this.setVisible(true);
        this.logo.setBounds(170, -30, 300, 150);
        this.logo.setFont(new Font("Serif", 0, 36));
        this.logo.setForeground(Color.WHITE);
        this.score.setBounds(150, 350, 300, 150);
        this.score.setFont(new Font("Serif", 1, 36));
        this.score.setForeground(Color.white);
        this.score.setOpaque(false);
        this.play.setBounds(150, 430, 300, 150);
        this.play.setFont(new Font("Serif", 1, 26));
        this.play.setForeground(Color.white);
        this.play.setOpaque(false);
        this.play.setContentAreaFilled(false);
        this.play.setBorderPainted(false);
        this.play.addActionListener(this);
        this.add(this.score);
        this.add(this.play);
        this.add(this.logo);
        this.readFile(newscore);
    }
    
    private void readFile(final int score) throws IOException {
        System.out.println("read");
        final Scanner input = new Scanner(new File("Highscore.csv")).useDelimiter(",");
        int highscore = 0;
        while (input.hasNext()) {
            highscore = input.nextInt();
        }
        input.close();
        if (score > highscore) {}
        Throwable t = null;
        try {
            final Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Highscore.csv"), "utf-8"));
            try {
                writer.write(Integer.toString(score));
            }
            finally {
                if (writer != null) {
                    writer.close();
                }
            }
        }
        finally {
            if (t == null) {
                final Throwable exception = null;
                t = exception;
            }
            else {
                final Throwable exception = null;
                if (t != exception) {
                    t.addSuppressed(exception);
                }
            }
        }
        final Scanner i = new Scanner(new File("Highscore.csv")).useDelimiter(",");
        this.score.setText("Highscore" + score);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() == this.play) {
            this.setVisible(false);
            System.exit(0);
        }
    }
}