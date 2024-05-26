import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        //this.setLayout(new FlowLayout());
        this.add(new Mypanel());
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
