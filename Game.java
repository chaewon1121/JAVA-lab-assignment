import java.util.Random;
import java.util.Scanner;


abstract class Object {
    int x, y;
    public Object(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public boolean overlap(Object o)
    {
        if(this.x == o.x && this.y == o.y)
            return true;
        else
            return false;
    }
    abstract void move();
    abstract char shape();
}

class Packman extends Object{
    public Packman(int x, int y)
    {
        super(x, y);
    }
    public void move()
    {

    }
    public char shape()
    {
        return 'P';
    }
}

class Ghost extends Object{
    public Ghost(int x, int y)
    {
        super(x,y);
    }
    public void move()
    {
    }
    public void move(Packman P)
    {
        Random random = new Random();
        int i = random.nextInt(100) + 1;
        if(i <= 20)
        {
            System.out.println("Ghost does not move");
        }
        else if(20<i && i <= 60)
        {
            if(20<i && i<= 50)
            {
                System.out.println("Ghost moves vertically 1 grid");
                this.x -= 1;
            }
                
            else
            {
                System.out.println("Ghost moves vertically 2 grids");
                this.x -= 2;
            }

        }
        else
        {
            if(60< i && i<=90)
            {
                System.out.println("Ghost moves horizontally 1 grid");
                this.y +=1;
            }

            else
            {
                System.out.println("Ghost moves horizontally 2 grids");
                this.y +=2;
            }
        }
    }
    public char shape()
    {
        return 'G';
    }
}
class Star extends Object{
    public Star(int x, int y)
    {
        super(x,y);
    }
    public void move()
    {
    }
    public char shape()
    {
        return 'S';
    }
}

public class Game{
    public static void main(String[] args) {
        Packman P = new Packman(1,4);
        Ghost G = new Ghost(3,3);
        Star S = new Star(1,3);
        System.out.println("Game Start");
        while(true) {
                    char [][] arr = new char[5][5];
                    for (int i = 0; i<5; i++)
                    {
                        for (int j = 0; j<5; j++)
                        {
                            arr[i][j] = '.';
                        }
                    }
                    arr[P.x][P.y] = P.shape();
                    arr[S.x][S.y] = S.shape();
                    arr[G.x][G.y] = G.shape();
                    for (int i = 0; i<5; i++)
                    {
                        for(int j = 0; j<5; j++)
                        {
                            System.out.print(arr[i][j]+ " ");
                        }
                        System.out.println();
                    }
                    // Write Game Map Print Code Using shape method
                    System.out.print("Please Input direction : ");
                    Scanner scn = new Scanner(System.in);
                    String command = scn.next();
                    switch(command){
                        case "a":
                            P.y -= 1;
                            break;
                        case "s":
                            P.x += 1;
                            break;
                        case "w":
                            P.x -= 1;
                            break;
                        case "d":
                            P.y += 1;
                            break;
                    }
                    P.move();
                    G.move(P);
                    // If Packman collides with Ghost and Star
                    if(P.x == S.x && P.x == G.x && P.y == S.y && P.y == G.y)
                    {
                        System.out.println("colides with Ghost and Star and Packman Wins!");
                        break;
                    }
                    // If Packman collides with Ghost
                    if(P.x == G.x && P.y == G.y)
                    {
                        System.out.println("You lose!");
                        break;
                    }
                    // If Packman collides with Star
                    if(P.x == S.x && P.y == S.y)
                    {
                        System.out.println("Packamn Wins!");
                        break;
                    }
        }
    }
}