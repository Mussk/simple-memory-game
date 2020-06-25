import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.WindowEvent;

//интерфейс окна таблицы лидеров
public class HighScoresFrame extends JFrame{

    JButton b1;


    public HighScoresFrame() {

        setLayout(new BorderLayout());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setPreferredSize(new Dimension(640, 480));

        setCursor(MainMenu.createCursor());

        initcomponents();

        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void initcomponents(){

        //компонент который позволяет создать нам визуальный лист
        JList<Player> list = new JList<>();

        //позволяет прокручивать лист вниз
        JScrollPane scroll = new JScrollPane(list);

        /** к листу нужно всегда добавлять его модель
         *  ListModel это интерфейс и чтобы лист заполнялся данными нам нужно просто прописать тела метод этого интерфейса.
         *  ниже написано что делает каждый метод, но почитать в интренете тоже не  помешает :)
         *  **/
        list.setModel(new ListModel<Player>() {

            //тут все просто, просто возвращаем размер листа, который мы хотим показывать в JList
            @Override
            public int getSize() {
                return HighScores.players.size();
            }

            /* самый важный метод, именно с помощью него елементы и отображаются в JList,
             возвращаем элемент нашего списка по индексу */
            @Override
            public Player getElementAt(int index) {

                return HighScores.players.get(index);
            }

            /* Эти два метода нам не нужны, поскольку наш JList нужен нам только для показывания,
              * пользователь ничего с ним делать не будет */

            @Override
            public void addListDataListener(ListDataListener l) {

            }

            @Override
            public void removeListDataListener(ListDataListener l) {

            }
        });

        /* Этот компонент делает первых три места в рейтинге разными цветами.
         * Если не ошибаюсь, то в твоем проекте это делать не нужно */
        ListCellRenderer<Player> winners = (list1, value, index, isSelected, cellHasFocus) -> {

            JPanel panel = new JPanel();

            JLabel label = new JLabel(value.toString());

            switch (HighScores.players.indexOf(value)){

                case 0 : panel.setBackground(Color.YELLOW);
                break;
                case 1 : panel.setBackground(Color.GRAY);
                break;
                case 2 : panel.setBackground(Color.ORANGE);
                break;
            }
            panel.add(label);

            return panel;
        };
        list.setCellRenderer(winners);

        //тут у нас просто кнопка и навешивание на нее ActionListener'a
        b1 = new JButton("Exit To Menu");

        b1.addActionListener(e -> {

            MainMenu.clip.stop();

            new MainMenu();

            //так же можно заменить на dispose() но это наверно лучше)
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        //не забываем добавлять все компоненты во фрейм

        this.add(b1,BorderLayout.SOUTH);

        this.add(scroll);
    }

}

