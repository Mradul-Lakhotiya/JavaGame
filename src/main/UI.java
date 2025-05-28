package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font poppins_42B, poppins_52B;
    // BufferedImage keyImage;
    
    public boolean messageOn = false;
    public String message = "";
    int messageSize = 0;
    int messageCounter = 0;

    public boolean gameFinished = false;

    double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    UI(GamePanel gp) {
        this.gp = gp;
        poppins_42B = new Font("Poppins", Font.BOLD, 42);
        poppins_52B = new Font("Poppins", Font.BOLD, 52);
        // OBJ_Key key = new OBJ_Key(gp);
        // keyImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(poppins_42B);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {

        }
        else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenterText(text);
        int y = gp.screenWidth / 2;
        g2.drawString(text, x, y);
    }


    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2; 
        return x;
    }
}