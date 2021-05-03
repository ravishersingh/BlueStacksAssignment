package com.tools.selenium;

import com.ExceptionHandler.WebDriverNotSetException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectActions {
    public static boolean debug = false;

    public static boolean isObjectExist(By args) throws WebDriverNotSetException {
        long startTime = System.currentTimeMillis();
        try {
            Drivers.GetDriver().findElement(args);
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            if(debug)
                System.out.print("Total time taken for Object existence check- " + elapsedTime + "seconds\t");
            System.out.println("Object found " + args.toString());
            return true;
        } catch (NoSuchElementException e) {
            if(debug) {
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                System.out.print("[FAIL] Total time taken for Object existence check- " + elapsedTime + "seconds\t");
            }
            System.out.println("Object not found " + args.toString());
            return false;
        }
    }

    public static boolean isObjectExistWithinElement(WebElement element, By args) {
        long startTime = System.currentTimeMillis();
        try {
            element.findElement(args);
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            if(debug)
                System.out.print("Total time taken for Object existence check within another Object- " + elapsedTime + "seconds\t");
            System.out.println("Object check fail." + args.toString());
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Object check fail." + args.toString());
            if(debug) {
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("[FAIL] Total time taken for Object existence check within another Object- " + elapsedTime + "seconds");
            }
            return false;
        }
    }

    public static boolean WaitForObject(By args, Integer Seconds) {
        System.out.print("[Request] Wait(" + Seconds + ") for Object By "+args.toString()+"\t");
        try {
            for (int i = 0; i <= Seconds.intValue(); i++) {
                if (isObjectExist(args))
                    return true;
                Thread.sleep(1000L);
            }
        } catch (Exception e) { }
            System.out.println("[Fail] Object not found after waiting for " + Seconds + "seconds with "+args.toString());
            return false;
    }

    public static boolean WaitForObjectDisappear(By args, Integer Seconds) {
        System.out.print("[Request] Wait(" + Seconds + ") for Object By "+args.toString()+" to disappear\t");
        try {
            for (int i = 0; i <= Seconds.intValue(); i++) {
                if (!isObjectExist(args))
                    return true;
                Thread.sleep(1000L);
            }
        } catch (Exception e) { }
        System.out.println("[Fail] Object found after waiting for " + Seconds + "seconds with "+args.toString());
        return false;
    }

}
