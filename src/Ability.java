/**
 * The ability class is used to get ability information
 * that allows the program to run properly. Each ability is 
 * assigned various parameters in order to understand what type
 * of ability it is, then execute the appropriate command based
 * on that ability type.
 * @author P. Cavan Gary-O'Toole
 *
 */
public class Ability {
	
	/** Name of the ability **/
	private String name;
	
	/** The type of ability it is
	 * 
	 * Note: Eventually turn type into enumeration
	 * Basic Attack, Magic Attack, Recovery, Magic Basic Attack, Special Attack
	 */
	private String type;
	
	/** The discription of what the ability does **/
	private String description;
	
	/** The amount of damage the ability does to the other player
	 * when called
	 */
	private int damageDone;
	
	/** The amount of health the ability restores for the player casting it **/
	private int healAmount;
	
	/** The amount of mana(special points) the ability will subtract from the user when cast**/
	private int manaCost;
	
	/**
	 * Basic constructor for an ability
	 * 
	 * @param name
	 * 		Name of the ability
	 * @param type
	 * 		The type of ability it is
	 * @param description
	 * 		The description of what the ability does
	 * @param damageDone
	 * 		The amount of damage the ability does to the other player
	 * 		when called
	 * @param healAmount
	 * 		The amount of health the ability restores for the player casting it
	 * @param manaCost
	 * 		The amount of mana(special points) the ability will subtract from the user when cast
	 */
	public Ability(String name, String type, String description, int damageDone, int healAmount, int manaCost)
	{
		this.name = name;
		this.type = type;
		this.description = description;
		this.damageDone = damageDone;
		this.healAmount = healAmount;
		this.manaCost = manaCost;
	}
	
	
	//Getters begin
	/**
	 * Getter for the name parameter of an ability
	 * @return
	 * 		returns the name of the ability of type String
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter for the type parameter of an ability
	 * @return
	 * 		returns the ability type as a String
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Getter for the description parameter of an ability
	 * @return
	 * 		returns the description of the ability as a String
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Getter for the damageDone parameter of an ability
	 * @return
	 * 		returns the damage done by the ability as an integer
	 */
	public int getDamageDone()
	{
		return this.damageDone;
	}
	
	/**
	 * Getter for the healAmount parameter of an ability
	 * @return
	 * 		returns the amount the ability heals as an integer
	 */
	public int getHealAmount()
	{
		return this.healAmount;
	}
	
	
	/**
	 * Getter for the manaCost parameter of an ability
	 * @return
	 * 		returns the amount of mana the ability will 
	 * 		cost the user upon use as an integer
	 */
	public int getManaCost()
	{
		return this.manaCost;
	}

}
