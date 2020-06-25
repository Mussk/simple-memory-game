import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/** Это у нас таймер, тут тоже потоки. Чесно скажу, брал код из инета но сам дописал к нему миллисекунды.
 * Вся сложность не в самом таймере, а в его анимации так сказать. Нужно в инете почитать про класс Timer и TimerTask.
 * Идея в том что мы запускаем таймер, передаем в него TimerTask, внутри  TimerTask'a у нас идет отдельный поток который
 * постоянно актуализирует нам JLabel куда мы записываем время **/

public class TimerLabel extends JLabel{

    public TimerLabel (Timer timer){

        timer.scheduleAtFixedRate(timerTask, 0,10);
    }

    private TimerTask timerTask = new TimerTask(){

        //модификатор volatile говорит о том что мы разрешаем Джаве использовать эту переменную нескольким потокам
        private volatile int time = -1;

        private Runnable refresher = () -> {



            int t = time;
            TimerLabel.this.setText(String.format("%02d:%02d:%02d", (t / 6000) % 60, (t / 100) % 60, t % 100));

        };

        @Override
        public void run (){
          
            time++;
            SwingUtilities.invokeLater(refresher); //przekierowanie do glownego strumenia

        }

    };


}
