import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.FloatControl.Type;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class FallingObject extends JLabel implements ActionListener {
   public static final ImageIcon[][] ExplosionSprites = new ImageIcon[][]{{new ImageIcon("Explosion/0.png"), new ImageIcon("Explosion/48.png")}, {new ImageIcon("Explosion/4.png"), new ImageIcon("Explosion/50.png")}, {new ImageIcon("Explosion/8.png"), new ImageIcon("Explosion/53.png")}, {new ImageIcon("Explosion/12.png"), new ImageIcon("Explosion/56.png")}, {new ImageIcon("Explosion/16.png"), new ImageIcon("Explosion/59.png")}, {new ImageIcon("Explosion/20.png"), new ImageIcon("Explosion/63.png")}, {new ImageIcon("Explosion/24.png"), new ImageIcon("Explosion/65.png")}, {new ImageIcon("Explosion/28.png"), new ImageIcon("Explosion/66.png")}, {new ImageIcon("Explosion/32.png"), new ImageIcon("Explosion/67.png")}, {new ImageIcon("Explosion/34.png"), new ImageIcon("Explosion/69.png")}, {new ImageIcon("Explosion/38.png"), new ImageIcon("Explosion/70.png")}, {new ImageIcon("Explosion/40.png"), new ImageIcon("Explosion/71.png")}, {new ImageIcon("Explosion/44.png"), new ImageIcon("Explosion/73.png")}};
   ImageIcon wood = new ImageIcon((new ImageIcon("images/Bonfire.gif")).getImage().getScaledInstance(100, 100, 0));
   ImageIcon bomb = new ImageIcon((new ImageIcon("images/Bomb.png")).getImage().getScaledInstance(100, 100, 0));
   ImageIcon rock = new ImageIcon((new ImageIcon("images/Rock.png")).getImage().getScaledInstance(100, 100, 0));
   File woodBreakingSFX = new File("sounds/WoodBreaking.wav");
   File bombBreakingSFX = new File("sounds/BombBreaking.wav");
   File rockBreakingSFX = new File("sounds/RockBreaking.wav");
   File woodFallingSFX = new File("sounds/WoodFalling.wav");
   File bombFallingSFX = new File("sounds/BombFalling.wav");
   File rockFallingSFX = new File("sounds/RockFalling.wav");
   private int hit = 0;
   private int played = 0;
   GameScreenGUI game = new GameScreenGUI(5);
   private int startY;
   private int startX;
   private int index;
   private String name;
   private Boolean reset = false;
   static ArrayList<String> strings = new ArrayList();
   private int row = 0;
   private int column = 0;
   private Timer moveTimer;
   private Timer animationTimer = new Timer(50, this);
   ArrayList<String> origin;
   Vocab v = new Vocab();

   public FallingObject(int speed, int startX, int startY, int width, int height, String name, int labelIndex, ArrayList<String> origin) {
      this.startX = startX;
      this.startY = startY;
      this.index = labelIndex;
      this.setSize(width, height);
      this.name = name;
      this.origin = origin;
      if (ThemeSelectionGUI.getTheme() == 1) {
         this.setIcon(new ImageIcon((new ImageIcon("images/Bonfire.gif")).getImage().getScaledInstance(100, 100, 0)));
      } else if (ThemeSelectionGUI.getTheme() == 2) {
         this.setIcon(new ImageIcon("images/Bomb.png"));
      } else {
         this.setIcon(new ImageIcon("images/Rock.png"));
      }

      this.moveTimer = new Timer(speed, this);
      this.moveTimer.start();
      GameScreenGUI.label[this.index].setFont(new Font("Sans Serif", 0, 24));
      GameScreenGUI.label[this.index].setForeground(Color.WHITE);
      GameScreenGUI.label[this.index].setText(this.name);
   }

   public int getStartX() {
      return this.startX;
   }

   public int getStartY() {
      return this.startY;
   }

   public String getNext() {
      if (strings.isEmpty()) {
         strings = new ArrayList(this.origin);
      }

      Random r = new Random();
      int index = r.nextInt(strings.size() - 1) + 0;
      String temp = (String)strings.get(index);
      return temp;
   }

   public String getName() {
      return this.name;
   }

   public void setStrings(ArrayList<String> array) {
      strings = array;
   }

   public void actionPerformed(ActionEvent event) {
      if (event.getSource() == this.moveTimer) {
         int y = this.getY();
         if ((double)y > (double)GameScreenGUI.height * 0.8D) {
            this.animationTimer.start();
            if (this.played == 1) {
               if (ThemeSelectionGUI.getTheme() == 1) {
                  playSFX(this.woodFallingSFX);
                  this.played = 2;
               } else if (ThemeSelectionGUI.getTheme() == 2) {
                  playSFX(this.bombFallingSFX);
                  this.played = 2;
               } else {
                  playSFX(this.rockFallingSFX);
                  this.played = 2;
               }
            }

            this.setLocation(this.getX(), this.getY());
            if (!this.isVisible()) {
               this.setVisible(true);
            }

            if (!this.reset) {
               ++GameScreenGUI.hits;
               System.out.println(this.index);
               GameScreenGUI.label[this.index].setText(this.getNext());
               GameScreenGUI.setHit(false);
               GameScreenGUI.setSpeed(1);
               if (GameScreenGUI.hits == 5) {
                  GameScreenGUI.setLabel();
                  GameScreenGUI.hits = 0;
               }

               this.reset = true;
            }
         } else {
            if (this.reset) {
               this.reset = false;
            }

            this.setLocation(this.getX(), y + GameScreenGUI.getSpeed());
            GameScreenGUI.label[this.index].setLocation(this.getX(), y + 100 + 1);
            int i;
            if (GameModeSelectionGUI.getMode() == 2) {
               if (((String)this.v.getInverseAnswer().get(GameScreenGUI.frenchLabel.getText())).equals(GameScreenGUI.label[this.index].getText())) {
                  for(i = 0; i < GameScreenGUI.bullets.size(); ++i) {
                     if (this.isVisible() && this.getBounds().intersects(((Projectile)GameScreenGUI.bullets.get(i)).getBounds())) {
                        GameScreenGUI.setHit(true);
                        GameScreenGUI.setSpeed(5);
                     }
                  }
               }
            } else if (GameModeSelectionGUI.getMode() != 2 && GameScreenGUI.key.get(GameScreenGUI.label[this.index].getText()) == GameScreenGUI.frenchLabel.getText()) {
               for(i = 0; i < GameScreenGUI.bullets.size(); ++i) {
                  if (this.isVisible() && this.getBounds().intersects(((Projectile)GameScreenGUI.bullets.get(i)).getBounds())) {
                     GameScreenGUI.setHit(true);
                     GameScreenGUI.setSpeed(4);
                  }
               }
            }

            if (this.hit == 1) {
               if (ThemeSelectionGUI.getTheme() == 1) {
                  playSFX(this.woodBreakingSFX);
                  ++this.hit;
               } else if (ThemeSelectionGUI.getTheme() == 2) {
                  playSFX(this.bombBreakingSFX);
                  ++this.hit;
               } else {
                  playSFX(this.rockBreakingSFX);
                  ++this.hit;
               }
            }

            for(i = 0; i < GameScreenGUI.bullets.size(); ++i) {
               if (this.getBounds().intersects(((Projectile)GameScreenGUI.bullets.get(i)).getBounds()) && GameScreenGUI.isHit()) {
                  if (this.isVisible()) {
                     GameScreenGUI.addScore();
                  }

                  this.setVisible(false);
                  ++this.hit;
               }
            }
         }
      }

      if (event.getSource() == this.animationTimer) {
         this.setIcon(ExplosionSprites[this.row][this.column]);
         this.setLocation(this.getX(), this.getY());
         this.hit = 0;
         ++this.played;
         if (this.row == 10 && this.column == 0) {
            this.row = 0;
            this.column = 1;
         } else if (this.row == 10 && this.column == 1) {
            this.animationTimer.stop();
            if (ThemeSelectionGUI.getTheme() == 1) {
               this.setIcon(new ImageIcon((new ImageIcon("images/Bonfire.gif")).getImage().getScaledInstance(100, 100, 0)));
            } else if (ThemeSelectionGUI.getTheme() == 2) {
               this.setIcon(new ImageIcon("images/Bomb.png"));
            } else {
               this.setIcon(new ImageIcon("images/Rock.png"));
            }

            this.setLocation(this.startX, this.startY);
            this.column = 0;
            this.row = 0;
         }

         ++this.row;
      }

   }

   static void playSFX(File Sound) {
      try {
         Clip clip = AudioSystem.getClip();
         clip.open(AudioSystem.getAudioInputStream(Sound));
         clip.start();
         FloatControl gainControl = (FloatControl)clip.getControl(Type.MASTER_GAIN);
         gainControl.setValue(-20.0F);
      } catch (Exception var3) {
         ;
      }

   }
}