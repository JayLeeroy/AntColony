package ColonAi.WorldObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ColonAi.WorldDimension.Vector2D;
import ColonAi.WorldDimension.WorldDimensionAbstract;

public abstract class WorldObjectAbstract {
	@Expose(serialize = true)
	@SerializedName("p")
	public Vector2D position = new Vector2D();
	
	@Expose(serialize = true)
	@SerializedName("n")
	public String name = this.getClass().getSimpleName().toLowerCase();
	
	@Expose(serialize = true)
	@SerializedName("s")
	public String status;
	
	public Vector2D lastposition = new Vector2D();
	
	
	public WorldDimensionAbstract actualDimension;
}
