
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.text.DecimalFormat;
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
public class ExtracaoRegioesInteresse extends JFrame {
    
    public static void main(String[] args){
        int x = 0, y = 0, w = 20, h = 20;
        
        String nomeImg = "tabuleiroNC.png";
        
        if(x < 0)
           x = 0;
        if(y < 0)
           y = 0;
        if(x > w)
           x = 0;
        if(y > h)
           y = 0;
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        ExtracaoRegioesInteresse rm = new ExtracaoRegioesInteresse(nomeImg, x, y, w, h);
        
        rm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rm.setSize(200, 200);
        rm.setVisible(true);
       
    }
    
    public ExtracaoRegioesInteresse(String aFile, int x, int y, int w, int h){
        BufferedImage bimage = null;
        
        File file = new File(aFile);
        try{
            bimage = ImageIO.read(file);
        }catch(Exception e){
            System.out.println("Imagem '" + aFile + "' nao existe.");
            System.exit(0);
        }
        
        setTitle("Média da ROI "+file.getName());
        
        if(w>bimage.getWidth())
            w = bimage.getWidth() - x;
        if(h>bimage.getHeight())
            h = bimage.getHeight() - y;
        
        if((w>bimage.getWidth()) && (h > bimage.getHeight())){
            w = bimage.getWidth();
            h = bimage.getHeight();
            x = 0;
            y = 0;
        }
        
        Rectangle regiao = new Rectangle(x, y, w, h);
        double mediaROI = ExtracaoRegioesInteresse.this.media(bimage, regiao);
        DecimalFormat df = new DecimalFormat("0.000");
        System.out.println("\nmedia" + df.format(mediaROI));
       
    }

    public double media(BufferedImage img, Rectangle regiao){
        BufferedImage biRegiao = img.getSubimage(regiao.x, regiao.y, regiao.width, regiao.height);
    
            JLabel limg = new JLabel(new ImageIcon(biRegiao));
            getContentPane().add(new JScrollPane(limg));
            setSize(biRegiao.getWidth(), biRegiao.getHeight());
    
            return media(biRegiao);
    }
    
    public double media(BufferedImage src){
        
        double sum = 0.0;
        
        Raster raster = src.getRaster();
        for(int i =0; i<src.getHeight();i++)
            for(int j =0; j<src.getWidth(); j++)
                sum+=raster.getSample(j, i, 0);
        
        return sum/(src.getWidth()*src.getHeight());
    
}
}
