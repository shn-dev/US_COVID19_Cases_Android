# USA COVID-19 Cases and Deaths by City
Provides daily updates of the number of US COVID-19 Cases for use in Android applications.

## Description
This repository contains an Android library that asynchronously connects to the Johns Hopkins US COVID-19 cases dataset located on GitHub. A function is exposed after the data is retrieved that allows UI updates or the data to be manipulated internally as desired.

## Source
The dataset is available at https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv and is updated daily.

## Usage
The code below shows an example of how to incorporate the dataset into your Android (SDK 18+) application. In summary, it is assumed you have a simple `Activity` with a button on its respective layout. Upon clicking the button, you execute the code that pulls the dataset. Once the code returns you are able to use the data without having to worry about conflicts with the main UI thread. You must also provide code in `onNetworkError` and `onParsingError` should the code execution fail somehow. 
```
/***
 * A demo activity that has a single button in its layout.
 * Clicking the button will pull COVID-19 data by calling getDataList().
 * It has a callback, bindToUI(), which allows you to do just that... bind the dataset to the UI.
 */
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
                DataManager.getInstance().getDataList(c, new DataManager.IDataBinding() {
                    @Override
                    public void bindToUI(List<Datum> data) {
                        //We are free to bind to the UI within this method without threading issues.
                        //Below we are just going to output the number of cases/deaths to the console
                        //but you are free to bind directly to a TextView, RecyclerView etc...
                        for (Datum d :
                                data) {
                            try {
                                Log.i(TAG, String.format("Number of cases in %1$s: %2$s", d.getCombinedKey(), d.getCurrentCases()));
                                Log.i(TAG, String.format("Number of deaths in %1$s: %2$s", d.getCombinedKey(), d.getCurrentDeaths()));
                            }
                            catch(Exception e){
                                Log.e(TAG, "A datum was unreadable... ", e);
                            }
                        }
                    }

                    @Override
                    public void onNetworkError(Exception error) {
                        Log.e(TAG, "onNetworkError: Something bad happened.", error);
                    }

                    @Override
                    public void onParsingError(Exception error) {
                        Log.e(TAG, "onNetworkError: Something bad happened.", error);
                    }
                });
            }
        });
    }
}
```
