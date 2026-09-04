import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class LerImagem {

    public static void main(String[] args) throws IOException {

        BufferedImage img;

        img = ImageIO.read(new File("tabuleiro.png"));

        WritableRaster wr = img.getRaster();
        int width = wr.getWidth();
        int height = wr.getHeight();
        int[] cor = new int[3];

        for (int h = 0; h < height; h++) {
    for (int w = 0; w < width; w++) {

        wr.getPixel(w, h, cor);
       
            System.out.println("R: " + cor[0]);
             System.out.println("Sample: " + wr.getSample(w, h, 0));
            
   
        }
    }
}
    }