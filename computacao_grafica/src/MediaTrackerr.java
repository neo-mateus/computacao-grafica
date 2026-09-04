import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.awt.Graphics;

public class MediaTrackerr extends Frame {

    static Image imagem;

    public static void main(String[] args) {
        String nomeImg = "tabuleiro.png";

        boolean existeImg = new File(nomeImg).exists();

        if (!existeImg) {
            System.out.println(nomeImg + " nao existe");
            System.exit(0);
        }

        MediaTrackerr mtI0 = new MediaTrackerr(nomeImg);

        mtI0.setSize(800, 600);
        mtI0.setVisible(true);
    }

    public MediaTrackerr(String arquivo) {

        imagem = Toolkit.getDefaultToolkit().getImage(arquivo);

        MediaTracker mt = new MediaTracker(this);

        mt.addImage(imagem, 0);

        try {
            mt.waitForID(0);
        } catch (InterruptedException ex) {
            System.out.println("ERRO");
            System.exit(0);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(imagem, 0, 0, this);
    }
}