using UnityEngine;
using System.Collections;

public class UnderMap : MonoBehaviour
{
	public static string WO_TUNNEL = "tunnel";
	public static string WO_STONE = "stone";

	public static int TUNTYPE_NO = 0;
	public static int TUNTYPE_STRAIGHT = 1;
	public static int TUNTYPE_L = 2;
	public static int TUNTYPE_T = 3;
	public static int TUNTYPE_CROSS = 4;

	// ----------------------------------------------------------------------------------------------
	[System.Serializable]
	public class Coord2D
	{
		public int x;
		public int y;

		public Coord2D() {
			x = 0;
			y = 0;
		}
	}

	// ----------------------------------------------------------------------------------------------
	[System.Serializable]
	public class WorldObject
	{
		public Coord2D p;
		public string n;
		public string s;

		public WorldObject() {
			p = new Coord2D();
			n = "";
			s = "";
		}
	}

	// ----------------------------------------------------------------------------------------------
	// Search and map update accelerator class
	public class TunnelMapItem
	{
		public int type;
		public int rotation;

		public TunnelMapItem(int p_TunnelType, int p_Rotation) {
			type = p_TunnelType;
			rotation = p_Rotation;
		}

		public TunnelMapItem() {
			type = 0;
			rotation = 0;
		}
	}

	// ----------------------------------------------------------------------------------------------
	public static UnderMap instance;

	public int m_Width = 0;
	public int m_Height = 0;

	public TunnelMapItem[,] m_TunnelMap;
	private bool[,] m_TunnelMask = {	// top, bottom, left, right
		{false, false, false, true},	// 0: left end
		{false, false, true, false},	// 1: right end
		{false, false, true, true},		// 2: horizontal
		{true, false, false, false},	// 3: bottom end
		{false, true, false, false},	// 4: top end
		{true, true, false, false},		// 5: vertical
		{true, false, true, false},		// 6: L top-left
		{true, false, false, true},		// 7: L top-right
		{false, true, true, false},		// 8: L bottom-left
		{false, true, false, true},		// 9: L bottom-right
		{true, true, true, false},		// 10: T left
		{true, true, false, true},		// 11: T right
		{true, false, true, true},		// 12: T top
		{false, true, true, true},		// 13: T bottom
		{true, true, true, true},		// 14: + cross
		{false, false, false, false}	// 15: nothing, so by default draw horizontal
	}; 

	// Type, Rotation
	private static int[,] m_MaskHelper = {
		{ 1, 0 },	{ 1, 0 },	{ 1, 0 },	{ 1, 1 },	{ 1, 1 },	{ 1, 1 },
		{ 2, 1 },	{ 2, 2 },	{ 2, 3 },	{ 2, 0 },
		{ 3, 1 },	{ 3, 3 },	{ 3, 0 },	{ 3, 2 },
		{ 4, 0 },
		{ 1, 0 }
	};

	// ----------------------------------------------------------------------------------------------
	void Awake () {
		if (instance == null)
			instance = this;
	}

	// ----------------------------------------------------------------------------------------------
	public void InitTunnelMap(int p_Width, int p_Height, WorldObject[] p_WorldObjects)
	{
		m_Height = p_Height;
		m_Width = p_Height;
		m_TunnelMap = new TunnelMapItem[p_Width,p_Height];
		FillTunnelMap ();

		TunnelMapItem l_TMI;
		foreach (WorldObject l_WO in p_WorldObjects)
		{
			if (l_WO.n == WO_TUNNEL)
			{
				l_TMI = new TunnelMapItem (TUNTYPE_STRAIGHT, 0);
				m_TunnelMap [l_WO.p.x, l_WO.p.y] = l_TMI;
			}
		}
		UpdateTunnelItems ();
	}

	// ----------------------------------------------------------------------------------------------
	bool CheckTunnelMaskAt(int p_X, int p_Y, bool p_Mask)
	{
		if (p_X < 0)
			return true;
		if (p_X >= m_Width)
			return true;

		if (p_Y < 0)
			return true;
		if (p_Y >= m_Height)
			return true;

		Debug.Log ("CheckMask: " + p_X + "," + p_Y + " " + p_Mask + " Type: " + m_TunnelMap [p_X, p_Y].type);

		return (p_Mask == (m_TunnelMap [p_X, p_Y].type != TUNTYPE_NO));
	}

	// ----------------------------------------------------------------------------------------------
	bool CheckTunnelMask(int p_X, int p_Y, int p_MaskIndex)
	{
		if (CheckTunnelMaskAt (p_X, p_Y - 1, m_TunnelMask [p_MaskIndex, 0]))
		if (CheckTunnelMaskAt (p_X, p_Y + 1, m_TunnelMask [p_MaskIndex, 1]))
		if (CheckTunnelMaskAt (p_X - 1, p_Y, m_TunnelMask [p_MaskIndex, 2]))
			return CheckTunnelMaskAt (p_X + 1, p_Y, m_TunnelMask [p_MaskIndex, 3]);
		
		return false;
	}

	// ----------------------------------------------------------------------------------------------
	void UpdateTunnelItem(int p_X, int p_Y)
	{
		TunnelMapItem l_TMI = m_TunnelMap [p_X, p_Y];
		if (l_TMI == null) {				// We do not have it initialized as did not come from the server
			l_TMI = new TunnelMapItem ();
			m_TunnelMap [p_X, p_Y] = l_TMI;
			return;
		}

		if (l_TMI.type == 0)
			return;

		for (int i = 0; i<m_TunnelMask.GetLength(0); i++) {
			if (CheckTunnelMask (p_X, p_Y, i))
			{
				m_TunnelMap [p_X, p_Y].type = m_MaskHelper [i,0];
				m_TunnelMap [p_X, p_Y].rotation = m_MaskHelper [i,1];
			}
		}
	}

	// ----------------------------------------------------------------------------------------------
	void UpdateTunnelItems() {
		for (int y = 0; y < m_Height; y++)
			for (int x = 0; x < m_Width; x++) {
				TunnelMapItem l_TMI = m_TunnelMap [x, y];
				if (null == l_TMI)
					continue;
				UpdateTunnelItem (x, y);
//				Debug.Log ("MAP @ " + x + "," + y + ": T" + l_TMI.type + " / R" + l_TMI.type);
			}
	}

	// ----------------------------------------------------------------------------------------------
	void FillTunnelMap() {
		for (int y = 0; y < m_Height; y++)
			for (int x = 0; x < m_Width; x++) {
				m_TunnelMap [x, y] = new TunnelMapItem(0,0);
			}
	}
}