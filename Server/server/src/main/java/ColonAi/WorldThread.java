package ColonAi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.jetty.websocket.api.Session;

import ColonAi.PackageManager.Manager;
import ColonAi.PackageManager.Packages.ReliablePackage;
import WebSocketServer.StockServiceWebSocket;

public class WorldThread implements Runnable{
	public StockServiceWebSocket socket;
	
	public WorldThread(StockServiceWebSocket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		while (true)
		{	
			this.socket.world1.run();
			
			StockServiceWebSocket.sessions.forEach((session)->{
	    		try {
	    			Manager.instance.addNextPackageToClient(session.hashCode());
	    			this.socket.sendMessage(Manager.instance.pack.getAsJson(), session);
	    			
	    			HashMap<Integer, ReliablePackage> map = (HashMap<Integer, ReliablePackage>) Manager.instance.reliablePacks.get(session.hashCode()).clone();
	    				
	    			for(Entry<Integer, ReliablePackage> entry : map.entrySet()) {
	    				ReliablePackage value = entry.getValue();
	    				if (value.isResendable()){
	    					//System.out.println(session.hashCode() + " resend " + map);
	    					this.socket.sendMessage(value.getAsJson(), session);
	    				}

	    			    // do what you have to do here
	    			    // In your case, an other loop.
	    			}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	});
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
