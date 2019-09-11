import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Splash$1 implements ActionListener {
   // $FF: synthetic field
   final Splash this$0;

   Splash$1(Splash var1) {
      this.this$0 = var1;
   }

   public void actionPerformed(ActionEvent arg0) {
      Splash.access$0().setValue(Splash.access$1());
      if (Splash.access$2() == Splash.access$1()) {
         this.this$0.dispose();
         Splash.access$3().stop();
         new StartScreenGUI();
      }

      Splash.access$4(Splash.access$1() + 1);
   }
}