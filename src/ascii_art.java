import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @ClassName ascii_art
 * @Description It is the class for ascii graphics in the terminal.
 */
public class ascii_art {

    private Font font;
    private int width;
    private int height;

    ascii_art(int w, int h){
        this.font = new Font("SansSerif", Font.BOLD, 24);
        this.width = w;
        this.height = h;
    }

    public void drawString(String text, String artChar) {
        BufferedImage image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = getGraphics2D(image.getGraphics());
        graphics2D.drawString(text, 6, ((int) (this.height * 0.67)));
        int y = 0;
        do{
            StringBuilder stringBuilder = new StringBuilder();
            int x = 0;
            do{
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : artChar);
                x++;
            }while(x<this.width);

            if (!stringBuilder.toString().trim().isEmpty()) {
                System.out.println(ColorUtils.PURPLE+stringBuilder+ColorUtils.RESET);
            }

            y++;

        }while(y < this.height);
    }

    private Graphics2D getGraphics2D(Graphics graphics) {
        graphics.setFont(this.font);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        return graphics2D;
    }
}