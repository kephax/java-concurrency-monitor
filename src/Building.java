import java.util.concurrent.locks.*;

/**
 * 
 * @author Pietro Bua
 *
 */
public class Building
{
	private Lock lock;
	private Condition newGuard, newVip, newCitizen;
	
	private int waitingCitizen, waitingVip; 
	private int nrOfCitizensInside, nrOfVipInside;
	private int vip;
	
	private boolean allowedCitizens, allowedVip;
	
	/**
	 * 
	 */
	public Building()
	{
		lock = new ReentrantLock();
		
		newGuard = lock.newCondition();
		newVip = lock.newCondition();
		newCitizen = lock.newCondition();
	}
	
	/**
	 * 
	 * @param t
	 * @throws InterruptedException
	 */
	public void checkIn( Thread t ) throws InterruptedException
	{
		lock.lock();
		
		try
		{	
			if( t instanceof Guard)
			{
				System.out.println("Guard: [" + t.getName() + " ]inside"); // do nothing
			}
			else
			{
				if( t instanceof Vip )
				{
					waitingVip++;
					while( (nrOfCitizensInside>0) || 
						   (( vip >=5)&&(!allowedVip)) )
					{
						newVip.await();
					}
					waitingVip--;
					nrOfVipInside++;
					
					assert( vip < 6 ):"More than 5 vip inside"; // while condition
					assert( nrOfCitizensInside == 0 ):"Some citizens are inside";
					
					if( (waitingCitizen>0)&&(! allowedVip ) )
					{
						vip++;
					}
					if( ! (waitingVip>0) )
					{
						allowedVip = false;
					}
				}
				else if( t instanceof Citizen )
				{
					waitingCitizen++;
					while( (nrOfVipInside>0) || 
						   ( (waitingVip>0)&&(!allowedCitizens) ) )
					{
						newCitizen.await();
					}
					waitingCitizen--;
					nrOfCitizensInside++;
					
					if(! (waitingCitizen>0) )
					{
						allowedCitizens = false;
					}
				}
			}
		}
		catch( InterruptedException e )
		{
			System.out.println( e );
		}
		finally
		{
			  lock.unlock();
		}
	}
	
	
	/**
	 * 
	 * @param t
	 */
	public void checkOut( Thread t )
	{
		lock.lock();
		
		try
		{
			if( t instanceof Guard )
			{
				// Do nothing
			}
			else
			{
				if( t instanceof Vip )
				{
					nrOfVipInside--;
					
					if(nrOfVipInside == 0)
					{
						assert( allowedCitizens == false ):"allowedCitizens is true";
						newCitizen.signalAll();
						vip = 0;
						allowedCitizens = true;
					}
				}
				else if( t instanceof Citizen )
				{
					nrOfCitizensInside--;

					
					if( nrOfCitizensInside == 0)
					{
						assert( allowedVip == false ):"allowedVip is true";
						newVip.signalAll();
						allowedVip = true;
					}
				}
			}
		}
		finally
		{
			lock.unlock();
		}
	}
}
