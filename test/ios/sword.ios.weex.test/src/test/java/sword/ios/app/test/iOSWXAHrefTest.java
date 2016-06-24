package sword.ios.app.test;

import com.taobao.sword.client.utils.ScreenCaptureUtils;
import com.taobao.sword.ios.object.By;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import sword.ios.app.utils.CIParams;
import sword.ios.app.utils.SleepUtils;

/**
 * Created by admin on 16/4/20.
 */
public class iOSWXAHrefTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findAHrefAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum AHRef {
        TC_AHref_Event,TC_AHref_Style,TC_AHref_Update;
    }

    @Test
    public void testScreen_TC_AHref_Event_Test() throws Exception {
        SleepUtils.sleep(10000);//first tc,wait for simulator ready
        app.tableCell(By.index(AHRef.TC_AHref_Event.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Event_01_init");

        app.staticText(By.index(3)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Event_02_afterClick");
    }

    @Test
    public void testScreen_TC_AHref_Style_Test() throws Exception {
        app.tableCell(By.index(AHRef.TC_AHref_Style.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Style");
    }

    @Test
    public void testScreen_TC_AHref_Update_Test() throws Exception {
        app.tableCell(By.index(AHRef.TC_AHref_Update.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_01_init");

        app.staticText(By.index(2)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_02_changeHref");

        app.staticText(By.index(3)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_03_changeWH");

        app.staticText(By.index(4)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_04_changeBG");

        app.staticText(By.index(5)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_05_changeTitle");

        app.staticText(By.index(6)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_06_changeMargin");

        app.staticText(By.index(7)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_07_changeBorder");

        app.staticText(By.index(8)).tap();
        waitAfterTap();

        screenshot("TC", "AHref", "Update_08_changePadding");

    }
}
