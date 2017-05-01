import javax.swing.JOptionPane;

/**
 * This class if composed of most of the user
 * prompts that allows the game to run including the 
 * actual battle simulation and taking turns
 * 
 * @author P. Cavan Gary-O'Toole
 *
 */
public class UserPrompts {
	
	/**
	 * Queries the user if they wish to player against
	 * another person or a computer
	 * @return
	 * 		Integer representation of what they chose
	 * 		0:Computer, 1:Person, 2:Quit
	 */
	public static int BattleOpponentQuery()
	{
		//Initializes all possible values for the list of JOptionPane
		Object[] possibleValues = { "Computer", "Person", "Quit" };
		
		//Produces the JOptionPane and assigns the value to selectedValue
		Object selectedValue = JOptionPane.showInputDialog(null,"Who would you like to play against?", "Game",JOptionPane.PLAIN_MESSAGE, null, possibleValues, possibleValues[0]);
		 
		//decodes the value to an integer
		if(selectedValue.equals("Computer"))//Computer
		{
			return 0;
		}
		else if(selectedValue.equals("Person"))//Person
		{
			return 1;
		}
		else//Quit
		{
			return 2;
		}
	}
	
	/**
	 * Gets the information from the user that is 
	 * required to create a player object in the game
	 * @param allCharacters
	 * 		The character roster from the .cvs file
	 * @return
	 * 		The player object representing the user
	 */
	public static Player getPlayerInfo(CharacterRoster allCharacters)
	{
		//Gets the players name
		String name = JOptionPane.showInputDialog("Hello player. Please enter your name.");
		
		//Beginning of the string printout for the player to choose their character
		String allCharacterPrompt = "1: " + allCharacters.get(0).getName();
		
		/*
		 * Loop that adds every other character in allCharacters 
		 * to the first one that was just initialized.
		 * Done this way to the the "newLine" spacing right
		 */
		for(int index = 1; index < allCharacters.size(); ++index)
		{
			//adds additional characters to the existing list of options if more exist
			allCharacterPrompt = allCharacterPrompt + "\n" + (index + 1) + ": " + allCharacters.get(index).getName();
		}
		
		//Prompts the player to pick a character
		String StringNum = JOptionPane.showInputDialog("Hello " + name + ". Please enter the corresponding number for the character you"
							+ " would like to fight as \n" + allCharacterPrompt);
		
		//Changes the string number to and integer
		int actualNum = Integer.parseInt(StringNum);
		
		//Creates the new player object based off of information produced earlier in the method
		Player player = new Player(name, allCharacters.get(actualNum - 1), false);
		
		return player;//the new player
		
		
	}
	
