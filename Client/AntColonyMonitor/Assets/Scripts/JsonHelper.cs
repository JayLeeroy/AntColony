using UnityEngine;
using System.Collections;

public class JsonHelper {
	
	public static T[] getJsonArray<T>(string p_Json)
	{
		string l_NewJson = "{ \"array\": " + p_Json + "}";
		Wrapper<T> l_Wrapper = JsonUtility.FromJson<Wrapper<T>> (l_NewJson);
		return l_Wrapper.array;
	}

	public static string ToJson<T>(T[] p_Array) {
		Wrapper<T> l_Wrapper = new Wrapper<T>();
		l_Wrapper.array = p_Array;
		return UnityEngine.JsonUtility.ToJson(l_Wrapper);
	}

	[System.Serializable]
	private class Wrapper<T>
	{
		public T[] array;
	}
}
