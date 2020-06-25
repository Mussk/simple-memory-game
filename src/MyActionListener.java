import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


/** Это кастомный ActionListener мы его потом прикручиваем к кнопкам в игре.
 * Делался отдельным классом потому что при нажатии на кнопку нужно было много всего проверить **/
public class MyActionListener implements ActionListener {

    private JButton button;

    private GameFrame gameFrame;

    public MyActionListener(JButton button, GameFrame gameFrame){

        this.button = button;

        this.gameFrame = gameFrame;
    }
/** вот собственно метод который мы перегружаем. Этот код выполняется при нажатии на кнопки в игре
 * (крч имитируем переворот карточки, удаляем картинку рубашки и рисуем картинку, которая должна быть на ее месте)**/
    @Override
    public void actionPerformed(ActionEvent e) {


            button.removeAll();

            button.add(new Pic(new ImageIcon(button.getText())));

            button.revalidate();

            button.repaint();

            button.setEnabled(false);// тут бы блокируем кнопку что бы мы не могли нажать на нее пока она отрыта

        //добавляем кнопку в историю нажатия (история применяется для подсчета очков)
            for (int i = 0; i < Game.buttons.size(); i++) {

                if (button == Game.buttons.get(i))
                    Game.pressedbuttons.add(button);
            }


            /*cюда мы попадаем каждую вторую нажатую кнопку (сначала нажимаем, открывается карта, а потом ищем ей пару
            и когда две отрыто, начинаем их сравнивать)*/
        if (Game.pressedbuttons.size() % 2 == 0 && Game.pressedbuttons.size() != 0) {

            Check(button,Game.pressedbuttons.get(Game.pressedbuttons.size() - 2));

        }

}

/** Этот метод сравнивает две кнопки, которые мы нажали, что бы понять одинаковые лы картинки на них нарисованы.
 *  Тут нужно ОБЯЗАТЕЛЬНО использовать потоки иначе вторая кнопка при нажатии попросту не отрисуется,
 *  а первая по окончанию метода или закроется, или останется окрытой в зависимости от того одинаковые кратинки или нет.
 *  Почему так? Потому что мы должны ононвременно закрыть обе карточки, а, наколько я понял, методы отрисовки в Яве блокируют основной поток программы.
 *  Это тоже можно подсмореть в документации **/
    public void Check(JButton b1, JButton b2) {

        //если мы не угадали картинку
        if (!b1.getText().equals(b2.getText())) {

            Thread thread = new Thread(() -> {

                try {

                    //блокируем все кнопки
                    for (int i = 0; i < Game.buttons.size(); i++) {

                        Game.buttons.get(i).setEnabled(false);
                    }
                    //задержка 2 секунды, в этот момнет игрок смотрит на две отрытые карточки
                    Thread.sleep(2000);

                    //обратно пререрисовываем рубашки
                    b1.removeAll();

                    b1.add(new Pic(new ImageIcon(Game.cover)));

                    b1.revalidate();

                    b1.setEnabled(true);

                    b2.removeAll();

                    b2.add(new Pic(new ImageIcon(Game.cover)));

                    b2.revalidate();

                    //отблокирываем кнопки
                    for (int i = 0; i < Game.buttons.size(); i++) {

                        Game.buttons.get(i).setEnabled(true);
                    }

                    b2.setEnabled(true);

                } catch (Exception e) {
                }
            });

            //запускаем то что выше
            thread.start();

            //если мы угадали
        } else {


            b1.setEnabled(false);

            b2.setEnabled(false);

            // +1 в кол-во угаданных пар
            Game.pary++;

            // проверка на выигрыш
            if (Game.pary == Game.buttons.size()/2) {

                gameFrame.dispatchEvent(new WindowEvent(gameFrame,WindowEvent.WINDOW_CLOSING));

                Game.Win(Game.x,Game.y);

                Game.pary = 0;
                Game.pressedbuttons.clear();
            }


        }


    }

}
