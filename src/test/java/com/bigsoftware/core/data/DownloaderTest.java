package com.bigsoftware.core.data;

import android.test.AndroidTestCase;
import android.util.Log;

import com.android.volley.VolleyError;

import org.junit.Test;

public class DownloaderTest {

    class MockActivity extends AndroidTestCase {}

    @Test
    public void getNewData() throws Exception {

        AndroidTestCase c = new AndroidTestCase();

        Downloader.getNewData(c.getContext(), new Downloader.IOnResponse() {
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