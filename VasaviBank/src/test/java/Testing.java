import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.vasavibank.main.dao.CustomerDao;

public class Testing {
	
	@Test
	public void testValidateLogin() throws SQLException {
		CustomerDao c=new CustomerDao();
		String email="lokesh@gmail.com";
		String pswd="7894";
		int status=c.validateLogin(email, pswd);
		assertEquals(11, status);
	}
	
	}
	


