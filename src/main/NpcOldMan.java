package main;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import javax.imageio.ImageIO;

public class NpcOldMan extends Entity {
    GamePanel gp;
    public NpcOldMan(GamePanel gp){

        super(gp);
        this.gp=gp;
        direction="down";
        speed=2;
        getNpcImage();
        setDialogue();
    }


    public void getNpcImage(){
        try {
            up1= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_up_1.png")));
            up2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_up_2.png")));
            left1=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_left_1.png")));
            left2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_left_2.png")));
            down1=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_down_1.png")));
            down2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_down_2.png")));
            right1=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_right_1.png")));
            right2=ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Npc/oldman_right_2.png")));


        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setDialogue(){
        dialogues[0]="Hello there!";
        dialogues[1]="I am the Great Gandalf of Valinor";
        dialogues[2]="GoodLuck for you treasure hunt!";


    }
    public void setAction(){
        npcActionChecker++;
        Random random=new Random();
        int i=random.nextInt(100)+1; // 1-100

        if (npcActionChecker==60) {
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            npcActionChecker=0;
        }
    }

    //int dialogueIndex=0;
    public void speak(){
        if (dialogues[dialogueIndex]==null){
            dialogueIndex=0;
        }
        gp.ui.currentDialogue=dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction="left";
                break;
        }

    }

}
