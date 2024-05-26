import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
public class Mypanel extends JPanel implements ActionListener {
    int width=1000;
    int height=700;
    int unit_len=2; //height and width of each cell
    int X_len=(int)width/unit_len;
    int Y_len=(int)height/unit_len;
    int initial_liveOnes=50000; //number of live ones at begining
    boolean[][] is_alive=new boolean[X_len][Y_len];
    int[][] num_neigb=new int[X_len][Y_len];
    Random random=new Random();
    Timer timer=new Timer(1,this);
    Mypanel(){
        this.setPreferredSize(new Dimension(width,height));
        timer.start();
        this.setBackground(Color.black);

        for(int i=0;i<initial_liveOnes;i++){
            int x=random.nextInt(1,X_len-1);
            int y=random.nextInt(1,Y_len-1);
            if(is_alive[x][y]){
                i--;
            }
            is_alive[x][y]=true;
        }
    }
    public void paint(Graphics g){
        super.paint(g);
        for(int i=1;i<Y_len-1;i++) {
            for (int j = 1; j < Y_len-1; j++) {
                if(is_alive[i][j]){
                    g.setColor(Color.white);
                }else{
                    g.setColor(Color.black);
                }
                g.fillRect(i*unit_len,j*unit_len,unit_len,unit_len);
            }
        }
    }
    public  void setNum_neigb(int x,int y){
        if(is_alive[x-1][y-1]){
            num_neigb[x][y]++;
        }
        if(is_alive[x][y-1]){
            num_neigb[x][y]++;
        }
        if(is_alive[x+1][y-1]){
            num_neigb[x][y]++;
        }
        if(is_alive[x-1][y]){
            num_neigb[x][y]++;
        }
        if(is_alive[x+1][y]){
            num_neigb[x][y]++;
        }
        if(is_alive[x-1][y+1]){
            num_neigb[x][y]++;
        }
        if(is_alive[x][y+1]){
            num_neigb[x][y]++;
        }
        if(is_alive[x+1][y+1]){
            num_neigb[x][y]++;
        }
    }
    public void setIs_alive(int x,int y){
        if(num_neigb[x][y]<2 && is_alive[x][y]){   // if number of neighbours of a live cell
            is_alive[x][y]=false;                  // is less than 2, then that cell is dead
        }
        if(num_neigb[x][y]>3 && is_alive[x][y]){   // if number of neighbours of a live cell
            is_alive[x][y]=false;                  // is greater than 3, then that cell is dead
        }
        if(num_neigb[x][y]==3 && !is_alive[x][y]){ // if number of neighbours of a dead cell
            is_alive[x][y]=true;                   // equal to 3, then that cell is alive
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=1;i<Y_len-1;i++) {
            for (int j = 1; j < Y_len-1; j++){
                setNum_neigb(i,j);
            }
        }
        for(int i=1;i<Y_len-1;i++) {
            for (int j = 1; j < Y_len-1; j++){
                setIs_alive(i,j);
            }
        }
        repaint();
        for(int i=1;i<Y_len-1;i++) {
            for (int j = 1; j < Y_len-1; j++) {
                num_neigb[i][j]=0;
            }
        }
    }
}
