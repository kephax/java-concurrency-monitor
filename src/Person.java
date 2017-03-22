/**
 * 
 * @author Pietro Bua
 *
 */
public class Person extends Thread
{
	protected Building myBuilding;
	
	/**
	 * 
	 * @param name
	 * @param building
	 */
	public Person( String name, Building building )
	{
		super(name);
		myBuilding = building;
	}

	/**
	 * 
	 */
	protected void justLive()
	{
		try
		{
			System.out.println(getName() + " living");
			Thread.sleep((int)(Math.random() * 1000));
		} 
		catch (InterruptedException e) 
		{
			System.out.println( e );
		}
	}
	
	/**
	 * 
	 */
	protected void visit()
	{
		try
		{
			System.out.println( getName() + " visiting");
			Thread.sleep((int)(Math.random() * 1000));
		} 
		catch (InterruptedException e) 
		{
			System.out.println( e );
		}
	}	

}
