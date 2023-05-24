import java.io.*;
import javax.sound.sampled.*;

public class Driver
{
    public static void main(String[] args) 
    {
        GameArena g = new GameArena(1200,900);
        
        //arena
        Rectangle rblue = new Rectangle(68, 230, 1064, 552, "BLUE");
        Rectangle rwhite = new Rectangle(88, 250, 1024, 512, "WHITE");
        
        //goals
        Rectangle rgrey = new Rectangle(88, 378, 10, 256, "GREY");
        Rectangle rgrey2 = new Rectangle(1102, 378, 10, 256, "GREY");
        g.addRectangle(rblue);
        g.addRectangle(rwhite);
        g.addRectangle(rgrey);
        g.addRectangle(rgrey2);

        Line mid = new Line( 600, 250, 600, 762,1, "BLUE");
        g.addLine(mid);

        //blue center arena
        Ball center = new Ball( 600, 506, 102, "BLUE");
        g.addBall(center);
        
        //white center arena
        Ball bwhite = new Ball( 600, 506, 100, "WHITE");
        g.addBall(bwhite);

        Ball mallet1 = new Ball( 259, 506, 75, "BLUE");
        g.addBall(mallet1);

        Ball mallet2 = new Ball( 941, 506, 75, "BLUE");
        g.addBall(mallet2);

        //puck
        //Ball puck = new Ball( 800, 506, 25, "BLACK");
        Ball puck = new Ball( 600, 506, 25, "BLACK");
        g.addBall(puck);

        Text welcome = new Text( "Welcome to Air Hockey!", 20, 88, 150, "WHITE", 1);
        g.addText(welcome);

        //left score
        Text lScore = new Text( "0", 50, 14, 506, "WHITE", 1);
        g.addText(lScore);

        //rightscore
        Text rScore = new Text( "0", 50, 1156, 506, "WHITE", 1);
        g.addText(rScore);
    
        //sound files
        Sound applause = new Sound("applause.wav");
        Sound bounce = new Sound("bounce.wav");
        Sound drumroll = new Sound("drumroll.wav");
        Sound fanfare = new Sound("fanfare.wav");
        Sound hit = new Sound("hit.wav");


        //initial speed of puck
        double xSpeed = 0;
        double ySpeed = 0;
        
        //initial score for both
        int lCountScore = 0;
        int rCountScore = 0;
        
        //to make the loop stop, change starter to 1
        //to make the loop continue, change starter back to 0 
        int starter = 0;

        //play sound at the start (bug fix)
        int fanfaresound = 0;
        

        //toggle sound on or off
        boolean SoundOn = true; 

        //always loop
        while (true)
        {
            
            //start the loop
            while (starter == 0) 
            {
                if (g.letterPressed('m')) 
                {
                    //toggle sound on
                    SoundOn = false; 
                } 
                if (g.letterPressed('n')) 
                {
                    //toggle sound off
                    SoundOn = true; 
                } 
                
                //play sound in the beginning
                if (fanfaresound <= 2)
                {
                    fanfare.playSound();
                    fanfaresound++;

                }
            
                
                //initialize mallet speed
                int malletSpeed = 7;
                

                //moving for right mallet(mallet2)                    

                if (g.leftPressed()) 
                {
                    //move left
                    mallet2.move(-malletSpeed, 0);
                    //left border for mallet
                    if (mallet2.getXPosition() <= 637)
                    {
                        mallet2.setXPosition(637);
                    }
    
                } else if (g.rightPressed()) 
                {
                    //move right
                    mallet2.move(malletSpeed, 0);
                    //right border for mallet
                    if (mallet2.getXPosition() >= 1075)
                    {
                        mallet2.setXPosition(1075);
                    }
                }
                if (g.upPressed()) 
                {
                    //move up
                    mallet2.move(0, -malletSpeed);
                    //up border for mallet
                    if (mallet2.getYPosition() <= 288)
                    {
                        mallet2.setYPosition(288);
                    }
                } 
                else if (g.downPressed()) 
                {
                    //move down
                    mallet2.move(0, malletSpeed);
                    //down border for mallet
                    if (mallet2.getYPosition() >= 725)
                    {
                        mallet2.setYPosition(725);
                    }
                }
            
                //moving for left mallet(mallet1)
                if (g.letterPressed('a')) 
                {
                    //move left
                    mallet1.move(-malletSpeed, 0);
                    //left border for mallet
                    if (mallet1.getXPosition() <= 125)
                    {
                        mallet1.setXPosition(125);
                    }
                } 
                else if (g.letterPressed('d')) 
                {
                    //move right
                    mallet1.move(malletSpeed, 0);
                    //right border for mallet
                    if (mallet1.getXPosition() >= 563)
                    {
                        mallet1.setXPosition(563);
                    }
                }
                if (g.letterPressed('w')) 
                {
                    //move up
                    mallet1.move(0, -malletSpeed);
                    //up border for mallet
                    if (mallet1.getYPosition() <= 288)
                    {
                        mallet1.setYPosition(288);
                    }
                } 
                else if (g.letterPressed('s')) 
                {
                    //move down
                    mallet1.move(0, malletSpeed);
                    //down border for mallet
                    if (mallet1.getYPosition() >= 725)
                    {
                        mallet1.setYPosition(725);
                    }
                }
                
                //cheat code
                //r to increase mallet1 size and t to decrease
                if (g.letterPressed('r') && (mallet1.getSize() <= 250))
                {
                    mallet1.setSize(mallet1.getSize() + 3);
                }
                if (g.letterPressed('t') && (mallet1.getSize() >= 50))
                {
                    mallet1.setSize(mallet1.getSize() - 3);
                }

                //o to increase mallet2 size and p to decrease
                if (g.letterPressed('o') && (mallet2.getSize() <= 250))
                {
                    mallet2.setSize(mallet2.getSize() + 3);
                }
                if (g.letterPressed('p') && (mallet2.getSize() >= 50))
                {
                    mallet2.setSize(mallet2.getSize() - 3);
                }
                
                //enter to reset mallet size
                if (g.enterPressed())
                {
                    mallet1.setSize(75);
                    mallet2.setSize(75);
                }

    
                
                //check if puck hits the left walls
                if (puck.getXPosition() <= 100 ) 
                {
                    //puck left border
                    puck.setXPosition(100);
                    if (SoundOn)
                    {
                        hit.playSound();
                    }
                    //bounces
                    xSpeed *= -1;

                }
                //check if puck hits the right walls
                if (puck.getXPosition() >= 1100)
                {
                    //puck right border
                    puck.setXPosition(1100);
                    if (SoundOn)
                    {
                        hit.playSound();
                    }
                    //puck bounces
                    xSpeed *= -1;
                }
                //check if puck hits the upward walls
                if (puck.getYPosition() <= 262) 
                {   
                    //puck up border
                    puck.setYPosition(262);
                    if (SoundOn)
                    {
                        hit.playSound();
                    }
                    //bounces
                    ySpeed *= -1;

                }
                //check if puck hits the downward walls
                if (puck.getYPosition() >= 750)
                {
                    //puck down border
                    puck.setYPosition(750);
                    if (SoundOn)
                    {
                        hit.playSound();
                    }
                    //bounces
                    ySpeed *= -1;
                }
                
                // check if theres collision (with mallet2)
                if (puck.collides(mallet2)) 
                {
                    if (SoundOn)
                    {
                        bounce.playSound();
                    }
                    
                    //return array from ball class (deflect method)
                    double[] puckSpeed = puck.deflect(puck, mallet2);
                    //new speed for puck
                    xSpeed = puckSpeed[0];
                    ySpeed = puckSpeed[1];
                }
                
                // check if theres collision (with mallet1)
                if (puck.collides(mallet1)) 
                {
                    if (SoundOn)
                    {
                        bounce.playSound();
                    }
                    //set new puck speed according to calculations from deflect method (ball class)
                    double[] puckSpeed = puck.deflect(puck, mallet1);
                    xSpeed = puckSpeed[0];
                    ySpeed = puckSpeed[1];
                }
                //change puck speed due to friction
                xSpeed *= 0.992; 
                ySpeed *= 0.992; 
                puck.move(xSpeed, ySpeed);
    
    
    
                //check if puck hits the left goal
                if ((puck.getXPosition() <= 100) && ((puck.getYPosition() >= 378) && (puck.getYPosition() <= 634)))
                {
                    //change welcome text to winning the round
                    welcome.setColour("GREEN");
                    welcome.setText("Player 2 wins the round!");
                    rCountScore += 1;
                    //set initial position for puck and mallet
                    puck.setXPosition(549);
                    puck.setYPosition(506);
                    //initialize puck speed to 0
                    xSpeed = 0;
                    ySpeed = 0;
                    mallet1.setXPosition(259);
                    mallet1.setYPosition(506);
                    mallet2.setXPosition(941);
                    mallet2.setYPosition(506);
                    
                    //play applause sound if theres a goal
                    if (rCountScore == 1)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        
                        rScore.setText("1");
                    
                    }
    
                    else if (rCountScore == 2)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        rScore.setText("2");
                    }
    
                    else if (rCountScore == 3)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        rScore.setText("3");
                    }
    
                    else if (rCountScore == 4)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        rScore.setText("4");
                    }
    
