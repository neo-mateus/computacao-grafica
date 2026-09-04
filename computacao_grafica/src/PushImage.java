
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

public class PushImage extends Frame {
    
    static Image imagem;
    
    
    public static void main(String[] args) {
        String nomeImg = "tabuleiro.png";
       
        
        boolean existeFile = new File(nomeImg).exists();
        if(!existeFile){
            System.out.println(nomeImg + "não existe.");
            System.exit(0);
        }
        PushImage display = new PushImage();
        display.load(nomeImg);
        
        File file = new File(nomeImg);
        display.setTitle(file.getName());
        
        display.setSize(200,200);
        display.setVisible(true);
    }
    
    public void load(String arquivo){
        imagem = Toolkit.getDefaultToolkit().getImage(arquivo);
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(imagem, 0, 0 , this);
    }
    
}
