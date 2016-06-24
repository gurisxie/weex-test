package sword.ios.app.test;

import com.taobao.sword.client.utils.ScreenCaptureUtils;
import com.taobao.sword.ios.manager.IOSRemoteDriver;
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
public class iOSWXInputTest extends iOSWeexTest {
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findInputAndTap();
    }

    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Input{
        TC_Input_Event,TC_Input_Placeholder,TC_Input_Style,TC_Input_Style_Overflow,TC_Input_Type,TC_Input_Update;
    }

    @Test
    public void testScreen_TC_Input_Style_Test() throws Exception{
        app.tableCell(By.index(Input.TC_Input_Style.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Input", "Style");
    }

    @Test
    public void testScreen_TC_Input_Type_Test() throws Exception{
        app.tableCell(By.index(Input.TC_Input_Type.ordinal())).tap();
        waitAfterTap();
        screenshot("TC", "Input", "Type_01_init");

        app.textField(By.index(0)).tap();
        waitAfterTap();
        app.input("Hello Weex");
        waitAfterTap();
        screenshot("TC", "Input", "Type_02_text");

        app.secureTextField(By.index(0)).tap();
        waitAfterTap();
        app.input("Hello1234");
        waitAfterTap();
        screenshot("TC", "Input", "Type_03_pwd");

        app.textField(By.index(2)).tap();
        waitAfterTap();
        app.input("1234567890");
        waitAfterTap();
        screenshot("TC", "Input", "Type_03_tel");

        app.textField(By.index(4)).tap();
        waitAfterTap();
        app.input("1234567890@qq.com");
        waitAfterTap();
        screenshot("TC", "Input", "Type_04_email");

        app.textField(By.index(6)).tap();
        waitAfterTap();
        app.input("http://www.1234567890.com");
        waitAfterTap();
        screenshot("TC", "Input", "Type_05_url");

    }

    @Test
    public void testScreen_TC_Input_Placeholder_Test() throws Exception{
        app.tableCell(By.index(Input.TC_Input_Placeholder.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Input", "Placeholder");
    }

    @Test
    public void testScreen_TC_Input_Event_Test() throws Exception{
        app.tableCell(By.index(Input.TC_Input_Event.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Input", "Event_01_init");

        //CLICK
        app.textField(By.index(0)).tap();
        waitAfterTap();

        //input
        app.textField(By.index(2)).tap();
        waitAfterTap();
        app.input("input");
        waitAfterTap();
        dismissKeyboard();
        waitAfterTap();

        screenshot("TC", "Input", "Event_02_input");

        //change
        app.textField(By.index(4)).tap();
        waitAfterTap();
        app.input("change");
        waitAfterTap();
        app.textField(By.index(10)).tap();
        waitAfterTap();

        //focus
        app.textField(By.index(6)).tap();
        waitAfterTap();
        app.input("focus");
        waitAfterTap();

        //blur
        app.textField(By.index(8)).tap();
        waitAfterTap();
        app.input("blur");
        waitAfterTap();
        app.textField(By.index(10)).tap();
        waitAfterTap();
        dismissKeyboard();
        waitAfterTap();

        screenshot("TC", "Input", "Event_03_finish");
    }
}
