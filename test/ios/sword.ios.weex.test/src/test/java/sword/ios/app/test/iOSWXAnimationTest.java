package sword.ios.app.test;

import com.taobao.sword.ios.object.By;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sword.ios.app.utils.SleepUtils;

/**
 * Created by admin on 16/4/20.
 */
public class iOSWXAnimationTest  extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findAnimationAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Animation {
        TC_Animation_Rotate,TC_Animation_Scale,TC_Animation_Shadow,
        TC_Animation_Transform,TC_Animation_Translate;
    }

    @Test
    public void testScreen_TC_Animation_Rotate_Test() throws Exception {
        app.tableCell(By.index(Animation.TC_Animation_Rotate.ordinal())).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Rotate_01_init");

        app.staticText(By.name("Click Me To Rotate")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Rotate_02_Turn90");

        app.staticText(By.name("Click Me To Rotate")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Rotate_03_Turn180");

        app.staticText(By.name("Click Me To Rotate")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Rotate_04_Turn270");

        app.staticText(By.name("Click Me To Rotate")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Rotate_05_Turn360");
    }

    @Test
    public void testScreen_TC_Animation_Scale_Test() throws Exception {
        app.tableCell(By.index(Animation.TC_Animation_Scale.ordinal())).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Scale_01_init");

        app.staticText(By.name("Click Me To Scale")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Scale_02_large");

        app.staticText(By.name("Click Me To Scale")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Scale_03_smaller");
    }

    @Test
    public void testScreen_TC_Animation_Shadow_Test() throws Exception {
        app.tableCell(By.index(Animation.TC_Animation_Shadow.ordinal())).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Shadow_01_init");

        app.staticText(By.name("Click Me To Opacity")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Shadow_02_opacityLow");

        app.staticText(By.name("Click Me To Opacity")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Shadow_03_opacityHigh");
    }

    @Test
    public void testScreen_TC_Animation_Transform_Test() throws Exception {
        app.tableCell(By.index(Animation.TC_Animation_Transform.ordinal())).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Transform_01_init");

        app.staticText(By.name("Click Me To Transform")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Transform_02_show");

        app.staticText(By.name("Click Me To Transform")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Transform_03_origin");
    }

    @Test
    public void testScreen_TC_Animation_Translate_Test() throws Exception {
        app.tableCell(By.index(Animation.TC_Animation_Translate.ordinal())).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Translate_01_init");

        app.staticText(By.name("Click Me To Translate")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Translate_02_Translate");

        app.staticText(By.name("Click Me To TranslateX")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Translate_03_TranslateX");

        app.staticText(By.name("Click Me To TranslateY")).tap();
        waitAfterTap();
        screenshot("TC", "Animation", "Translate_04_TranslateY");
    }
}
