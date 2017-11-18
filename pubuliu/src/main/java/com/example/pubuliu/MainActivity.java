package com.example.pubuliu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  RecyclerView recyclerView;
    Myadaper adaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adaper=new Myadaper(MainActivity.this);
        recyclerView.setAdapter(adaper);
        adaper.replaceAll(getData());
    }

    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        for (String url : ImageUtil.imageUrls) {
            list.add(url);
        }
        return list;
    }
}
