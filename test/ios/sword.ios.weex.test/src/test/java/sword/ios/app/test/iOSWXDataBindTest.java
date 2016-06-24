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
public class iOSWXDataBindTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findDataBindAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum DataBind {
        TC_DataBind_AddComponent,TC_DataBind_ChildUpdateWhenPU,TC_DataBind_DataChange,
        TC_DataBind_DotType,TC_DataBind_FuncType,TC_DataBind_If,TC_DataBind_ListPushInAsync,
        TC_DataBind_ListPushInSync,TC_DataBind_RefreshInstance,TC_DataBind_RemoveComponent,
        TC_DataBind_Repeat,TC_DataBind_TrackBy,TC_DataBind_UpdateClass,TC_DataBind_UpdateStyle;
    }

    @Test
    public void testScreen_TC_DataBind_AddComponent_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_AddComponent.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "AddComponent_01_init");

        app.staticText(By.index(3)).tap();
        waitAfterTap();
        app.staticText(By.index(5)).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "AddComponent_02_afterClick");
    }

    @Test
    public void testScreen_TC_DataBind_ChildUpdateWhenPU_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_ChildUpdateWhenPU.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "ChildUpdateWhenPU_01_init");

        app.staticText(By.name("Toggle")).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "ChildUpdateWhenPU_02_afterClick");
    }

    @Test
    public void testScreen_TC_DataBind_DataChange_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_DataChange.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "DataChange_01_init");

        app.staticText(By.name("refresh instance")).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "DataChange_02_afterClick");
    }

    @Test
    public void testScreen_TC_DataBind_DotType_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_DotType.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "DotType");
    }

    @Test
    public void testScreen_TC_DataBind_FuncType_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_FuncType.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "FuncType");
    }

    @Test
    public void testScreen_TC_DataBind_If_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_If.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "If_01_init");

        app.staticText(By.name("Toggle in scroller")).tap();
        waitAfterTap();

        app.staticText(By.name("Toggle in List")).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "If_02_afterClick");
    }

    @Test
    public void testScreen_TC_DataBind_ListPushInAsync_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_ListPushInAsync.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "ListPushInAsync");
    }
    @Test
    public void testScreen_TC_DataBind_ListPushInSync_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_ListPushInSync.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "ListPushInSync");
    }
    @Test
    public void testScreen_TC_DataBind_RefreshInstance_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_RefreshInstance.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "RefreshInstance_01_init");

        app.staticText(By.index(3)).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "RefreshInstance_02_afterClick");
    }

    @Test
    public void testScreen_TC_DataBind_RemoveComponent_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_RemoveComponent.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "RemoveComponent_01_init");

        app.staticText(By.index(3)).tap();
        waitAfterTap();
        app.staticText(By.index(3)).tap();
        waitAfterTap();
        app.staticText(By.index(5)).tap();
        waitAfterTap();
        app.staticText(By.index(5)).tap();
        waitAfterTap();
        app.staticText(By.index(5)).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "RemoveComponent_02_afterClick");
    }

    @Test
    public void testScreen_TC_DataBind_Repeat_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_Repeat.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "Repeat");
    }

    @Test
    public void testScreen_TC_DataBind_UpdateClass_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_UpdateClass.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "UpdateClass_01_init");

        app.staticText(By.index(3)).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "UpdateClass_02_afterClick");

    }

    @Test
    public void testScreen_TC_DataBind_UpdateStyle_Test() throws Exception {
        app.tableCell(By.index(DataBind.TC_DataBind_UpdateStyle.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "UpdateStyle_01_init");

        app.staticText(By.index(3)).tap();
        waitAfterTap();

        screenshot("TC", "DataBind", "UpdateStyle_02_afterClick");
    }

}
