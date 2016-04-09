using UnityEngine;
using System.Collections;

public class TargetCam : MonoBehaviour
{
	public Transform m_TargetObject;

	// Update is called once per frame
	void Update ()
	{
		transform.LookAt (m_TargetObject);	
	}
}
