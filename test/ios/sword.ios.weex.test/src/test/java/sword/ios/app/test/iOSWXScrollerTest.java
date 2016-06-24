package sword.ios.app.test;

import com.taobao.sword.ios.object.By;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by admin on 16/4/20.
 */
public class iOSWXScrollerTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findScrollerAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Scroller {
        TC_Scroller_AsChild,TC_Scroller_AsRoot,TC_Scroller_DirectHorizontal,TC_Scroller_DirectVertical,TC_Scroller_Event_AsChild,TC_Scroller_Event_AsRoot,TC_Scroller_Event_Horizontal,TC_Scroller_Fixed_Bottom,TC_Scroller_Fixed_InDivAsChild,TC_Scroller_Fixed_Inherit,TC_Scroller_Fixed_Middle,TC_Scroller_Fixed_ScrollerAsChild,TC_Scroller_Fixed_Top,TC_Scroller_RefreshAndLoading,TC_Scroller_Sticky,TC_Scroller_Style,TC_Scroller_Style_SetHeightAsRoot,TC_Scroller_Update;
    }

    @Test
    public void testScreen_TC_Scroller_Event_AsChild_Test() throws Exception {
        app.tableCell(By.index(Scroller.TC_Scroller_Event_AsChild.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Scroller", "Event_AsChild_01_init");

        app.staticText(By.name("Placeholder For Layout")).tap();
        waitAfterTap();
        screenshot("TC", "Scroller", "Event_AsChild_02_click");

        app.staticText(By.name("Click To Bottom")).tap();
        waitAfterTap();
        screenshot("TC", "Scroller", "Event_AsChild_03_scrolltobottom");

        app.staticText(By.name("Click To Top OffsetY50")).tap();
        waitAfterTap();
        screenshot("TC", "Scroller", "Event_AsChild_04_scrolltotop_offsetY50");


    }


    @Test
    public void testScreen_TC_Scroller_Event_Horizontal_Test() throws Exception {
        app.tableCell(By.index(Scroller.TC_Scroller_Event_Horizontal.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Scroller", "Event_Horizontal_01_init");

        app.staticText(By.name("Placeholder For Layout")).tap();
        waitAfterTap();
        screenshot("TC", "Scroller", "Event_Horizontal_02_click");

        app.staticText(By.name("Click To Right")).tap();
        waitAfterTap();
        screenshot("TC", "Scroller", "Event_Horizontal_03_scrolltoright");

        app.staticText(By.name("Click To Left OffsetL50")).tap();
        waitAfterTap();
        screenshot("TC", "Scroller", "Event_Horizontal_04_scrolltoleft");


    }

}
