package com.w2.testcases;
import com.utilities.Utilities;
import com.w2a.basepackage.Page;
import com.w2a.pages.RegistrationPage;
import org.testng.annotations.Test;

public class Registration extends Page{

    RegistrationPage rp=new RegistrationPage();
        Utilities util=new Utilities();
        String gmailid = util.random(15) + "@gmail.com";

    @Test(priority = 0)
    public void registernewuser() {
        rp.setmailId(gmailid);
        rp.createaccount();
    }

    @Test(priority = 1)
    public void fillalldetails()
    {
        rp.fillalldetails();
    }
    @Test(priority = 2)
    public void registeruser()
    {
        rp.registeruser();
    }


}
