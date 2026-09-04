import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class DesenhaImg {

    public static void main(String[] args) throws IOException {
        int largura = 256;
        int altura = 256;

        BufferedImage img =
                new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        WritableRaster wr = img.getRaster();

        Random randCor = new Random();

        for (int h = 0; h < altura; h++) {
            for (int w = 0; w < largura; w++) {

                int[] cor = {
                    randCor.nextInt(256), // R
                    randCor.nextInt(256), // G
                    randCor.nextInt(256)  // B
                };

                wr.setPixel(w, h, cor);
            }
        }

        ImageIO.write(img, "PNG", new File("tabuleiro.png"));
    }
}