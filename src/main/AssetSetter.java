package main;

import entity.NPC_OldMan;

public class AssetSetter {
    
    GamePanel gp;

    AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

    }

    public void setNPC() {
        gp.NPC[0] = new NPC_OldMan(gp);
        gp.NPC[0].worldX = gp.tileSize * 21;
        gp.NPC[0].worldY = gp.tileSize * 21;
    }
}
