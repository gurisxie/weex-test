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
public class iOSWXStyleTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findStyleAndTap();
    }
    @After
    public void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Style {
        Style_AlignItems,Style_Border,Style_Border_Unilateral,Style_Flex,
        Style_JustifyContent,Style_MarginPadding,Style_MaxHeight,
        Style_MaxWidth,Style_MinHeight,Style_MinWidth,
        Style_Overflow,Style_Update;
    }

    @Test
    public void testScreen_TC_Style_AlignItems_Test() throws Exception {
        app.tableCell(By.index(Style.Style_AlignItems.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "AlignItems");
    }

    @Test
    public void testScreen_TC_Style_Border_Test() throws Exception {
        app.tableCell(By.index(Style.Style_Border.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "Border");
    }

    @Test
    public void testScreen_TC_Style_Border_Unilateral_Test() throws Exception {
        app.tableCell(By.index(Style.Style_Border_Unilateral.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "Border_Unilateral");
    }

    @Test
    public void testScreen_TC_Style_Flex_Test() throws Exception {
        app.tableCell(By.index(Style.Style_Flex.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "Flex");
    }

    @Test
    public void testScreen_TC_Style_JustifyContent_Test() throws Exception {
        app.tableCell(By.index(Style.Style_JustifyContent.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "JustifyContent");
    }

    @Test
    public void testScreen_TC_Style_MarginPadding_Test() throws Exception {
        app.tableCell(By.index(Style.Style_MarginPadding.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "MarginPadding");
    }

    @Test
    public void testScreen_TC_Style_MaxHeight_Test() throws Exception {
        app.tableCell(By.index(Style.Style_MaxHeight.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "MaxHeight");
    }

    @Test
    public void testScreen_TC_Style_MaxWidth_Test() throws Exception {
        app.tableCell(By.index(Style.Style_MaxWidth.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "MaxWidth");
    }

    @Test
    public void testScreen_TC_Style_MinHeight_Test() throws Exception {
        app.tableCell(By.index(Style.Style_MinHeight.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "MinHeight");
    }

    @Test
    public void testScreen_TC_Style_MinWidth_Test() throws Exception {
        app.tableCell(By.index(Style.Style_MinWidth.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "MinWidth");
    }

    @Test
    public void testScreen_TC_Style_Overflow_Test() throws Exception {
        app.tableCell(By.index(Style.Style_Overflow.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Style", "Overflow");
    }

//    @Test
//    public void testScreen_TC_Style_Update_Test() throws Exception {
//        app.tableCell(By.index(Style.Style_Update.ordinal())).tap();
//        waitAfterTap();
//
//        screenshot("TC", "Style", "Update");
//    }
}
