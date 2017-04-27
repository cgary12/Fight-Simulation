import java.util.ArrayList;

public class AbilityList {
	
	ArrayList<Ability> allAbilities = new ArrayList<Ability>();
	
	//constructer
	public AbilityList()
	{
		
	}
	
	public void addAbility(Ability ability)
	{
		allAbilities.add(ability);
	}
	
	public Ability getAbility(int index)
	{
		
		return allAbilities.get(index);
	}
	
	public int size()
	{
		return allAbilities.size();
	}

}
