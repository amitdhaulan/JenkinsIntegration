package org.example.utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Listener implements ITestListener {
    public static final String fileName = new SimpleDateFormat("MMddyyyy_HHmmss").format(Calendar.getInstance().getTime());

    @Override
    public void onFinish(ITestContext contextFinish) {

    }

    @Override
    public void onStart(ITestContext contextStart) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseClass.LOGGER.fatal(BaseClass.timeStamp + " " + result.getName() + " failed due to: " + result.getThrowable());
        try {
            BaseClass.takeScreenShot(BaseClass.driver, "./utilities/images/" + result.getName()+"_" + fileName + ".png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseClass.LOGGER.info(BaseClass.timeStamp + " " + result.getName() + " passed.");
    }


}
