package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    int standCounter = 0;

    int pixelCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;

        solidArea = new Rectangle(1, 1, 46, 46);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = setup("src/resources/player/Walking_sprites/boy_up_1");
        up2 = setup("src/resources/player/Walking_sprites/boy_up_2");
        down1 = setup("src/resources/player/Walking_sprites/boy_down_1");
        down2 = setup("src/resources/player/Walking_sprites/boy_down_2");
        left1 = setup("src/resources/player/Walking_sprites/boy_left_1");
        left2 = setup("src/resources/player/Walking_sprites/boy_left_2");
        right1 = setup("src/resources/player/Walking_sprites/boy_right_1");
        right2 = setup("src/resources/player/Walking_sprites/boy_right_2");
        
    }

    public void update() {

        if (moving == false) {
            if (keyH.upPressed == true || keyH.downPressed == true 
                || keyH.leftPressed == true || keyH.rightPressed == true) {
            
                if (keyH.upPressed) {
                    direction = "up";
                }
                else if (keyH.downPressed) {
                    direction = "down";
                }
                else if (keyH.leftPressed) {
                    direction = "left";
                }
                else if (keyH.rightPressed) {
                    direction = "right";
                }

                moving = true;
            
                // CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkCollision(this);

                // CHECK OBJECT COLLISION
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);
            }
            else {
                standCounter++;
    
                if (standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }

        if (moving == true) {
            // IF COLLISION IS FALSE PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;

                    case "down":
                        worldY += speed;
                        break;

                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;

                    default:
                        break;
                }
            }

            spritesCounter++;
    
            if (spritesCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
    
                spritesCounter = 0;
            }

            pixelCounter += speed;

            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize); 

        BufferedImage image = null;

        switch (direction) {
            case "up":

                if (spriteNum == 1) {
                    image = up1;
                }

                if (spriteNum == 2) {
                    image = up2;
                }

                break;
            
            case "down":
            
                if (spriteNum == 1) {
                    image = down1;
                }

                if (spriteNum == 2) {
                    image = down2;
                }

                break;
            
            case "left":
                
                if (spriteNum == 1) {
                    image = left1;
                }

                if (spriteNum == 2) {
                    image = left2;
                }

                break;
            
            case "right":
                
                if (spriteNum == 1) {
                    image = right1;
                }

                if (spriteNum == 2) {
                    image = right2;
                }

                break;
            
            default:
                System.out.println("Something is very Wrong (Check The Player Update Function)");
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
