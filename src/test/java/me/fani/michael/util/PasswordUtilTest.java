package me.fani.michael.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(JUnit4ClassRunner.class)
public class PasswordUtilTest {

    @Test
    public void testGoodPasswordGood() {
        Assert.assertTrue(PasswordUtil.isGoodPassword("12345678"));
    }

    @Test
    public void testGoodPasswordBad() {
        Assert.assertFalse(PasswordUtil.isGoodPassword("123"));
    }
}
