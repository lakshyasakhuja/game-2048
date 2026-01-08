package shapes.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    public static int N = 4;
    private Tile[][] grid;
    private Random random = new Random();

    public Board(int size) {
        N = size;
        grid = new Tile[N][N];
    }

    public void reset() {
        grid = new Tile[N][N];
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void spawnRandom() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (grid[row][col] == null) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }
        if (emptyCells.isEmpty()) return;
        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        int value = random.nextDouble() < 0.5 ? 2 : 4;
        grid[cell[0]][cell[1]] = new Tile(value, cell[0], cell[1]);
    }

    public boolean canMove() {
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                if (grid[row][col] == null) return true;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (row + 1 < N && grid[row][col].value == grid[row+1][col].value) return true;
                if (col + 1 < N && grid[row][col].value == grid[row][col+1].value) return true;
            }
        }
        return false;
    }

    public boolean move(Direction dir) {
        boolean moved = false;
        for (int row = 0; row < N; row++)
            for (int col = 0; col < N; col++)
                if (grid[row][col] != null)
                    grid[row][col].hasMerged = false;

        if (dir == Direction.LEFT) {
            for (int row = 0; row < N; row++) {
                for (int col = 1; col < N; col++) if (grid[row][col] != null) {
                    int target = col;
                    while (target > 0 && grid[row][target-1] == null) {
                        grid[row][target-1] = grid[row][target];
                        grid[row][target]   = null;
                        grid[row][target-1].column = target-1;
                        moved = true; target--;
                    }
                    if (target > 0
                     && grid[row][target-1].value == grid[row][target].value
                     && !grid[row][target-1].hasMerged) {
                        grid[row][target-1].value *= 2;
                        grid[row][target-1].hasMerged = true;
                        grid[row][target] = null;
                        moved = true;
                    }
                }
            }
        }
        if (dir == Direction.RIGHT) {
            for (int row = 0; row < N; row++) {
                for (int col = N-2; col >= 0; col--) if (grid[row][col] != null) {
                    int target = col;
                    while (target < N-1 && grid[row][target+1] == null) {
                        grid[row][target+1] = grid[row][target];
                        grid[row][target]   = null;
                        grid[row][target+1].column = target+1;
                        moved = true; target++;
                    }
                    if (target < N-1
                     && grid[row][target+1].value == grid[row][target].value
                     && !grid[row][target+1].hasMerged) {
                        grid[row][target+1].value *= 2;
                        grid[row][target+1].hasMerged = true;
                        grid[row][target] = null;
                        moved = true;
                    }
                }
            }
        }
        if (dir == Direction.UP) {
            for (int col = 0; col < N; col++) {
                for (int row = 1; row < N; row++) if (grid[row][col] != null) {
                    int target = row;
                    while (target > 0 && grid[target-1][col] == null) {
                        grid[target-1][col] = grid[target][col];
                        grid[target][col]   = null;
                        grid[target-1][col].row = target-1;
                        moved = true; target--;
                    }
                    if (target > 0
                     && grid[target-1][col].value == grid[target][col].value
                     && !grid[target-1][col].hasMerged) {
                        grid[target-1][col].value *= 2;
                        grid[target-1][col].hasMerged = true;
                        grid[target][col] = null;
                        moved = true;
                    }
                }
            }
        }
        if (dir == Direction.DOWN) {
            for (int col = 0; col < N; col++) {
                for (int row = N-2; row >= 0; row--) if (grid[row][col] != null) {
                    int target = row;
                    while (target < N-1 && grid[target+1][col] == null) {
                        grid[target+1][col] = grid[target][col];
                        grid[target][col]   = null;
                        grid[target+1][col].row = target+1;
                        moved = true; target++;
                    }
                    if (target < N-1
                     && grid[target+1][col].value == grid[target][col].value
                     && !grid[target+1][col].hasMerged) {
                        grid[target+1][col].value *= 2;
                        grid[target+1][col].hasMerged = true;
                        grid[target][col] = null;
                        moved = true;
                    }
                }
            }
        }

        if (moved) spawnRandom();
        return moved;
    }
}
