
import java.io.*;
import java.util.ArrayList;

//имплементируем интрейфейс serializable чтобы сохранять таблицу лидеров после закрытия программы,можешь об этом почитать в интернете
public class HighScores implements Serializable {

   // Player p;

  static ArrayList<Player> players = new ArrayList<>();

    public HighScores() {

        unpack();

        new HighScoresFrame();

    }

    //записываем в  файл таблицу лидеров
    public static void serialize(ArrayList<Player> players){

        try{
            FileOutputStream fos = new FileOutputStream("highscores.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(players);
            oos.flush();
            oos.close();

        }catch (Exception e){}
    }

    // и наоборот распаковываем
    public static ArrayList<Player> unpack() {
        try {
            FileInputStream fis = new FileInputStream("highscores.tmp");
            //все отличие от обычной записи файла в том что мы все пишем и читаем как Object
            ObjectInputStream oin = new ObjectInputStream(fis);
            HighScores.players = (ArrayList<Player>) oin.readObject();
        } catch (Exception ex) {}
        return players;
    }
}
