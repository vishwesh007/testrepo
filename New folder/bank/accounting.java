package bank;


class Account{
    public String name;
    protected String email;
    private String password;
    public String getPassword(){
        return password;
    }
    public void setPassword(String pass){


        this.password= pass;
    }
}

public class Bank{


    public static void main(String[] args) {
        Account account1= new Account();
        account1.name="apna college";

    }
}