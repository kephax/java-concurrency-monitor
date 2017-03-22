/**
 * 
 * @author Pietro Bua
 *
 */
public class Guard extends Person
{
	/**
	 * 
	 * @param name
	 */
	public Guard( String name, Building building )
	{
		super(name, building );
	}
	
	/**
	 * 
	 */
	public void run()
	{
		while(true)
		{
			justLive();
			try
			{
				System.out.println( "Guard [" + Thread.currentThread().getName() + "] is waiting to enter");
				myBuilding.checkIn(this);
				visit();
				myBuilding.checkOut(this);
				System.out.println( "Guard [" + Thread.currentThread().getName() + "] is leaving the building");
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
