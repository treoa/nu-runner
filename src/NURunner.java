import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class NURunner  {
    public static JFrame window;
    public static Common common;
    private static Display display;
    private static boolean implemented = false;


    private NURunner() throws IOException {
        window = new JFrame("Treo's NURunner");
        common = new Common();
        display = new Display(common);
        window.add(display);
        window.setLocationByPlatform(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(3);
        window.pack();
        SwingUtilities.invokeLater(new Runnable(this)); //as in tutorial (slides) GUI
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        NURunner nuRunner = new NURunner();
        while (true){
            if (!implemented){
                common.stepAllEntities();
                window.repaint();
            }
            Thread.sleep(50L);
        }
    }
}
