package ColonAi.PackageManager.Packages;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReliablePackage extends Package {
	long lStartTime = System.currentTimeMillis();
	@Expose(serialize = true)
	public int w;
	@Expose(serialize = true)
	public int h;
	@Expose(serialize = true)
	@SerializedName("reliable")
	public Boolean isReliable = true;
	
	public Boolean isResendable()
	{	
		long lEndTime = System.currentTimeMillis();
		
		if ((lEndTime - this.lStartTime) > 2000)
		{
			this.lStartTime = lEndTime;
			
			return true;
		}
		
		return false;
	}
}
