package shapes.game;

import processing.core.PApplet;

public class App extends PApplet {
    private static final int FRAMES_PER_SECOND = 60;
    private static final float ANIMATION_SPEED = 0.35f;
    public static final float BORDER = 70;

    private Board gameBoard;
    private float tileSize;

    private boolean hasStarted = false;
    private boolean isOver = false;
    private long beginTime;
    private long endTime;

    private int hoverRow = -1, hoverCol = -1;

    public static void main(String[] args) {
        int gridSize = 4;
        if (args.length > 0) {
            try { gridSize = Integer.parseInt(args[0]); }
            catch (Exception ignored) {}
        }
        Board.N = gridSize;
        PApplet.main("shapes.game.App");
    }

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        frameRate(FRAMES_PER_SECOND);
        gameBoard = new Board(Board.N);
        tileSize = (width - 2 * BORDER) / Board.N;
        textFont(createFont("Arial", 32));
    }

    public void draw() {
        background(187, 173, 160);

        Tile[][] cells = gameBoard.getGrid();

        for (int row = 0; row < Board.N; row++) {
            for (int col = 0; col < Board.N; col++) {
                float posX = BORDER + col * tileSize;
                float posY = BORDER + row * tileSize;
                if (row == hoverRow && col == hoverCol && cells[row][col] == null)
                    fill(210, 200, 180);
                else
                    fill(205, 193, 180);
                noStroke();
                rect(posX, posY, tileSize, tileSize, 16);
            }
        }

        noFill();
        stroke(99, 85, 74);
        strokeWeight(12);
        for (int row = 0; row < Board.N; row++) {
            for (int col = 0; col < Board.N; col++) {
                float posX = BORDER + col * tileSize;
                float posY = BORDER + row * tileSize;
                rect(posX, posY, tileSize, tileSize, 16);
            }
        }
        noStroke();

        for (int row = 0; row < Board.N; row++) {
            for (int col = 0; col < Board.N; col++) {
                Tile tile = cells[row][col];
                if (tile != null) {
                    if (tile.animColumn < tile.column)
                        tile.animColumn = min(tile.animColumn + ANIMATION_SPEED, tile.column);
                    else if (tile.animColumn > tile.column)
                        tile.animColumn = max(tile.animColumn - ANIMATION_SPEED, tile.column);
                    if (tile.animRow < tile.row)
                        tile.animRow = min(tile.animRow + ANIMATION_SPEED, tile.row);
                    else if (tile.animRow > tile.row)
                        tile.animRow = max(tile.animRow - ANIMATION_SPEED, tile.row);
                }
            }
        }

        for (int row = 0; row < Board.N; row++) {
            for (int col = 0; col < Board.N; col++) {
                if (cells[row][col] != null)
                    cells[row][col].draw(this, tileSize);
            }
        }

        if (hasStarted && !isOver && !gameBoard.canMove()) {
            isOver = true;
            endTime = millis();
        }

        fill(0);
        textAlign(RIGHT, TOP);
        long seconds;
        if (!hasStarted) seconds = 0;
        else if (isOver) seconds = (endTime - beginTime) / 1000;
        else seconds = (millis() - beginTime) / 1000;
        text(seconds + "s", width - BORDER + 7, BORDER - 50);

        if (isOver) {
            textAlign(CENTER, CENTER);
            textSize(64);
            fill(255, 50, 50);
            text("GAME OVER", width/2f, height/2f);
        }
    }

    public void mouseMoved() {
        int col = (int)((mouseX - BORDER) / tileSize);
        int row = (int)((mouseY - BORDER) / tileSize);
        if (row >= 0 && row < Board.N && col >= 0 && col < Board.N) {
            hoverRow = row;
            hoverCol = col;
        } else {
            hoverRow = hoverCol = -1;
        }
    }

    public void mouseClicked() {
        if (!hasStarted && mouseButton == LEFT) {
            int col = (int)((mouseX - BORDER) / tileSize);
            int row = (int)((mouseY - BORDER) / tileSize);
            if (row >= 0 && row < Board.N && col >= 0 && col < Board.N
                && gameBoard.getGrid()[row][col] == null) {
                int value = random(1) < 0.5f ? 2 : 4;
                gameBoard.getGrid()[row][col] = new Tile(value, row, col);
                hasStarted = true;
                beginTime = millis();
            }
        }
    }

    @Override
    public void keyPressed() {
        if (key == CODED && hasStarted && !isOver) {
            switch (keyCode) {
                case UP:    gameBoard.move(Direction.UP);    break;
                case DOWN:  gameBoard.move(Direction.DOWN);  break;
                case LEFT:  gameBoard.move(Direction.LEFT);  break;
                case RIGHT: gameBoard.move(Direction.RIGHT); break;
            }
            return;
        }
        if ((key == 'r' || key == 'R') && isOver) {
            gameBoard.reset();
            hasStarted = false;
            isOver = false;
        }
    }
}
