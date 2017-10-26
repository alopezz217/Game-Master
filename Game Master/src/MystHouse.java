import java.util.ArrayList;
/**
 * CECS 277 Sec01
 * Project 4 
 * Mystery House Object
 * 
 * @author AnthonyLopez
 * e-mail: Anthony.Lopez@student.csulb.edu
 *
 */
public class MystHouse extends ARepl {
	int m_mark_count = 0;
	int m_bite_count = 2;
	int m_choice;
	Room lastRoom;
	Player m_playerSlot = new Player();
	
	Zombie Huey = new Zombie();
	Zombie Dooey = new Zombie();
	Zombie Louie = new Zombie();
	
	Room m_drawing = new Room();
	Room m_outside = new Room();
	
	ArrayList <Room> m_rooms = new ArrayList <Room> ();
	
	FirstAidKit m_kit = new FirstAidKit();
	
	boolean runGame = true;

	@Override
	public void setup() {
		// TODO Auto-generated method stub	
		
		m_drawing.name = "Drawing Room";
		m_outside.name = "Outside";
		m_rooms.add(0, null);
		
		//fills array with rooms//
		int randomNum = (int) (Math.random()*12)+10;
		for (int i = 1; i < randomNum; i++ ){
			Room newRoom = new Room();
			newRoom.name = "Room "+i;
			newRoom.m_mark = false;
			m_rooms.add(i, newRoom);
			//System.out.println(m_rooms.get(i).name);
		}
		
		//sets room 1 to outside//
		m_rooms.set(1, m_outside);
		m_rooms.get(1).m_mark = true;
		m_mark_count++;
		
		//marks room and sets drawing room//
		while(m_mark_count <= m_rooms.size()-2){
			int randomTarget = (int) (Math.random()*(m_rooms.size()-2))+2;
			
			if(m_rooms.get(randomTarget).m_mark != true && m_mark_count != m_rooms.size()-2 ){

				m_rooms.get(randomTarget).m_mark = true;
				m_mark_count++;
				
				//initializes kids to be equal to room 0 which is NULL//
				for(int i = 0; i < 7; i++){
					m_rooms.get(randomTarget).m_kids.add(m_rooms.get(0));
				}
				
			}
			else if(m_mark_count == m_rooms.size()-2 && m_rooms.get(randomTarget).m_mark != true){
				m_rooms.set(randomTarget,m_drawing);
				m_mark_count++;
				//System.out.println("Drawing Room is room:"+randomTarget);
				for(int i = 0; i < 7; i++){
					m_rooms.get(randomTarget).m_kids.add(m_rooms.get(0));
				}
				//set player location to outside room
				m_playerSlot.location = m_rooms.get(randomTarget);
			}
		}
		
		//puts random rooms in the kids of each room//
		for(int i = 2; i < m_rooms.size();i++){
			int count = 0;
			while(count<7){
				int randomKid = (int) (Math.random()*(5))+1;
				int randomRoom = (int) (Math.random()*(m_rooms.size()-2))+2;
				m_rooms.get(i).m_kids.set(randomKid,m_rooms.get(randomRoom));
				count++;
			}
		}
		
		//puts outside room in a random room exit//
		int randomRoom1Kid = (int) (Math.random()*(5))+1;
		int randomRoom1 = (int) (Math.random()*(m_rooms.size()-2))+2;
		m_rooms.get(randomRoom1).m_kids.set(randomRoom1Kid,m_rooms.get(1));
		//System.out.println("Room 1 is inside:"+m_rooms.get(randomRoom1).name);
		
		//puts medKit in random room//
		int randomMedKitKid = (int) (Math.random()*(5))+1;
		int randomMedKitRoom = (int) (Math.random()*(m_rooms.size()-2))+2;
		m_kit.location = m_rooms.get(randomMedKitRoom);
		//System.out.println("The med kit is in:"+m_kit.location.name);
		
		//puts zombies in a randomRoom//
		int zombieCount = 0;
		int firstPreviousRoom = 0;
		int secondPreviousRoom = 0;
		while(zombieCount < 3){
			int randomZombieRoom = (int) (Math.random()*(m_rooms.size()-2))+2;
			
			if(firstPreviousRoom != randomZombieRoom && secondPreviousRoom != randomZombieRoom 
					&& m_rooms.get(randomZombieRoom) != m_drawing){
				if(zombieCount == 0){
					Huey.location = m_rooms.get(randomZombieRoom);
					//System.out.println("Huey is in:"+Huey.location.name);
					firstPreviousRoom = randomZombieRoom;
					zombieCount++;
				}
				else if(zombieCount == 1){
					Dooey.location = m_rooms.get(randomZombieRoom);
					//System.out.println("Dooey is in:"+Dooey.location.name);
					secondPreviousRoom = randomZombieRoom;
					zombieCount++;
				}
				else if(zombieCount == 2){
					Louie.location = m_rooms.get(randomZombieRoom);
					//System.out.println("Louie is in:"+Louie.location.name);
					zombieCount++;
				}
			}
		}
		
	}

