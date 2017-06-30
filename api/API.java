package com.codingblocks.restapiretrofitjson.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abheisenberg on 30/6/17.
 */

public class API {

    private static API instance = null;

    private CommentsAPI commentsAPI;
    private UsersAPI usersAPI;
    private PostsAPI postsAPI;

    public CommentsAPI getCommentsAPI() {
        return commentsAPI;
    }

    public UsersAPI getUsersAPI() {
        return usersAPI;
    }

    public PostsAPI getPostsAPI() {
        return postsAPI;
    }

    private API (){
        Retrofit retrofit
                = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        commentsAPI
                = retrofit.create(CommentsAPI.class);
        usersAPI
                = retrofit.create(UsersAPI.class);
        postsAPI
                = retrofit.create(PostsAPI.class);
    }

    public static API getAPIinstance(){
        if(instance == null){
            instance = new API();
        }
        return instance;
    }
}
