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
public class iOSWXSwitchTest  extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findSwitchAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Switch {
        TC_Switch_Event,TC_Switch_Style,TC_Switch_Style_Layout,TC_Switch_Update;
    }

    @Test
    public void testScreen_TC_Switch_Style_Test() throws Exception {
        app.tableCell(By.index(Switch.TC_Switch_Style.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Switch", "Style");
    }

    @Test
    public void testScreen_TC_Switch_Style_Layout_Test() throws Exception {
        app.tableCell(By.index(Switch.TC_Switch_Style_Layout.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Switch", "Style_Layout");
    }


}
