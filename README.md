# US_COVID19_Cases_Android
Provides daily updates of the number of US COVID-19 Cases for use in Android applications.

## Description
This repository contains an Android library that asynchronously connects to the Johns Hopkins US COVID-19 cases dataset located on GitHub. A function is exposed after the data is retrieved that allows UI updates or the data to be manipulated internally as desired.

## Source
The dataset is available at https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv and is updated daily.

## Usage
The code below shows an example of how to incorporate the dataset into your Android (SDK 18+) application. In summary, it is assumed you have a simple `Activity` with a button on its respective layout. Upon clicking the button, you execute the code that pulls the dataset. Once the code returns you are able to use the data without having to worry about conflicts with the main UI thread. You must also provide code in `onNetworkError` and `onParsingError` should the code execution fail somehow. 
```
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context c = (Context)this;
        Button b = findViewById(R.id.databtn);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getDataList(c, new DataManager.IDataBinding() {
                    @Override
                    public void bindToUI(List<Datum> data) {
                        for (Datum d :
                                data) {
                            Log.i(TAG, "onCreate: " + d.getCombinedKey());
                        }
                    }

                    @Override
                    public void onNetworkError(String error) {
                        Log.e(TAG, "onNetworkError: ", new Exception(error));
                    }

                    @Override
                    public void onParsingError(Exception error) {
                        Log.e(TAG, "onParsingError: ", error);
                    }
                });
            }
        });
    }
}
```
