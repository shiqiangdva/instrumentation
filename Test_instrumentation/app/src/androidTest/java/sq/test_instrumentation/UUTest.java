package sq.test_instrumentation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by sp01 on 2017/5/26.
 */
@RunWith(AndroidJUnit4.class)
public class UUTest {

    private UiDevice uiDevice;

    @Before
    public void setUp(){
        // 初始化
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        String launcherPackageName = getLauncherPackageName();
        assertThat(launcherPackageName,notNullValue());
        uiDevice.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)),5000);
    }

    @Test
    public void testDemo() throws UiObjectNotFoundException{

        uiDevice.pressHome();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        uiDevice.pressHome();

        // 进入设置菜单
        UiObject settingApp = new UiObject(new UiSelector().text("微信"));
        settingApp.click();
        // 休眠三秒
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            new UiObject(new UiSelector().text("通讯录")).clickAndWaitForNewWindow(2000);
            UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
            UiObject uiObject = scrollable.getChildByText(new UiSelector().text("温油的老大"),"温油的老大",true);
            if (uiObject != null){
                uiObject.clickAndWaitForNewWindow(2000);
            new UiObject(new UiSelector().text("发消息")).clickAndWaitForNewWindow(2000);

//                UiScrollable set = new UiScrollable(new UiSelector().scrollable(true));
//                UiObject language = set.getChildByText(new UiSelector().text("Language & input"),"Language & input",true);
//                language.clickAndWaitForNewWindow();

                uiDevice.pressBack();

                new UiObject(new UiSelector().text("通讯录")).clickAndWaitForNewWindow(2000);

            }

            UiScrollable scrollable1 = new UiScrollable(new UiSelector().scrollable(true));
            UiObject uiObject1 = scrollable1.getChildByText(new UiSelector().text("新的朋友"),"新的朋友",true);
            if (uiObject1 != null){
                uiObject1.clickAndWaitForNewWindow(2000);
            }
            uiDevice.pressBack();

        }catch (Exception e){

        }

        try {
            new UiObject(new UiSelector().text("群聊")).clickAndWaitForNewWindow(2000);
            new UiObject(new UiSelector().text("铁铁铁")).clickAndWaitForNewWindow(2000);
            uiDevice.pressBack();
        }catch (Exception e){

        }

    }

    private String getLauncherPackageName(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent,PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

}
