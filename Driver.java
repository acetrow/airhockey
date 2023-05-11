import java.util.Random;

public class Driver 
{
    public static void main(String[] args) 
    {
        GameArena g = new GameArena(1200,900);
        
        Rectangle rblue = new Rectangle(68, 230, 1064, 552, "BLUE");
        Rectangle rwhite = new Rectangle(88, 250, 1024, 512, "WHITE");
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

        Text score1 = new Text( "0", 50, 14, 506, "WHITE", 1);
        g.addText(score1);

        Text score2 = new Text( "0", 50, 1156, 506, "WHITE", 1);
        Text score3 = new Text( "3", 80, 1156, 506, "WHITE", 1);
        g.addText(score2);

        //initial speed of puck
        double xSpeed = 3;
        double ySpeed = 3;
        
        //moving for right mallet(mulet2)
        while (true) {
            if (g.leftPressed()) {
                mallet2.move(-5, 0);
                if (mallet2.getXPosition() <= 637)
                {
                    mallet2.setXPosition(637);
                }

            } else if (g.rightPressed()) {
                mallet2.move(5, 0);
                if (mallet2.getXPosition() >= 1075)
                {
                    mallet2.setXPosition(1075);
                }
            }
            if (g.upPressed()) {
                mallet2.move(0, -5);
                if (mallet2.getYPosition() <= 288)
                {
                    mallet2.setYPosition(288);
                }
            } else if (g.downPressed()) {
                mallet2.move(0, 5);
                if (mallet2.getYPosition() >= 725)
                {
                    mallet2.setYPosition(725);
                }
            }
        
            //moving for left mallet(mallet1)
            if (g.letterPressed('a')) {
                mallet1.move(-5, 0);
                if (mallet1.getXPosition() <= 125)
                {
                    mallet1.setXPosition(125);
                }
            } else if (g.letterPressed('d')) {
                mallet1.move(5, 0);
                if (mallet1.getXPosition() >= 563)
                {
                    mallet1.setXPosition(563);
                }
            }
            if (g.letterPressed('w')) {
                mallet1.move(0, -5);
                if (mallet1.getYPosition() <= 288)
                {
                    mallet1.setYPosition(288);
                }
            } else if (g.letterPressed('s')) {
                mallet1.move(0, 5);
                if (mallet1.getYPosition() >= 725)
                {
                    mallet1.setYPosition(725);
                }
            }


            
            //check if puck hits the walls
            if (puck.getXPosition() < 100 || puck.getXPosition() > 1100) {
                xSpeed *= -1;
            }
            if (puck.getYPosition() < 262 || puck.getYPosition() > 750) {
                ySpeed *= -1;
            }

            if (puck.collides(mallet2)) {
                double[] puckSpeed = puck.deflect(puck, mallet2);
                xSpeed = puckSpeed[0];
                ySpeed = puckSpeed[1];
            }
            
            if (puck.collides(mallet1)) {
                double[] puckSpeed = puck.deflect(puck, mallet1);
                xSpeed = puckSpeed[0];
                ySpeed = puckSpeed[1];
            }
            puck.move(xSpeed, ySpeed);

        
            
            g.pause();
        }
        
        
    }

}