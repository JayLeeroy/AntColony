using UnityEngine;
using System.Collections;

public class TunnelVisualizer : MonoBehaviour
{
	public string m_InputJSON;
	public GameObject m_TunnelPrefab;
	public int m_Width = 10;
	public int m_Height = 10;
	public Transform m_Soil;

	private UnderMap.WorldObject m_WO;
	private Vector3 m_TopCenter;
	private float m_UnitSize = 0.1f;
	private int m_WidthHalf = 5;

	private UnderMap.WorldObject[] m_WOs;

	// ----------------------------------------------------------------------------------------------
	void Start ()
	{
		m_UnitSize = m_Width / m_Soil.localScale.x * 0.1f;
		m_WidthHalf = m_Width / 2;

		Debug.Log ("UnitSize: "  + m_UnitSize);
			
		m_WOs = JsonHelper.getJsonArray<UnderMap.WorldObject> (m_InputJSON);
		string l_JO = JsonHelper.ToJson (m_WOs);
		UnderMap.instance.InitTunnelMap (m_Width, m_Height, m_WOs);
		SpawnTunnels ();
	}

	// ----------------------------------------------------------------------------------------------
	private void SpawnTunnel(int p_X, int p_Y)
	{
		UnderMap.TunnelMapItem l_TMI;
		l_TMI = UnderMap.instance.m_TunnelMap [p_X, p_Y];

		// Divide the free surface of the underground mesh by the matrix size
		if (l_TMI.type == UnderMap.TUNTYPE_NO)
			return;

		Vector3 l_TunPos = new Vector3((p_X-m_WidthHalf) * m_UnitSize, -p_Y * m_UnitSize, -0.2f);
		GameObject l_Tunnel = (GameObject)Instantiate (m_TunnelPrefab, l_TunPos, Quaternion.identity);

		TunnelMeshGen l_MeshGen = (TunnelMeshGen)l_Tunnel.GetComponent<TunnelMeshGen> ();
		l_MeshGen.m_Type = l_TMI.type;
		l_MeshGen.m_Rotation = l_TMI.rotation;
		l_Tunnel.SetActive (true);
	}

	// ----------------------------------------------------------------------------------------------
	private void SpawnTunnels()
	{
		for (int y = 0; y < UnderMap.instance.m_Height; y++)
			for (int x = 0; x < UnderMap.instance.m_Width; x++)
			{
				SpawnTunnel (x, y);
			}
	}
}
