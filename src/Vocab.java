import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Vocab {
   ArrayList<String> temp;
   static ArrayList<String> fvocab = new ArrayList();
   static ArrayList<String> evocab = new ArrayList();
   static HashMap<String, String> answerKey = new HashMap();
   static HashMap<String, String> inverseKey = new HashMap();
   static ArrayList<String> sentences = new ArrayList();
   static ArrayList<String> tenses = new ArrayList();
   static Map<String, ArrayList<String>> answer = new HashMap();
   static HashMap<String, String> inverseAnswer = new HashMap();
   static ArrayList<String> sentence = new ArrayList();
   static ArrayList<String> superlative = new ArrayList();
   static HashMap<String, String> soloution = new HashMap();
   static HashMap<String, String> inverse = new HashMap();

   public ArrayList<String> getFvocab() {
      return fvocab;
   }

   public void setFvocab(String french) {
      fvocab.add(french);
   }

   public ArrayList<String> getEvocab() {
      return evocab;
   }

   public void setEvocab(String english) {
      evocab.add(english);
   }

   public HashMap<String, String> getAnswerKey() {
      return answerKey;
   }

   public void setKey(String french, String english) {
      answerKey.put(french, english);
      inverseKey.put(english, french);
   }

   public HashMap<String, String> getInverseKey() {
      return inverseKey;
   }

   public ArrayList<String> getSentences() {
      return sentences;
   }

   public void setSentences(String french) {
      sentences.add(french);
   }

   public ArrayList<String> getTenses() {
      return tenses;
   }

   public void setTenses(String english) {
      tenses.add(english);
   }

   public Map<String, ArrayList<String>> getAnswer() {
      return answer;
   }

   public void setAnswers(String french, String english) {
      System.out.println(french);
      if (!french.equals("g")) {
         ArrayList temp = new ArrayList();

         try {
            if (!((ArrayList)answer.get(english)).isEmpty()) {
               temp = (ArrayList)((ArrayList)answer.get(english)).clone();
            }
         } catch (NullPointerException var5) {
            ;
         }

         temp.add(french);
         answer.put(english, temp);
         inverseAnswer.put(french, english);
      }

   }

   public HashMap<String, String> getInverseAnswer() {
      return inverseAnswer;
   }

   public ArrayList<String> getSentence() {
      return sentence;
   }

   public void setSentence(String french) {
      sentence.add(french);
   }

   public ArrayList<String> getSuperlative() {
      return superlative;
   }

   public void setSuperlative(String english) {
      superlative.add(english);
   }

   public HashMap<String, String> getSoloution() {
      return soloution;
   }

   public void setSoloution(String french, String english) {
      soloution.put(french, english);
      inverse.put(english, french);
   }

   public HashMap<String, String> getInverse() {
      return inverse;
   }
}