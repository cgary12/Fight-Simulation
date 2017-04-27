
public class Ability {
	
	private String name;
	private String type;
	private String description;
	private int damageDone;
	private int healAmount;
	private int manaCost;
	
	public Ability(String name, String type, String description, int damageDone, int healAmount, int manaCost)
	{
		this.name = name;
		this.type = type;
		this.description = description;
		this.damageDone = damageDone;
		this.healAmount = healAmount;
		this.manaCost = manaCost;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getDamageDone()
	{
		return this.damageDone;
	}
	
	public int getHealAmount()
	{
		return this.healAmount;
	}
	
	public int getManaCost()
	{
		return this.manaCost;
	}

}
