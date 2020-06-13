import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SILab2 {
	
	public static void main(String args[]) throws RuntimeException {
		
		
	}
	

    public static boolean function (User user, List<String> allUsers) throws RuntimeException {
        String specialCharacters="!#$%&'()*+,-./:;<=>?@[]^_`{|}";//1
        if (user==null) {//2
				throw new RuntimeException("The user is not instantiated");//3			
        }//3
        if (user.getUsername()==null || user.getPassword()==null)//4
				throw new RuntimeException("The user is missing some mandatory information");//5
        String password = user.getPassword();//6
        String passwordLower = password.toLowerCase();//6;
        if (passwordLower.contains(user.getUsername().toLowerCase())) {//7
            return false;//8
        }//8
        else if (passwordLower.length()<8)//9
            return false;//10
        else {//11
            boolean digit = false, upper = false, special = false;//11

            for (int i=0;i<password.length();i++) {//12
                if (Character.isDigit(password.charAt(i)))//13
                    digit = true;//14
                if (Character.isUpperCase(password.charAt(i)))//15
                    upper = true;//16
                if (specialCharacters.contains(String.valueOf(password.charAt(i))))//17
                    special = true;//18
            }//19
            if (!digit || !upper || !special)//20
                return false;//21
        }
        return true;
    }//22
}

class User {
    String username;
    String password;
    String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}


class RuntimeException extends Exception{

	public RuntimeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RuntimeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RuntimeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RuntimeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}

class SILab2Test{
	
	public void testEveryPath() throws RuntimeException {
		//Testiranje spored every path 
				//System.out.print(function(null,null));
				SILab2  u=new SILab2();
				RuntimeException ex;
				ex= Assertions.assertThrows(RuntimeException.class, ()-> u.function(null,null));
				Assertions.assertTrue(ex.getMessage().equals("The user is not instantiated"));
				//System.out.println(function(new User(null,"savev","aaaa"),null));
				ex= Assertions.assertThrows(RuntimeException.class, ()-> u.function(new User(null,"savev","aaaa"),null));
				Assertions.assertTrue(ex.getMessage().equals("The user is missing some mandatory information"));
				//System.out.println(function(new User("nikolasavev","savev","aaa"),null));
				Assertions.assertTrue(u.function(new User("nikolasavev","savev","aaa"),null)==false);
				//System.out.println(function(new User("nikola","asd","aaaa"),null));
				Assertions.assertTrue(u.function(new User("nikola","asd","aaaa"),null)==false);
				//System.out.println(function(new User("nikola","savevsavev","aaaa"),null));
				Assertions.assertTrue(u.function(new User("nikola","savevsavev","aaaa"),null)==false);
				//System.out.println(function(new User("nikola","TeglaAjavar!11@3","aaaa"),null));
				Assertions.assertTrue(u.function(new User("nikola","TeglaAjavar!11@3","aaaa"),null)==true);
	}
	
	public void testEveryBranch() throws RuntimeException {
		//Testiranje spored every branch
		SILab2  u=new SILab2();
		RuntimeException ex;
		ex= Assertions.assertThrows(RuntimeException.class, ()-> u.function(null,null));
		Assertions.assertTrue(ex.getMessage().equals("The user is not instantiated"));
		//(1,2) (2,3) (3,22)
		ex= Assertions.assertThrows(RuntimeException.class, ()-> u.function(new User(null,"savev","aaaa"),null));
		Assertions.assertTrue(ex.getMessage().equals("The user is missing some mandatory information"));
		//(1,2) (2,4) (4,5) (5,22)
		Assertions.assertTrue(u.function(new User("nikolasavev","savev","aaa"),null)==false);
		//(1,2) (2,4) (4,6) (6,7) (7,8) (8,22)
		Assertions.assertTrue(u.function(new User("nikola","asd","aaaa"),null)==false);
		//(1,2) (2,4) (4,6) (6,7) (7,9) (9,10) (10,22)
		Assertions.assertTrue(u.function(new User("nikola","savevsavev","aaaa"),null)==false);
		//(1,2) (2,4) (4,6) (6,7) (7,9) (9,11) (11, 12.1) (12.1 12.2) (12.2 22) (12.2 13) (12.3 12.2) (13 ,15) (15,17)...
		Assertions.assertTrue(u.function(new User("nikola","TeglaAjavar!11@3","aaaa"),null)==true);
	}
	
	
}