	/**
	 * Executes the battle simulation alternating turns until one player runs out of health and
	 * is defeated. 
	 * All information is displayed to the user via the console window.
	 * @param player1
	 * 		The first player participating in the battle
	 * @param player2
	 * 		The second player participating in the battle
	 */
	public static void battle(Player player1, Player player2)
	{
		//Basic instruction prompting for the game
		System.out.println("Let the battle begin \n" + "Each player will be granted one action per turn and first one to "
				+ "run out of health loses. \n" + "Good Luck!");
		
		//Displays the player stats before a game
		displayPlayerInfo(player1, player2);
		int turnNumber = 1;//initializes the number of turns passed
		
		//ends the loop once a persons health reaches 0
		while(!(player1.getCharacter().isDead() || player2.getCharacter().isDead()))
		{
			System.out.println("\n\nTurn " + turnNumber);//turn counter
			
			//checks speeds to see which character goes first (higher speed grants first action)
			if(player1.getCharacter().getSpeed() > player2.getCharacter().getSpeed())//player one faster that player two
			{
				//finds the ability they wish to use
				//first player's turn
				Ability player1Choice = takeTurn(player1);
				
				//TODO DONE check to see if exits loop before next players turn
				takeAction(player1, player1Choice, player2);//executes the given ability and effects associated with it
				
				//checks to see if player attacked is out of health points
				if(player2.getCharacter().isDead())
				{
					//Displays winning prompt and final battle information
					System.out.println("\nCongrats " + player1.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);
					break;//exits the loop
				}
				
				//finds the ability they wish to use
				//second player's turn
				Ability player2Choice = takeTurn(player2);
				
				//executes the given ability and effects associated with it
				takeAction(player2, player2Choice, player1);
				
				//checks to see if player attacked is out of health points
				if(player1.getCharacter().isDead())
				{
					//Displays winning prompt and final battle information
					System.out.println("\nCongrats " + player2.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);
					break;//exits the battle loop
				}
				
				
			}
			else//player two faster than player one, or both were same (player one got to pick character first)
			{
				//finds the ability they wish to use
				//second player's turn
				Ability player2Choice = takeTurn(player2);
				
				//executes the given ability and effects associated with it
				takeAction(player2, player2Choice, player1);
				
				//checks to see if player attacked is out of health points
				if(player1.getCharacter().isDead())
				{
					//Displays winning prompt and final battle information
					System.out.println("\nCongrats " + player2.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);
					break;//exits the battle loop
				}
				
				//finds the ability they wish to use
				//first player's turn
				Ability player1Choice = takeTurn(player1);
				
				//executes the given ability and effects associated with it
				takeAction(player1, player1Choice, player2);
				
				//checks to see if player attacked is out of health points
				if(player2.getCharacter().isDead())
				{
					//Displays winning prompt and final battle information
					System.out.println("\nCongrats " + player1.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);
					break;//exits the battle loop
				}
			}
			++turnNumber;//adds one to the turn number
			System.out.println("-----------------------------------------------------\n");//separates turns easier
			
			//displays character information before the next turn
			displayPlayerInfo(player1, player2);
		}
	}
	
	/**
	 * Displays both player's Name, character, health, mana, strength, speed, and defense
	 * allowing for more strategic play between turns
	 * @param player1
	 * 		First player of the game
	 * @param player2
	 * 		Second player of the game
	 */
	public static void displayPlayerInfo(Player player1, Player player2)
	{
		//Displays the stats of each character when called
		String player1Info = player1.getName() + "'s " + player1.getCharacter().getName() + "\n" 
				+ "  Health: " + player1.getCharacter().getHealth() + "\n" + "    Mana: " + player1.getCharacter().getMana() 
				+ "\nStrength: " + player1.getCharacter().getStrength() + "\n" + "   Speed: " + player1.getCharacter().getSpeed() 
				+ "\n Defense: " + player1.getCharacter().getDefense();
		
		String player2Info = player2.getName() + "'s " + player2.getCharacter().getName() + "\n" 
				+ "  Health: " + player2.getCharacter().getHealth() + "\n" + "    Mana: " + player2.getCharacter().getMana() 
				+ "\nStrength: " + player2.getCharacter().getStrength() + "\n" + "   Speed: " + player2.getCharacter().getSpeed() 
				+ "\n Defense: " + player2.getCharacter().getDefense();
		
		System.out.println("\n" + player1Info + "\n \n" + player2Info);
	}
	
	
	/**
	 * Displays only the players Name, character, health and mana.
	 * Mostly used as a simplified version of displayPlayerInfo() specifically
	 * right after and action, before a turn has ended
	 * @param player
	 * 		Playr to display battle info of
	 */
	public static void displayBattleInfo (Player player)
	{
		//The information of the player that is printed out to the console
		String playerInfo = player.getName() + "'s " + player.getCharacter().getName() + "\n" 
				+ "  Health: " + player.getCharacter().getHealth() + "\n" + "    Mana: " + player.getCharacter().getMana();
		
		System.out.println("\n" + playerInfo);
	}
	
