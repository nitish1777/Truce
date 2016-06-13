package truce.app.assign.truce;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import truce.app.assign.truce.Adapter.WordListAdapter;
import truce.app.assign.truce.DataClass.SuperHeroes;
import truce.app.assign.truce.DataClass.VocabData;
import truce.app.assign.truce.Utils.Config;


public class MainActivity extends AppCompatActivity {

    //Creating a List of superheroes
    private List<SuperHeroes> listSuperHeroes;
    private List<VocabData> listVocabData;

    //Creating Views
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    ProgressDialog loading;//= new ProgressDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Views
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //Calling method to get data
        getDataSimleRequest();
    }

    //This method will get data from the web api
    private void getDataSimleRequest() {
        loading = ProgressDialog.show(this, "Loading Data", "Please wait...", false, false);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest myReq = new StringRequest(Request.Method.GET,
                Config.DATA_URL,
                createMyReqSuccessListener(),
                createMyReqErrorListener());

        queue.add(myReq);
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("data", "Responce:-" + response.toString());
                loading.dismiss();
                parseDataWords(response);
                //         mTvResult.setText(response);
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //           mTvResult.setText(error.getMessage());
                loading.dismiss();
                Log.d("exec", "Exception" + error.toString());
            }
        };
    }


    private void parseDataWords(String array) {

        try {
            listVocabData = new ArrayList<>();
            JSONObject jsonObj = new JSONObject(array.toString());
            JSONArray jsonArrayy = jsonObj.getJSONArray("words");

            for (int i = 0; i < jsonArrayy.length(); i++) {
                JSONObject object = jsonArrayy.getJSONObject(i);

                VocabData data = new VocabData();
//{"id":360,"word":"unique","variant":3,"meaning":"to be the only one of a kind","ratio":-1}
                data.setId(object.getInt("id"));
                data.setWord(object.getString("word"));
                data.setMeaning(object.getString("meaning"));
                data.setRatio(object.getDouble("ratio"));


                if (data.getRatio() > 0) {

                    listVocabData.add(data);
                }

            }

            Log.d("data", "Responce:-" + listVocabData.toString());

            adapter = new WordListAdapter(listVocabData, this);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }


    }


}