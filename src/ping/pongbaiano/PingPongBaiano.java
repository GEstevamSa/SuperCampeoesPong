package ping.pongbaiano;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PingPongBaiano extends JFrame implements ActionListener{
    Font normal = new Font ("Digiface", Font.BOLD, 35);
    
    Grafico gr;
    Timer time;
    int bx = 50, by = 50,velX = 1, velY = 1, dir = 1, dir1 = 1,vidaPl = 5, vidaAd =5;
    int px = 10, py = 100, iaX = 750, iay = 100;
    
    boolean FimJogo, Restart;
    
    @Override
    public void actionPerformed (ActionEvent e){
        try { 
            jogar();
        } catch (InterruptedException ex) {
            Logger.getLogger(PingPongBaiano.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void jogar() throws InterruptedException{
        
        bx += velX;
        by += velY;
        
        if( bx > 705 && bx < 710 && by > iay && by < iay + 100){
            velX *= -1;
        }
        
        if(bx < 60 && bx > 50 && by > py && by < py + 100){
            velX *= -1;            
        }
        
        if(by > 500 ||  by <= 40){
            velY *= -1;
        }
        
        if(bx < -40 && vidaPl > 0){
            bx = 300;
            velX = 2;
            sleep(1000);
            vidaPl--;
        } else if(vidaPl == 0){
                JOptionPane.showMessageDialog(null, "Hyuga Ganhou!!");
                System.exit(0);
            }
        
        if(bx > 800 && vidaAd > 0){
            bx = 300;
            velX = 2;
            sleep(1000);
            vidaAd--;
        }else if(vidaAd == 0){
                JOptionPane.showMessageDialog(null, "Tsubasa Ganhou!!");
                System.exit(0);
            }
        
        if(dir == 1 && py > 40){
            py -=3;
        }else if (dir == 2 && py < 420){
            py += 3;
        }
        
        if(dir1 == 1 && iay > 40){
            iay -=3;
        }else if (dir1 == 2 && iay < 420){
            iay += 3;
        }
        
        gr.repaint();
    }
    
    class Grafico extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            setBackground(Color.GREEN);
            Graphics2D bola = (Graphics2D) g;
            Graphics2D barra1 = (Graphics2D) g;
            Graphics2D barra2 = (Graphics2D) g;
            Graphics2D barra3 = (Graphics2D) g;
            Graphics2D play = (Graphics2D) g;
            Graphics2D adversario = (Graphics2D) g;
            Graphics2D vida = (Graphics2D) g;
            Graphics2D meio = (Graphics2D) g;
            
            vida.setFont(normal);

            bola.setColor(Color.BLACK);
            bola.fill(new Rectangle2D.Double(bx,by,15,15));
            barra1.setColor(Color.WHITE);
            barra1.fill(new Rectangle2D.Double(40,20,700,20));
            barra2.setColor(Color.WHITE);
            barra2.fill(new Rectangle2D.Double(40,520,700,20));
            barra3.setColor(Color.WHITE);
            barra3.fill(new Rectangle2D.Double(400,20,5,500));
            play.setColor(Color.RED);
            play.fill(new Rectangle2D.Double(40,py,20,100));
            adversario.setColor(Color.BLUE);
            adversario.fill(new Rectangle2D.Double(720,iay,20,100));
            vida.setColor(Color.red);           
            meio.setColor(Color.WHITE);
            meio.fill(new Rectangle2D.Double(395,275,15,15));
            
            vida.drawString("Tsubasa: " + vidaPl + "                      " + "Hyuga: " + vidaAd,100,500);
        }
    }
    
    public void tela(){
        
        setTitle("Ping Pong 1.0");
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        gr = new Grafico();
        add(gr);
        time = new Timer(2,this);
        time.start();
    }
    
    public void  controle(){
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
               if(e.getKeyCode() == KeyEvent.VK_UP){
                   dir1 = 1;
               } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                   dir1 = 2;
               }
               
               if(e.getKeyCode() == KeyEvent.VK_SPACE){
                   Restart = true;
               }
               if(e.getKeyCode() == KeyEvent.VK_W){
                   dir = 1;
               } else if(e.getKeyCode() == KeyEvent.VK_S){
                   dir = 2;
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP){
                   dir1 = 0;
               } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                   dir1 = 0;
               }
               
               if(e.getKeyCode() == KeyEvent.VK_SPACE){
                   Restart = false;
               }
               if(e.getKeyCode() == KeyEvent.VK_W){
                   dir = 0;
               } else if(e.getKeyCode() == KeyEvent.VK_S){
                   dir = 0;
               }
            }
            
        });
    }

    public static void main(String[] args) {
        
        PingPongBaiano jogo = new PingPongBaiano();
        jogo.tela();
        jogo.controle();
    }
    
}