	/**
	 * Executes one turn of a single player during the 
	 * battle simulation
	 * @param player
	 * 		The player who's turn it is
	 * @return
	 * 		The ability the player chose on thier turn
	 */
	public static Ability takeTurn (Player player)
	{
		//gets the player's character's ability list
		AbilityList currentAbilities = player.getCharacter().getAbilities();
		
		//Checks to see if player is a computer or user(this changes prompting)
		if(player.getIsComputer() == false)//player
		{
			//Prompts the user that it's their turn and to choose an action
			System.out.println("\nIt is " + player.getName() +  "'s turn");
			System.out.println("Choose an action.");
			
			//Displays all the abilities (and their parameters) of the character so the user may choose the one they want
			String actions = "1: " + currentAbilities.getAbility(0).getName() + "\n" + currentAbilities.getAbility(0).getDescription() 
					+ "\nDamage Done: " + currentAbilities.getAbility(0).getDamageDone() + "\nHeal Amount: " 
					+ currentAbilities.getAbility(0).getHealAmount() + "\nMana Cost: " + currentAbilities.getAbility(0).getManaCost();
					
			//Adds the rest of the abilities to the string list
			for(int index = 1; index < currentAbilities.size(); ++index)
			{
				actions = actions + "\n" + (index + 1)+ ": " + currentAbilities.getAbility(index).getName() + "\n" + currentAbilities.getAbility(index).getDescription() 
						+ "\nDamage Done: " + currentAbilities.getAbility(index).getDamageDone() + "\nHeal Amount: " 
						+ currentAbilities.getAbility(index).getHealAmount() + "\nMana Cost: " + currentAbilities.getAbility(index).getManaCost();
			}
			
			int abilityIndex = -1;//allows to compile and can be source of debugging
			
			//Try-Catch used to catch null exception if user exits or presses cancel on JOptionPane
			try
			{
				//Prompts the user for input about what ability they want by requesting an integer relating to an index
				String abilityNum = JOptionPane.showInputDialog(null, actions, "Input the number corresponding to desired ability", JOptionPane.INFORMATION_MESSAGE);
				abilityIndex = Integer.parseInt(abilityNum);//changes string of index to actual integer
			}
			catch (Exception e)//cancel
			{
				//exits game
				System.out.print("Thanks for playing!");
				System.exit(0);
			}
			
			return currentAbilities.getAbility(abilityIndex - 1);//account for the numbering list to the user
		}
		else if(player.getIsComputer())//computer
		{
			//computer generates a random number for index of ability in abilityList
			int abilityIndex = AI.randomNumber(currentAbilities.size());
			
			return currentAbilities.getAbility(abilityIndex);//no subtraction because random number generator accounts for extra number
		}
		else//shouldn't ever reach code because player must have isComputer boolean value
		{
			return null;//debugging purposes
		}
		
	}
	
