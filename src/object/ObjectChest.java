package object;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class ObjectChest extends SuperObject {
	 public ObjectChest(){
	        name="chest";
	        try {

	            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/chest.png")));

	        }catch (IOException e){
	            e.printStackTrace();
	        }
	        collision=true;
	    }

}
