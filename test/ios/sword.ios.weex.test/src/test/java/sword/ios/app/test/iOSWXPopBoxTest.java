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
public class iOSWXPopBoxTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findPopBoxAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum PopBox {
        TC_PopBox_Alert,TC_PopBox_Confirm,TC_PopBox_Prompt,TC_PopBox_Toast;
    }

    @Test
    public void testScreen_TC_PopBox_Alert_Test() throws Exception {
        app.tableCell(By.index(PopBox.TC_PopBox_Alert.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Alert_01_init");

        app.staticText(By.name("okTitle default")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Alert_02_okTitle_default");
        app.element(By.name("OK")).tap();
        waitAfterTap();

        app.staticText(By.name("okTitle hello")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Alert_03_okTitle_hello");
        app.element(By.name("hello")).tap();
        waitAfterTap();

        app.staticText(By.name("much message")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Alert_04_much_message");
        app.element(By.name("much message")).tap();
        waitAfterTap();

        app.staticText(By.name("much okTitle")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Alert_05_much_okTitle");
        app.element(By.name("yes,ok button use so long info is not suitable,we just want to test it")).tap();
        waitAfterTap();

        app.staticText(By.name("message is null")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Alert_06_message_isnull");
        app.element(By.name("OK")).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Alert_07_finish");

    }

    @Test
    public void testScreen_TC_PopBox_Confirm_Test() throws Exception {
        app.tableCell(By.index(PopBox.TC_PopBox_Confirm.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Confirm_01_init");

        app.staticText(By.name("okTitle default")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_02_okTitle_default");
        app.element(By.name("cancel")).tap();
        waitAfterTap();

        app.staticText(By.name("cancelTitle default")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_03_cancelTitle_default");
        app.element(By.name("Hello")).tap();
        waitAfterTap();

        app.staticText(By.name("okTitle blank")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_04_okTitle_blank");
        app.element(By.name("cancel")).tap();
        waitAfterTap();

        app.staticText(By.name("cancelTitle blank")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_05_cancelTitle_blank");
        app.element(By.name("OK")).tap();
        waitAfterTap();

        app.staticText(By.name("message long")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_06_message_long");
        app.element(By.name("message long")).tap();
        waitAfterTap();

        app.staticText(By.name("okTitle long")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_07_okTitle_long");
        app.element(By.name("This is a long message that we can describe complicated info. however,we just want to test it")).tap();
        waitAfterTap();

        app.staticText(By.name("message blank")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Confirm_08_message_blank");
        app.element(By.name("OK")).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Confirm_09_finish");
    }

    @Test
    public void testScreen_TC_PopBox_Prompt_Test() throws Exception {
        app.tableCell(By.index(PopBox.TC_PopBox_Prompt.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Prompt_01_init");

        app.staticText(By.name("okTitle default")).tap();
        waitAfterTap();
        app.input("input then cancel");
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_02_okTitle_default");
        app.element(By.name("cancel")).tap();
        waitAfterTap();

        app.staticText(By.name("cancelTitle default")).tap();
        waitAfterTap();
        app.input("input then ok");
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_03_cancelTitle_default");
        app.element(By.name("OK")).tap();
        waitAfterTap();

        app.staticText(By.name("okTitle blank")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_04_okTitle_blank");
        app.element(By.name("Hello-Cancel")).tap();
        waitAfterTap();

        app.staticText(By.name("cancelTitle blank")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_05_cancelTitle_blank");
        app.element(By.name("Hello-OK")).tap();
        waitAfterTap();

        app.staticText(By.name("message long")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_06_message_long");
        app.element(By.name("message long")).tap();
        waitAfterTap();

        app.staticText(By.name("okTitle long")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_07_okTitle_long");
        app.element(By.name("This is a long message that we can describe complicated info. however,we just want to test it")).tap();
        waitAfterTap();

        app.staticText(By.name("message blank")).tap();
        waitAfterTap();
        screenAppShot("TC", "PopBox", "Prompt_08_message_blank");
        app.element(By.name("OK")).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Prompt_09_finish");
    }

    @Test
    public void testScreen_TC_PopBox_Toast_Test() throws Exception {
        app.tableCell(By.index(PopBox.TC_PopBox_Toast.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "PopBox", "Toast_01_init");

        app.staticText(By.name("duration default")).tap();
        waitAfterTap();
        SleepUtils.sleep(1500);
        screenAppShot("TC", "PopBox", "Toast_02_duration_default_show");
        SleepUtils.sleep(1500);
        screenAppShot("TC", "PopBox", "Toast_03_duration_default_hidden");

        app.staticText(By.name("duration custom")).tap();
        waitAfterTap();
        SleepUtils.sleep(4000);
        screenAppShot("TC", "PopBox", "Toast_04_duration_custom_show");
        SleepUtils.sleep(1500);
        screenAppShot("TC", "PopBox", "Toast_05_duration_custom_hidden");

        app.staticText(By.name("message long")).tap();
        waitAfterTap();
        SleepUtils.sleep(1000);
        screenAppShot("TC", "PopBox", "Toast_06_message_long");
        SleepUtils.sleep(500);

        app.staticText(By.name("message blank")).tap();
        waitAfterTap();
        SleepUtils.sleep(1000);
        screenAppShot("TC", "PopBox", "Toast_07_message_blank");
        SleepUtils.sleep(500);

        screenshot("TC", "PopBox", "Toast_08_finish");
    }
}
