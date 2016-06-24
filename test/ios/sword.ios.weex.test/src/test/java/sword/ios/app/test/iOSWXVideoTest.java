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
public class iOSWXVideoTest  extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findVideoAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Video {
        TC_Video_Event,TC_Video_Style,TC_Video_Update;
    }

    @Test
    public void testScreen_TC_Video_Event_Test() throws Exception {
        SleepUtils.sleep(10000);//WAIT FOR VIDEO LOADED

        app.tableCell(By.index(Video.TC_Video_Event.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Video", "Event_01_init");
        //TODO click event

        app.staticText(By.name("SetPlay")).tap();
        waitAfterTap();
        screenshot("TC", "Video", "Event_02_Play");

        SleepUtils.sleep(1000);
        app.staticText(By.name("SetPause")).tap();
        waitAfterTap();
        screenshot("TC", "Video", "Event_03_Pause");

        app.staticText(By.name("SetPlay")).tap();
        SleepUtils.sleep(8000);
        screenshot("TC", "Video", "Event_04_Finish");
    }

    @Test
    public void testScreen_TC_Video_Style_Test() throws Exception {
        SleepUtils.sleep(10000);//WAIT FOR VIDEO LOADED

        app.tableCell(By.index(Video.TC_Video_Style.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Video", "Style");
    }
}


