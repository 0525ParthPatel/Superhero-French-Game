import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class SuperVillian extends JLabel implements ActionListener {
   private int x;
   private int y;
   private int magnitudex;
   private int magnitudey;
   private int dx;
   private int dy;
   private Timer moveTimer = new Timer(50, this);
   private Timer dir = new Timer(8000, this);
   private Timer shoot = new Timer(1100, this);
   ImageIcon villian = new ImageIcon((new ImageIcon("images/alienVillian.png")).getImage().getScaledInstance(200, 200, 0));
   ImageIcon projectile;
   GameScreenGUI game = new GameScreenGUI(5);

   public SuperVillian(int x, int y, ImageIcon projectile) {
      this.setVisible(true);
      this.x = x;
      this.y = y;
      this.setIcon(this.villian);
      this.setVisible(false);
      this.setProjectile(projectile);
      this.dir.start();
      this.moveTimer.start();
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public ImageIcon getVillian() {
      return this.villian;
   }

   public void setVillian(ImageIcon projectile) {
      this.villian = projectile;
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.dir) {
         Random r = new Random();
         this.dx = this.getDirection();
         this.dy = this.getDirection();
         this.magnitudex = r.nextInt(5) + 3;
         this.magnitudey = r.nextInt(3);
      }

      if (e.getSource() == this.moveTimer) {
         if (!this.isVisible()) {
            this.setVisible(true);
         }

         if (this.dx == -1 && (double)(this.getX() + this.magnitudex * this.dx) < 0.1D * (double)GameScreenGUI.width) {
            this.dx = -this.dx;
         } else if (this.dx == 1 && (double)(this.getX() + this.magnitudex * this.dx) > 0.9D * (double)GameScreenGUI.width) {
            this.dx = -this.dx;
         }

         if (this.dy == 1 && (double)(this.getY() + this.magnitudey * this.dy) > 0.5D * (double)GameScreenGUI.height) {
            this.dy = -this.dy;
         } else if (this.dy == -1 && (double)(this.getY() + this.magnitudey * this.dy) < 0.3D * (double)GameScreenGUI.height) {
            this.dy = -this.dy;
         }

         if (Math.abs(this.getX() - GameScreenGUI.player.getX()) <= 200) {
            if (this.getX() < GameScreenGUI.player.getX()) {
               this.dx = 1;
               if (!this.shoot.isRunning()) {
                  this.shoot.start();
               }
            } else if (this.getX() == GameScreenGUI.player.getX()) {
               this.dx = 0;
               if (!this.shoot.isRunning()) {
                  this.shoot.start();
               }
            } else {
               this.dx = -1;
               if (!this.shoot.isRunning()) {
                  this.shoot.start();
               }
            }
         } else if (this.shoot.isRunning()) {
            this.shoot.stop();
         }

         this.x += this.magnitudex * this.dx;
         this.y += this.magnitudey * this.dy;
      }

      if (e.getSource() == this.shoot) {
         try {
            this.game.shoot();
         } catch (AWTException var3) {
            var3.printStackTrace();
         }
      }

   }

   private int getDirection() {
      Random r = new Random();
      return r.nextInt(2) == 0 ? -1 : 1;
   }

   public void setProjectile(ImageIcon projectile) {
      this.projectile = projectile;
   }

   public ImageIcon getProjectile() {
      return this.projectile;
   }
}