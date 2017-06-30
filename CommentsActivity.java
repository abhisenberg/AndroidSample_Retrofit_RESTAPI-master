package com.codingblocks.restapiretrofitjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.codingblocks.restapiretrofitjson.adapters.CommentAdapter;
import com.codingblocks.restapiretrofitjson.api.CommentsAPI;
import com.codingblocks.restapiretrofitjson.models.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentsActivity extends AppCompatActivity {

    final String TAG = "CommentsActivity";

    RecyclerView recyclerView;
    CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        commentAdapter = new CommentAdapter(
                CommentsActivity.this, new ArrayList<Comment>()
        );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentAdapter);

        Retrofit retrofit
                = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        CommentsAPI commentsAPI
                = retrofit.create(CommentsAPI.class);

        Callback<ArrayList<Comment>> commentsCallback
                = new Callback<ArrayList<Comment>>() {
            @Override
            public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
                Log.d(TAG, "onResponse: ");
                commentAdapter.updateComments(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        };

        int userIdReceived
                = getIntent().getIntExtra("userID",-1);
        if(userIdReceived == -1){
            commentsAPI.getComments().enqueue(commentsCallback);
        } else {
            commentsAPI.getCommentsByPostId(userIdReceived).enqueue(commentsCallback);
        }
    }
}
