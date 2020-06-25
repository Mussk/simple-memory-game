
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//это кастомная JPanel в которую можно на задний фон добавлять картинку вместо обычного цвета
public class JpanelPic extends JPanel {

    private Image picture;



    public JpanelPic(String path){

        try {

            picture = ImageIO.read(new File(path));

        }catch (IOException ex){}

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(picture, 0, 0,getWidth(),getHeight(), null);
    }


}
