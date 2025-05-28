package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {
    
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        name = "Chest";
        this.gp = gp;

        try {
            this.image = ImageIO.read(new File("src/resources/object/chest.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
