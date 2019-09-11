import java.io.File;
import javax.swing.ImageIcon;

public class Superhero {
   File LoseLife = new File("sounds/LoseLifeSoundEffect.wav");
   private ImageIcon[] batman = new ImageIcon[]{new ImageIcon("images/BatmanIdle.gif"), new ImageIcon("images/BatmanRight.gif"), new ImageIcon("images/BatmanLeft.gif"), new ImageIcon((new ImageIcon("images/batarang.gif")).getImage().getScaledInstance(70, 70, 0))};
   private ImageIcon[] flash = new ImageIcon[]{new ImageIcon("images/FlashIdle.gif"), new ImageIcon("images/FlashRight.gif"), new ImageIcon("images/FlashLeft.gif"), new ImageIcon((new ImageIcon("images/Thunder.gif")).getImage().getScaledInstance(70, 70, 0))};
   private ImageIcon[] captain = new ImageIcon[]{new ImageIcon("images/CaptainIdle.gif"), new ImageIcon("images/CaptainRight.gif"), new ImageIcon("images/CaptainLeft.gif"), new ImageIcon((new ImageIcon("images/Shield.png")).getImage().getScaledInstance(70, 70, 0))};
   private ImageIcon[] ironMan = new ImageIcon[]{new ImageIcon("images/IronmanIdle.gif"), new ImageIcon("images/IronmanRight.gif"), new ImageIcon("images/IronmanLeft.gif"), new ImageIcon((new ImageIcon("images/Laser.gif")).getImage().getScaledInstance(70, 70, 0))};

   public ImageIcon[] getBatman() {
      return this.batman;
   }

   public ImageIcon[] getFlash() {
      return this.flash;
   }

   public ImageIcon[] getCaptain() {
      return this.captain;
   }

   public ImageIcon[] getIronMan() {
      return this.ironMan;
   }
}