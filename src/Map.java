public class Map {
    int m = Properties.m;           //< height
    int n = Properties.n;           //< width
    int bombs = Properties.bombs;   //< # of bombs

    final private Block[][] map = new Block[m][n];

    Map() {
        initMap();
    }

    // getter of map
    public Block getBlock(int i, int j) {
        return map[i][j];
    }

    // initialize map: locate bombs
    void initMap() {
        int remainBomb = bombs;
        int remainLand = m * n - bombs;
        for (int i = 0; i <= m + 1; i++) {
            for (int j = 0; j <= n + 1; j++) {
                // if boundary, locate WallBlock
                if (i == 0 || i == m + 1 || j == 0 || j == n + 1) map[i][j] = new WallBlock(i, j, this);

                // if not boundary, locate BombBlock or LandBlock
                else {
                    if (remainBomb == 0) {
                        map[i][j] = new LandBlock(i, j, this);
                        remainLand--;
                    } else if (remainLand == 0) {
                        map[i][j] = new BombBlock(i, j, this);
                        remainBomb--;
                    } else {
                        if (Math.random() < ((double) remainBomb) / (remainLand + remainBomb)) {
                            map[i][j] = new BombBlock(i, j, this);
                            remainBomb--;
                        } else {
                            map[i][j] = new LandBlock(i, j, this);
                            remainLand--;
                        }
                    }
                }
            }
        }

        // set each block's mineCnt variable
        for (int i = 1; i <= m; i++) for (int j = 1; j <= n; j++) map[i][j].init();
    }

    // click position (pi, pj)
    boolean click(int pi, int pj) {
        if (map[pi][pj] instanceof BombBlock) return true;
        boolean[][] visit = new boolean[m + 2][n + 2];
        for (int i = 0; i <= m + 1; i++) {
            for (int j = 0; j <= n + 1; j++) {
                if (!(map[i][j] instanceof LandBlock) || map[i][j].visible) visit[i][j] = true;
            }
        }
        updateMap(pi, pj, visit);
        return false;
    }

    // function for click() function
    void updateMap(int pi, int pj, boolean[][] visit) {
        map[pi][pj].visible = true;
        visit[pi][pj] = true;
        if (map[pi][pj].mineCnt != 0) return;
        int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++) if (!visit[pi + di[i]][pj + dj[i]]) updateMap(pi + di[i], pj + dj[i], visit);
    }

    // toString() for debugging
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] instanceof BombBlock) ret.append("X");
                else ret.append("O");
            }
            ret.append("\n");
        }
        return ret.toString();
    }
}

abstract class Block {
    Map map;            //< Map class for LandBlock. Otherwise, not used
    boolean visible;    //< if the block is visible
    boolean marked;     //< if the block is marked
    boolean mouseOn;    //< if mouse is on the block
    boolean pressed;    //< if mouse is pressed
    int pi;             //< i coordinate of block
    int pj;             //< j coordinate of block
    int mineCnt = 0;    //< # of BombBlocks around the block, for LandBlock. Otherwise not used

    public Block(int pi, int pj, Map map) {
        this.map = map;
        this.pi = pi;
        this.pj = pj;
    }

    abstract void init();
}

class BombBlock extends Block {
    BombBlock(int pi, int pj, Map map) {
        super(pi, pj, map);
    }

    @Override
    void init() {
        // nothing to do
    }
}

class LandBlock extends Block {

    LandBlock(int pi, int pj, Map map) {
        super(pi, pj, map);
    }

    @Override
    void init() {
        // set mineCnt variable by counting # of Bombs around this block
        int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int i = 0; i < 8; i++)
            if (map.getBlock(pi + di[i], pj + dj[i]) instanceof BombBlock)
                mineCnt++;
    }
}

class WallBlock extends Block {
    WallBlock(int pi, int pj, Map map) {
        super(pi, pj, map);
    }

    @Override
    void init() {
        // nothing to do
    }
}