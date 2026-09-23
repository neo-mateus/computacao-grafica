import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class A09_2_TAfim extends JFrame {
    
    public static void main(String[] args) {
        double theta = 15.0;
        String nomeImg="meudeputado.jpg";
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame ta = new A09_2_TAfim(nomeImg, theta);
        
        ta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ta.setVisible(true);
    }
    
    public A09_2_TAfim(String arquivo,double angulo){
        BufferedImage src=null, dest1=null, dest2,dest3,dest4,dest5,dest6;
        JLabel srcL, destL1, destL2,destL3,destL4,destL5,destL6;
        int w, h;
        
        File file = new File(arquivo);
        try{
            src = ImageIO.read(file);
        } catch (IOException ex) {
            System.out.println("Imagem não existe");
            System.exit(0);
        }
        
        angulo = Math.toRadians(angulo);
        
        double x = src.getWidth()/2.0;
        double y = src.getHeight()/2.0;
        
        /*
        AffineTransform atGira = new AffineTransform();
        atGira.setToTranslation(x, y);
        atGira.rotate(angulo);
        atGira.translate(-x, -y);
        */
        AffineTransform atGira = AffineTransform.getRotateInstance(angulo, x, y);
        AffineTransform atTrans = AffineTransform.getTranslateInstance(x-x/2, y-y/2);
        AffineTransform atEsc = AffineTransform.getScaleInstance(0.5, 0.5);
        AffineTransform atDis = AffineTransform.getShearInstance(0.30, 0);
        AffineTransform atRef = new AffineTransform();
        atRef.translate(src.getWidth(), 0);
        atRef.scale(-1.0, 1.0);
       

        dest1 = tAfim(src, atGira);
        dest2 = tAfim(src,atTrans);
        dest3 = tAfim(src, atEsc);
        dest4 = tAfim(src, atDis);
        dest5 = tAfim(src,atRef);
        
        srcL = new JLabel(new ImageIcon(src));
        destL2 = new JLabel(new ImageIcon(dest2));
        destL1 = new JLabel(new ImageIcon(dest1));
        destL3 = new JLabel(new ImageIcon(dest3));
        destL4 = new JLabel(new ImageIcon(dest4));
        destL5 = new JLabel(new ImageIcon(dest5));
        
        w = dest1.getWidth() + src.getWidth()+dest2.getWidth()+dest3.getWidth()+dest4.getWidth()+dest5.getWidth();
        h = dest1.getHeight() + src.getHeight()+dest2.getHeight()+dest3.getHeight()+dest4.getHeight()+dest5.getHeight();
       
        setLayout(new GridLayout(2,3));
        getContentPane().add(new JScrollPane (srcL));
        getContentPane().add(new JScrollPane (destL2));
        getContentPane().add(new JScrollPane (destL1));
        getContentPane().add(new JScrollPane (destL3));
        getContentPane().add(new JScrollPane (destL4));
        getContentPane().add(new JScrollPane (destL5));
        
        setSize(w, h);
    }
    
    private BufferedImage tAfim(BufferedImage src, AffineTransform atGira){

        int w = src.getWidth();
        int h = src.getHeight();
        int tipo = BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage imagemTx = new BufferedImage(w, h, tipo);
        Graphics2D cg = imagemTx.createGraphics();
        cg.drawImage(src, atGira, this);
        
        return imagemTx;
    }
}