	@Override
	public void hello() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println( "|| Welcome to the Mystery House! ||" );
		System.out.println("You have woken up from an unusual nap. You're in a strange room.");
		System.out.println();
		
		
	}

	@Override
	public void listen() {
		// TODO Auto-generated method stub
		System.out.println("Current location: "+ (m_playerSlot.location).name);
		System.out.println("Current Health: "+ m_bite_count);
		System.out.println();
		
		//checks if zombies are in rooms kids//
		int checkZombieRoom = 0;
		while (checkZombieRoom < 1){
			int zombieLocated = 0;
			for(int i = 0; i < (m_playerSlot.location).m_kids.size();i++){
				if(Huey.location == (m_playerSlot.location).m_kids.get(i) 
						|| Dooey.location == (m_playerSlot.location).m_kids.get(i) 
						|| Louie.location == (m_playerSlot.location).m_kids.get(i)){
					zombieLocated++;
				}
			}
			if(zombieLocated > 0 && zombieLocated < 2){
				System.out.println("You hear rustling in a nearby room.");
				System.out.println();
				checkZombieRoom++;
			}
			else if(zombieLocated >= 2){
				System.out.println("You hear strange things coming from multiple rooms nearby.");
				System.out.println();
				checkZombieRoom++;
			}
			checkZombieRoom++;
		}
		
		//shows user exit directions//
		System.out.println("There are 6 exits, please choose one (1-6):");
		System.out.println("1. North");
		System.out.println("2. South");
		System.out.println("3. East");
		System.out.println("4. West");
		System.out.println("5. Up");
		System.out.println("6. Down");
		
		m_choice = CheckInput.checkInt( 1,6 );
		
	}

	@Override
	public void respond() {
		// TODO Auto-generated method stub
		
		if(m_playerSlot.location.m_kids.get(m_choice) == null){
			System.out.println("The door seems to be locked");
			System.out.println();
		}
		else if(m_playerSlot.location.m_kids.get(m_choice).name.equalsIgnoreCase("Outside")) {
			System.out.println("CONGRATS! You have made it ouside and escaped the Mystery House!");
			System.out.println();
			m_choice = 7;
		}
		else{
			m_playerSlot.location = m_playerSlot.location.m_kids.get(m_choice);
			System.out.println();
			
			//checks to see if player finds med kit//
			if(m_kit.location == m_playerSlot.location && m_kit.usage == 0){
				System.out.println("PHEW! You found a Med Kit! Health +2.");
				System.out.println();
				m_bite_count += 2;
				m_kit.usage++;
			}
			
			if(Huey.location == m_playerSlot.location){
				System.out.println("ZOMBIE ALERT!");
				System.out.println(". . .");
				System.out.println();
				int hueyBite = Huey.zombieBiteRoutine();
				if(hueyBite == 1){
					System.out.println("BITE! You were bitten by Huey the Zombie! You lose 1 health");
					System.out.println();
					m_bite_count--;
				}
				else{
					System.out.println("You fought off Huey the Zombie! Huey ran away");
					System.out.println();
				}
			}
			else if(Dooey.location == m_playerSlot.location){
				System.out.println("ZOMBIE ALERT!");
				System.out.println(". . .");
				System.out.println();
				int dooeyBite = Huey.zombieBiteRoutine();
				if(dooeyBite == 1){
					System.out.println("BITE! You were bitten by Dooey the Zombie! You lose 1 health");
					System.out.println();
					m_bite_count--;
				}
				else{
					System.out.println("You fought off Dooey the Zombie! Dooey ran away");
					System.out.println();
				}
			}
			else if(Louie.location == m_playerSlot.location){
				System.out.println("ZOMBIE ALERT!");
				System.out.println(". . .");
				System.out.println();
				int louieBite = Huey.zombieBiteRoutine();
				if(louieBite == 1){
					System.out.println("BITE! You were bitten by Louie the Zombie! You lose 1 health");
					System.out.println();
					m_bite_count--;
				}
				else{
					System.out.println("You fought off Louie the Zombie! Louie ran away");
					System.out.println();
				}
			}
			
			if(m_bite_count == 0){
				System.out.println("THE ZOMBIES GOT YOU! GAME OVER.");
				System.out.println();
				m_choice = 7;
			}
			
			//moves zombies//
			int zombieCount = 0;
			int firstPreviousRoom = 0;
			int secondPreviousRoom = 0;
			while(zombieCount < 3){
				int randomZombieRoom = (int) (Math.random()*(m_rooms.size()-2))+2;
				
				if(firstPreviousRoom != randomZombieRoom && secondPreviousRoom != randomZombieRoom 
						&& m_rooms.get(randomZombieRoom) != m_drawing){
					if(zombieCount == 0){
						Huey.location = m_rooms.get(randomZombieRoom);
						//System.out.println("Huey is in:"+Huey.location.name);
						firstPreviousRoom = randomZombieRoom;
						zombieCount++;
					}
					else if(zombieCount == 1){
						Dooey.location = m_rooms.get(randomZombieRoom);
						//System.out.println("Dooey is in:"+Dooey.location.name);
						secondPreviousRoom = randomZombieRoom;
						zombieCount++;
					}
					else if(zombieCount == 2){
						Louie.location = m_rooms.get(randomZombieRoom);
						//System.out.println("Louie is in:"+Louie.location.name);
						zombieCount++;
					}
				}
			}
		}
		
	}

	@Override
	public boolean endChk() {
		// TODO Auto-generated method stub
		if(m_choice == 7 ){
			runGame = false;
		}
		return runGame;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		System.out.println( "-Thanks for entering the Mystery House-");
		System.out.println();
	}

}
