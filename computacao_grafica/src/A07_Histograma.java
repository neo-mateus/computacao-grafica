import java.awt.Color;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;
import java.io.File;

public class A07_Histograma extends Frame {
        
    int w, h, hist[] = new int [256], max_hist = 0;   
    Image img;


    public static void main(String[] args) {
        String nomeImg = "tabuleiro.png";
                A07_Histograma f = new A07_Histograma(nomeImg);
                f.setVisible(true);
    }
    
    public A07_Histograma(String nomeImg){
        int p, R, G, B, y, iW = 0, iH = 0, pixels[] = new int [1];
        
        
        PixelGrabber pg = null;
        
        File file = new File(nomeImg);
        if(!file.exists()){
            System.out.println("Arquivo nao encontrado");
            System.exit(0);
            
        }
        
        img = Toolkit.getDefaultToolkit().getImage(nomeImg);
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(img, 0);
        try {
            mt.waitForID(0);
            iW = img.getWidth(null);
            iH = img.getHeight(null);
            pixels = new int [iW*iH];
            pg = new PixelGrabber(img, 0,0 , iW, iH, pixels, 0, iW);
            pg.grabPixels();
            
        } catch (InterruptedException ex) {
            System.out.println("Erro lendo arquivo");
            System.exit(0);
        }
        
        w = img.getWidth(null);
        h = img.getHeight(null);
        setSize(w, h);
        
        
        ColorModel cModel = pg.getColorModel();
        for (int i=0 ; i<pixels.length ; i++ ){
            R = cModel.getRed(pixels[i]);
            hist[R]++;
         }
        
        for (int i=0 ; i<256 ; i++){
            max_hist = Math.max(hist[i], max_hist);
            
        }
        
        
        
        
      }
            @Override
            public void paint(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(img, 0, 0, null);
                
                int x, y, lasty;
                x = (w-256)/2;
                lasty = h - h*hist[0]/max_hist;
                for(int i=0 ; i<256 ; i++, x++){
                    y = h-h*hist[i]/max_hist;
                    g2.setColor(Color.red);
                    g2.fillRect(x, y, 1, h);
                    g2.setColor(Color.black);
                    g2.drawLine(x-1, lasty, x, y);
                    lasty = y;
                    
                }
            }
    
            @Override
            public boolean handleEvent(Event event) {
                if (event.id == Event.WINDOW_DESTROY) {
                System.exit(0);
    }

    return false;
 }
    }
    


