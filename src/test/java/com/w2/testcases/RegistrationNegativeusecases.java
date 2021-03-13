package com.w2.testcases;

import com.utilities.Utilities;
import com.w2a.basepackage.Page;
import com.w2a.pages.RegistrationPage;
import org.testng.annotations.Test;

public class RegistrationNegativeusecases extends Page {
    RegistrationPage rp=new RegistrationPage();
    Utilities util=new Utilities();
    String gmailid = util.random(15) + "@gmail.com";

    @Test(priority = 5)
    public void createaccountwithoutemail() {
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
