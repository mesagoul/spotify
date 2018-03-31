package android.workshop.dmii.playlistspotifygenerator.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.workshop.dmii.playlistspotifygenerator.R;
import android.workshop.dmii.playlistspotifygenerator.adapters.ImageAdapter;
import android.workshop.dmii.playlistspotifygenerator.models.User;

/**
 * Created by benja on 21/02/2018.
 */

public class PlaylistListFragment  extends Fragment{

    private GridView listPlaylist;
    private User user;

    public PlaylistListFragment(){
        //empty constructor
        user =  User.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_playlist, container,false);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listPlaylist = (GridView) view.findViewById(R.id.playlistList);

        createGridView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        user = ViewModelProviders.of(this).get(User.class);
        user.loadUser();
        user.getPlayListList().observe(this, playListList -> {
            // TODO ici on à une ListPlaylist
            Log.d("PlayList", playListList.toString());
        });

    }

    private void createGridView(){
        listPlaylist.setAdapter(new ImageAdapter(this.getContext()));

        listPlaylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
