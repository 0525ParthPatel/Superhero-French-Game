import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Projectile extends JLabel implements ActionListener {
   private int x;
   private int y;
   private int speed;
   private Timer gravity = new Timer(250, this);
   private Boolean tyler1;
   private Boolean player = true;
   ImageIcon projectile = new ImageIcon();

   public Projectile(int x, int y, ImageIcon image) {
      this.setVisible(true);
      this.setSize(100, 90);
      this.x = x;
      this.y = y;
      this.tyler1 = false;
      this.projectile = image;
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x += x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y += y;
   }

   public void setSpeed(int speed) {
      this.speed = speed;
   }

   public int getSpeed() {
      return this.speed;
   }

   public ImageIcon getProjectile() {
      return this.projectile;
   }

   public void startGravity() {
      this.gravity.start();
   }

   public boolean getTyler1() {
      return this.tyler1;
   }

   public void setTyler1(boolean tyler1) {
      this.tyler1 = tyler1;
   }

   public void setAlien() {
      this.player = false;
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.gravity) {
         this.speed = (int)((double)this.speed + (double)GameScreenGUI.height * 0.002D);
         if (this.getY() > GameScreenGUI.height) {
            this.gravity.stop();
            if (this.player) {
               GameScreenGUI.bullets.remove(0);
            } else {
               GameScreenGUI.alienBullets.remove(0);
            }
         }
      }

   }
}