package ColonAi.WorldDimension;

import ColonAi.Ants.Ant;
import ColonAi.Ants.AntAbstract;
import ColonAi.Ants.AntTask;
import ColonAi.PackageManager.Manager;
import ColonAi.WorldObjects.Tunnel;
import ColonAi.WorldObjects.WorldObjectAbstract;

public class UnderGroundDimension extends WorldDimensionAbstract {

	public UnderGroundDimension(int x, int y) {
		super(x, y);
	}
	
	public void createObject(WorldObjectAbstract object, int x, int y)
	{	
		if (Tunnel.class.isAssignableFrom(object.getClass()))
		{
			super.createObject(object, x, y);
			this.staticObjects.add(object);
			object.status = "c";
			Manager.instance.reliablePack.staticObjects.add(object);
		}
		
		if (AntAbstract.class.isAssignableFrom(object.getClass()))
		{
			WorldObjectAbstract objectInPos;
			try {
				objectInPos = this.getObject(x, y);
				
				if (objectInPos.getClass().isAssignableFrom(Tunnel.class))
				{
					this.moveAnt((AntAbstract)object, x, y, null);
					this.ants.add((AntAbstract)object);
					object.status = "c";
				
					Manager.instance.reliablePack.ants.add(object);
					System.out.println(object.hashCode() + " created at " + object.position.x + " " + object.position.y);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void diediDieMyDarling(Ant object)
	{
		this.ants.remove(object);
		object.status = "d";
		
		Tunnel t = (Tunnel) this.getMatrix()[object.position.x][object.position.y];
		
		if (object.task.eq(AntTask.feed()))
		{
			t.feedtunnel = null;
		}
		
		if (object.task.eq(AntTask.food()))
		{
			t.foodtunnel = null;
		}
		
		Manager.instance.reliablePack.ants.add(object);
		
		System.out.println(object.position.hashCode() + "died");
	}

	@Override
	public void moveAnt(AntAbstract object, int x, int y, AntTask task) {
		WorldObjectAbstract destination = this.getMatrix()[x][y];
		
		if (destination != null && destination.getClass().isAssignableFrom(Tunnel.class))
		{
			Tunnel myTunnel  = (Tunnel)destination;
			if (myTunnel.foodtunnel == null)
			{
				object.lastposition.x  = object.position.x;
				object.lastposition.y  = object.position.y;
				Tunnel tun = (Tunnel)this.getMatrix()[object.position.x][object.position.y];
				
				if (tun != null)
				{
					if (tun.foodtunnel !=null && tun.foodtunnel.equals(object))
					{
						tun.foodtunnel = null;
						tun.foodsourceVectors.add(tun.position);
					}
					
					if (tun.feedtunnel !=null && tun.feedtunnel.equals(object))
					{
						tun.feedtunnel = null;
						tun.feedtunnelsourceVectors.add(tun.position);
					}
				
				}
			
				object.position.x      = x;
				object.position.y      = y;
				object.actualDimension = this;
				object.status          = "m";
				
				if(Ant.class.isAssignableFrom(object.getClass()))
				{
					Ant anti= (Ant)object;
					
					if (anti.task.eq(AntTask.feed()))
					{
						myTunnel.feedtunnel = object;
						//tun.feedtunnelsourceVectors.add(object.lastposition);
					}
					else if (anti.task.eq(AntTask.food()))
					{
						myTunnel.foodtunnel = object;
						//tun.foodsourceVectors.add(object.lastposition);
					}
				}
				myTunnel.foodtunnel       = object;
				
			}
			
			if (!Manager.instance.reliablePack.ants.contains(object))
			{				
				Manager.instance.pack.ants.add(object);
			}
			
			//System.out.println(object.hashCode() + " moved to " + object.position.x + " " + object.position.y);
		}
	}
	
	
}
