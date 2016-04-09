package ColonAi.PackageManager.Packages;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ColonAi.WorldObjects.WorldObjectAbstract;

public class Package {
		
	@Expose(serialize = true)
	@SerializedName("id")
	public Integer id;

	public static Integer staticId = 0;
	
	public Package()
	{
		Package.staticId++;
		this.id = Package.staticId;
	}
	
	@Expose(serialize = true)
	@SerializedName("s")
	public List<WorldObjectAbstract> staticObjects = new ArrayList<WorldObjectAbstract>();
	@Expose(serialize = true)
	@SerializedName("a")
	public List<WorldObjectAbstract> ants = new ArrayList<WorldObjectAbstract>();
	
	public String getAsJson()
	{
		GsonBuilder builder = new GsonBuilder();
	    builder.excludeFieldsWithoutExposeAnnotation();
		
		Gson bson = builder.create();
		
		return bson.toJson(this);
	}
}
