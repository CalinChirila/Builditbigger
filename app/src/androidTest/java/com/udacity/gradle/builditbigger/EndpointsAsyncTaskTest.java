package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * Code snippet inspired from
 * https://github.com/mbejaranoe/build_it_bigger/blob/master/app/src/androidTest/java/com/udacity/gradle/builditbigger/EndpointAsyncTaskTest.java
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    final CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void testJoke() {
        final EndpointsAsyncTask task = new EndpointsAsyncTask(){
            @Override
            protected String doInBackground(Void... voids) {
                return super.doInBackground(voids);
            }

            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertFalse(TextUtils.isEmpty(result));
                signal.countDown();
            }
        };

        Runnable runTest = new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        };

        runTest.run();

        try {
            signal.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
