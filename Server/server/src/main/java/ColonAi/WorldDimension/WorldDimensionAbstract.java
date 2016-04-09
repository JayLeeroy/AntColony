package ColonAi.WorldDimension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ColonAi.Ants.AntAbstract;
import ColonAi.Ants.AntTask;
import ColonAi.WorldObjects.WorldObjectAbstract;

public abstract class WorldDimensionAbstract {
	private WorldObjectAbstract[][] matrix;
	
	public CopyOnWriteArrayList<WorldObjectAbstract> staticObjects;
	
	public CopyOnWriteArrayList<AntAbstract> ants;
	
	public WorldDimensionAbstract(int x, int y)
	{
		this.matrix        = new WorldObjectAbstract[x][y];
		this.staticObjects = new CopyOnWriteArrayList<WorldObjectAbstract>();
		this.ants          = new CopyOnWriteArrayList<AntAbstract>();
	}
	
	public HashMap<Vector2D, WorldObjectAbstract> getSurrondings(WorldObjectAbstract object, int range)
	{
		HashMap<Vector2D, WorldObjectAbstract> nearblyObjects = new HashMap<Vector2D, WorldObjectAbstract>();
		
		for (int rangeIndex = 0; rangeIndex  < range; rangeIndex++ )
		{
			try {
				WorldObjectAbstract obj = getObject(object.position.x+1 , object.position.y);
				
				Vector2D vpos = new Vector2D();
				vpos.x = object.position.x+1;
				vpos.y = object.position.y;
				

				nearblyObjects.put(vpos, obj);
				
			} catch (Exception e) {
			}
			
			try {
				WorldObjectAbstract obj = getObject(object.position.x-1 , object.position.y);
				
				Vector2D vpos = new Vector2D();
				vpos.x = object.position.x-1;
				vpos.y = object.position.y;
				

				nearblyObjects.put(vpos, obj);
			} catch (Exception e) {
			}
			
			try {
				WorldObjectAbstract obj = getObject(object.position.x , object.position.y+1);
				
				Vector2D vpos = new Vector2D();
				vpos.x = object.position.x;
				vpos.y = object.position.y+1;
				

				nearblyObjects.put(vpos, obj);
			} catch (Exception e) {
			}
			
			try {
				WorldObjectAbstract obj = getObject(object.position.x , object.position.y-1);
				
				Vector2D vpos = new Vector2D();
				vpos.x = object.position.x;
				vpos.y = object.position.y-1;
				

				nearblyObjects.put(vpos, obj);
			} catch (Exception e) {
			}
		}
	  
		return nearblyObjects;
	}
	
	public WorldObjectAbstract getObject(int x, int y) throws Exception
	{
		return this.getMatrix()[x][y];
	} 
	
	public void createObject(WorldObjectAbstract object, int x, int y)
	{

		if (this.getMatrix()[x][y] == null)
		{
			object.position.x      = x;
			object.position.y      = y;
			object.actualDimension = this;
			
			this.getMatrix()[x][y] = object;
		}
	}

	
	public abstract void moveAnt(AntAbstract object, int x, int y, AntTask task);
	
	

	public WorldObjectAbstract[][] getMatrix() {
		return matrix;
	}
}
