package utilz;

public class PlayerAnimations {
	public static class PlayerConstants{
		public static final int IDLE=0;   
		public static final int RUNNING=1;
		
		public static int  getPlayerAnimation(int playerAction) {
			switch(playerAction) {
			case IDLE:
				return 6;
			case RUNNING:
				return 8;
			default :
				return 1;	
		
			}
			
		}
	}

}
