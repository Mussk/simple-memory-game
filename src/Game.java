import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/** в этом классе мы загружаем файлы (картинки) из Source в программу **/

public class Game {

    private int cardscount;

    //рахмерность сетки
   static int x,y;

   //спискок кнопок
     static HashMap<Integer,JButton> buttons = new HashMap<>();

     //пути к картинкам
   private ArrayList<String> icons = new ArrayList<>();

   //список путей на картинки, размер этого списка равен кол-ву кнопок на фрейме
    private  ArrayList<String> LinkPics = new ArrayList<>();

    //таймер
    private static TimerLabel mytimer;

    //сюда складываем кнопки, которые мы уже открыли, что бы потом заблокировать
     static ArrayList<JButton> pressedbuttons = new ArrayList<>();

     //рубашка карты
   static String cover = "src/Source/Cover.jpg";

   //кол-во пар
    static int pary;

    public Game(int x,int y){

        this.cardscount = x * y;

        this.x = x;

        this.y = y;

        icons.add("src/Source/2320620_640px.jpg");
        icons.add("src/Source/Naruto_newshot.png");
        icons.add("src/Source/Naruto_Part_III.png");
        icons.add("src/Source/Sai_Infobox.png");
        icons.add("src/Source/Sakura-1.jpg");

        //тасуем список картинок
        Collections.shuffle(icons);

        new GameFrame(this);


    }


    public ArrayList<String> getLinkPics() {
        return LinkPics;
    }

    public int getCardscount(){
         return cardscount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ArrayList<String> getIcons() {
        return icons;
    }

    public TimerLabel getMytimer() {
        return mytimer;
    }

    public void SetTimerLabel(TimerLabel tl){
        this.mytimer = tl;
    }


//метод, проверяющий или игрок выиграл
    public static void Win(int x, int y){

         //запиминаем время игры
                String time = mytimer.getText();

                // просим ввести имя
                String name = JOptionPane.showInputDialog("Your name", "Player");

                //добавляем игрока в таблицу лидеров
                HighScores.players.add(new Player(name, time, x, y, pressedbuttons.size(), 0));

                //и сортируем
                Collections.sort(HighScores.players, (o1, o2) -> {
                    if (o1.getScore() > o2.getScore())
                        return -1;

                    else return 1;
                });

                //записываем в файл
                HighScores.serialize(HighScores.players);

                new HighScores();
        }
}
