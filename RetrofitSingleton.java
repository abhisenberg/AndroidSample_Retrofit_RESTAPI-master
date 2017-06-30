package com.codingblocks.restapiretrofitjson;

import com.codingblocks.restapiretrofitjson.api.CommentsAPI;
import com.codingblocks.restapiretrofitjson.api.PostsAPI;
import com.codingblocks.restapiretrofitjson.api.UsersAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abheisenberg on 30/6/17.
 */

public class RetrofitSingleton {

    private RetrofitSingleton instance = null;

    private CommentsAPI commentsAPI;
    private PostsAPI postsAPI;
    private UsersAPI usersAPI;

    public CommentsAPI getCommentsAPI() {
        return commentsAPI;
    }

    public PostsAPI getPostsAPI() {
        return postsAPI;
    }

    public UsersAPI getUsersAPI() {
        return usersAPI;
    }

    private RetrofitSingleton(){
        Retrofit retrofit
                = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        commentsAPI = retrofit.create(CommentsAPI.class);
        postsAPI    = retrofit.create(PostsAPI.class);
        usersAPI    = retrofit.create(UsersAPI.class);

    }

    public RetrofitSingleton getRetrofitSingleton (){
        if(instance == null){
            instance = new RetrofitSingleton();
        }
        return instance;
    }

}
