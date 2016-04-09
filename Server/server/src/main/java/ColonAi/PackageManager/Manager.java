package ColonAi.PackageManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ColonAi.PackageManager.Packages.Package;
import ColonAi.PackageManager.Packages.ReliablePackage;
import WebSocketServer.StockServiceWebSocket;

public class Manager {
	public Package pack;
	public ReliablePackage reliablePack;
	
	public Map<Integer,HashMap<Integer, ReliablePackage>> reliablePacks = new HashMap<Integer, HashMap<Integer, ReliablePackage>>();
	
	public static Manager instance;
	
	public Manager()
	{
		this.pack = new Package();
		this.reliablePack = new ReliablePackage();
		Manager.instance = this;
	}
	
	public void reset()
	{
		this.reliablePack.ants.forEach((s)->{
    		try {
				s.status = null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		this.reliablePack.staticObjects.forEach((s)->{
    		try {
				s.status = null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		this.pack.ants.forEach((s)->{
    		try {
				s.status = null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		this.pack.staticObjects.forEach((s)->{
    		try {
				s.status = null;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		this.reliablePack = new ReliablePackage();
		this.pack = new Package();
	}
	
	public void addFirstPackage(ReliablePackage firstPackage, Integer clientId)
	{
	
		if(!(this.reliablePacks.get(clientId) == null))
		{
			HashMap<Integer, ReliablePackage> fpackage = this.reliablePacks.get(clientId);
			fpackage.put(firstPackage.id, firstPackage);
			this.reliablePacks.put(clientId, fpackage);
		}else
		{
			HashMap<Integer, ReliablePackage> fpackage = new HashMap<Integer, ReliablePackage>();
			fpackage.put(firstPackage.id, firstPackage);
			this.reliablePacks.put(clientId, fpackage);
		}
	}
	
	public void addNextPackageToClient(Integer clientId)
	{
		if(!(this.reliablePacks.get(clientId) == null))
		{
			HashMap<Integer, ReliablePackage> fpackage = this.reliablePacks.get(clientId);
			fpackage.put(this.reliablePack.id, this.reliablePack);
			this.reliablePacks.put(clientId, fpackage);
		}else
		{
			HashMap<Integer, ReliablePackage> fpackage = new HashMap<Integer, ReliablePackage>();
			fpackage.put(this.reliablePack.id, this.reliablePack);
			this.reliablePacks.put(clientId, fpackage);
		}
	}

}
