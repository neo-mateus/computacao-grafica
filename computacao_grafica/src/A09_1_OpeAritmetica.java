
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mateu
 */
public class A09_1_OpeAritmetica extends JFrame {
    
    public static void main(String[] args) {
        
        String img1 = "binZadeh.png";
        String img2 = "tabuleiro.png";
        Scanner entrada = new Scanner(System.in);
        String op;
        System.out.print("Escreva o operador: ");
        op = entrada.nextLine();
        
        A09_1_OpeAritmetica math = new A09_1_OpeAritmetica(img1, op, img2);
        math.setVisible(true);
    }
 
    private A09_1_OpeAritmetica(String image1, String op, String image2) {
        BufferedImage img1 = null, img2 = null, dest = null;
        JLabel img1L, img2L, img3L;
        int w1, h1;
        
        File file1 = new File(image1);
        try {
            img1 = ImageIO.read(file1);
        } catch (IOException ex) {
            System.out.println("Imagem nao existe.");
            System.exit(0);
        }
        
        File file2 = new File(image2);
        try {
            img2 = ImageIO.read(file2);
        } catch (IOException ex) {
            System.out.println("Imagem nao existe.");
            System.exit(0);
        }
        
   
        if(img1.getType() != BufferedImage.TYPE_BYTE_GRAY){
            System.out.println("Formato img1 invalido.");
            System.exit(0);
        }
        
        if(img2.getType() != BufferedImage.TYPE_BYTE_GRAY){
            System.out.println("Formato img2 invalido.");
            System.exit(0);
        }
        
        if(img1.getWidth()> img2.getWidth() || img1.getHeight() > img2.getHeight()){
            System.out.println("Imagem 1 > Imagem 2");
            System.exit(0);
        
        }
        
        if("+".equals(op))
            dest = adiciona(img1, img2);
        
        if("-".equals(op))
            dest = subtrai(img1,img2);
        
        if("*".equals(op))
            dest = multiplica(img1,img2);
        
        if("/".equals(op))
            dest = divide(img1,img2);
        else
            System.out.println("Operador invalido (somente +, -, *, /)");
        
            
        w1 = dest.getWidth();
        h1 = dest.getHeight();
        
        getContentPane().setLayout(new GridLayout(1,3));
        img1L = new JLabel(new ImageIcon(img1));
        img2L = new JLabel(new ImageIcon(img2));
        img3L = new JLabel(new ImageIcon(dest));
        
        getContentPane().add(new JScrollPane(img1L));
        getContentPane().add(new JScrollPane(img2L));
        getContentPane().add(new JScrollPane(img3L));
        setSize(3*w1, h1+50);
       
    }

    private BufferedImage adiciona(BufferedImage img1, BufferedImage img2) {
        int w, h, pixel1 = 0, pixel2 = 0, soma = 0, tipo;
        w = img1.getWidth();
        h = img1.getHeight();
        
        tipo = BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage dest = new BufferedImage(w, h, tipo);
  
        
        Raster img1R = img1.getRaster();
        Raster img2R = img2.getRaster();
        WritableRaster destWR = dest.getRaster();
        
        for (int y = 0; y<h; y++)
            for(int x = 0; x<w; x++){
                pixel1 = img1R.getSample(x, y, 0);
                pixel2 = img2R.getSample(x, y, 0);
                soma = pixel1 + pixel2;
                
                if(soma > 255)
                    soma = 255;
                destWR.setSample(x, y, 0, soma);
            }
        return dest;
        
    }

    private BufferedImage subtrai(BufferedImage img1, BufferedImage img2) {
        int w, h, pixel1 = 0, pixel2 = 0, subtracao = 0, tipo;
        w = img1.getWidth();
        h = img1.getHeight();
        
        tipo = BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage dest = new BufferedImage(w, h, tipo);
  
        
        Raster img1R = img1.getRaster();
        Raster img2R = img2.getRaster();
        WritableRaster destWR = dest.getRaster();
        
        for (int y = 0; y<h; y++)
            for(int x = 0; x<w; x++){
                pixel1 = img1R.getSample(x, y, 0);
                pixel2 = img2R.getSample(x, y, 0);
                subtracao = pixel2 - pixel1;
                
                if(subtracao < 0)
                    subtracao = 0;
                
                destWR.setSample(x, y, 0, subtracao);
            }
        return dest;
    }

    private BufferedImage multiplica(BufferedImage img1, BufferedImage img2) {
        int w, h, pixel1 = 0, pixel2 = 0, multiplicacao = 0, tipo;
        w = img1.getWidth();
        h = img1.getHeight();
        
        tipo = BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage dest = new BufferedImage(w, h, tipo);
  
        
        Raster img1R = img1.getRaster();
        Raster img2R = img2.getRaster();
        WritableRaster destWR = dest.getRaster();
        
        for (int y = 0; y<h; y++)
            for(int x = 0; x<w; x++){
                pixel1 = img1R.getSample(x, y, 0);
                pixel2 = img2R.getSample(x, y, 0);
                multiplicacao = pixel1*pixel2/255;
                
                if(multiplicacao > 255)
                    multiplicacao = 255;
                destWR.setSample(x, y, 0, multiplicacao);
            }
        return dest;
    }

    private BufferedImage divide(BufferedImage img1, BufferedImage img2) {
    int w, h, pixel1, pixel2, pixel, tipo;
    double res;

    w = img1.getWidth();
    h = img1.getHeight();

    tipo = BufferedImage.TYPE_BYTE_GRAY;
    BufferedImage dest = new BufferedImage(w, h, tipo);

    Raster img1R = img1.getRaster();
    Raster img2R = img2.getRaster();
    WritableRaster destWR = dest.getRaster();

    for (int y = 0; y < h; y++)
        for (int x = 0; x < w; x++) {

            pixel1 = img1R.getSample(x, y, 0);
            pixel2 = img2R.getSample(x, y, 0);

            if (pixel2 == 0) {
                pixel = 255;
            } else {

                res = (double) pixel1 / pixel2;

                if (res <= 1) {
                    pixel = (int) (127 * (res + 1));
                } else {
                    pixel = (int) (128 + (res / 2));
                }
            }

            destWR.setSample(x, y, 0, pixel);
        }

    return dest;
}
         
    
}
