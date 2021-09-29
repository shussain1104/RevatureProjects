package AccountInfoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.AccountInfo;

public class AccountInfoTest {
	AccountInfo account = new AccountInfo(123456789,1234);
	@Test
	 public void testAccount(){
        AccountInfo account = new AccountInfo();
    }
	@Test
	public void testGetAccountNum()
	{
		account.setAccountNumber(123456789);
		assertEquals(123456789,account.getAccountnumber());
	}
 
	@Test
    public void testGetBalance()
    {
    	account.setBalance(200.78);
    	assertEquals(200.78 , account.getBalance(),.00001);
    }
}
