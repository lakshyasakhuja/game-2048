package shapes.game;

import processing.core.PApplet;
import processing.core.PConstants;

public class Tile {
    public int value;
    public int row;
    public int column;
    public float animRow;
    public float animColumn;
    public boolean hasMerged;

    public Tile(int value, int row, int column) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.animRow = row;
        this.animColumn = column;
    }

    public void draw(PApplet p, float tileSize) {
        float margin = tileSize * 0.10f;
        float size = tileSize - margin * 2;
        float x = App.BORDER + animColumn * tileSize + margin;
        float y = App.BORDER + animRow * tileSize + margin;

        switch (value) {
            case 2:    p.fill(238,228,218); break;
            case 4:    p.fill(237,224,200); break;
            case 8:    p.fill(242,177,121); break;
            case 16:   p.fill(245,149,99);  break;
            case 32:   p.fill(246,124,95);  break;
            case 64:   p.fill(246,94,59);   break;
            case 128:  p.fill(237,207,114); break;
            case 256:  p.fill(237,204,97);  break;
            case 512:  p.fill(237,200,80);  break;
            case 1024: p.fill(237,197,63);  break;
            case 2048: p.fill(237,194,46);  break;
            default:   p.fill(60,58,50);    break;
        }

        p.noStroke();
        p.rect(x, y, size, size, 12);

        p.fill(value <= 4 ? 119 : 240);
        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.textSize(size * 0.4f);
        p.text(value, x + size/2, y + size/2);
    }
}
