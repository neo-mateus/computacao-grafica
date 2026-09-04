import java.awt.Event;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

public class A06_Image2Buffered extends Frame {

    
    private BufferedImage bimage = null;
    public A06_Image2Buffered(String imageName) {

        Image img;
        int imgW, imgH, type;
        File file = new File(imageName);

        
        if (!file.exists()) {
            System.out.println("Arquivo nao existe.");
            System.exit(0);
        }

       
        img = readImage(imageName);
        if (img == null) {
            System.out.println("Nao foi possivel ler a imagem.");
            System.exit(0);
        }

        imgW = img.getWidth(null);
        imgH = img.getHeight(null);

       
        type = BufferedImage.TYPE_BYTE_GRAY;
        bimage = returnBuffered(img, imgW, imgH, type);
        setSize(imgW, imgH);
        setTitle("Imagem em BufferedImage");
    }

    public static void main(String[] args) {
        String imageName = "tabuleiro.png";
        A06_Image2Buffered ib = new A06_Image2Buffered(imageName);
        ib.setVisible(true);
    }

   
    private Image readImage(String imageName) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage(imageName);
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(img, 0);

        try {
            mt.waitForID(0);
        } catch (InterruptedException ex) {
            System.out.println("Problema em ler imagem.");
            return null;
        }

        return img;
    }

   
    private BufferedImage returnBuffered(
            Image img, int w, int h, int tipo) {
        BufferedImage bi = new BufferedImage(w, h, tipo);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        return bi;
    }

   
    @Override
    public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
        if (bimage != null) {
        g2.drawImage(bimage, 0, 0, this);
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