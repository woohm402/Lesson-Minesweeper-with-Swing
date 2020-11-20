import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Display extends JFrame {
    Map map;                    //< map to display
    final int m = Properties.m; //< height of the map
    final int n = Properties.n; //< width of the map
    JPanel[][] panels;          //< panels that will cover each block
    JLabel[][] labels;          //< labels that will display the number
    JLabel gameOverLabel;       //< label that will show "Game Over"

    boolean end;                //< true when the game is over

    Display(Map map) {
        this.map = map;
        panels = new JPanel[m + 2][n + 2];
        labels = new JLabel[m + 2][n + 2];
        // TODO set the size of the frame properly
        //  write your code here

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                final int finalI = i;               //< variable that will be used in MouseListener
                final int finalJ = j;               //< variable that will be used in MouseListener
                final Block block = map.getBlock(i, j);  //< variable that will be used in MouseListener
                panels[i][j] = new JPanel();
                // TODO set panels[i][j]'s border, background, bounds
                //  write your code here
                panels[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // nothing to do
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        block.pressed = true;
                        display();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        block.pressed = false;
                        if (!end && !block.visible) {
                            // check if clicked mouse button is left or right
                            if (SwingUtilities.isLeftMouseButton(e)) end = map.click(finalI, finalJ);
                            else if (SwingUtilities.isRightMouseButton(e)) block.marked = !block.marked;
                        }
                        display();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        block.mouseOn = true;
                        display();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        block.mouseOn = false;
                        display();
                    }
                });
                add(panels[i][j]);

                labels[i][j] = new JLabel();
                // TODO set label[i][j]'s bounds, horizontal alignment, font, background, border
                //  write your code here
                add(labels[i][j]);
            }
        }

        // construct "Game Over" Label
        gameOverLabel = new JLabel();
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setText("Game Over!");
        gameOverLabel.setBounds(0, 0, 2 * Properties.space + n * Properties.block_size, Properties.topSpace);
        gameOverLabel.setFont(Properties.gameOverFont);
        gameOverLabel.setVisible(false);
        add(gameOverLabel);

        add(new JPanel()); // dummy
        setVisible(true);
    }

    // display the map after updated by click
    void display() {
        // if game is end, show all blocks and the "Game Over" Label
        if (end) {
            gameOverLabel.setVisible(true);
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    Block block = map.getBlock(i, j);
                    JPanel panel = panels[i][j];
                    JLabel label = labels[i][j];

                    // for each block,
                    // if block is BombBlock:
                    if (block instanceof BombBlock) {
                        label.setVisible(false);
                        panel.setVisible(true);
                        panel.setBackground(Properties.panel_bomb_color);
                    }
                    // if block is LandBlock:
                    else {
                        panel.setBackground(Properties.panel_invisible);
                        if (block.mineCnt != 0) label.setText(block.mineCnt + "");
                        else label.setText("");
                        label.setVisible(true);
                    }
                }
            }
        }
        // if game is not end, iterate each blocks and display them properly
        else {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    Block block = map.getBlock(i, j);
                    JPanel panel = panels[i][j];
                    JLabel label = labels[i][j];

                    // TODO show the block properly
                    //  maybe you have to use a lot of if(){} and else{}
                    //  write your code here
                }
            }
        }
    }
}
