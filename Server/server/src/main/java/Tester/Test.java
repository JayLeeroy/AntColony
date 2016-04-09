package Tester;

import ColonAi.World;
import ColonAi.PackageManager.Manager;
import ColonAi.WorldObjects.Tunnel;

public class Test {

	public static void main(String[] args) {
		World world = new World(100,100);
		 
		 //int a = world.underGroundDimension.getMatrix()[0].length;
		 
		 //System.out.println(a);
		 
	     world.init();
	     System.out.println(world.getForNewClient(1).getAsJson());
	     
	     while (true)
	     {	    	 
	    	 world.run();
	    	 try {
				Thread.sleep(600);
				System.out.println(Manager.instance.pack.getAsJson());
				System.out.println(Manager.instance.reliablePack.getAsJson());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     
	}

}
