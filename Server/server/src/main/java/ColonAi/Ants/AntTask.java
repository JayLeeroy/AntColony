package ColonAi.Ants;

public class AntTask {
	protected String value;
	
	public static final String dig = "dig";
	
	public static final String food = "food";
	
	public static final String feed = "feed";
	
	protected  AntTask(String value)
	{
		this.value = value;
	}
	
	public static AntTask dig()
	{
		return new AntTask(AntTask.dig);
	}
	
	public static AntTask food()
	{
		return new AntTask(AntTask.food);
	}
	
	public static AntTask feed()
	{
		return new AntTask(AntTask.feed);
	}
	
	public Boolean eq(AntTask obj)
	{
		return obj.whatIsTheTask() == this.value;
	}

	public String whatIsTheTask()
	{
		return this.value;
	}
}
