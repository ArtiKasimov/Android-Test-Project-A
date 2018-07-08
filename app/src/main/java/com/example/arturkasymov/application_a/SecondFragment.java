package com.example.arturkasymov.application_a;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {


    private final String EXTRA_FRAGMENT_ID = "com.example.arturkasymov.application_a.FRAGMENT_ID";
    private final String FRAGMENT_ID = "2";

    private RecyclerView mRe_cordRecyclerView;
    private Re_codrAdapter mAdapter;

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment, container, false);

        DBHandler db = new DBHandler(getContext());
        List<Re_cord> re_cords = db.getAllRecords();

        mRe_cordRecyclerView = (RecyclerView) v.findViewById(R.id.Re_cord_Recycler_View);
        mRe_cordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));










        /*
        // Here must be customAdapter
        ArrayList<String> references = new ArrayList<String>();
        for (Re_cord temp: re_cords){
            references.add(temp.getReference());
        }
        // it's end

        // it's must be improved
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                references);

        ListView listView =view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        // it's end

        */
        /* Потом впихнуть туда, где будет открываться приложение Б с вкладки история
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.example.arturkasymov.application_b",
                "com.example.arturkasymov.application_b.MainActivity"));
        i.putExtra(EXTRA_FRAGMENT_ID,FRAGMENT_ID);
        startActivity(i);

        */

        updateUI();

        return v;
    }

    private void updateUI(){
        //CrimeLab crimeLab = CrimeLab.get(getActivity());
        DBHandler db = new DBHandler(getContext());
        List<Re_cord> Re_cords = db.getAllRecords();
        mAdapter = new Re_codrAdapter(Re_cords);
        mRe_cordRecyclerView.setAdapter(mAdapter);
    }

    private class Re_cordHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView mTitleTextView;

        public Re_cordHolder(View itemView){
            super(itemView);
            mTitleTextView = (TextView)itemView;
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){

            Intent i = new Intent();
            i.setComponent(new ComponentName("com.example.arturkasymov.application_b",
                    "com.example.arturkasymov.application_b.MainActivity"));
            i.putExtra(EXTRA_FRAGMENT_ID,FRAGMENT_ID);
            ///////////
            /// здесь место для передачи чего-то там
            /////////////
            startActivity(i);

        }
    }


    private class Re_codrAdapter extends RecyclerView.Adapter<Re_cordHolder>{

        private List<Re_cord> mRe_cords;

        public Re_codrAdapter(List<Re_cord> crimes){
            mRe_cords = crimes;
        }

        @Override
        public Re_cordHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);
            return new Re_cordHolder(v);
        }

        @Override
        public void onBindViewHolder(Re_cordHolder holder, int position){
            Re_cord re_cord = mRe_cords.get(position);
            holder.mTitleTextView.setText(re_cord.getReference());
        }

        public int getItemCount(){
            return mRe_cords.size();
        }
    }

}