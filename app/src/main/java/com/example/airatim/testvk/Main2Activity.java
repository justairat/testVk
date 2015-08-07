package com.example.airatim.testvk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.ArrayList;


public class Main2Activity extends Activity {
    ListView listView;
    private final ArrayList<User> friends = new ArrayList<User>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView)findViewById(R.id.listView);

        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,"photo_50"));

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                parse(response);
                Toast.makeText(getApplicationContext(), "Execute Request VK...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }
        });

    }


    public void parse(VKResponse response) {

        String json = response.responseString;

        JsonParser jsonParser = new JsonParser();
        JsonObject mainObject = jsonParser.parse(json).getAsJsonObject();
        JsonObject resObject = mainObject.getAsJsonObject("response");
        JsonArray arrFriend = resObject.getAsJsonArray("items");
        JsonObject obj = new JsonObject();

        String[] str = new String[arrFriend.size()];
        for(int i=0;i<arrFriend.size();i++){
             obj= arrFriend.get(i).getAsJsonObject();
             str[i] = obj.get("first_name").toString()+ " "+ obj.get("last_name").toString();
            User varUser = new User(str[i].replace("\"", ""),obj.get("photo_50").toString().replace("\"", ""));
            friends.add(i,varUser);
        }

        FriendsAdapter adapter = new FriendsAdapter(this,R.layout.mylistitem,friends);
        listView.setAdapter(adapter);

    }


        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
