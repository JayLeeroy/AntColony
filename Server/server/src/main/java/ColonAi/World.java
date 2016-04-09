package ColonAi;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ColonAi.Ants.AntAbstract;
import ColonAi.Ants.AntQueen;
import ColonAi.PackageManager.Manager;
import ColonAi.PackageManager.Packages.ReliablePackage;
import ColonAi.WorldDimension.UnderGroundDimension;
import ColonAi.WorldObjects.Tunnel;

public class World {
	@Expose(serialize = true)
	@SerializedName("u")
	public UnderGroundDimension underGroundDimension;
	
	private int i = 0;
	
	private static World instance;
	
	private Manager manager;
	
	public World(int ux, int uy)
	{
		this.underGroundDimension = new UnderGroundDimension(ux, uy);
		
		World.instance = this;
		
		this.manager = new Manager();
	}
	
	public static World getInstance()
	{
		return World.instance;
	}
	
	public void init()
	{
		this.underGroundDimension.createObject(new Tunnel(), 50, 0);
		this.underGroundDimension.createObject(new AntQueen(), 50, 0);
	}
	
	public Manager run()
	{
		this.manager.reset();
		
		
		Iterator<AntAbstract> iter = this.underGroundDimension.ants.iterator();
		while(iter.hasNext()){
			AntAbstract ant = iter.next();
			try {
				ant.doAsYouWishMyChild();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.manager;
	}
	
	public ReliablePackage getForNewClient(int clientId)
	{
		ReliablePackage packageData = new ReliablePackage();
		packageData.w = this.underGroundDimension.getMatrix().length;
		packageData.h = this.underGroundDimension.getMatrix()[0].length;
		
		this.underGroundDimension.staticObjects.forEach((s)->{
    		try {
    			packageData.staticObjects.add(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		this.underGroundDimension.ants.forEach((s)->{
    		try {
    			packageData.ants.add(s);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    			
    	});
		
		Manager.instance.addFirstPackage(packageData, clientId);
		
		return packageData;
	}
	
	public String getAsJson()
	{
		GsonBuilder builder = new GsonBuilder();
		
	    builder.excludeFieldsWithoutExposeAnnotation();
		
		Gson bson = builder.create();
		
		return bson.toJson(this);
	}
}
