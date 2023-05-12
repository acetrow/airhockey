
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
        Ball bw = new Ball( 600, 506, 100, "WHITE");
        g.addBall(bw);

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

        //initial speed of puck
        double xSpeed = 0;
        double ySpeed = 0;
        
        //initial score for both
        int lCountScore = 0;
        int rCountScore = 0;

        //moving for right mallet(mulet2)
        while (true) {
            if (g.leftPressed()) 
            {
                mallet2.move(-5, 0);
                if (mallet2.getXPosition() <= 637)
                {
                    mallet2.setXPosition(637);
                }

            } else if (g.rightPressed()) 
            {
                mallet2.move(5, 0);
                if (mallet2.getXPosition() >= 1075)
                {
                    mallet2.setXPosition(1075);
                }
            }
            if (g.upPressed()) 
            {
                mallet2.move(0, -5);
                if (mallet2.getYPosition() <= 288)
                {
                    mallet2.setYPosition(288);
                }
            } 
            else if (g.downPressed()) 
            {
                mallet2.move(0, 5);
                if (mallet2.getYPosition() >= 725)
                {
                    mallet2.setYPosition(725);
                }
            }
        
            //moving for left mallet(mallet1)
            if (g.letterPressed('a')) 
            {
                mallet1.move(-5, 0);
                if (mallet1.getXPosition() <= 125)
                {
                    mallet1.setXPosition(125);
                }
            } 
            else if (g.letterPressed('d')) 
            {
                mallet1.move(5, 0);
                if (mallet1.getXPosition() >= 563)
                {
                    mallet1.setXPosition(563);
                }
            }
            if (g.letterPressed('w')) 
            {
                mallet1.move(0, -5);
                if (mallet1.getYPosition() <= 288)
                {
                    mallet1.setYPosition(288);
                }
            } 
            else if (g.letterPressed('s')) 
            {
                mallet1.move(0, 5);
                if (mallet1.getYPosition() >= 725)
                {
                    mallet1.setYPosition(725);
                }
            }


            
            //check if puck hits the walls
            if (puck.getXPosition() <= 100 || puck.getXPosition() >= 1100) 
            {
                xSpeed *= -1;
            }
            if (puck.getYPosition() <= 262 || puck.getYPosition() >= 750) 
            {
                ySpeed *= -1;
            }

            if (puck.collides(mallet2)) 
            {
                double[] puckSpeed = puck.deflect(puck, mallet2);
                xSpeed = puckSpeed[0];
                ySpeed = puckSpeed[1];
            }
            
            if (puck.collides(mallet1)) 
            {
                double[] puckSpeed = puck.deflect(puck, mallet1);
                xSpeed = puckSpeed[0];
                ySpeed = puckSpeed[1];
            }
            xSpeed *= 0.992; 
            ySpeed *= 0.992; 
            puck.move(xSpeed, ySpeed);



            //check if puck hits the left goal
            if ((puck.getXPosition() <= 100) && ((puck.getYPosition() >= 378) && (puck.getYPosition() <= 634)))
            {
                welcome.setColour("GREEN");
                welcome.setText("Player 2 wins the round!");
                rCountScore += 1;
                puck.setXPosition(549);
                puck.setYPosition(506);
                xSpeed = 0;
                ySpeed = 0;
                mallet1.setXPosition(259);
                mallet1.setYPosition(506);
                mallet2.setXPosition(941);
                mallet2.setYPosition(506);

                if (rCountScore == 1)
                {
                    rScore.setText("1");
                }

                else if (rCountScore == 2)
                {
                    rScore.setText("2");
                }

                else if (rCountScore == 3)
                {
                    rScore.setText("3");
                }

                else if (rCountScore == 4)
                {
                    rScore.setText("4");
                }

                else if (rCountScore == 5)
                {
                    rScore.setText("5");
                }

                else if (rCountScore == 6)
                {
                    rScore.setText("6");
                }

                else if (rCountScore == 7)
                {
                    rScore.setText("7");
                    welcome.setColour("GREEN");
                    welcome.setText("Player 2 wins with 7 points! Press Space to start a new game");
                    puck.setXPosition(600);
                    puck.setYPosition(506);
                    if (g.spacePressed())
                    {
                        lCountScore = 0;
                        lScore.setText("0");
                        rCountScore = 0;
                        rScore.setText("0");
                    }
    
                }
                
            }

            //check if puck hits the right goal
            if ((puck.getXPosition() >= 1100) && ((puck.getYPosition() >= 378) && (puck.getYPosition() <= 634)))
            {
                welcome.setColour("GREEN");
                welcome.setText("Player 1 wins the round!");
                lCountScore += 1;
                puck.setXPosition(651);
                puck.setYPosition(506);
                xSpeed = 0;
                ySpeed = 0;
                mallet1.setXPosition(259);
                mallet1.setYPosition(506);
                mallet2.setXPosition(941);
                mallet2.setYPosition(506);

                
                if (lCountScore == 1)
                {
                    lScore.setText("1");
                }

                else if (lCountScore == 2)
                {
                    lScore.setText("2");
                }

                else if (lCountScore == 3)
                {
                    lScore.setText("3");
                }

                else if (lCountScore == 4)
                {
                    lScore.setText("4");
                }

                else if (lCountScore == 5)
                {
                    lScore.setText("5");
                }

                else if (lCountScore == 6)
                {
                    lScore.setText("6");
                }

                else if (lCountScore == 7)
                {
                    lScore.setText("7");
                    welcome.setColour("GREEN");
                    welcome.setText("Player 1 wins with 7 points! Press Space to start a new game");
                    puck.setXPosition(600);
                    puck.setYPosition(506);
                    if (g.spacePressed())
                    {
                        lCountScore = 0;
                        lScore.setText("0");
                        rCountScore = 0;
                        rScore.setText("0");
                    }

                }
                
            }
            
            
            g.pause();
        }
        
        
    }

}