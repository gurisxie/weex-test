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
 * Created by admin on 16/3/30.
 */
public class iOSWXEventTest extends iOSWeexTest {
//    @Before
//    public void startDriver() throws Exception {
//        super.startDriver();
//    }
//
//    @After
//    public  void stopDriver() throws Exception{
//        super.stopDriver();
//    }
//
//    @Test
//    public void testScreen_TC_Event_ElementOpt_Test() throws Exception{
//        findEvent_ElementOptAndTap();
//
//        String base64 = app.scrollView(By.index(0)).captureScreen();
//        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
//                CIParams.getPicSaveDir()+"TC_Event_ElementOpt_01_init.png");
//        Assert.assertNotNull(imageFile);
//
//        //click to add a can-add element
//        app.staticText(By.index(3)).tap();
//
//        //click to add a from added text
//        app.staticText(By.index(3)).tap();
//
//        //click to add a can-remove element
//        app.staticText(By.index(6)).tap();
//
//        //click to add a can-remove element
//        app.staticText(By.index(6)).tap();
//        base64 = app.scrollView(By.index(0)).captureScreen();
//        imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
//                CIParams.getPicSaveDir()+"TC_Event_ElementOpt_02_addElements.png");
//        Assert.assertNotNull(imageFile);
//
//        //remove
//        app.staticText(By.index(8)).tap();
//        base64 = app.scrollView(By.index(0)).captureScreen();
//        imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
//                CIParams.getPicSaveDir()+"TC_Event_ElementOpt_03_removeElement.png");
//        Assert.assertNotNull(imageFile);
//
//        app.staticText(By.index(9)).tap();
//        app.staticText(By.index(11)).tap();
//        app.staticText(By.index(12)).tap();
//        app.staticText(By.index(13)).tap();
//
//        base64 = app.scrollView(By.index(0)).captureScreen();
//        imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
//                CIParams.getPicSaveDir()+"TC_Event_ElementOpt_04_finish.png");
//        Assert.assertNotNull(imageFile);
//    }
//
//    public void findEvent_ElementOptAndTap() throws Exception{
//        findEventAndTap();
//        app.tableCell(By.index(CIParams.adjustIndex(0))).tap();
//    }
}
