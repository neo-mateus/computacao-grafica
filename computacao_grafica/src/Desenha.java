
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mateu
 */
public class Desenha {
    
    public static void main(String[] args) {
         JFrame frame = new JFrame();

        frame.add(new Painel());
        frame.setTitle("Exemplo Java 2D");
        frame.setSize(390, 440);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
}
class Painel extends JPanel {
    
    @Override 
    public void paintComponent(Graphics g){
        try {
            desenhaLinhas(g);
            desenhaPontos(g);
            desenhaCaps(g);
            desenhaJoins(g);
            desenhaTexto(g);
            desenhaShapesBasicos(g);
            desenhaImagem(g);
            salvarImagem();
        } catch (IOException ex) {
            Logger.getLogger(Painel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void desenhaPontos(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        
        int w = getWidth();
        int h = getHeight();
        
        Random r = new Random();      
         
             for(int i=0; i<2000; i++){
                 int x = Math.abs(r.nextInt())% w;
                 int y = Math.abs(r.nextInt())% w;
                 g2.drawLine( x, y, x, y);
             }
    }
    
    private void desenhaLinhas(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        
        g2.drawLine(30,30,200,30);
        g2.drawLine(200,30,30,200);
        g2.drawLine(30,200,200,200);
      
        
   }
     
    private void desenhaCaps(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        BasicStroke bs1 = new BasicStroke(8, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke (bs1);
        g2.drawLine(20,30,250,30);
        
         BasicStroke bs2 = new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL);
        g2.setStroke (bs2);
        g2.drawLine(20,80,250,80);
        
         BasicStroke bs3 = new BasicStroke(8, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL);
        g2.setStroke (bs3);
        g2.drawLine(20,130,250,130);
        
        BasicStroke bs4 = new BasicStroke();
        g2.setStroke (bs4);
        g2.drawLine(20,20,20,140);
        g2.drawLine(250,20,250,140);
        g2.drawLine(254,20,254,140);        
        
    }
    
    private void desenhaJoins(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke bs1 = new BasicStroke(8,
        BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL);
        
        g2.setStroke(bs1);
        g2.drawRect(15,15,80,50);
        
        BasicStroke bs2 = new BasicStroke(8,
        BasicStroke.JOIN_MITER,BasicStroke.JOIN_BEVEL);
        
        g2.setStroke(bs2);
        g2.drawRect(15,45,80,50);
        
        BasicStroke bs3 = new BasicStroke(8,
        BasicStroke.JOIN_ROUND,BasicStroke.JOIN_BEVEL);
        
        g2.setStroke(bs3);
        g2.drawRect(15,90,80,50);
    }
    
    private void desenhaTexto(Graphics g){
         Graphics2D g2 = (Graphics2D) g;
        
         g2.setPaint(Color.BLACK);
         g2.setFont(new Font("Century Gothic",Font.PLAIN,30));
         
         String texto = "Desenhando um texto";
         g2.drawString(texto,80,80);

    }
    
    private void desenhaShapesBasicos (Graphics g){
         Graphics2D g2 = (Graphics2D) g;
         
         g2.setPaint(Color.BLACK);
         
         g2.fillRect(30, 20, 50, 50);
         g2.fillRoundRect(250, 20, 70, 60, 25, 25);
         g2.fill(new Ellipse2D.Double(10, 100, 80, 100));
         g2.fillArc(120, 130, 110, 100, 5, 150);
    }
    
    private void desenhaImagem (Graphics g) throws IOException{
        Graphics2D g2 = (Graphics2D) g;
                
        BufferedImage img;
        img = ImageIO.read(new File("TI.PNG"));
        g2.drawImage(img, 160, 160, 240, 240, this);
        g2.drawImage(img, 10, 10, this);
        
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRoundRect(110, 110, img.getWidth()/2, img.getHeight()/2,10,10);
    }
    
    private void salvarImagem()throws IOException {
        int width = 256;
        int height = 256;
        
        BufferedImage img = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = img.getRaster();
        
        int[] cor1 = new int[]{255,0,0};
        int[] cor2 = new int[]{0,0,255};
        
        for(int h=0; h<height ; h++)
            for(int w=0; w<width ; w++){
                if( ((w/32)+(h/32)) %2 == 0)
                raster.setPixel(w, h, cor1);
                else 
                    raster.setPixel( w,h, cor2);
    }
    ImageIO.write(img,"PNG", new File("teste1.png"));
        
    }
    

}