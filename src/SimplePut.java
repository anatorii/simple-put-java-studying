import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SimplePut extends JFrame {
    static int width = 800;
    static int height = 600;
    private int clientWidth;
    private int clientHeight;
    private BufferedImage image;
    public JPanel panel;
    public JLabel label;
    public SimplePut() throws IOException {
        super("simple put");
        initGui();
    }
    public void setVisible(boolean b) {
        super.setVisible(b);
        clientWidth = SimplePut.width;
        clientHeight = SimplePut.height;
        if (isResizable()) {
            clientWidth = getContentPane().getWidth();
            clientHeight = getContentPane().getHeight();
        }
    }
    private void initGui() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(SimplePut.width, SimplePut.height));
        this.setLocation(d.width / 2 - SimplePut.width / 2, d.height / 2 - SimplePut.height / 2);
        this.getContentPane().setBackground(Color.orange);
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setFocusable(true);
        panel.setLayout(null);
        this.getContentPane().add(panel);

        image = ImageIO.read(new File("object-50.jpg"));

        label = new JLabel();
        label.setIcon(new ImageIcon(image));
        panel.add(label);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int newX = e.getX();
                if (clientWidth - newX < label.getWidth()) {
                    newX = clientWidth - label.getWidth();
                }
                int newY = e.getY();
                if (clientHeight - newY < label.getHeight()) {
                    newY = clientHeight - label.getHeight();
                }
                label.setBounds(newX, newY, image.getWidth(), image.getHeight());
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    SimplePut frame = new SimplePut();
                    frame.pack();
                    frame.setVisible(true);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        });
    }
}
