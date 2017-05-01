
/**
 * This class is used to create character objects 
 * that a player will use in battle and includes various 
 * methods that allow manipulation of the character data.
 * 
 * Note: Eventually, the defense and strength parameters
 * will be used to change the amount of damage a character will 
 * receive and how much damage is done by attacks respectively.
 * 
 * @author P. Cavan Gary-O'Toole
 *
 */
public class Character {
	
	/** The name of the character **/
	private String name;
	
	/** The list of the character's abilities **/
	private AbilityList abilities;
	
	/** The max amount of health the character starts with **/
	private int health;
	
	/** The max amount of mana the character starts with **/
	private int mana;
	
	/** The speed of the character that effects what order 
	 * the turns go in
	 */
	private int speed;
	
	/** The defense of the character that reduces the amount
	 * of damage a character will receive
	 */
	private int defense;
	
	/** The strength of the character that increases the amount
	 * of damage a the character's abilities will deal
	 */
	private int strength;

	/** 
	 * The main constructer for a character object 
	 * @param name
	 * 		
	 * @param abilities
	 * 		The list of ability objects that belong to each character
	 * @param health
	 * 		The max amount of health the character starts with
	 * @param mana
	 * 		 The max amount of mana the character starts with 
	 * @param speed
	 * 		The speed of the character that effects what order 
	 * 		the turns go in
	 * @param defense
	 * 		The defense of the character that reduces the amount
	 * 		of damage a character will receive
	 * @param strength
	 * 		The strength of the character that increases the amount
	 * 		of damage a the character's abilities will deal
	 */
	public Character(String name,AbilityList abilities, int health, int mana, int speed, int defense, int strength)
	{
		this.name = name;
		this.abilities = abilities;
		this.health = health;
		this.mana = mana;
		this.speed = speed;
		this.defense = defense;
		this.strength = strength;
	}

	//getters
	/** 
	 * Gets the name of the character
	 * @return
	 * 		String representation of the name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Gets the list of ability objects the character has
	 * @return
	 * 		The list of abilities as an AbilityList object
	 */
	public AbilityList getAbilities()
	{
		return this.abilities;
	}
	
	/**
	 * Gets the health of the character 
	 * @return
	 * 		The integer representation of their health
	 */
	public int getHealth()
	{
		return this.health;
	}
	
	/**
	 * Gets the mana of the character
	 * @return
	 * 		The integer representation of their mana
	 */
	public int getMana()
	{
		return this.mana;
	}
	
	/**
	 * Gets the speed of the character
	 * @return
	 * 		The integer representation of their speed
	 */
	public int getSpeed()
	{
		return this.speed;
	}
	
	/**
	 * Get the defense of the character
	 * @return
	 * 		The integer representation of their defense
	 */
	public int getDefense()
	{
		return this.defense;
	}
	
	/**
	 * Get the strength of the character
	 * @return
	 * 		The integer representation of their strength
	 */
	public int getStrength()
	{
		return this.strength;
	}
	
	//setters
	/**
	 * Sets the name of the character to a different name
	 * @param newName
	 * 		The name to change the character to
	 */
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	/**
	 * Sets the AbilityList of the character to a different reference
	 * and potentially changes the abilities
	 * @param newAbilities
	 * 		The new abilities to be assigned to the character
	 */
	public void setAbilityList(AbilityList newAbilities)
	{
		this.abilities = newAbilities;
	}
	
	/**
	 * Sets the health of the character to a different value
	 * @param newHealth
	 * 		The new health value to be assigned to the character
	 */
	public void setHealth(int newHealth)
	{
		this.health = newHealth;
	}
	
	/**
	 * Sets the mana of the character to a different value
	 * @param newMana
	 * 		The new mana value to be assigned to the character
	 */
	public void setMana(int newMana)
	{
		this.mana = newMana; 
	}
	
	/**
	 * Sets the speed of the character to a different value
	 * @param newSpeed
	 * 		The new speed value to be assigned to the character
	 */
	public void setSpeed(int newSpeed)
	{
		this.speed = newSpeed;
	}
	
	/**
	 * Sets the defense of the character to a different value
	 * @param newDefense
	 * 		The new defense value to be assigned to the character
	 */
	public void setDefense(int newDefense)
	{
		this.defense = newDefense;
	}
	
	/**
	 * Sets the strength of the character to a different value
	 * @param newStrength
	 * 		The new strength value to be assigned to the character
	 */
	public void setStrength(int newStrength)
	{
		this.strength = newStrength;
	}
	
	//actions
	/**
	 * Decreases the health of a character by getting the damage
	 * done by an ability and subtracting it from current health
	 * @param ability
	 * 		The ability that is damaging the character
	 */
	public void loseHealth(Ability ability)
	{
		//ensures health always stays greater than or equal to 0
		if(this.health - ability.getDamageDone() < 0)
		{
			this.health = 0;//assigns health to zero if reaches a negative value
		}
		else
		{
			//subtracts the ability's damageDone parameter from the health of the character
			this.health = this.health - ability.getDamageDone();
		}
	}
	
	/**
	 * Increases the health of a character by getting the healAmount
	 * parameter from the ability used and adding it to the character's health
	 * @param ability
	 * 		The ability used to heal the character
	 */
	public void heal(Ability ability)
	{
		//checks to see if user has enough mana
		if(this.mana - ability.getManaCost() < 0)
		{
			this.health = this.health;//hp doesn't change because not enough mana to attack
			this.mana = this.mana;//mp won't change either
		}
		else
		{
			this.mana = this.mana - ability.getManaCost();//subtracts mana cost of ability from character's mana
			this.health = this.health + ability.getHealAmount();//adds points to the characters health
		}
			
	}
	
	/**
	 * Decreases the mana of a character
	 * @param ability
	 * 		Ability that resulted in loss in mana when used
	 */
	public void loseMana (Ability ability)
	{
		
		this.mana = this.mana - ability.getManaCost();
	}
	
	/**
	 * Adds to the mana of a character
	 * 
	 * NOTE: Mana gained will still be noted as "Mana cost" in program
	 * even though it adds mana. Ability description should clarify this.
	 * @param ability
	 * 		Ability that resulted in the addition of mana when used
	 */
	public void gainMana (Ability ability)
	{
		this.mana = this.mana + ability.getManaCost();
	}
	
	
	/**
	 * Check to see if a character's health has reached 0, resulting
	 * in death and the end of a game unless they have special abilities 
	 * dictating otherwise
	 * @return
	 * 		Boolean that gives true if dead and false if alive
	 */
	public boolean isDead()
	{
		//checks to see if health is below or equal to 0
		if(this.health <= 0)
		{
			return true;//is dead
		}
		else
		{
			return false;//is alive
		}
	}
	
	/**
	 * Checks to see if a character's mana has reached 0, resulting in an
	 * empty mana pool and cannot cast special abilities unless they gain
	 * some mana back
	 * @return
	 * 		Boolean value that gives true if out of mana and false if there is still some left
	 */
	public boolean isOom()//Oom stands for Out Of Mana
	{
		//Checks to see if mana is at or below 0
		if(this.mana <= 0)
		{
			return true;//is out of mana
		}
		else
		{
			return false;//has mana
		}
	}
}
