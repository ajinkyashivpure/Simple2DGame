package object;

import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class ObjectKey extends SuperObject{
	public ObjectKey() {
		name="key";
		try {
			 image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Objects/key.png")));
		}
		catch (IOException e){
            e.printStackTrace();
        }
        collision=true;
	}

}
