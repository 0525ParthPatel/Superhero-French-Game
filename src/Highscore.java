import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Highscore {
   private static int highscore;
   private static String name;

   public static void writeFile() {
      try {
         FileWriter fw = new FileWriter("highscore.txt");
         PrintWriter pw = new PrintWriter(fw);
         pw.println();
         pw.println();
         pw.close();
      } catch (IOException var2) {
         System.out.println("Error");
      }

   }

   public static int getHighscore() {
      return highscore;
   }

   public static String getname() {
      return name;
   }

   public static void highscoreInput() {
      try {
         Scanner input = new Scanner(new File("highscore.txt"));
         highscore = input.nextInt();
         name = input.next();
      } catch (FileNotFoundException var1) {
         System.out.println("File does not exist");
      }

   }
}