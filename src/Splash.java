import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;

public class Splash extends JWindow {
   private static JProgressBar loadingScreenProgressBar;
   private static int count = 1;
   private static int TIMER_PAUSE = 10;
   private static int MAX = 150;
   private static Timer progressBarTimer;

   public Splash() {
      this.createSplash();
   }

   private void createSplash() {
      JPanel panel = new JPanel();
      panel.setLayout(new BorderLayout());
      JLabel splashImage = new JLabel(new ImageIcon("./images/Splash.gif"));
      panel.add(splashImage);
      loadingScreenProgressBar = new JProgressBar();
      loadingScreenProgressBar.setMaximum(MAX);
      loadingScreenProgressBar.setForeground(Color.RED);
      loadingScreenProgressBar.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      panel.add(loadingScreenProgressBar, "South");
      this.setContentPane(panel);
      this.pack();
      this.setLocationRelativeTo((Component)null);
      this.setVisible(true);
      this.startProgressBar();
   }

   private void startProgressBar() {
      progressBarTimer = new Timer(TIMER_PAUSE, new Splash$1(this));
      progressBarTimer.start();
   }

   // $FF: synthetic method
   static JProgressBar access$0() {
      return loadingScreenProgressBar;
   }

   // $FF: synthetic method
   static int access$1() {
      return count;
   }

   // $FF: synthetic method
   static int access$2() {
      return MAX;
   }

   // $FF: synthetic method
   static Timer access$3() {
      return progressBarTimer;
   }

   // $FF: synthetic method
   static void access$4(int var0) {
      count = var0;
   }
}