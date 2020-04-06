package com.bigsoftware.core;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.android.volley.VolleyError;
import com.bigsoftware.core.data.Downloader;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by shanesepac on 4/5/20.
 */

@RunWith(AndroidJUnit4.class)
public class DownloaderTest{


    public DownloaderTest(){
    }
    @Test
    public void getNewData() throws Exception {

        Downloader.getNewData((Context)new MockActivity(), new Downloader.IOnResponse() {
            @Override
            public void parsingReady(String response) {
                Log.d("TEST", "parsingReady: " + response);
            }

            @Override
            public void onError(VolleyError err) {
                Log.d("TEST", "onError: " + err.getMessage());
            }
        });

    }

}