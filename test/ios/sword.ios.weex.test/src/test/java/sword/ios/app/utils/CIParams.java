package sword.ios.app.utils;

/**
 * Created by admin on 16/3/31.
 */
public class CIParams {
    public static String pic_save_dir = "/Users/admin/Downloads/weex/iOS/";
    public static String device_uuid = "E4600EE1-6A56-4A0F-B642-CBEBC3606B1F";
    public static String device_name = "iPhone 6";
    public static String test_app_path = "/Users/admin/Documents/03_project_git/02_Weex_OpenSource/weex_gitLab_ios/ios/playground/DerivedData/WeexDemo/Build/Products/Debug-iphonesimulator/WeexUITestDemo.app";

    public static int index = 0;

    public static String getPicSaveDir(){
        String picSaveDir=System.getProperty("picSaveDir");
        if (picSaveDir!=null&&picSaveDir!="") {
            pic_save_dir=picSaveDir;
        }
        if(!pic_save_dir.endsWith("/")){
            pic_save_dir=pic_save_dir+"/";
        }
        return pic_save_dir;
    }

    public static String getDeviceUuid(){
        String deviceUuid=System.getProperty("deviceUuid");
        if (deviceUuid!=null&&deviceUuid!="") {
            device_uuid=deviceUuid;
        }
        if(device_uuid==""){
            //TODO
        }
        return device_uuid;
    }

    public static String getDeviceUuidByName(){
        String deviceName = System.getProperty("deviceName");
        if (deviceName!=null&&deviceName!="") {
            device_name=deviceName;
        }
        //TODO 通过 instruments -s devices 来查找匹配的uuid

        return device_name;
    }

    public static String getTestAppPath(){
        String testAppPath = System.getProperty("testAppPath");
        if (testAppPath!=null&&testAppPath!="") {
            test_app_path=testAppPath;
        }

        return test_app_path;
    }
    public static int adjustIndex(int originIndex){
        return originIndex + index;
    }

    public static String imageNameForCI(String first,String sec,String thd){
        return first+"_"+sec+"_"+thd+".png";
    }
}
