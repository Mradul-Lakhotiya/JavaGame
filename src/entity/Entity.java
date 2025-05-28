package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
    GamePanel gp;
    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    
    public int spritesCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean moving = false;

    public int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}
    public void update() {
        setAction();

        int standCounter = 0;
        int pixelCounter = 0;

        collisionOn = false;
        gp.cChecker.checkCollision(this);

        if (moving == false) {
                moving = true;
            
                // CHECK TILE COLLISION
                collisionOn = false;
                gp.cChecker.checkCollision(this);
            }
        else {
            standCounter++;

            if (standCounter == 20) {
                spriteNum = 1;
                standCounter = 0;
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

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

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

                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();

        BufferedImage scaledImage = null;

        try {
            scaledImage = ImageIO.read(new File(imagePath +".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return scaledImage;
    }
}
