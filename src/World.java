/**
 * 
 * @author Pietro Bua
 *
 */
public class World
{
	private Thread[] citizens;
	private Thread[] vips;
	private Thread[] guards;
	
	private final int NR_OF_VIP = 5;
	private final int NR_OF_CITIZENS = 1000;
	private final int NR_OF_GUARDS = 10;
	
	/**
	 * 
	 */
	public World()
	{
		Building building = new Building();
		
		citizens = new Thread[NR_OF_CITIZENS];
		for(int i=0;i<NR_OF_CITIZENS;i++)
		{
			citizens[i] = new Citizen( "Citizen " + i, building );
			citizens[i].start();
		}
		
		vips = new Thread[NR_OF_VIP];
		for(int i=0;i<NR_OF_VIP;i++)
		{
			vips[i] = new Vip( "Vip " + i, building );
			vips[i].start();
		}
		
		guards = new Thread[NR_OF_GUARDS];
		for(int i=0;i<NR_OF_GUARDS;i++)
		{
			guards[i] = new Guard( "Guard " + i, building );
			guards[i].start();
		}
		
	}
}
