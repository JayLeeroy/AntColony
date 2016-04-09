using UnityEngine;
using System.Collections;

public class ZiziCukor : MonoBehaviour
{
	public float m_MaxDif = 0.5f;
	public float m_Interval = 1f;
	public float m_Speed = 0.05f;

	private Vector3 m_TargetDif;
	private Vector3 m_CurPos;
	private float m_TimeStamp;
	private float m_TimeSpent;

	// Use this for initialization
	void Awake ()
	{
		m_TargetDif = GetNewDestination ();
		m_CurPos = transform.localPosition;
		m_TimeStamp = Time.timeSinceLevelLoad;
	}
	
	// Update is called once per frame
	void Update ()
	{
		m_TimeSpent = Time.timeSinceLevelLoad - m_TimeStamp;
		m_CurPos = Vector3.Lerp (transform.localPosition, m_TargetDif, m_TimeSpent * m_Speed);
		transform.localPosition = m_CurPos;

		if (m_TimeSpent > m_Interval) {
			m_TargetDif = GetNewDestination ();
			m_TimeStamp = Time.timeSinceLevelLoad;
		}
	}

	private Vector3 GetNewDestination ()
	{
		return new Vector3(Random.Range(-m_MaxDif, m_MaxDif), Random.Range(-m_MaxDif, m_MaxDif), Random.Range(-m_MaxDif, m_MaxDif));
	}
}
