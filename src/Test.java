import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = (new Scanner(new File("Vocab.csv"))).useDelimiter(",");
      Vocab v = new Vocab();

      String french;
      String english;
      while(input.hasNextLine()) {
         french = input.next();
         english = input.next();
         v.setEvocab(french);
         v.setFvocab(english);
         v.setKey(english, french);
      }

      System.out.println(Arrays.toString(v.getEvocab().toArray()));
      System.out.println(Arrays.toString(v.getFvocab().toArray()));
      input = (new Scanner(new File("VerbTense.csv"))).useDelimiter(",");

      while(input.hasNextLine()) {
         french = input.next();
         english = input.next();
         if (!french.equals("g")) {
            v.setTenses(english);
            v.setSentences(french);
            v.setAnswers(french, english);
         }
      }

      System.out.println(Arrays.toString(v.getSentences().toArray()));
      System.out.println(Arrays.toString(v.getTenses().toArray()));
      input = (new Scanner(new File("SuperlativesComparatives.csv"))).useDelimiter(",");

      while(input.hasNextLine()) {
         french = input.next();
         english = input.next();
         v.setSentence(french);
         v.setSuperlative(english);
         v.setSoloution(french, english);
      }

      System.out.println(v.getInverseAnswer().toString());
      new Splash();
   }
}