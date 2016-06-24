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
public class iOSWXColorTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findColorAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Color {
        Color_Type,Color_Update;
    }

    @Test
    public void testScreen_TC_Color_Type_Test() throws Exception {
        app.tableCell(By.index(Color.Color_Type.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Color", "Type");
    }

     @Test
     public void testScreen_TC_Color_Update_Test() throws Exception {
         app.tableCell(By.index(Color.Color_Update.ordinal())).tap();
         waitAfterTap();

         screenshot("TC", "Color", "Update_01_beforeClick");

         app.staticText(By.index(3)).tap();
         waitAfterTap();
         app.staticText(By.index(5)).tap();
         waitAfterTap();
         app.staticText(By.index(6)).tap();
         waitAfterTap();
         app.staticText(By.index(7)).tap();
         waitAfterTap();
         app.staticText(By.index(8)).tap();
         waitAfterTap();
         app.staticText(By.index(9)).tap();
         waitAfterTap();
         app.staticText(By.index(10)).tap();
         waitAfterTap();

         screenshot("TC", "Color", "Update_02_afterClick");
     }
}
