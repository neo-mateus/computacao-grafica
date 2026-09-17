
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
public class AmpliacaoReducao extends JFrame {
    
    public static void main(String[] args) {
        String img = "mini_einstein.jpg";
        
        //tipoOp 1 - ampliação, 2- redução
        int fator = 1, tipoOp = 1;
        
        if(fator <= 0)
            fator = 1;
        if((tipoOp <= 0) || (tipoOp > 2))
            tipoOp = 1;
       
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        JFrame zoomImg = new AmpliacaoReducao(img, fator, tipoOp);
        
        zoomImg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        zoomImg.setVisible(true);
    }
    
    public AmpliacaoReducao(String aFile, int aFator, int aTipoOp){
        BufferedImage bimage = null, dest = null;
        
        File file = new File(aFile);
        try{
            bimage = ImageIO.read(file);
        } catch (IOException e){
            System.out.println("Imagem '" + aFile + "' nao existe.");
            System.exit(0);
        }
        
        setTitle("Amplia/Reduz imagem: " + file.getName());
        
        dest = zoomImage(bimage, aFator, aTipoOp);
        
        JLabel limg = new JLabel(new ImageIcon(dest));
        getContentPane().add(new JScrollPane(limg));
        setSize(bimage.getWidth(), bimage.getHeight());
 
    }
    
    public BufferedImage zoomImage(BufferedImage image, int fator, int tipoOp){
        
        BufferedImage bi =null;
        
        int w, h;
        
        switch(tipoOp){
            case 1:
                w = fator*image.getWidth();
                h = fator*image.getHeight();
                bi = new BufferedImage(w, h, image.getType());
                for(int j = 0; j<h;j++)
                    for(int i = 0; i<w;i++)
                        bi.setRGB(i, j, image.getRGB(i/fator, j/fator));
                break;
            case 2:
                w = (int)image.getWidth();
                h = (int)image.getHeight();
                bi = new BufferedImage(w, h, image.getType());
                for (int j = 0; j < h; j++)
                    for(int i = 0; i <w; i++)
                        bi.setRGB(i, j, image.getRGB(i*fator, j*fator));
                break;
        }
        return bi;
    }
}
