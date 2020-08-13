package com.sebix.dagger.ui.main.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.bumptech.glide.ListPreloader;
import com.sebix.dagger.SessionManager;
import com.sebix.dagger.models.Post;
import com.sebix.dagger.network.main.MainApi;
import com.sebix.dagger.ui.main.Resource;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostsViewModel extends ViewModel {
    private static final String TAG = "PostsViewModel";


    //inject
    private final SessionManager sessionManager;
    private final MainApi mainApi;

    private MediatorLiveData<Resource<List<Post>>> posts;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi){
        this.sessionManager = sessionManager;
        this.mainApi=mainApi;
        Log.d(TAG, "PostsViewModel: is working");
    }

    public LiveData<Resource<List<Post>>> observePost(){
        if(posts==null){
            posts=new MediatorLiveData<>();
            posts.setValue(Resource.loading((List<Post>) null));
            final LiveData<Resource<List<Post>>> source = LiveDataReactiveStreams.fromPublisher(
                    mainApi.getPostsFromUser(sessionManager.getAuthUser().getValue().data.getId())
                    .onErrorReturn(new Function<Throwable, List<Post>>() {
                        @Override
                        public List<Post> apply(Throwable throwable) throws Exception {
                            Log.e(TAG,"apply: " + throwable);
                            Post post = new Post();
                            post.setId(-1);
                            ArrayList<Post> posts = new ArrayList<>();
                            posts.add(post);
                            return posts;
                        }
                    })
                    .map(new Function<List<Post>, Resource<List<Post>>>() {
                        @Override
                        public Resource<List<Post>> apply(List<Post> posts) throws Exception {
                            if(posts.size()>0){
                                if(posts.get(0).getId()==-1){
                                    return Resource.error("Something went wrong",null);
                                }
                                return Resource.success(posts);
                            }
                            return null;
                        }
                    })
                    .subscribeOn(Schedulers.io())
            );
            posts.addSource(source, new Observer<Resource<List<Post>>>() {
                @Override
                public void onChanged(Resource<List<Post>> listResource) {
                    posts.setValue(listResource);
                    posts.removeSource(source );
                }
            });
        }
        return posts;
    }

}
