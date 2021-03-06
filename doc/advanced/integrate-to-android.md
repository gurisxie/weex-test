# import Weex Android to your project

You will need to build Weex from source if you want to work on a new feature/bug fix, try out the latest features which are not released yet, or maintain your own fork with patches that cannot be merged to the core.

## Prerequisites

Assuming you have the Android SDK installed, run `android` to open the Android SDK Manager.

Make sure you have the following installed:

1. Android SDK version 23 (compileSdkVersion in [`build.gradle`](https://github.com/alibaba/weex/blob/master/android/sdk/build.gradle))
2. SDK build tools version 23.0.1 (buildToolsVersion in [`build.gradle`](https://github.com/alibaba/weex/blob/master/android/sdk/build.gradle))
3. Android Support Repository >= 17 (for Android Support Library)
4. Android NDK (download & extraction instructions [here](http://developer.android.com/ndk/downloads/index.html))

Point Gradle to your Android SDK: either have `$ANDROID_SDK` and `$ANDROID_NDK ` defined, or create a local.properties file in the root of your react-native checkout with the following contents:

```
sdk.dir=absolute_path_to_android_sdk
ndk.dir=absolute_path_to_android_ndk
```

Example:

```
sdk.dir=/Users/your_unix_name/android-sdk-macosx
ndk.dir=/Users/your_unix_name/android-ndk/android-ndk-r10e
```


## Building the source

#### 1. Clone source from github

First, you need to git clone `weex` from github:

```shell
git clone https://github.com/alibaba/weex.git
```

#### 2. Adding the `:weex_sdk_android` project

Add the `:weex_sdk_android` project in `android/settings.gradle`:

```gradle
include ':weex_sdk_android'

project(':weex_sdk_android').projectDir = new File(
    rootProject.projectDir, '../weex_sdk_android')
```

Modify your `android/app/build.gradle` to use the `:weex_sdk_android` project instead of the pre-compiled library, e.g. - replace `compile 'com.taobao.android:weex_sdk:1.3.+'` with `compile project(':weex_sdk_android')`:

```gradle
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    compile 'com.android.support:appcompat-v7:23.0.1'

    compile project(':weex_sdk_android')

    ...
}
```

#### 3. Making 3rd-party modules use your fork

If you use 3rd-party weex modules, you need to override their dependencies so that they don't build the pre-compiled library. Otherwise you'll get an error while compiling - `Error: more than one library with package name 'com.taobao.weex'`.

Modify your `android/app/build.gradle` and replace `compile project(':weex-custom-module')` with:

```gradle
compile(project(':weex-custom-module')) {
    exclude group: 'com.taobao.weex', module: 'weex_sdk_android'
}
```

## Building from Android Studio

From the Welcome screen of Android Studio choose "Import project" and select the `android` folder of your app.

You should be able to use the _Run_ button to run your app on a device. 
## Additional notes

Since the packet size limit is currently only compiled arm , X86 does not support.


## Troubleshooting

Gradle build fails in `ndk-build`. See the section about `local.properties` file above.

#Quick access
 
## Requirements

* an existing, gradle-based Android app

## Prepare your app

In your app's `build.gradle` file add the React Native dependency:

    compile 'com.taobao.weex:weex_sdk:1.3.+'

You can find the latest version of the react-native library on [Maven Central](). Next, make sure you have the Internet permission in your `AndroidManifest.xml`:

    <uses-permission android:name="android.permission.INTERNET" />

This is only really used in dev mode when reloading JavaScript from the development server, so you can strip this in release builds if you need to.

## Add native code

You need to add some native code in order to start the Weex runtime and get it to render something. To do this, we're going to create an `Application` to init weex, then we we're going to create an `Activity` that creates a WeexContainerView, starts a Weex application inside it and sets it as the main content view.


```java
public class WXApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        WXEnvironment.addCustomOptions("appName","TBSample");
        WXSDKEngine.init(this);
        try {

            WXSDKEngine.registerComponent("wtRichText", WTRichText.class);
            ......
            
            WXSDKEngine.registerModule("event", WXEventModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }
    }
}
```

Next, 

```java
//crate Weex instance
WXSDKInstance mInstance = new WXSDKInstance(this);
//set image Adapter
mInstance.setImgLoaderAdapter(new ImageAdapter(this));
//register render listener
mInstance.registerRenderListener(new IWXRenderListener() {
   @Override
   public void onViewCreated(WXSDKInstance instance, View view) {
       // addview
       mViewGroup.addView(view);
   } 
   @Override
   public void onRenderSuccess(WXSDKInstance instance) {
   }
   @Override
   public void onRefreshSuccess(WXSDKInstance instance) {
   }
   @Override
   public void onException(WXSDKInstance instance, String errCode,String msg) {
   }
}); 
//start render weex view   
mInstance.render(pageName,template, null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
```

That's it, your activity is ready to run some JavaScript code.

## Reference Example   

[`Weex Examples`](https://github.com/alibaba/weex/tree/master/examples)