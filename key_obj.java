import java.util.ArrayList;
import java.util.Date;
import java.lang.StringBuilder;

public class key_obj {

int keyID; boolean isPhysicalKey; boolean isMasterKey; boolean isavailableForIssue; String keyType; int pin;
ArrayList<String> opensDoors = new ArrayList<String>(); String assignedTo; String assignedToJobTitle; String employeeID;
String authorizedBy; String authorizedByTitle; String dateAssigned; String dateReturned;
String reasonAssigned; String entryCreationDate; String locksmithName; boolean accountedFor;
String reasonLost; String dateLost; ArrayList<String> keyHistory = new ArrayList<String>();

/**
 * Generalized basic key record constructor.
 * @param keyID The individual key identification number associated with the key.
 * @param isPhysical Boolean value, Is this key a physical key or a number?
 * @param keyType This is the variable that stores the keyType
 * @param pin	The pin associated with key if not physical, otherwise it is -1
 * @param opensDoors Arraylist of doors that can be opened with the given key
 * @param locksmithName Name of locksmith who installed/cut the keys/lock
 */
	public key_obj(int keyID, boolean isPhysical, String keyType, int pin, ArrayList<String> opensDoors, String locksmithName)
		{
			this.keyID = keyID;
			this.isPhysicalKey = isPhysical;
			
			if(this.isPhysicalKey == false)
			{
				this.pin = pin;
			}
			else
			{
				this.pin = -1;
			}
			this.keyType = keyType;
			this.opensDoors = opensDoors;
			this.locksmithName = locksmithName;
			this.accountedFor = true;
			entryCreationDate = new Date().toString();
		
	}
	
