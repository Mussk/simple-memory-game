import java.io.Serializable;


public class Player implements Serializable {

    String name, time;

    int x, y, score;

   private double parsedtime;

    public Player(String name, String time, int x, int y, int openedcards, int score) {

        this.score = score;

        this.name = name;

        this.time = time;

        this.x = x;

        this.y = y;

       String[] ar = time.split(":");

       int div = 10000;

       //приводим время в одну числовую метрику (тут вроде бы в секунды)
        for (int i = 0; i < ar.length; i++) {

            parsedtime += Integer.parseInt(ar[i]) * div;

            div /= 100;
        }

        parsedtime /= 100;
        //формула, по который мы выщитываем очки
        setScore((int)((openedcards/ parsedtime) * (x * y) * 100));


    }
    //toString обязательно нужен, тут условно подаем шаблон по которому будет выводится информация о игрроках в рейтинге
    @Override
    public String toString() {
        return String.format("%s (Time: %s, grid %dx%d)  Score: %d", name, time, x, y, score);
    }


    public double getScore() {
        return score;
    }

    public void setScore(int s){
        this.score = s;

    }
}