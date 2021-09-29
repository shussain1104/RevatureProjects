package NewUserTest;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.NewUser;

public class NewUserTest {
	NewUser user = new NewUser(0,0,"test","test","test","test","test");
	 @Test
	    public void testNewUser1(){
	        NewUser user = new NewUser();
	    }
	 @Test //Tests to see if username returns correctly
	    public void testGetID(){
	        user.setId(0);
	        assertEquals(0, user.getId());
	    }
	    @Test //Tests to see if username returns correctly
	    public void testGetUsername(){
	        user.setUsername("Username1");
	        assertEquals("Username1", user.getUsername());
	    }
	    @Test //Tests to see if password returns correctly
	    public void testGetPassword(){
	        user.setPassword("Password1");
	        assertEquals("Password1", user.getPassword());
	    }
	    @Test //Tests to see if email returns correctly
	    public void testGetEmail(){
	        user.setEmail("emailTester");
	        assertEquals("emailTester", user.getEmail());
	    }
	    @Test
	    public void testGetFirstname(){
	        user.setFirstname("Fname1");
	        assertEquals("Fname1", user.getFirstname());
	    }
	    @Test //Tests to see if lastName returns correctly
	    public void testGetLName(){
	        user.setLastname("Lname1");
	        assertEquals("Lname1", user.getLastname());
	    }
	    @Test //Pin is returned correctly?
	    public void testAccountNumber()
	    {
	    	user.setAccountnumber(123456789);
	    	assertEquals(123456789,user.getAccountnumber());
	    }
}
