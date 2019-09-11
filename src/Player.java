import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Player extends JLabel implements ActionListener {
   ImageIcon idlle;
   ImageIcon left;
   ImageIcon right;
   ImageIcon projectile;
   private static final int MOVE_AMOUNT = 10;
   private static boolean running = false;
   private static final int START_X = 500;
   private static final int START_Y;
   private int lives;
   private String name;
   private int x;
   private int y;
   private int changeX;
   private Timer runTimer;

   static {
      START_Y = GameScreenGUI.height - 180;
   }

   public Player(ImageIcon[] hero, String name, int time, int lives) {
      this.setIdlle(hero[0]);
      this.setRight(hero[1]);
      this.setLeft(hero[2]);
      this.setProjectile(hero[3]);
      this.name = name;
      this.setIcon(this.getIdlle());
      this.setLocation(500, START_Y);
      this.x = 500;
      this.y = START_Y;
      this.runTimer = new Timer(time, this);
      this.setIcon(this.getIdlle());
      this.lives = lives;
   }

   public void abilty() {
   }

   public ImageIcon getIdlle() {
      return this.idlle;
   }

   public void setIdlle(ImageIcon idlle) {
      this.idlle = idlle;
   }

   public ImageIcon getLeft() {
      return this.left;
   }

   public void setLeft(ImageIcon left) {
      this.left = left;
   }

   public ImageIcon getRight() {
      return this.right;
   }

   public void setRight(ImageIcon right) {
      this.right = right;
   }

   public ImageIcon getProjectile() {
      return this.projectile;
   }

   public void setProjectile(ImageIcon projectile) {
      this.projectile = projectile;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
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

   public void run(int key) {
      this.runTimer.start();
      this.changeX = 0;
      if (key == 39) {
         if ((double)this.getX() <= (double)GameScreenGUI.width * 0.97D) {
            this.setIcon(this.getRight());
            this.changeX = 10;
         }
      } else if (key == 37 && (double)this.getX() >= (double)GameScreenGUI.width * 0.03D) {
         this.setIcon(this.getLeft());
         this.changeX = -10;
      }

   }

   public static boolean isRunning() {
      return running;
   }

   public static void setRunning(boolean run) {
      running = run;
   }

   public int getLives() {
      return this.lives;
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.runTimer) {
         if (running) {
            this.setX(this.getX() + this.changeX);
            this.setLocation(this.getX(), this.getY());
         } else {
            this.setIcon(this.getIdlle());
            this.runTimer.stop();
         }
      }

   }

   public void die() {
      --this.lives;
      GameScreenGUI.gameLabels[1].setText("Vies: " + this.lives);
   }
}