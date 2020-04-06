package com.bigsoftware.core.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigsoftware.core.R;


/**
 * Created by shanesepac on 4/5/20.
 */

public class Downloader {

    private static String data;
    protected interface IOnResponse{
        void parsingReady(String response);
        void onError(VolleyError err);
    }
    protected static String getCachedData() throws NullPointerException{
        if(data!=null)
            return data;

        throw new NullPointerException("No cached data found. Call getNewData() first.");
    }

    static void getNewData(Context c, final IOnResponse onDataReceived) {

        RequestQueue rq = Volley.newRequestQueue(c);

        String srcURI = c.getResources().getString(R.string.datasource);

        StringRequest sr = new StringRequest(Request.Method.GET,
                srcURI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        data = response; //cache
                        onDataReceived.parsingReady(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onDataReceived.onError(error);
                    }
                });

        rq.add(sr);
    }
}
