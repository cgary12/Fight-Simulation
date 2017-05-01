import java.util.ArrayList;

/**
 * This class keeps track of all the given 
 * abilities for a specific character and organizes 
 * them in a way that is easily accessible by the user.
 * 
 * Note: Once GUI is implemented, the ability list should
 * not exceed a length of four to accommodate all of the 
 * buttons
 * @author P. Cavan Gary-O'Toole
 *
 */
public class AbilityList {
	
	/** The array list of ability objects **/
	ArrayList<Ability> allAbilities = new ArrayList<Ability>();
	
	/**
	 * The constructor for the ability list
	 */
	public AbilityList()
	{
		
	}
	
	/**
	 * Adds an ability to the ability list
	 * @param ability
	 * 		The ability being added to the list
	 */
	public void addAbility(Ability ability)
	{
		allAbilities.add(ability);
	}
	
	//Getters
	/**
	 * Gets the ability at the designated index
	 * @param index
	 * 		The index of the ability
	 * @return
	 * 		Returns the ability object that corresponds to the 
	 * 		given index of the arrayList
	 */
	public Ability getAbility(int index)
	{
		
		return allAbilities.get(index);
	}
	
	
	/**
	 * Gets the size of the ability list
	 * @return
	 * 		The integer representation of the size of the abilityList
	 */
	public int size()
	{
		return allAbilities.size();
	}

}
