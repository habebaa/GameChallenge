/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch8threads;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author ASUS
 */
public class MovingBalls extends JPanel implements Runnable{

    public ArrayList<Ball>Balls=new ArrayList<Ball>(5);
    public Tank BlueTank=new Tank("c:\\Users\\LENOVO\\Downloads\\Ch8ThreadsTankGame\\TankBlueS.jpg");
    public Tank Tank2=new Tank("c:\\Users\\LENOVO\\Downloads\\Ch8ThreadsTankGame\\TankBlueS - Copy.jpg");
    public MovingBalls()
    {
        setSize(600,600);
        setBackground(Color.red);
        BlueTank.pos.x=150;
        BlueTank.pos.y=340;
        Tank2.pos.x=450;
        Tank2.pos.y=340;
        this.addKeyListener(new keylist());
        
    }
    private class keylist implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
           
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("Test");
            if (e.getKeyCode()==KeyEvent.VK_RIGHT)
            {
                
                BlueTank.mover();
                
            }
            if (e.getKeyCode()==KeyEvent.VK_LEFT)
            {
                
                BlueTank.movel();
                //repaint();
            }
            if (e.getKeyCode()==KeyEvent.VK_SPACE)
            {
                
                BlueTank.fireBullet();
            }
            if (e.getKeyCode()==KeyEvent.VK_D)
            {
                
                Tank2.mover();
                
            }
            if (e.getKeyCode()==KeyEvent.VK_A)
            {
                
               Tank2.movel();
                //repaint();
            }
            if (e.getKeyCode()==KeyEvent.VK_SHIFT)
            {
                
                Tank2.fireBullet();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
           
        }
    }
    public void  paintComponent(Graphics g)
    {
        g.clearRect(0, 0, 600, 600);
        
        try
        {
        BufferedImage imgtank = ImageIO.read(new File(BlueTank.ImagePath));
        BufferedImage imgtank2 = ImageIO.read(new File(Tank2.ImagePath));
        BufferedImage imgrocket = ImageIO.read(new File(BlueTank.Rocket.imgPath));
         BufferedImage imgrocket2 = ImageIO.read(new File(Tank2.Rocket2.imgPath));
        g.drawImage(imgtank, BlueTank.pos.x, BlueTank.pos.y,null);
        g.drawImage(imgtank2,Tank2.pos.x, Tank2.pos.y,null);
        g.drawImage(imgrocket, BlueTank.Rocket.pos.x, BlueTank.Rocket.pos.y,null);
        g.drawImage(imgrocket2, Tank2.Rocket2.pos.x, Tank2.Rocket2.pos.y,null);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        
        
        for (Ball OneBall: Balls)
        
        {
            g.setColor(OneBall.CurrentColor);
            g.fillOval(OneBall.x,OneBall.y, 50, 50);
            if (BlueTank.Rocket.pos.distance(OneBall.x, OneBall.y)<=50)
            {
                //System.out.println("Hit Occurs" + OneBall.CurrentColor.toString());
                Balls.remove(OneBall);
                break;
                
            }
             if (Tank2.Rocket2.pos.distance(OneBall.x, OneBall.y)<=50)
            {
                //System.out.println("Hit Occurs" + OneBall.CurrentColor.toString());
                Balls.remove(OneBall);
                break;
                
            }
            
        }
        
    }
    @Override
    public void run() {
    try
    {
    while(true)
    {
        for (Ball OneBall: Balls)
        {
            OneBall.move(this.getWidth());
        }    
        
        //y+=10;
        Thread.sleep(50);
        repaint();
    }
    }
    catch (InterruptedException e)
    {}
    }
    
}
