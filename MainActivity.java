package com.codingblocks.restapiretrofitjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.codingblocks.restapiretrofitjson.adapters.UserAdapter;
import com.codingblocks.restapiretrofitjson.api.API;
import com.codingblocks.restapiretrofitjson.api.CommentsAPI;
import com.codingblocks.restapiretrofitjson.api.PostsAPI;
import com.codingblocks.restapiretrofitjson.api.UsersAPI;
import com.codingblocks.restapiretrofitjson.interfaces.OnItemClickListener;
import com.codingblocks.restapiretrofitjson.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "USERS";

    RecyclerView rvUserList;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvUserList = (RecyclerView) findViewById(R.id.rvUserList);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this, new ArrayList<User>());
        rvUserList.setAdapter(userAdapter);

        ((Button) findViewById(R.id.btnAllPosts)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(
                                MainActivity.this,
                                PostsActivity.class
                        ));
                    }
                }
        );

        ((Button) findViewById(R.id.btnAllComments)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(
                                MainActivity.this,
                                CommentsActivity.class
                        ));
                    }
                }
        );

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com")
//                .addConverterFactory(
//                        GsonConverterFactory.create()
//                )
//                .build();

        final UsersAPI usersAPI
//                = retrofit.create(UsersAPI.class);
                = API.getAPIinstance().getUsersAPI();

        usersAPI.getUsers().enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call,
                                   Response<ArrayList<User>> response) {
                Log.d(TAG, "onResponse: ");
                userAdapter.updateUsers(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }



}
