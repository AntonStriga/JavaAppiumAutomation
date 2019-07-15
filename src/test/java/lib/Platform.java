package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static final String
        PLATFORM_IOS = "ios",
        PLATFORM_ANDROID = "android",
        PLATFORM_MOBILE_WEB = "mobile_web",
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

    public RemoteWebDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);

        if (this.isAndroid()) {
            return new AndroidDriver(URL,this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return  new IOSDriver(URL,this.getIosDesiredCapabiliities());
        } else if (this.isMobWeb()){
            return new ChromeDriver(this.getMWChomeOptions());
        } else {
            throw new Exception("Cannot detect type of driver. Platform variable: " + this.getPlatformVariable());
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

    public boolean isMobWeb()
    {
        return isPlatform(PLATFORM_MOBILE_WEB);
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
        capabilities.setCapability("platformVersion","6.0");
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

    private ChromeOptions getMWChomeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Geko); Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVariable();
        return my_platform.equals(platform);
    }

    public String getPlatformVariable()
    {
        return System.getenv("PLATFORM");
    }
}
