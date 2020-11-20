import javax.swing.border.LineBorder;
import java.awt.*;

// Option class
public class Properties {
    final static int m = 20;        //< height
    final static int n = 25;        //< width
    final static int bombs = 50;    //< # of bombs

    final static int block_size = 20;   //< size of block is block_size * block_size
    final static int space = 10;        //< left and right space. See the README file
    final static int topSpace = 100;    //< top space. See the README file
    final static int bottomSpace = 30;  //< bottem space. See the README file

    final static Color panel_default_color = new Color(100, 100, 100);          //< default color when not clicked, mouse is not over here, not marked
    final static Color panel_onMouse_color = new Color(150, 150, 150);          //< when mouse is over here (not marked)
    final static Color panel_clicked_color = new Color(80, 80, 80);             //< when pressed
    final static Color panel_marker_color = new Color(250, 200, 200);           //< when marked
    final static Color panel_marker_onClick_color = new Color(150, 100, 100);   //< when mouse is over here (marked)
    final static Color panel_bomb_color = Color.BLACK;                                   //< color of bomb block. displayed when the game is over
    final static Color panel_invisible = new Color(0, 0, 0, 0);              //< just an invisible color. Used when the block is visible.

    final static Font blockFont = new Font("SansSerif", Font.PLAIN, 15);      //< font used for label, displaying # of blocks
    final static Font gameOverFont = new Font("SansSerif", Font.BOLD, 50);    //< font used for label "Game Over"
    final static LineBorder panelLine = new LineBorder(Color.BLACK, 1);         //< border of block label
}
