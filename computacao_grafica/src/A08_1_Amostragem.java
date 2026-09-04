import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class A08_1_Amostragem extends JFrame {

    public static void main(String[] args) {

        String nomeImg = "tabuleiro.png";
        int resolucaoEspacial = 129; // 8 16 32 64 128

        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame jf = new A08_1_Amostragem(nomeImg, resolucaoEspacial);

        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public A08_1_Amostragem(String nomeImg, int resolucaoEspacial) {

        BufferedImage src = null;
        BufferedImage copia = null;
        BufferedImage dest = null;

        int w, h, ladoMenor, dimPot2;

        File file = new File(nomeImg);

        try {
            src = ImageIO.read(file);
        } catch (IOException ex) {
            System.out.println("Imagem nao encontrada");
            return;
        }

        w = src.getWidth();
        h = src.getHeight();

        ladoMenor = Math.min(w, h);

        dimPot2 = potencia2(ladoMenor);

        copia = copiaImg(dimPot2, src);

        dest = mediaBloco(resolucaoEspacial, copia);

        JLabel view = new JLabel(new ImageIcon(dest));

        getContentPane().add(new JScrollPane(view));

        setSize(w, h);
    }

    private int potencia2(int ladoMenor) {

        if (ladoMenor < 16) {
            return 8;
        } else if (ladoMenor < 32) {
            return 16;
        } else if (ladoMenor < 64) {
            return 32;
        } else if (ladoMenor < 128) {
            return 64;
        } else if (ladoMenor < 256) {
            return 128;
        } else {
            return 256;
        }
    }

    public BufferedImage copiaImg(int dimPot2, BufferedImage src) {

        BufferedImage destImg = new BufferedImage(
                dimPot2,
                dimPot2,
                BufferedImage.TYPE_BYTE_GRAY
        );

        Raster srcR = src.getRaster();
        WritableRaster destWR = destImg.getRaster();

        for (int y = 0; y < dimPot2; y++) {
            for (int x = 0; x < dimPot2; x++) {

                int pixel = srcR.getSample(x, y, 0);

                destWR.setSample(x, y, 0, pixel);
            }
        }

        return destImg;
    }

    public BufferedImage mediaBloco(
            int res, BufferedImage copia) {
                
            int width = 0, height = 0;
            float soma;
            int media, bloco, resX, resY;
            
            width = copia.getWidth();
            height = copia.getHeight();
            resX = width / res;
            resY = resX;
            
            BufferedImage destImg = 
                    new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
                    
            Raster copiaR = copia.getRaster();
            WritableRaster destWR = destImg.getRaster();
            
            bloco = resX*resY;
            
            for (int y=0 ; y<width ; y+=resY)
                for (int x = 0; x<height ; x+=resX){
                    soma = 0.0f;
                    for( int j = 0 ; j<resY ; j++)
                        for (int i = 0; i<resX ; i++)
                            soma += copiaR.getSample(x+i, y+j, 0);
                    
                    media = Math.round(soma/bloco);
                    for( int j = 0 ; j<resY ; j++)
                        for (int i = 0; i<resX ; i++)
                            destWR.setSample(x+i, y+j, 0, media);
                    
                }
                    
                
        return destImg;
    }
}