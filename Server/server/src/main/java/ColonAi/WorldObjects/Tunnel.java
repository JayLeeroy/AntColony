package ColonAi.WorldObjects;

import java.util.ArrayList;
import java.util.List;

import ColonAi.Ants.AntAbstract;
import ColonAi.Ants.AntTask;
import ColonAi.WorldDimension.Vector2D;

public class Tunnel extends WorldObjectAbstract {
	public AntAbstract foodtunnel;
	
	public AntAbstract feedtunnel;
	
	public List<Vector2D> feedtunnelsourceVectors = new ArrayList<Vector2D>();
	
	public List<Vector2D> foodsourceVectors = new ArrayList<Vector2D>();
}
