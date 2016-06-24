package sword.ios.app.test;

import com.taobao.sword.ios.object.By;
import com.taobao.sword.ios.object.Rect;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sword.ios.app.utils.SleepUtils;

/**
 * Created by admin on 16/6/13.
 */
public class iOSWXGestureTest extends iOSWeexTest{
    @Before
    public void startDriver() throws Exception {
        super.startDriver();
        findGestureAndTap();
    }
    @After
    public  void stopDriver() throws Exception{
        super.stopDriver();
    }

    enum Gesture {
        TC_Gesture_Click,TC_Gesture_Conflict,TC_Gesture_LongPress,
        TC_Gesture_Pan,TC_Gesture_Swipe,TC_Gesture_Touch;
    }

    @Test
    public void testScreen_TC_Gesture_Click_Test() throws Exception {
        app.tableCell(By.index(Gesture.TC_Gesture_Click.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Gesture", "Click_01_init");

        app.staticText(By.name("Click me")).tap();
        waitAfterTap();

        screenshot("TC", "Gesture", "Click_02_afterClick");
    }

    @Test
    public void testScreen_TC_Gesture_LongPress_Test() throws Exception {
        app.tableCell(By.index(Gesture.TC_Gesture_LongPress.ordinal())).tap();
        waitAfterTap();

        screenshot("TC", "Gesture", "LongPress_01_init");

        app.staticText(By.name("Long Press ME")).touch(2);
        waitAfterTap();

        screenshot("TC", "Gesture", "LongPress_02_afterLongPress");
    }

    //oh no,dragInsideWithOptions and dragFromToForDuration is busted
//    @Test
//    public void testScreen_TC_Gesture_Swipe_Test() throws Exception {
//        app.tableCell(By.index(Gesture.TC_Gesture_Swipe.ordinal())).tap();
//        waitAfterTap();
//        screenshot("TC", "Gesture", "Swipe_01_init");
//
//        Rect rect=app.staticText(By.name("swipe me")).getRect();
//        float centerX= rect.getOrigin().getX()+rect.getSize().getWidth()/2;
//        float centerY= rect.getOrigin().getY()+rect.getSize().getHeight()/2;
//
//        float leftX=rect.getOrigin().getX()+rect.getSize().getWidth()/4;
//        float rightX=rect.getOrigin().getX()+rect.getSize().getWidth()*3/4;
//        float topY=rect.getOrigin().getY()+rect.getSize().getHeight()/4;
//        float bottomY=rect.getOrigin().getY()+rect.getSize().getHeight()*3/4;
//        System.out.println(leftX + " " + centerY + " " + rightX + " " + centerY);
//
//        //driver.drag(leftX, centerY, rightX, centerY, 3);
//
//        app.staticText(By.name("swipe me")).drag((float)0.1, (float)0.5, (float)0.5, (float)0.5, 3);
//
//    }
}
