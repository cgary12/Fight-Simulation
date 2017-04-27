
public class Character {
	
	private String name;
	private AbilityList abilities;
	private int health;
	private int mana;
	private int speed;
	private int defense;
	private int strength;

	//constructer
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
	public String getName()
	{
		return this.name;
	}
	public AbilityList getAbilities()
	{
		return this.abilities;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public int getMana()
	{
		return this.mana;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public int getDefense()
	{
		return this.defense;
	}
	
	public int getStrength()
	{
		return this.strength;
	}
	
	//setters
	//TODO check to see if setters should return void
	public void setName(String newName)
	{
		this.name = newName;
		return;
	}
	
	public void setAbilityList(AbilityList newAbilities)
	{
		this.abilities = newAbilities;
		return;
	}
	
	public void setHealth(int newHealth)
	{
		this.health = newHealth;
		return;
	}
	
	public void setMana(int newMana)
	{
		this.mana = newMana; 
		return;
	}
	
	public void setSpeed(int newSpeed)
	{
		this.speed = newSpeed;
		return;
	}
	
	public void setDefense(int newDefense)
	{
		this.defense = newDefense;
		return;
	}
	
	public void setStrength(int newStrength)
	{
		this.strength = newStrength;
		return;
	}
	
	//actions
	//TODO DONE check to see if this.heal can be refered to twice
	public void loseHealth(Ability ability)
	{
		this.health = this.health - ability.getDamageDone();
				
	}
	
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
			this.mana = this.mana - ability.getManaCost();
			this.health = this.health + ability.getHealAmount();
		}
			
		
	}
	
	public void loseMana (Ability ability)
	{
		this.mana = this.mana - ability.getManaCost();
	}
	
	public void gainMana (Ability ability)
	{
		this.mana = this.mana + ability.getManaCost();
	}
	
	
	//Health checking
	public boolean isDead()
	{
		if(this.health <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Mana checking
	public boolean isOom()//Oom stands for Out Of Mana
	{
		if(this.mana <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
