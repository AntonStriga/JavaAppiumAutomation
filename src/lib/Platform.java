package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String
        PLATFORM_IOS = "ios",
        PLATFORM_ANDROID = "android",
        APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance()
    {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);

        if (this.isAndroid()) {
            return new AndroidDriver(URL,this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return  new IOSDriver(URL,this.getIosDesiredCapabiliities());
        } else {
            throw new Exception("Cannot detecttype ofdriver. Platform variable: " + this.getPlatformVariable());
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getCapabilitiesByPlatrormEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();


        if (platform.equals(PLATFORM_ANDROID)){

        }else if (platform.equals(PLATFORM_IOS)){

        }else {
            throw new Exception("Cannot determinate platform platform variable '" + platform + "'");
        }

        return capabilities;
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/qa/Desktop/JavaAppiumAutomation/apks/old.org.wikipedia.apk");
        capabilities.setCapability("orientation","PORTRAIT");

        return capabilities;
    }

    private DesiredCapabilities getIosDesiredCapabiliities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("deviceName","iPhone X");
        capabilities.setCapability("platformVersion","11.2");
        capabilities.setCapability("app","/Users/qa/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("orientation","PORTRAIT");

        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVariable();
        return my_platform.equals(platform);
    }

    private String getPlatformVariable()
    {
        return System.getenv("PLATFORM");
    }
}
