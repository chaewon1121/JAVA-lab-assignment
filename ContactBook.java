import java.util.HashMap;
import java.util.Scanner;

public class ContactBook {
	public static void main (String[] args) {
		HashMap<String,String> map =
				new HashMap<>();
				
		Scanner scn = new Scanner(System.in);

		String i,t;		
		
		while(true){
			
		
			System.out.print("Please enter a name (Exit:0) :  ");
			i = scn.nextLine();
			
			if(!(i.equals("0"))){
				if (map.containsKey(i)) {
					System.out.print("Find Member, " +i+"'s phone number : ");
					System.out.println(map.get(i));
	
				}
				else {
					System.out.print("New Member, Please enter a number (Cancel:0) : ");
					t = scn.nextLine();
					
					if(!(t.equals("0"))) {
			
					map.put(i, t);
					System.out.println("Save Success.");
					}
					
					else {
						System.out.println("Cancel Success.");
						
					}
			
				}
				
					
			}
			
							
			else 
				{
				System.out.println("There is " + map.size() + " member in Contact Book Currently, Bye!");
				break;
				}
			

		}
	}

}
