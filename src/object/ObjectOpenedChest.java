package object;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class ObjectOpenedChest extends SuperObject {
	   public ObjectOpenedChest(){
	        name="opened_chest";
	        try {
	            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/chest_opened.png")));

	        }catch (IOException e){
	            e.printStackTrace();
	        }
	        collision=true;
	    }


}
