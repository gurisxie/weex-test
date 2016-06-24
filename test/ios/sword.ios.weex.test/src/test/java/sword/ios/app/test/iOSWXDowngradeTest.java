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
 * Created by admin on 16/3/30.
 */
public class iOSWXDowngradeTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findDowngradeAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Downgrade {
        Downgrade_appV_False, Downgrade_appV_True,
        Downgrade_devM_False, Downgrade_devM_True,
        Downgrade_osV_False,Downgrade_osV_True,
        Downgrade_weexV_False,Downgrade_weexV_True;
    }

    @Test
    public void testScreen_TC_Downgrade_appV_False_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_appV_False.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Downgrade", "appV_False");
    }

    @Test
    public void testScreen_TC_Downgrade_appV_True_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_appV_True.ordinal())).tap();
        waitAfterTap();
        SleepUtils.sleep(1000);

        String base64 = app.captureScreen();
        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
                CIParams.getPicSaveDir()+CIParams.imageNameForCI("TC", "Downgrade", "appV_True"));
        Assert.assertNotNull(imageFile);
    }

    @Test
    public void testScreen_TC_Downgrade_devM_False_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_devM_False.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Downgrade", "devM_False");
    }

    @Test
    public void testScreen_TC_Downgrade_devM_True_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_devM_True.ordinal())).tap();
        waitAfterTap();
        SleepUtils.sleep(1000);

        String base64 = app.captureScreen();
        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
                CIParams.getPicSaveDir()+CIParams.imageNameForCI("TC", "Downgrade", "devM_True"));
        Assert.assertNotNull(imageFile);
    }

    @Test
    public void testScreen_TC_Downgrade_osV_False_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_osV_False.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Downgrade", "osV_False");
    }

    @Test
    public void testScreen_TC_Downgrade_osV_True_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_osV_True.ordinal())).tap();
        waitAfterTap();
        SleepUtils.sleep(1000);
        
        String base64 = app.captureScreen();
        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
                CIParams.getPicSaveDir()+CIParams.imageNameForCI("TC", "Downgrade", "osV_True"));
        Assert.assertNotNull(imageFile);
    }

    @Test
    public void testScreen_TC_Downgrade_weexV_False_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_weexV_False.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Downgrade", "weexV_False");
    }

    @Test
    public void testScreen_TC_Downgrade_weexV_True_Test() throws Exception {
        app.tableCell(By.index(Downgrade.Downgrade_weexV_True.ordinal())).tap();
        waitAfterTap();
        SleepUtils.sleep(1000);

        String base64 = app.captureScreen();
        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
                CIParams.getPicSaveDir()+CIParams.imageNameForCI("TC", "Downgrade", "weexV_True"));
        Assert.assertNotNull(imageFile);

    }
}
