import javax.swing.JOptionPane;

public class UserPrompts {
	
	public static Player getPlayerInfo(CharacterRoster allCharacters)
	{
		
		String name = JOptionPane.showInputDialog("Hello player. Please enter your name.");
		
		String allCharacterPrompt = "1: " + allCharacters.get(0).getName();
		
		for(int index = 1; index < allCharacters.size(); ++index)
		{
			allCharacterPrompt = allCharacterPrompt + "\n" + (index + 1) + ": " + allCharacters.get(index).getName();
		}
		
		String StringNum = JOptionPane.showInputDialog("Hello " + name + ". Please enter the corresponding number for the character you"
							+ " would like to fight as \n" + allCharacterPrompt);
		
		int actualNum = Integer.parseInt(StringNum);
		
		Player player = new Player(name, allCharacters.get(actualNum - 1));
		
		return player;
		
		
	}
	
	public static void battle(Player player1, Player player2)
	{
		
		System.out.println("Let the battle begin \n" + "Each player will be granted one action per turn and first one to "
				+ "run out of health loses. \n" + "Good Luck!");
		
		//Displays the player stats before a game
		displayPlayerInfo(player1, player2);
		int turnNumber = 1;//initializes the number of turns passed
		
		//ends the loop once a persons health reaches 0
		while(!(player1.getCharacter().isDead() || player2.getCharacter().isDead()))
		{
			System.out.println("\n\nTurn " + turnNumber);
			
			
			
			if(player1.getCharacter().getSpeed() > player2.getCharacter().getSpeed())
			{
				//finds the abilites they
				Ability player1Choice = takeTurn(player1);
				Ability player2Choice = takeTurn(player2);
				
				//TODO check to see if exits loop before next players turn
				takeAction(player1, player1Choice, player2);
				if(player2.getCharacter().isDead())
				{
					System.out.println("\nCongrats " + player1.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);
					break;
				}
				
				
		
				takeAction(player2, player2Choice, player1);
				if(player1.getCharacter().isDead())
				{
					System.out.println("\nCongrats " + player2.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);

					break;
				}
				
				
			}
			else
			{
				Ability player2Choice = takeTurn(player2);
				Ability player1Choice = takeTurn(player1);
				
				takeAction(player2, player2Choice, player1);
				if(player1.getCharacter().isDead())
				{
					System.out.println("\nCongrats " + player2.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);

					break;
				}
				
				
				takeAction(player1, player1Choice, player2);
				if(player2.getCharacter().isDead())
				{
					System.out.println("\nCongrats " + player1.getName() + "!\n" + "You won!");
					displayPlayerInfo(player1, player2);

					break;
				}
			}
			++turnNumber;
			displayPlayerInfo(player1, player2);
		}
	}
	
	public static void displayPlayerInfo(Player player1, Player player2)
	{
		//Displays the stats of each character before a game
		String player1Info = player1.getName() + "'s " + player1.getCharacter().getName() + "\n" 
				+ "  Health: " + player1.getCharacter().getHealth() + "\n" + "    Mana: " + player1.getCharacter().getMana() 
				+ "\nStrength: " + player1.getCharacter().getStrength() + "\n" + "   Speed: " + player1.getCharacter().getSpeed() 
				+ "\n Defense: " + player1.getCharacter().getDefense();
		
		String player2Info = player2.getName() + "'s " + player2.getCharacter().getName() + ". \n" 
				+ "  Health: " + player2.getCharacter().getHealth() + "\n" + "    Mana: " + player2.getCharacter().getMana() 
				+ "\nStrength: " + player2.getCharacter().getStrength() + "\n" + "   Speed: " + player2.getCharacter().getSpeed() 
				+ "\n Defense: " + player2.getCharacter().getDefense();
		
		System.out.println("\n" + player1Info + "\n \n" + player2Info);
	}
	
	public static Ability takeTurn (Player player)
	{
		
		AbilityList currentAbilities = player.getCharacter().getAbilities();
		System.out.println("\nIt is " + player.getName() +  "'s turn");
		System.out.println("Choose an action.");
		
		String actions = "1: " + currentAbilities.getAbility(0).getName() + "\n" + currentAbilities.getAbility(0).getDescription() 
				+ "\nDamage Done: " + currentAbilities.getAbility(0).getDamageDone() + "\nHeal Amount: " 
				+ currentAbilities.getAbility(0).getHealAmount() + "\nMana Cost: " + currentAbilities.getAbility(0).getManaCost();
				
		for(int index = 1; index < currentAbilities.size(); ++index)
		{
			actions = actions + "\n" + (index + 1)+ ": " + currentAbilities.getAbility(index).getName() + "\n" + currentAbilities.getAbility(index).getDescription() 
					+ "\nDamage Done: " + currentAbilities.getAbility(index).getDamageDone() + "\nHeal Amount: " 
					+ currentAbilities.getAbility(index).getHealAmount() + "\nMana Cost: " + currentAbilities.getAbility(index).getManaCost();
		}
		String abilityNum = JOptionPane.showInputDialog(actions);
		int abilityIndex = Integer.parseInt(abilityNum);
		
		return currentAbilities.getAbility(abilityIndex - 1);//account for the numbering list to the user
		
	}
	
	public static void takeAction (Player initiatingPlayer, Ability usedAbility, Player recievingPlayer)
	{
		final String ONE = "Basic Attack";
		final String TWO = "Magic Attack";
		final String THREE = "Recovery";
		final String FOUR = "Mage Basic Attack";
	
		//checks to see what type of ability the used ability is
		if(usedAbility.getType().equalsIgnoreCase(ONE))
		{
			recievingPlayer.getCharacter().loseHealth(usedAbility);
		}
		else if(usedAbility.getType().equalsIgnoreCase(TWO))
		{
			initiatingPlayer.getCharacter().loseMana(usedAbility);
			if(initiatingPlayer.getCharacter().getMana() <= 0)
			{
				System.out.println("Out of mana");
			}
			else
			{
				recievingPlayer.getCharacter().loseHealth(usedAbility);
			}
		}
		else if(usedAbility.getType().equalsIgnoreCase(THREE))
		{
			initiatingPlayer.getCharacter().heal(usedAbility);
		}
		else if(usedAbility.getType().equalsIgnoreCase(FOUR))
		{
			initiatingPlayer.getCharacter().gainMana(usedAbility);
			recievingPlayer.getCharacter().loseHealth(usedAbility);
		}
	}
	
	
	

}
