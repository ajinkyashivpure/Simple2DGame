package object;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class ObjectDoor extends SuperObject{
    public ObjectDoor(){
        name="door";
        try {

            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/door.png")));

        }catch (IOException e){
            e.printStackTrace();
        }
        collision=true;

    }
}