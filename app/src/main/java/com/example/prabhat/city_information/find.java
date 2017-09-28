package com.example.prabhat.city_information;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;


public class find extends AppCompatActivity {
   private ImageView img;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    //private TextView t6;
    private  String image;
    private String popu;
    private String story;
    private String lang;
    private String place;
    private String Area;
    private model m;
    private AutoCompleteTextView AT;
    private Button search;
   private String[] city ;//= {"India", "China", "Australia", "New Zealand", "England", "Pakistan","chinkara","Indo","America","neitherland"};
  private String[] link;
    private LinkedHashMap<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_find);
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);
       // t6=(TextView)findViewById(R.id.t6);
        img=(ImageView)findViewById(R.id.img);
              m=new model();
           map=new LinkedHashMap<>();

          city=getResources().getStringArray(R.array.city);
          link=getResources().getStringArray(R.array.link);
       for(int i=0;i<city.length;i++)
       {
            map.put(city[i],link[i]);
       }
          search=(Button)findViewById(R.id.search);
          AT=(AutoCompleteTextView) findViewById(R.id.AT);
          ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,city);
          AT.setAdapter(adapter);
       search.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                String c=AT.getText().toString();
                String l=map.get(c);
              /* String image=m.getimage();
                String tex=m.getText();
                String lang=m.getlang();
                String popu=m.getpopu();
                String area=m.getArea();
                String place=m.getPlace();*/
                   new JSONTask().execute(l);
                Picasso.with(getApplicationContext())
                        .load(image)
                        .into(img);
                t1.setText(story);
                t2.setText("Language   :  "+"\n"+lang);
                t3.setText("Population    :"+popu);
                t4.setText("Area   :"+Area);
                t5.setText("Place to visit in this city  :"+ place);
              //  Toast.makeText(getApplicationContext(), "please click one more time .you are one step away", Toast.LENGTH_LONG).show();


            }

        });

    }
    public class JSONTask extends AsyncTask<String,String,model>
    {


        @Override
        protected model doInBackground(String... params) {

            HttpURLConnection connection=null;
            BufferedReader br=null;
            try{
                URL url=new URL(params[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream stream=connection.getInputStream();
                br=new BufferedReader(new InputStreamReader(stream));
                StringBuilder sb=new StringBuilder();
                String line="";

                while((line=br.readLine())!=null)
                {
                    sb.append(line);
                }

                String jsObject= sb.toString();

                JSONObject jo=new JSONObject(jsObject);
                 image=jo.getString("image");
                 story=jo.getString("story");
                 lang=jo.getString("Language");
                 popu=jo.getString("Population");
                 Area=jo.getString("Area");
                 place=jo.getString("place");
               /* m.setimage(image);
                m.setText(story);
                m.setlang(lang);
                m.setpopu(popu);
                m.setArea(Area);
                m.setPlace(place);
               /* Glide.with(getApplicationContext())
                       .load(image)
                       .into(img);*/
                return m;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally{

                if(connection!=null)connection.disconnect();
                try{
                    if(br!=null)br.close();
                }catch(IOException e){e.printStackTrace();}

            }


            return null;
        }

        @Override
        protected void onPostExecute(model m) {
            super.onPostExecute(m);

        }
    }








}
