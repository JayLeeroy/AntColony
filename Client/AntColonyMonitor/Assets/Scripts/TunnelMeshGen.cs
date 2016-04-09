using UnityEngine;
using System.Collections;

[RequireComponent(typeof(MeshFilter),typeof(MeshRenderer))]
public class TunnelMeshGen : MonoBehaviour
{
	public int m_Type = 1;

	public float m_Length = 0.2f;
	public float m_Thick = 0.05f;
	public float m_Depth = 0.1f;

	[Range(0, 3)]
	public int m_Rotation = 1;

	// Use this for initialization
	void Start() {
		MeshFilter l_MeshFilter = GetComponent<MeshFilter>();
		transform.Rotate (0, 0, 90f * m_Rotation);

		switch (m_Type) {
		case 2:
			l_MeshFilter.mesh = MeshTunnel2(m_Length, m_Thick, m_Depth);
			return;
		case 3:
			l_MeshFilter.mesh = MeshTunnel3(m_Length, m_Thick, m_Depth);
			return;
		case 4:
			l_MeshFilter.mesh = MeshTunnel4(m_Length, m_Thick, m_Depth);
			return;
		default:
			l_MeshFilter.mesh = MeshTunnel1 (m_Length, m_Thick, m_Depth);
			return;
		}
	}

	// -------------------------------------------------------------------------------------------
	Mesh MeshTunnel1(float p_Length, float p_Thick, float p_Depth)
	{
		Mesh l_Mesh = new Mesh();
		l_Mesh.name = "Tunnel_1";
		l_Mesh.vertices = new Vector3[] {
			new Vector3(p_Length, -p_Thick, 0),
			new Vector3(-p_Length, -p_Thick, 0),
			new Vector3(-p_Length, 0, p_Depth),
			new Vector3(p_Length, 0, p_Depth),

			new Vector3(p_Length, 0, p_Depth),
			new Vector3(-p_Length, 0, p_Depth),
			new Vector3(-p_Length, p_Thick, 0),
			new Vector3(p_Length, p_Thick, 0)
		};
		l_Mesh.uv = new Vector2[] {
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0)
		};
		l_Mesh.triangles = new int[] {
			0, 1, 2, 0, 2, 3,
			4, 5, 6, 4, 6, 7 };
		
		l_Mesh.RecalculateNormals();

		return l_Mesh;
	}

	// -------------------------------------------------------------------------------------------
	Mesh MeshTunnel2(float p_Length, float p_Thick, float p_Depth)
	{
		Mesh l_Mesh = new Mesh();
		l_Mesh.name = "Tunnel_2";
		l_Mesh.vertices = new Vector3[] {
			new Vector3(-p_Thick, p_Length, 0),
			new Vector3(0, p_Length, p_Depth),
			new Vector3(0, 0, p_Depth),
			new Vector3(-p_Thick, -p_Thick, 0),

			new Vector3(0, p_Length, p_Depth),
			new Vector3(p_Thick, p_Length, 0),
			new Vector3(p_Thick, p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, 0, p_Depth),
			new Vector3(p_Thick, p_Thick, 0),
			new Vector3(p_Length, p_Thick, 0),
			new Vector3(p_Length, 0, p_Depth),

			new Vector3(0, 0, p_Depth),
			new Vector3(p_Length, 0, p_Depth),
			new Vector3(p_Length, -p_Thick, 0),
			new Vector3(-p_Thick, -p_Thick, 0)
		};
		l_Mesh.uv = new Vector2[] {
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0)
		};
		l_Mesh.triangles = new int[] {
			 0, 1, 2, 0, 2, 3,
			 4, 5, 6, 4, 6, 7,
			 8, 9,10, 8,10,11,
			12,13,14,12,14,15
		};

		l_Mesh.RecalculateNormals();

		return l_Mesh;
	}

	// -------------------------------------------------------------------------------------------
	Mesh MeshTunnel3(float p_Length, float p_Thick, float p_Depth)
	{
		Mesh l_Mesh = new Mesh();
		l_Mesh.name = "Tunnel_3";
		l_Mesh.vertices = new Vector3[] {
			new Vector3(-p_Length, 0, p_Depth),
			new Vector3(-p_Length, p_Thick, 0),
			new Vector3(-p_Thick, p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(-p_Thick, p_Thick, 0),
			new Vector3(-p_Thick, p_Length, 0),
			new Vector3(0, p_Length, p_Depth),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, p_Length, p_Depth),
			new Vector3(p_Thick, p_Length, 0),
			new Vector3(p_Thick, p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, 0, p_Depth),
			new Vector3(p_Thick, p_Thick, 0),
			new Vector3(p_Length, p_Thick, 0),
			new Vector3(p_Length, 0, p_Depth),

			new Vector3(-p_Length, 0, p_Depth),
			new Vector3(p_Length, 0, p_Depth),
			new Vector3(p_Length, -p_Thick, 0),
			new Vector3(-p_Length, -p_Thick, 0)
		};
		l_Mesh.uv = new Vector2[] {
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0)
		};
		l_Mesh.triangles = new int[] {
			0, 1, 2, 0, 2, 3,
			4, 5, 6, 4, 6, 7,
			8, 9,10, 8,10,11,
			12,13,14,12,14,15,
			16,17,18,16,18,19
		};

		l_Mesh.RecalculateNormals();

		return l_Mesh;
	}

	// -------------------------------------------------------------------------------------------
	Mesh MeshTunnel4(float p_Length, float p_Thick, float p_Depth)
	{
		Mesh l_Mesh = new Mesh();
		l_Mesh.name = "Tunnel_3";
		l_Mesh.vertices = new Vector3[] {
			new Vector3(-p_Length, 0, p_Depth),
			new Vector3(-p_Length, p_Thick, 0),
			new Vector3(-p_Thick, p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(-p_Thick, p_Thick, 0),
			new Vector3(-p_Thick, p_Length, 0),
			new Vector3(0, p_Length, p_Depth),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, p_Length, p_Depth),
			new Vector3(p_Thick, p_Length, 0),
			new Vector3(p_Thick, p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, 0, p_Depth),
			new Vector3(p_Thick, p_Thick, 0),
			new Vector3(p_Length, p_Thick, 0),
			new Vector3(p_Length, 0, p_Depth),


			new Vector3(-p_Length, 0, p_Depth),
			new Vector3(-p_Length, -p_Thick, 0),
			new Vector3(-p_Thick, -p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(-p_Thick, -p_Thick, 0),
			new Vector3(-p_Thick, -p_Length, 0),
			new Vector3(0, -p_Length, p_Depth),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, -p_Length, p_Depth),
			new Vector3(p_Thick, -p_Length, 0),
			new Vector3(p_Thick, -p_Thick, 0),
			new Vector3(0, 0, p_Depth),

			new Vector3(0, 0, p_Depth),
			new Vector3(p_Thick, -p_Thick, 0),
			new Vector3(p_Length, -p_Thick, 0),
			new Vector3(p_Length, 0, p_Depth),
		};
		l_Mesh.uv = new Vector2[] {
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),

			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
			new Vector2 (0, 0),
			new Vector2 (0, 1),
			new Vector2 (1, 1),
			new Vector2 (1, 0),
		};
		l_Mesh.triangles = new int[] {
			0, 1, 2, 0, 2, 3,
			4, 5, 6, 4, 6, 7,
			8, 9,10, 8,10,11,
			12,13,14,12,14,15,
			17,16,18,16,19,18,
			21,20,22,20,23,22,
			25,24,26,24,27,26,
			29,28,30,28,31,30
		};

		l_Mesh.RecalculateNormals();

		return l_Mesh;
	}
}
