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
                   new JSONTask().execute("https://api.myjson.com/bins/"+l);
                Picasso.with(getApplicationContext())
                        .load(m.getimage())
                        .into(img);
                t1.setText(m.getText());
                t2.setText("Language   :  "+m.getlang());
                t3.setText("Population  :"+m.getpopu());
                t4.setText("Area   :"+m.getArea());
                Toast.makeText(getApplicationContext(), "please click one more time .you are one step away", Toast.LENGTH_LONG).show();


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
                String image=jo.getString("image");
                String story=jo.getString("story");
                String lang=jo.getString("Language");
                String popu=jo.getString("Population");
                String Area=jo.getString("Area");
                m.setimage(image);
                m.setText(story);
                m.setlang(lang);
                m.setpopu(popu);
                m.setArea(Area);
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
