import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.Timer;

public class GameFrame extends JFrame {

    /** по сути игра выглядит так: в сетку вкладываем кнопки, на кнопки записываем стрингом путь к картинке и сверху
     * отрисовываем картинку, текст мы не видим потому что он под картинкой **/

    Game game;

    public GameFrame(Game game) {

        setName("Memory the game");

        this.game = game;

        setLayout(new BorderLayout());

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initTopPanel();

        initBotPanel();

        setPreferredSize(new Dimension(640, 480));

        setMinimumSize(new Dimension(640,480));

        setCursor(MainMenu.createCursor());

        pack();

        setLocationRelativeTo(null);

        setVisible(true);

        addkey_closing();

    }

    //в этом методе загружаем кнопки
    private void initTopPanel(){

        JPanel panT = new JpanelPic("src/Source/BG_Game.jpg");

        panT.setLayout(new GridLayout(game.getX(),game.getY(),4,4));


        for (int i = 0; i < game.getCardscount(); i++) { //tworzymy przyciski

            Game.buttons.put(i,new JButton());

            Game.buttons.get(i).setBorder(BorderFactory.createEmptyBorder());



            panT.add(Game.buttons.get(i));

        }

        FillButtons();

        add(panT,BorderLayout.CENTER);


    }

    //загружаем таймер
    private void initBotPanel(){

        JPanel panB = new JpanelPic("src/Source/BG_Game.jpg");

        game.SetTimerLabel(new TimerLabel(new Timer()));

        game.getMytimer().setFont(new Font(game.getMytimer().getFont().getFontName(), game.getMytimer().getFont().getStyle(), 36));

        panB.add(game.getMytimer());

        add(panB,BorderLayout.SOUTH);
    }


    //подгружаем картинки на кнопки
    void FillButtons() {

        /* тут какая-то хитрая фомула подгрузки картинок, я не помню точно как она работает,
        но сначала мы считаем коефициент, ниже на табличке видно что с каждым размером сетки он увеличивается на 2
        в общем этот алгоритм позволяет нам заполнить сетку картинками так, что бы у каждой была пара, можешь запустить
        дебаг и просмотреть как он работает
*/
        int k = 0;

        int coef = (Game.buttons.size() / 2 - 1) - (game.getIcons().size() - 1);

        for (int i = 0; i < Game.buttons.size() / 2; i++) {

            for (int j = k; j < 2 + k && j < Game.buttons.size(); j++) {

                if (i == Game.buttons.size() / 2 - coef) i = 0;

                game.getLinkPics().add(game.getIcons().get(i));

            }

            /* 1 3 	5 	7	9	11	13
            12	16	20	24	28	32	36
            3x4	4x4	5x4	6x4	7x4	8x4	6x6 */


            k += 2;
        }

        Collections.shuffle(game.getLinkPics());

        //тут мы добавляем картинки на кнопки и еще к тому же текст который есть путем к файлу
        // (в будущем нам это понадобится что бы сравнивать кратинки на кнопках)
        for (int i = 0; i < Game.buttons.size(); i++) {

            Game.buttons.get(i).add(new Pic(new ImageIcon("src/Source/Cover.jpg")));

            Game.buttons.get(i).setText(game.getLinkPics().get(i));

            Game.buttons.get(i).addActionListener(new MyActionListener(Game.buttons.get(i),this));

        }

    }


    /** В этом методе мы реализуем возможность выхода из игры при нажатии клавиш cmd(ctrl)+shift+q **/

    public void addkey_closing() {

        //Keystroke - реперезентует комбинацию клавиш
        KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK);


        Action performSave = new AbstractAction("Click Me") {

            // в этом методе мы создаем действие (Action)
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
                dispose();

                new MainMenu();


            }
        };

        //тут мы привязываем дейсввие и комбинацию клавиш фрейму
        getRootPane().getActionMap().put("Click Me", performSave);
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(ks, "Click Me");


        /** Это второй вариант с  одной клавишей, он проще **/

          /*  addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE){

                    setVisible(false);
                    dispose();

                    new MainFrame();
                }
            }
        }); */
    }



    }









