package com.w2.testcases;

import com.utilities.Utilities;
import com.w2a.basepackage.Page;
import com.w2a.pages.RegistrationPage;
import org.testng.annotations.Test;

public class RegistrationNegativeusecases extends Page {
    RegistrationPage rp=new RegistrationPage(driver);
    Utilities util=new Utilities();
    String gmailid = util.random(15) + "@gmail.com";

    @Test(priority = 5)
    public void createaccountwithoutemail() {
        rp.createaccountwithoutmail();
    }

    @Test(priority = 6)
    public void createaccountwithexistingemail()
    {
        rp.createaccountwithexistingemail();
    }

    @Test(priority=7)
    public void createaccountwithoutfirstname()
    {
        rp.setmailId(gmailid);
        rp.createaccountwithoutfirstname();
    }
    @Test(priority=7)
    public void createaccountwithoutlastname()
    {
        rp.setmailId(gmailid);
        rp.createaccountwithoutlastname();
    }
    @Test(priority=8)
    public void createaccountwithoutpassword()
    {
        rp.createaccountwithoutpassword();
    }
    @Test(priority=9)
    public void createaccountwithoutaddress()
    {
        rp.createaccountwithoutaddress();
    }
    @Test(priority=10)
    public void createaccountwithoutpostalcode()
    {
        rp.createaccountwithoutpostalcode();
    }

}