	/**
	 * Used for importing toString() records and generating the key from records.
	 * @param keyToString toString() representation of the key object
	 */
	public key_obj(String keyToString)
	{
		int delimiterCount = 0;
		char keyArray[] = keyToString.toCharArray();
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < keyArray.length; i++)
		{
			if(keyArray[i] == '*')
			{
				delimiterCount++;
				switch (delimiterCount)
				{
				case 1: 
					this.keyID = Integer.valueOf(temp.toString());
					temp = new StringBuilder();
					break;
				case 2: 
					if(temp.toString().equalsIgnoreCase("true"))
					{
						this.isPhysicalKey = true;
					}
					else
					{
						this.isPhysicalKey = false;
					}
					temp = new StringBuilder();
					break;
				case 3:
					if(temp.toString().equalsIgnoreCase("true"))
					{
						this.isMasterKey = true;
						
					}
					else
					{
						this.isMasterKey = false;
					}
					temp = new StringBuilder();
					break;
				case 4:
					if(temp.toString().equalsIgnoreCase("true"))
					{
						this.isavailableForIssue = true;
					}
					else
					{
						this.isavailableForIssue = false;
					}
					temp = new StringBuilder();
					break;
				case 5:
					this.keyType = temp.toString();
					temp = new StringBuilder();
					break;
				case 6:
					this.pin = Integer.valueOf(temp.toString());
					temp = new StringBuilder();
					break;
				case 7:
					this.assignedTo = temp.toString();
					temp = new StringBuilder();
					break;
				case 8:
					this.assignedToJobTitle = temp.toString();
					temp = new StringBuilder();
					break;
				case 9:
					this.employeeID = temp.toString();
					temp = new StringBuilder();
					break;
				case 10:
					this.authorizedBy = temp.toString();
					temp = new StringBuilder();
					break;
				case 11:
					this.authorizedByTitle = temp.toString();
					temp = new StringBuilder();
					break;
				case 12:
					this.dateAssigned = temp.toString();
					temp = new StringBuilder();
					break;
				case 13:
					this.dateReturned = temp.toString();
					temp = new StringBuilder();
					break;
				case 14: 
					this.reasonAssigned = temp.toString();
					temp = new StringBuilder();
					break;
				case 15:
					this.entryCreationDate = temp.toString();
					temp = new StringBuilder();
					break;
				case 16:
					this.locksmithName = temp.toString();
					temp = new StringBuilder();
					break;
				case 17:
					if(temp.toString().equalsIgnoreCase("true"))
					{
						this.accountedFor = true;
						
					}
					else
					{
						this.accountedFor = false;
					}
					temp = new StringBuilder();
					break;
				case 18:
					this.reasonLost = temp.toString();
					temp = new StringBuilder();
					break;
				case 19:
					this.dateLost = temp.toString();
					temp = new StringBuilder();
					
				}
			}
			else if (keyArray[i] =='#')
			{
				keyHistory.add(temp.toString());
				temp = new StringBuilder();
			}
			else if (keyArray[i] == '&')
			{
				opensDoors.add(temp.toString());
				temp = new StringBuilder();
			}
			else
			{
				temp.append(keyArray[i]);
			}
		}
	}

	/**
	 * Implemented to assign the object to a particular person
	 * @param assignedTo Individual the key is assigned to
	 * @param assignedToJobTitle Jobtitle of the individual the key is assigned to
	 * @param employeeID EmployeeID of the individual the key is assigned to
	 * @param authorizedBy Individual who authorized the key to be assigned to the individual
	 * @param authorizedBy Title Title of the authorizing party
	 * @param dateAssigned Date the key was assigned
	 * @param reasonAssigned Reason the key was assigned
	 * @return returns true if key was able to be assigned, false if key is not available for issue.
	 */
	public boolean assign(String assignedTo, String assignedToJobTitle, String employeeID, String authorizedBy, String authorizedByTitle, String dateAssigned, String reasonAssigned)
		{
			if(isavailableForIssue == true)
			{
				//create history entry
				isavailableForIssue = false;
				this.assignedTo = assignedTo;
				this.assignedToJobTitle = assignedToJobTitle;
				this.employeeID = employeeID;
				this.authorizedBy = authorizedBy;
				this.authorizedByTitle = authorizedByTitle;
				this.dateAssigned = dateAssigned;
				this.accountedFor = true;
				this.reasonAssigned = reasonAssigned;
				this.dateAssigned = new Date().toString();
				this.dateReturned = "";
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
	
	/**
	 * Archives the current state of the key into the key history array
	 */
	private void archive()
	{
		String entry = assignedTo+","+assignedToJobTitle+","+employeeID+","+authorizedBy+","+authorizedByTitle+","+dateAssigned+","+dateReturned+","+reasonAssigned;
		keyHistory.add(entry);
	}

	
	/**
	 * Changes the state of the key to available for issue.
	 */
	public void returnKey()
	{
		this.dateReturned = new Date().toString();
		this.isavailableForIssue = true;
		archive();
	}
	
	public int getKeyID()
	{
		return this.keyID;
	}
	
	public boolean getIsPhysicalKey()
	{
		return isPhysicalKey;
	}

	public boolean getIsMasterKey()
	{
		return isMasterKey;
	}

	public boolean getIsAvailableForIssue()
	{
		return isavailableForIssue;
	}

	public String getKeyType()
	{
		return keyType;
	}
	
	public int getPin()
	{
		return pin;
	}
	
	public String getAssignedTo()
	{
		return assignedTo;
	}
	
	public String getEmployeeID()
	{
		return this.employeeID;
	}
	
 	public String getAssignedToJobTitle()
	{
		return assignedToJobTitle;
	}

	public String getDateAssigned()
	{
		return dateAssigned;
	}
	
	public String getDateReturned()
	{
		return dateReturned;
	}
	
	public String getReasonAssigned()
	{
		return reasonAssigned;
	}
	
	public String getEntryCreationDate()
	{
		return entryCreationDate;
	}
	
	public String locksmithName()
	{
		return locksmithName;
	}
	
	public boolean getAccountedFor()
	{
		return accountedFor;
	}
	
	public String getDateLost()
	{
		return dateLost;
	}
	
	public String getAuthorizedBy()
	{
		return this.authorizedBy;
	}
	
	public String getAuthorizedByTitle()
	{
		return this.authorizedByTitle;
	}
	
	public String getLocksmithName()
	{
		return this.locksmithName;
	}

	public String getReasonLost()
	{
		return this.reasonLost;
	}
	
	public ArrayList<String> getKeyHistory()
	{
		return keyHistory;
	}
	
	public ArrayList<String> getOpensDoors()
	{
		return opensDoors;
	}
	
	public void setKeyID(int keyID)
	{
		this.keyID = keyID;
	}
	
	public void setIsPhysical(boolean isPhysical)
	{
		this.isPhysicalKey = isPhysical;
	}
	
	public void setIsMasterKey(boolean isMasterKey)
	{
		this.isMasterKey = isMasterKey;
	}
	
	public void setIsAvailableForIssue(boolean isAvailableForIssue)
	{
		this.isavailableForIssue = isAvailableForIssue;
	}
	
	public void setKeyType(String keyType)
	{
		this.keyType = keyType;
	}
	
	public void setPin(int pin)
	{
		this.pin = pin;
	}
	
	public void setAssignedTo(String assignedTo)
	{
		this.assignedTo = assignedTo;
	}
	
	public void setAssignedToJobTitle(String assignedToJobTitle)
	{
		this.assignedToJobTitle = assignedToJobTitle;
	}
	
	public void setDateAssigned(String dateAssigned)
	{
		this.dateAssigned = dateAssigned;
	}
	
	public void setDateReturned(String dateReturned)
	{
		this.dateReturned = dateReturned;
	}
	
	public void setReasonAssigned(String reasonAssigned)
	{
		this.reasonAssigned = reasonAssigned;
	}
	
	public void setLocksmithName(String locksmithName)
	{
		this.locksmithName = locksmithName;
	}
	
	public void setAccountedFor(boolean accountedFor)
	{
		this.accountedFor = accountedFor;
	}
	
	public void setDateLost(String dateLost)
	{
		this.dateLost = dateLost;
	}
	
	public void setEmployeeID(String employeeID)
	{
		this.employeeID = employeeID;
	}
	
	public void setOpensDoors(ArrayList<String> opensDoors)
	{
		this.opensDoors = opensDoors;
	}
	
	public void setAuthorizedBy(String authorizedBy)
	{
		this.authorizedBy = authorizedBy;
	}

	
	/**
	 * Creates a string representation of the object. This representation can be used to parse the object into a new object using the key_obj(String toString) constructor.
	 */
	public String toString()
	{
		String toString = keyID+"*"+isPhysicalKey+"*"+isMasterKey+"*"+isavailableForIssue+"*"+keyType+"*"+pin+"*"+assignedTo+"*"+
				assignedToJobTitle+"*"+employeeID+"*"+authorizedBy+"*"+authorizedByTitle+"*"+dateAssigned+"*"+dateReturned+"*"+reasonAssigned+"*"+
				entryCreationDate+"*"+locksmithName+"*"+accountedFor+"*"+reasonLost+"*"+dateLost+"*";
		for(int i = 0; i < keyHistory.size(); i++)
		{
			toString = toString+keyHistory.get(i).toString()+"#";
		}
		
		for(int i = 0; i < opensDoors.size(); i++)
		{
			toString = toString+opensDoors.get(i).toString()+"&";
		}
		
		return toString;
	}

}
