package com.example.timetable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private Context mContext;
    private JSONArray mExampleList;
    MainActivity mainActivity;

    long date = System.currentTimeMillis();
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
    String dateString = sdf.format(date);
    String[] nameArray = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday","Sunday"};

    public ExampleAdapter(Context context, JSONArray exampleList) {
        this.mContext = context;
        this.mExampleList = exampleList;
    }



    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {


        try {
            final JSONObject obj = mExampleList.getJSONObject(position);
            String day = obj.getString("Day");
           // List<String> nameList = new ArrayList<>(Arrays.asList(nameArray));
            if (day.contentEquals(dateString)) {
               // holder.mslot.setText("Slots : " + obj.getString("Slot"));
                holder.mfaculty.setText("Faculty Name : " + obj.getString("Faculty"));
                //holder.mclass.setText("Class : " + obj.getString("Class"));
                holder.msubject.setText("Subject : " + obj.getString("Subject"));
                holder.mtp.setText("Theory/Practical : " + obj.getString("T/P"));
                //holder.mlink.setText("Link : " + obj.getString("Class/Lab/Link"));
                //holder.mday.setText("Day : " + obj.getString("Day"));
                final String st="https://stackoverflow.com/questions/41004236/convert-string-to-url-in-android-java";
                holder.mlink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent (view.getContext(), web_view.class);
                        intent.putExtra("hello", st);
                        view.getContext().startActivity(intent);
                        //Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                //Toast.makeText(mContext, "holiday is here", Toast.LENGTH_SHORT).show();
//                TextView txtView = mainActivity.findViewById(R.id.hoiday);
//                txtView.setVisibility(View.VISIBLE);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.length();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        //public TextView mslot;
        public TextView mfaculty;
        //public TextView mclass;
        public TextView msubject;
        public TextView mtp;
        public TextView mlink;
        //public TextView mday;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            //mslot = itemView.findViewById(R.id.text_slot);
            mfaculty = itemView.findViewById(R.id.text_faculty);
            //mclass = itemView.findViewById(R.id.text_class);
            msubject = itemView.findViewById(R.id.text_subject);
            mtp = itemView.findViewById(R.id.text_T_P);
            mlink = itemView.findViewById(R.id.text_link);
            //mday = itemView.findViewById(R.id.text_day);

//            mlink.setOnClickListener();

        }
    }
}
