package sword.ios.app.test;

import com.taobao.sword.client.utils.ScreenCaptureUtils;
import com.taobao.sword.ios.elements.IIOSApplication;
import com.taobao.sword.ios.elements.types.IIOSTextField;
import com.taobao.sword.ios.library.utils.LibraryUtils;
import com.taobao.sword.ios.manager.IIOSDriver;
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
 * Created by admin on 16/3/22.
 */
public class iOSWeexTest {

    protected static IIOSDriver driver;
    public static IIOSApplication app;

    @Before
    public void startDriver() throws Exception {
        driver = IOSRemoteDriver.start(CIParams.getTestAppPath(),
                CIParams.getDeviceUuid(), true);
        app = driver.getApplication();
        waitInit(1);
    }

    @After
    public  void stopDriver() throws Exception{
        System.out.println("stopDriver:----start background");
        driver.deactivate(1);
        System.out.println("stopDriver:----finish background");
    }

    enum SIndex {
        TC_AHref,TC_Animation,TC_AppendTree,TC_BizComponent,TC_BizModule,TC_Color,
        TC_DataBind,TC_Downgrade,TC_Gesture,TC_Image,TC_Input,TC_List,TC_Monitor,
        TC_PopBox,TC_Scroller,TC_Select,TC_Slider,TC_Style,TC_Switch,TC_Text,TC_Video,TC_Web;
    }

    public void findAHrefAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_AHref.ordinal())).tap();
        waitAfterTap();
    }

    public void findAnimationAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Animation.ordinal())).tap();
        waitAfterTap();
    }

    public void findAppendTreeAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_AppendTree.ordinal())).tap();
        waitAfterTap();
    }

    public void findBizComponentAndTap()throws Exception{
        app.tableCell(By.index(SIndex.TC_BizComponent.ordinal())).tap();
        waitAfterTap();
    }

    public void findBizModuleAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_BizModule.ordinal())).tap();
        waitAfterTap();
    }

    public void findColorAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Color.ordinal())).tap();
        waitAfterTap();
    }

    public void findDataBindAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_DataBind.ordinal())).tap();
        waitAfterTap();
    }

    public void findDowngradeAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Downgrade.ordinal())).tap();
        waitAfterTap();
    }

    public void findGestureAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Gesture.ordinal())).tap();
        waitAfterTap();
    }

    public void findImageAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Image.ordinal())).tap();
        waitAfterTap();
    }

    public void findInputAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Input.ordinal())).tap();
        waitAfterTap();
    }

    public void findListViewAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_List.ordinal())).tap();
        waitAfterTap();
    }

    public void findMonitorAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Monitor.ordinal())).tap();
        waitAfterTap();
    }

    public void findPopBoxAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_PopBox.ordinal())).tap();
        waitAfterTap();
    }

    public void findScrollerAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Scroller.ordinal())).tap();
        waitAfterTap();
    }

    public void findSelectAndTap() throws Exception{
        
        app.tableCell(By.index(SIndex.TC_Select.ordinal())).tap();
        waitAfterTap();
    }

    public void findSliderAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Slider.ordinal())).tap();
        waitAfterTap();
    }

    public void findStyleAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Style.ordinal())).tap();
        waitAfterTap();
    }

    public void findSwitchAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Switch.ordinal())).tap();
        waitAfterTap();
    }

    public void findTextAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Text.ordinal())).tap();
        waitAfterTap();
    }

    public void findVideoAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Video.ordinal())).tap();
        waitAfterTap();
    }

    public void findWebAndTap() throws Exception{
        app.tableCell(By.index(SIndex.TC_Web.ordinal())).tap();
        waitAfterTap();
    }

    public void dismissKeyboard() throws Exception{
        app.window(1).button(By.name("Done")).tap();
    }

    public void waitInit(long sec){
        SleepUtils.sleep(sec * 1000);
    }

    public void waitAfterTap(){
        SleepUtils.sleep(500);
    }

    public void screenshot(String first,String sec,String thd) throws Exception{
        String base64 = app.scrollView(By.index(0)).captureScreen();
        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
                CIParams.getPicSaveDir()+CIParams.imageNameForCI(first, sec, thd));
        Assert.assertNotNull(imageFile);
        waitAfterTap();
    }

    public void screenAppShot(String first,String sec,String thd) throws Exception{
        String base64 = app.captureScreen();
        File imageFile = ScreenCaptureUtils.saveBase64ToFile(base64,
                CIParams.getPicSaveDir()+CIParams.imageNameForCI(first, sec, thd));
        Assert.assertNotNull(imageFile);
        waitAfterTap();
    }
}
