package sword.ios.app.test;

import com.taobao.sword.client.utils.ScreenCaptureUtils;
import com.taobao.sword.ios.object.By;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import sword.ios.app.utils.CIParams;

/**
 * Created by admin on 16/4/20.
 */
public class iOSWXSliderTest extends iOSWeexTest {
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findSliderAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Slider{
        TC_Slider_Event,TC_Slider_Style,TC_Slider_Style_Indicator,TC_Slider_Update;
    }

    @Test
    public void testScreen_TC_Slider_Style_Test() throws Exception{
        app.tableCell(By.index(Slider.TC_Slider_Style.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Slider", "Style");
    }

    @Test
    public void testScreen_TC_Slider_Style_Indicator_Test() throws Exception{
        app.tableCell(By.index(Slider.TC_Slider_Style_Indicator.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Slider", "Style_Indicator");
    }
}
