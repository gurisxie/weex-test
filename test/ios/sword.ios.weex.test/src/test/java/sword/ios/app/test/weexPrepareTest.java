package sword.ios.app.test;

import com.taobao.sword.client.utils.ScreenCaptureUtils;
import com.taobao.sword.ios.library.utils.LibraryUtils;
import com.taobao.sword.ios.object.By;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import sword.ios.app.utils.CIParams;
import sword.ios.app.utils.SleepUtils;

/**
 * Created by admin on 16/3/22.
 */
public class weexPrepareTest extends iOSWeexTest {

    @Before
    public void startDriver() throws Exception {
        super.startDriver();
    }

    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    @Test
    public void getPage_Test() throws Exception {
        SleepUtils.sleep(10000);
        captureOnScreen();
    }

//    @Test
//    public void testForCode() throws Exception {
//
//    }

    /*********************** 公共方法 *************************/
    public void captureOnScreen(String Path) throws Exception {
        LibraryUtils.createLibraryHtmlExt(driver.getApplication(), Path,"prepare");

    }

    public void captureOnScreen() throws Exception {
        captureOnScreen(new File("src/main/resources").getAbsolutePath());
    }

}
