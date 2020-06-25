
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;

//Это окно главного меню
public class MainMenu extends JFrame {

   static Clip clip;


    public static void main(String[] args) {

        new MainMenu();
    }

    //тут у нас идем процесс создания самого окна
    public MainMenu() {

        JFrame menu = new JFrame("NARUTO ULTIMATE MEMORY");

        menu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JpanelPic pan = new JpanelPic("src/Source/BG.png");

        pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS)); // z gory do dolu

        JLabel label = new JLabel("NARUTO ULTIMATE MEMORY GAME");

        label.setFont(new Font("Verdana", Font.BOLD, 20));

        label.setForeground(Color.RED);

        label.setHorizontalAlignment(JLabel.CENTER);

        JLabel label2 = new JLabel("S16934");

        label2.setForeground(Color.WHITE);

        label2.setFont(new Font("Verdana", Font.PLAIN, 15));

        label2.setHorizontalAlignment(JLabel.CENTER);

        JButton buttons[] = new JButton[3];

        buttons[0] = new JButton("New Game");
        buttons[1] = new JButton("High Scores");
        buttons[2] = new JButton("Exit");

        pan.add(createRigidArea(40, 10));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        pan.add(label);

        pan.add(createRigidArea(100, 50));

        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        pan.add(label2);

        pan.add(createRigidArea(100, 30));

        buttons[0].addActionListener(e -> {


            Object[] possibilities = {"3x4", "4x4", "6x6"};

            //тут мы показываем игроку диалоговое окно для выбора рамера сетки
            Object SelectedVal = JOptionPane.showInputDialog(

                    pan, "Select grid size:",
                    "New name", JOptionPane.PLAIN_MESSAGE,
                    null, possibilities,
                    "3x4");

            try {

                switch (SelectedVal.toString()) {

                    case "3x4":
                        Game gameFrameFrame34 = new Game(3, 4);
                        break;
                    case "4x4":
                        Game gameFrameFrame44 = new Game(4, 4);
                        break;
                    case "6x6":
                        Game gameFrameFrame66 = new Game(6, 6);
                        break;
                }

            } catch (NullPointerException ex) {

                new MainMenu();
            }

            menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));

        });

        buttons[1].addActionListener(e -> {

            new HighScores();

            menu.dispatchEvent(new WindowEvent(menu, WindowEvent.WINDOW_CLOSING));

        });

        buttons[2].addActionListener(e -> {

            int choice = JOptionPane.showConfirmDialog(

                    pan, "Do you want to exit?",
                    "Exit", JOptionPane.YES_NO_OPTION);

            if (choice == 0) System.exit(0);

        });

        for (JButton b : buttons) {

            b.setMaximumSize(new Dimension(120, 40));

            b.setAlignmentX(Component.CENTER_ALIGNMENT);

            pan.add(createRigidArea(40, 10));

            pan.add(b);

        }

        pan.setCursor(createCursor());

        pan.setPreferredSize(new Dimension(640, 480));

        menu.setMinimumSize(new Dimension(640, 480));

        menu.add(pan);

        menu.pack();

        menu.setVisible(true);

        File sound = new File("/Users/Alex/IdeaProjects/GUI PROJECT II/src/Source/NARUTO.wav");

        // это все добавляем музыку, добавил по приколу, проект сдавал без этого :D
        try {

          AudioInputStream ais = AudioSystem.getAudioInputStream(sound);

            clip = AudioSystem.getClip();

            clip.open(ais);

            clip.setFramePosition(0);

            clip.start();


        } catch (Exception ex) {}

    }


    //маленький костыль)) добавляет невидимый компонент что бы сдвинуть кнопки немного ниже
    
    public Component createRigidArea(int width,int height){ // niewidoczny component

        Component rigidArea = Box.createRigidArea(new Dimension(width,height));

        return rigidArea;
    }

    //этот метод меняет курсор на картинку, тоже не нужно, но если хочется, то можно сделать))
    public static Cursor createCursor(){

        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Image image = toolkit.getImage("src/Source/Cursor.png");

        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "name");

        return c;
    }
}
