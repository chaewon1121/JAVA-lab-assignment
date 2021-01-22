import java.util.Random;
import java.util.Scanner;

public class Hotel {
    public static void main(String args[]){
        Room[] rooms;
        rooms = new Room[10];
        for(int i=0;i<10;i++){
            rooms[i]=new Room();
        }
        System.out.println("Each Room's Capacity");
        for(int i=0;i<10;i++){
            System.out.print(rooms[i].capacity+" ");
        }
        System.out.println();
        while(true){
            System.out.println("Enter number (1 : check in), (2 : check out), (3 : Finish)");
            Scanner scn = new Scanner(System.in);
            int pick = scn.nextInt();
            if(pick == 3){
                break;
            }
            if(pick == 1){
                System.out.println("Enter room number");
                int n = scn.nextInt();
                System.out.println("How many people?");
                int j = scn.nextInt();
                checkin(n,j,rooms);
            }
            else if(pick == 2){
                System.out.println("Enter room number");
                int n = scn.nextInt();
                checkout(n,rooms);
            }
        }
    }
    static void checkin(int n, int j, Room[] r){
        if(r[n-1].states){
            System.out.println("Already check in!");
        }
        else if(r[n-1].capacity<j){
            System.out.println("Too many people!");
        }
        else{
            r[n-1].states=true;
            System.out.println("Check in finish");
        }
    }
    static void checkout(int n, Room[] r){
        if(!r[n-1].states){
            System.out.println("Already check out!");
        }
        else{
            r[n-1].states=false;
            System.out.println("Check out finish");
        }
    }
}
class Room{
    boolean states;
    int capacity;
    Room(){
        Random random = new Random();
        states=false;
        capacity=random.nextInt(4)+1;
    }
}