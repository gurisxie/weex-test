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
public class iOSWXImageTest extends iOSWeexTest {
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findImageAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Image{
        TC_Image_Event,TC_Image_Resize,TC_Image_Style,TC_Image_Update;
    }

    @Test
    public void testScreen_TC_Image_Resize_Test() throws Exception{
        app.tableCell(By.index(Image.TC_Image_Resize.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Image", "Resize");
    }

    @Test
    public void testScreen_TC_Image_Style_Test() throws Exception{
        app.tableCell(By.index(Image.TC_Image_Style.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Image", "Style");
    }


    @Test
    public void testScreen_TC_Image_Update_Test() throws Exception{
        app.tableCell(By.index(Image.TC_Image_Update.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Image", "Update_01_init");

        for (int i = 2; i < 16; i++) {
            app.staticText(By.index(i)).tap();
            waitAfterTap();

            String suffix = PIC_IMAGE_NAME.values()[i-2].toString().replace("TC_Image_","");
            screenshot("TC", "Image", suffix);
        }
    }

    enum PIC_IMAGE_NAME{
        TC_Image_Update_02_errorSrc,
        TC_Image_Update_03_changeSrc,
        TC_Image_Update_04_normalSrc,
        TC_Image_Update_05_notTransparent,
        TC_Image_Update_06_transparent,
        TC_Image_Update_07_largeWidth,
        TC_Image_Update_08_smallWidth,
        TC_Image_Update_09_largeHeight,
        TC_Image_Update_10_smallHeight,
        TC_Image_Update_11_largeScale,
        TC_Image_Update_12_smallScale,
        TC_Image_Update_13_recover,
        TC_Image_Update_14_cover,
        TC_Image_Update_15_contain,
    }
}
