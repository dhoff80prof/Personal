using UnityEngine;
using System.Collections;

using UnityEngine.SceneManagement;

public class PlayerController : MonoBehaviour 
{
	[Tooltip("Adjust the speed of the player moving forward.")]
	public float fMoveSpeed = 2;

	[Tooltip("Adjust the speed of the player sprinting forward.")]
	public float sprintMoveSpeed = 10;

	[Tooltip("Adjust the speed of the player strafing.")]
	public float rMoveSpeed = 2;

	[Tooltip("Adjust the speed of the player rotating.")]
	public float yAmount = 1;

	[Tooltip("Adjust the height of the player jump.")]
	public float jumpAmount = 10;

	private Rigidbody myRB;

	private bool inAir;

	public GameObject startSpawn;

	public GameObject fsfFromLsSpawn;

	public GameObject coneyFromFsfSpawn;

	public GameObject FsfFromConeySpawn;

	public GameObject leaveStarterUI;

	//First function ran to keep the player from being destroyed
	void Awake ()
	{
		DontDestroyOnLoad (transform);
	}

	// Use this for initialization
	void Start () 
	{
		myRB = GetComponent<Rigidbody> ();

		transform.position = startSpawn.GetComponent<Transform>().position;

		transform.Rotate(0, startSpawn.GetComponent<Transform>().position.y,0);
		

	}


	
	// Update is called once per frame
	void Update () 
	{
		playerMovement ();
	}

	void playerMovement ()
	{
		if (Input.GetKey (KeyCode.W)) 
		{
			transform.position += transform.forward * fMoveSpeed * Time.deltaTime; 
		}

		if (Input.GetKey (KeyCode.LeftShift)) 
		{
			transform.position += transform.forward * sprintMoveSpeed * Time.deltaTime; 
		}

		if (Input.GetKey (KeyCode.S)) 
		{
			transform.position -= transform.forward * fMoveSpeed * Time.deltaTime; 
		}

		if (Input.GetKey (KeyCode.D)) 
		{
			transform.position += transform.right * rMoveSpeed * Time.deltaTime; 
		}

		if (Input.GetKey (KeyCode.A)) 
		{
			transform.position -= transform.right * rMoveSpeed * Time.deltaTime; 
		}

		if (Input.GetKey (KeyCode.E)) 
		{
			transform.Rotate(0, yAmount, 0, Space.Self);
		}

		if (Input.GetKey (KeyCode.Q)) 
		{
			transform.Rotate(0, -yAmount, 0, Space.Self);
		}

		if (Input.GetKeyDown (KeyCode.Space) && inAir == false) 
		{
			myRB.AddForce(transform.up * jumpAmount);
		}
	}

	void OnCollisionStay (Collision other)
	{
		if (other.gameObject.tag == "Terrain") 
		{
			inAir = false;

			//Debug.Log ("I'm on the ground.");
		}
	}

	void OnCollisionExit (Collision other)
	{
		if (other.gameObject.tag == "Terrain") 
		{
			inAir = true;

			//Debug.Log ("I'm in the air.");
		}
	}

	void OnTriggerEnter (Collider other)
	{
		if (other.tag == "Zonetofsforrest")
		{
			leaveStarterUI.GetComponent<Canvas> ().enabled = true;

		}
			
		if (other.tag == "ToFSForrestFromConey")
		{
			SceneManager.LoadScene ("FSForrestScene");

			transform.position = FsfFromConeySpawn.GetComponent<Transform>().position;

			transform.Rotate(0, FsfFromConeySpawn.GetComponent<Transform>().position.y,0);

		}

		if (other.tag == "Zonetoconey")
		{
			SceneManager.LoadScene ("ConeyPlateauScene");

			transform.position = coneyFromFsfSpawn.GetComponent<Transform>().position;

			transform.Rotate(0, coneyFromFsfSpawn.GetComponent<Transform>().position.y,0);

		}
	}

	public void leaveSquareton ()
	{
		SceneManager.LoadScene ("FSForrestScene");

		transform.position = fsfFromLsSpawn.GetComponent<Transform>().position;

		transform.Rotate(0, fsfFromLsSpawn.GetComponent<Transform>().position.y,0);
	}

	public void stayInSquareton ()
	{
		leaveStarterUI.GetComponent<Canvas> ().enabled = false;
	}

}
