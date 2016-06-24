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
 * Created by admin on 16/6/2.
 */
public class iOSWXWebTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findWebAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Web {
        TC_Web_Event,TC_Web_Style,TC_Web_Update;
    }

    @Test
    public void testScreen_TC_Web_Style_Test() throws Exception {
        app.tableCell(By.index(Web.TC_Web_Style.ordinal())).tap();
        waitAfterTap();
        SleepUtils.sleep(8000);

        screenshot("TC", "Web", "Style");
    }

}