                    else if (rCountScore == 5)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        rScore.setText("5");
                    }
    
                    else if (rCountScore == 6)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        rScore.setText("6");
                    }
                    
                    //if mallet1 wins
                    else if (rCountScore == 7)
                    {
                        if (SoundOn)
                        {
                            drumroll.playSound();  
                        }
                        //set initial position for puck and mallet
                        rScore.setText("7");
                        welcome.setColour("GREEN");
                        welcome.setText("Player 2 wins with 7 points! Press Space to start a new game");
                        puck.setXPosition(600);
                        puck.setYPosition(506);
                        //change starter to 1 to stop the loop
                        starter = 1;
                        
                    }
                    
                }
                
                
                //check if puck hits the right goal
                if ((puck.getXPosition() >= 1100) && ((puck.getYPosition() >= 378) && (puck.getYPosition() <= 634)))
                {
                    //change welcome text to winning the round
                    welcome.setColour("GREEN");
                    welcome.setText("Player 1 wins the round!");
                    lCountScore += 1;
                    //set puck and mallet position to their initial position
                    puck.setXPosition(651);
                    puck.setYPosition(506);
                    //set puck speed to 0
                    xSpeed = 0;
                    ySpeed = 0;
                    mallet1.setXPosition(259);
                    mallet1.setYPosition(506);
                    mallet2.setXPosition(941);
                    mallet2.setYPosition(506);
    
                    
                    if (lCountScore == 1)
                    {
                        
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        lScore.setText("1");
                    }
    
                    else if (lCountScore == 2)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        lScore.setText("2");
    
                    }
    
                    else if (lCountScore == 3)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        lScore.setText("3");
                    }
    
                    else if (lCountScore == 4)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        lScore.setText("4");
                    }
    
                    else if (lCountScore == 5)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        lScore.setText("5");
                    }
    
                    else if (lCountScore == 6)
                    {
                        if (SoundOn)
                        {
                            applause.playSound();
                        }
                        lScore.setText("6");
                    }
    
                    else if (lCountScore == 7)
                    {
                        if (SoundOn)
                        {
                            drumroll.playSound(); 
                        }
                        
                        lScore.setText("7");
                        welcome.setColour("GREEN");
                        welcome.setText("Player 1 wins with 7 points! Press Space to start a new game");
                        puck.setXPosition(600);
                        puck.setYPosition(506);
                        starter = 1;
    
                    }
                    
                }
                
    
                
            //animate everything moving
            g.pause();
            }
            
            //game is paused until g is pressed
            while (!g.spacePressed()) 
            {
                g.pause();
            }
            //reset everything to start
            lCountScore = 0;
            rCountScore = 0;
            mallet1.setSize(75);
            mallet2.setSize(75);
            lScore.setText("0");
            rScore.setText("0");
            welcome.setText("Welcome to Air Hockey!");
            starter = 0;
            if (SoundOn)
            {
                fanfare.playSound();
            }
         
            
        }

    }
    

}