	/**
	 * Takes the ability that the player chose and executes it usually
	 * either applying damage to the other player or healing themselves
	 * @param initiatingPlayer
	 * 		The player who's turn it was and chose the ability
	 * @param usedAbility
	 * 		The ability the initiatingPlayer chose
	 * @param recievingPlayer
	 * 		The player to potentially recieve damage
	 */
	public static void takeAction (Player initiatingPlayer, Ability usedAbility, Player recievingPlayer)
	{
		//Begins the action prompt telling the players what player did what action 
		String playerIntro = initiatingPlayer.getName() + "'s " + initiatingPlayer.getCharacter().getName() + " ";
		
		//TODO: Eventually change to enumeration
		//All possible ability types
		final String ONE = "Basic Attack";//regular base attacks
		final String TWO = "Magic Attack";//spells that deal damage and lose mana
		final String THREE = "Recovery";//spells that heal and lose mana
		final String FOUR = "Mage Basic Attack";//basic attack for casters that deals damage and increases mana
		final String FIVE = "Special Attack";//physical attacks that drain mana
	
		//checks to see what type of ability the used ability is
		if(usedAbility.getType().equalsIgnoreCase(ONE))//Basic Attack
		{
			//displays information based on ability type, this case shows a player losing health because it is an attack
			recievingPlayer.getCharacter().loseHealth(usedAbility);
			System.out.println(playerIntro + "used " + usedAbility.getName() + " dealing " + usedAbility.getDamageDone() + " damage");
			displayBattleInfo(recievingPlayer);//displays the receiving player's information after the attack
		}
		else if(usedAbility.getType().equalsIgnoreCase(TWO))//Magic Attack
		{
			//Temporarily assigns the original mana of character before subtraction when using the ability
			int originalMana= initiatingPlayer.getCharacter().getMana();
			
			//checks to see if player has enough mana to execute the attack
			initiatingPlayer.getCharacter().loseMana(usedAbility);
			if(initiatingPlayer.getCharacter().getMana() < 0)//ability requires more mana than player has
			{
				//sets mana back to original amount
				initiatingPlayer.getCharacter().setMana(originalMana);
				System.out.println("Insufficient mana, attack failed");//Tells user they don't have enough mana
				displayBattleInfo(initiatingPlayer);//displays information after the action
			}
			else//player has enough mana to execute ability
			{
				//sets health to value after attack executes
				recievingPlayer.getCharacter().loseHealth(usedAbility);
				//Tells user how much damage they took
				System.out.println(playerIntro + "used " + usedAbility.getName() + " dealing " + usedAbility.getDamageDone() + " damage\n" );
				displayBattleInfo(recievingPlayer);//displays information after the action
			}
		}
		else if(usedAbility.getType().equalsIgnoreCase(THREE))//Recovery
		{
			//Temporarily assigns the original mana of character before subtraction when using the ability
			int originalMana= initiatingPlayer.getCharacter().getMana();
			
			//checks to see if player has enough mana to execute the attack
			initiatingPlayer.getCharacter().loseMana(usedAbility);
			if(initiatingPlayer.getCharacter().getMana() < 0)//ability requires more mana than player has
			{
				//sets mana back to original amount
				initiatingPlayer.getCharacter().setMana(originalMana);
				System.out.println("Insufficient mana, heal failed");//tells user they don't have enough mana
				displayBattleInfo(initiatingPlayer);//displays information after the action
			}
			else//player has enough mana to execute ability
			{
				//sets health to value after attack executes
				initiatingPlayer.getCharacter().heal(usedAbility);
				//Tells user how much damage they took
				System.out.println(playerIntro + "used " + usedAbility.getName() + " recovering " + usedAbility.getHealAmount() + "Hp\n");
				displayBattleInfo(initiatingPlayer);//displays information after the action
			}
		}
		else if(usedAbility.getType().equalsIgnoreCase(FOUR))//Magic Basic Attack
		{
			//player who executes ability gains mana
			initiatingPlayer.getCharacter().gainMana(usedAbility);
			
			//Opposing player losses heath
			recievingPlayer.getCharacter().loseHealth(usedAbility);
			
			//Tells user how much damage they took
			System.out.println(playerIntro + "used " + usedAbility.getName() + " dealing " + usedAbility.getDamageDone() + " damage");
			displayBattleInfo(recievingPlayer);//Displays information after the action
		}
	}
	
	/**
	 * Checks to see if both players chose the same character.
	 * If they chose same character, it creates a new character object with same
	 * information of the character they chose, but of a different instance.
	 * This allows both players to not have shared character instances
	 * Example: Both characters would have the same health pool and die at same time
	 * 
	 * @param player1
	 * 		First player of the game
	 * @param player2
	 * 		Second player of the game
	 * @return
	 * 		If same character, player containing a copy of the character they chose.
	 * 		If different, simply returns the same player
	 */
	public static Player duplicateCharacterCheck(Player player1, Player player2)
	{
		//checks to see if characters are the same
		if(player1.getCharacter().equals(player2.getCharacter()))
		{
			//creates a new character with same parameter values as the character they chose(it's just a copy)
			Character duplicateChar = new Character(player2.getCharacter().getName(), player2.getCharacter().getAbilities(), player2.getCharacter().getHealth(), 
					player2.getCharacter().getMana(), player2.getCharacter().getSpeed(), player2.getCharacter().getDefense(), player2.getCharacter().getStrength());
			
			//sets the new character to be player2's character
			player2.setCharacter(duplicateChar);
		}

		return player2;//returns player with same character
	}
	
	
	

}
