package com.bigsoftware.core.data;

import android.content.Context;

import com.android.volley.VolleyError;
import com.bigsoftware.core.utility.CSVParsing;
import com.bigsoftware.core.utility.DateParsing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shanesepac on 4/5/20.
 */

public class DataManager {

    private static List<Datum> data;

    public interface IDataBinding {
        void bindToUI(List<Datum> data);
        void onNetworkError(String error);
        void onParsingError(Exception error);
    }

    /**
     *
     * @param c The context the method is being used
     * @param dataBinder An implementation of IDataBinding that provides methods that handle errors and UI updates.
     * @return Returns a parsed list of the most current COVID-19 cases.
     */
    public static List<Datum> getDataList(Context c, final IDataBinding dataBinder){

        if(data!=null)
            return data;

        Downloader.getNewData(c, new Downloader.IOnResponse() {
            @Override
            public void parsingReady(String response) {
                //try parse
                try{
                    data = parseAll(response);
                    dataBinder.bindToUI(data);
                }
                catch(Exception ex){
                    dataBinder.onParsingError(ex);
                }
            }

            @Override
            public void onError(VolleyError err) {
                dataBinder.onNetworkError(err.getMessage());
            }
        });

        return data;
    }

    /**
     * Turns raw CSV data into parsed POJOs
     * @param text
     * @return
     * @throws NullPointerException
     * @throws java.text.ParseException
     */
    private static List<Datum> parseAll(String text) throws NullPointerException, java.text.ParseException{

        if(text==null)
            throw new NullPointerException();

        String[] lines = text.split("\n");
        List<Date> dates = new ArrayList<Date>();
        boolean isFirstLine = true;
        List<Datum> data = new ArrayList<>();
        for (String line :
                lines) {
            Datum d = new Datum();
            List<String> fields = CSVParsing.parseLine(line);

            d.setUID(fields.get(0));
            d.setIso2(fields.get(1));
            d.setIso3(fields.get(2));
            d.setCode3(fields.get(3));
            d.setFIPS(fields.get(4));
            d.setAdmin2(fields.get(5));
            d.setProvinceState(fields.get(6));
            d.setCountryRegion(fields.get(7));
            d.setLatitude(fields.get(8));
            d.setLongitude(fields.get(9));
            d.setCombinedKey(fields.get(10));

            //Continue code execution since headers are no longer being recorded
            List<Datum.DateValuePair> dvpList = new ArrayList<>();

            for(int i=11;i<fields.size();i++){
                if(isFirstLine){
                    dates.add(DateParsing.convertSpecialDate(fields.get(i)));
                }
                else{
                    Datum.DateValuePair dvp = new Datum.DateValuePair();
                    dvp.setDate(dates.get(i-11));
                    int value = Integer.parseInt(fields.get(i));
                    dvpList.add(dvp);
                }
            }
            d.setCasualties(dvpList);
            isFirstLine=false;
            data.add(d);
        }

        return data;
    }

}
