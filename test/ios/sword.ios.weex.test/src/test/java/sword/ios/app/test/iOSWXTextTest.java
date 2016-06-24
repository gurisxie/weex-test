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
public class iOSWXTextTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findTextAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Text{
        TC_Text_Event,TC_Text_Font,TC_Text_Lineheight,TC_Text_Overflow,
        TC_Text_Style,TC_Text_Style_Column,TC_Text_Style_Own,
        TC_Text_Style_Row,TC_Text_Type,TC_Text_Update;
    }

    @Test
    public void testScreen_TC_Text_Style_Column_Test() throws Exception {
        app.tableCell(By.index(Text.TC_Text_Style_Column.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Text", "Style_Column_01_beforeClick");

        //click all text
        for (int i = 3; i <= 8; i++) {
            app.staticText(By.index(i)).tap();
            waitAfterTap();
        }
        for (int i = 10; i <= 13; i++) {
            app.staticText(By.index(i)).tap();
            waitAfterTap();
        }

        screenshot("TC", "Text", "Style_Column_02_afterClick");
    }

    @Test
    public void testScreen_TC_Text_Style_Row_Test() throws Exception {
        app.tableCell(By.index(Text.TC_Text_Style_Row.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Text", "Style_Row_01_beforeClick");

        //click all text
        for (int i = 4; i <= 19; i=i+3) {
            app.staticText(By.index(i)).tap();
            waitAfterTap();
        }
        for (int i = 23; i <= 32; i=i+3) {
            app.staticText(By.index(i)).tap();
            waitAfterTap();
        }

        screenshot("TC", "Text", "Style_Row_02_afterClick");
    }

    @Test
    public void testScreen_TC_Text_Type_Test() throws Exception {
        app.tableCell(By.index(Text.TC_Text_Type.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Text", "Type");
    }
}
