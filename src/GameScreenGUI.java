import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class GameScreenGUI extends JFrame implements KeyListener, ActionListener {
   static Toolkit toolkit = Toolkit.getDefaultToolkit();
   static Dimension dim;
   public static int hits;
   public static final int width;
   public static final int height;
   static Vocab vocab;
   static Player player;
   Superhero superheros = new Superhero();
   public static Superhero batman;
   public static Superhero flash;
   public static Superhero captain;
   public static Superhero ironman;
   private static int score;
   private static boolean hit;
   public SuperVillian villian;
   private Timer moveVillian = new Timer(50, this);
   ImageIcon batarang = new ImageIcon((new ImageIcon("images/batarang.gif")).getImage().getScaledInstance(70, 70, 0));
   ImageIcon shield = new ImageIcon((new ImageIcon("images/Shield.png")).getImage().getScaledInstance(70, 70, 0));
   ImageIcon thunder = new ImageIcon((new ImageIcon("images/Thunder.gif")).getImage().getScaledInstance(70, 70, 0));
   ImageIcon laser = new ImageIcon((new ImageIcon("images/Laser.gif")).getImage().getScaledInstance(70, 70, 0));
   public static ArrayList<Projectile> bullets;
   public static ArrayList<Projectile> alienBullets;
   public ArrayList<String> origin;
   private Timer velocity = new Timer(50, this);
   private static int speed;
   public static FallingObject[] object;
   public static JLabel[] label;
   public static JLabel[] gameLabels;
   private ArrayList<String> strings;
   public static int ammo;
   public static JLabel frenchLabel;
   private ArrayList<String> texts;
   static HashMap<String, String> key;
   static HashMap<String, ArrayList<String>> specialKey;
   public static int countdown;
   Dimension screenSize;

   static {
      dim = toolkit.getScreenSize();
      hits = 0;
      width = dim.width;
      height = dim.height;
      vocab = new Vocab();
      batman = new Superhero();
      flash = new Superhero();
      captain = new Superhero();
      ironman = new Superhero();
      score = 0;
      hit = false;
      bullets = new ArrayList();
      alienBullets = new ArrayList();
      speed = 1;
      object = new FallingObject[5];
      label = new JLabel[5];
      gameLabels = new JLabel[3];
      ammo = 20;
      frenchLabel = new JLabel();
   }

   public GameScreenGUI() {
      this.texts = new ArrayList(vocab.getFvocab());
      this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLayout((LayoutManager)null);
      System.out.println(width + " " + height);
      this.setLayout((LayoutManager)null);
      this.setDefaultCloseOperation(3);
      this.setResizable(false);
      this.setSize(width, height);
      this.setLocation(0, 0);
      if (ThemeSelectionGUI.getTheme() == 1) {
         this.setContentPane(new JLabel(new ImageIcon((new ImageIcon("images/Background.gif")).getImage().getScaledInstance(width, height, 0))));
      } else if (ThemeSelectionGUI.getTheme() == 2) {
         this.setContentPane(new JLabel(new ImageIcon((new ImageIcon("images/Background2.gif")).getImage().getScaledInstance(width, height, 0))));
      } else {
         this.setContentPane(new JLabel(new ImageIcon((new ImageIcon("images/Background3.gif")).getImage().getScaledInstance(width, height, 0))));
      }

      this.addKeyListener(this);
      this.setUpVillan();
      this.setUpHero();
      this.setGameLabels();
      this.setUpFrench();
      this.setUpFallingObjects();
      this.setVisible(true);
   }

   public GameScreenGUI(int n) {
      this.texts = new ArrayList(vocab.getFvocab());
      this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   }

   private void setUpVillan() {
      this.villian = new SuperVillian(1000, 100, new ImageIcon((new ImageIcon("images/blue-fire-flames-png-27.png")).getImage().getScaledInstance(50, 50, 0)));
      this.villian.setBounds(this.villian.getX(), this.villian.getY(), 200, 200);
      this.villian.setVisible(false);
      this.add(this.villian);
      this.moveVillian.start();
   }

   private void setUpHero() {
      this.velocity.start();
      if (SuperheroSelection.heroNum == 1) {
         player = new Player(this.superheros.getBatman(), "Batman", 30, 5);
      } else if (SuperheroSelection.heroNum == 2) {
         player = new Player(this.superheros.getFlash(), "Flash", 10, 5);
      } else if (SuperheroSelection.heroNum == 3) {
         player = new Player(this.superheros.getCaptain(), "Captain", 30, 10);
      } else if (SuperheroSelection.heroNum == 4) {
         player = new Player(this.superheros.getIronMan(), "IronMan", 30, 5);
      }

      player.setBounds(player.getX(), player.getY(), 300, 125);
      this.add(player);
   }

   public void setUpFallingObjects() {
      for(int x = 0; x < object.length; ++x) {
         label[x] = new JLabel();
         label[x].setSize(400, 30);
         label[x].setVisible(true);
         this.add(label[x]);
         if (x < 2) {
            object[x] = new FallingObject(20, 50 + x * width / 5, -200, 90, 100, this.getNext(), x, this.origin);
         } else if (x < 3) {
            object[x] = new FallingObject(20, 50 + x * width / 5, -200, 90, 100, this.getNext(), x, this.origin);
         } else if (x < 4) {
            object[x] = new FallingObject(20, 50 + x * width / 5, -200, 90, 100, this.getNext(), x, this.origin);
         } else if (x < 5) {
            object[x] = new FallingObject(20, 50 + x * width / 5, -200, 90, 100, this.getNext(), x, this.origin);
         }

         object[x].setBounds(object[x].getStartX(), object[x].getStartY(), object[x].getWidth(), object[x].getHeight());
         this.add(object[x]);
      }

      object[0].setStrings(this.strings);
      setLabel();
   }

   public void setGameLabels() {
      gameLabels[0] = new JLabel("Score:" + score);
      gameLabels[1] = new JLabel("Vies: " + player.getLives());
      gameLabels[2] = new JLabel("Munitions: " + ammo);

      for(int x = 0; x < 3; ++x) {
         gameLabels[x].setLayout((LayoutManager)null);
         gameLabels[x].setForeground(Color.WHITE);
         gameLabels[x].setBounds(10 + 150 * x, -100, 300, 300);
         gameLabels[x].setFont(new Font("San Serif", 0, 30));
         gameLabels[x].setVisible(true);
         this.add(gameLabels[x]);
      }

   }

   public void setUpFrench() {
      String text = "";
      frenchLabel.setBounds(0, height - 150, width, 150);
      frenchLabel.setForeground(Color.WHITE);
      frenchLabel.setFont(new Font("Sans Serif", 0, 34));
      frenchLabel.setVisible(true);
      frenchLabel.setText("<html><div style='text-align: center;'>" + text + "</div></html>");
      this.add(frenchLabel);
      if (GameModeSelectionGUI.getMode() == 1) {
         this.strings = new ArrayList(vocab.getFvocab());
         this.origin = new ArrayList(vocab.getFvocab());
         key = new HashMap(vocab.getAnswerKey());
      } else if (GameModeSelectionGUI.getMode() == 2) {
         this.strings = new ArrayList(vocab.getTenses());
         this.origin = new ArrayList(vocab.getFvocab());
         specialKey = new HashMap(vocab.getAnswer());
      } else {
         this.strings = new ArrayList(vocab.getSuperlative());
         this.origin = new ArrayList(vocab.getFvocab());
         key = new HashMap(vocab.getInverse());
      }

   }

   public static void setLabel() {
      Random r;
      String text;
      if (GameModeSelectionGUI.getMode() == 2) {
         r = new Random();
         text = label[r.nextInt(5)].getText();
         System.err.println(((ArrayList)specialKey.get(text)).size());
         if (((ArrayList)specialKey.get(text)).size() <= 1) {
            System.out.println(Arrays.toString(((ArrayList)vocab.getAnswer().get(text)).toArray()));
            specialKey.replace(text, (ArrayList)vocab.getAnswer().get(text));
         }

         int rand = r.nextInt(((ArrayList)specialKey.get(text)).size() - 1);
         frenchLabel.setText((String)((ArrayList)specialKey.get(text)).get(rand));
         ArrayList<String> temp = new ArrayList((Collection)specialKey.get(text));
         temp.remove(rand);
         specialKey.replace(text, temp);
      } else {
         r = new Random();
         text = label[r.nextInt(5)].getText();
         frenchLabel.setText((String)key.get(text));
      }
   }

   public void lose() throws IOException {
      ThemeSelectionGUI.stopMusic();
      Highscore.writeFile();
      this.dispose();
      new endScreen(score);
   }

   public String getNext() {
      Random r = new Random();
      int index = r.nextInt(this.strings.size());
      String temp = (String)this.strings.get(index);
      this.strings.remove(index);
      return temp;
   }

   public static Boolean isHit() {
      return hit;
   }

   public void keyPressed(KeyEvent event) {
      if (event.getKeyCode() == 37 || event.getKeyCode() == 39) {
         player.run(event.getKeyCode());
         Player.setRunning(true);
      }

      if (event.getKeyCode() == 32 && ammo > 0) {
         bullets.add(new Projectile(player.getX(), player.getY() - 100, player.getProjectile()));
         this.add((Component)bullets.get(bullets.size() - 1));
         ((Projectile)bullets.get(bullets.size() - 1)).setIcon(((Projectile)bullets.get(bullets.size() - 1)).getProjectile());
         ((Projectile)bullets.get(bullets.size() - 1)).setLocation(((Projectile)bullets.get(bullets.size() - 1)).getX(), ((Projectile)bullets.get(bullets.size() - 1)).getY());
         ((Projectile)bullets.get(bullets.size() - 1)).setSpeed((int)((double)(-height) * 0.025D));
         ((Projectile)bullets.get(bullets.size() - 1)).startGravity();
         --ammo;
         System.out.println(ammo);
         gameLabels[2].setText("Munitions: " + ammo);
      }

      if (event.getKeyCode() == 81) {
         alienBullets.add(new Projectile(this.villian.getX(), this.villian.getY(), this.villian.getProjectile()));
         ((Projectile)alienBullets.get(alienBullets.size() - 1)).setSpeed(0);
         ((Projectile)alienBullets.get(alienBullets.size() - 1)).startGravity();
         ((Projectile)alienBullets.get(alienBullets.size() - 1)).setAlien();
         this.add((Component)alienBullets.get(alienBullets.size() - 1));
         ((Projectile)alienBullets.get(alienBullets.size() - 1)).setVisible(true);
         ((Projectile)alienBullets.get(alienBullets.size() - 1)).setIcon(this.villian.getProjectile());
         ((Projectile)alienBullets.get(alienBullets.size() - 1)).setLocation(((Projectile)alienBullets.get(alienBullets.size() - 1)).getX(), ((Projectile)alienBullets.get(alienBullets.size() - 1)).getY());
      }

   }

   public void shoot() throws AWTException {
      Robot r = new Robot();
      int keyCode = 81;
      r.keyPress(keyCode);
      r.keyRelease(keyCode);
   }

   public void keyReleased(KeyEvent event) {
      Player.setRunning(false);
   }

   public void keyTyped(KeyEvent arg0) {
   }

   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == this.moveVillian) {
         this.villian.setLocation(this.villian.getX(), this.villian.getY());
      }

      if (e.getSource() == this.velocity) {
         int n;
         for(n = 0; n < bullets.size(); ++n) {
            ((Projectile)bullets.get(n)).setY(((Projectile)bullets.get(n)).getSpeed());
            ((Projectile)bullets.get(n)).setLocation(((Projectile)bullets.get(n)).getX(), ((Projectile)bullets.get(n)).getY());
            if (((Projectile)bullets.get(n)).getBounds().intersects(player.getBounds()) && ((Projectile)bullets.get(n)).getSpeed() > 0 && !((Projectile)bullets.get(n)).getTyler1()) {
               ++ammo;
               gameLabels[2].setText("Munitions: " + ammo);
               ((Projectile)bullets.get(n)).setVisible(false);
               ((Projectile)bullets.get(n)).setTyler1(true);
            }
         }

         for(n = 0; n < alienBullets.size(); ++n) {
            ((Projectile)alienBullets.get(n)).setY(((Projectile)alienBullets.get(n)).getSpeed());
            ((Projectile)alienBullets.get(n)).setLocation(((Projectile)alienBullets.get(n)).getX(), ((Projectile)alienBullets.get(n)).getY());
            if (((Projectile)alienBullets.get(n)).getBounds().intersects(player.getBounds()) && !((Projectile)alienBullets.get(n)).getTyler1()) {
               player.die();
               ((Projectile)alienBullets.get(n)).setTyler1(true);
               ((Projectile)alienBullets.get(n)).setVisible(false);
               if (player.getLives() == 0) {
                  try {
                     this.lose();
                  } catch (IOException var4) {
                     var4.printStackTrace();
                  }
               }
            }
         }
      }

   }

   public static void addScore() {
      ++score;
      gameLabels[0].setText("Score: " + score);
      System.out.println(score);
   }

   public static void setHit(boolean b) {
      hit = b;
   }

   public static int getSpeed() {
      return speed;
   }

   public static void setSpeed(int speed) {
      speed = speed;
   }
}