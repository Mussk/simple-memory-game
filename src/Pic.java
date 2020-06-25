import javax.swing.*;
import java.awt.*;

//Класс который репрезентует картинку и отрисовывает ее
class Pic extends JComponent {

    private ImageIcon ii;

    public Pic(ImageIcon ii){

        this.ii = ii;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ii.getImage(),0,0,getWidth(),getHeight(),null);
    }

}