package com.example.jeremy_ssd.appsbergdesigntest;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends ArrayAdapter<AppModel> {

    //tweets est la liste des models à afficher
    public AppAdapter(Context context, List<AppModel> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_items,parent, false);
        }

        AppViewHolder viewHolder = (AppViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AppViewHolder();
            viewHolder.pseudo = convertView.findViewById(R.id.visit);
            viewHolder.text = convertView.findViewById(R.id.usage_time);
            viewHolder.avatar = convertView.findViewById(R.id.imageViewicon);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        AppModel app = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.pseudo.setText(app.getPseudo());
        viewHolder.text.setText(app.getText());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(app.getColor()));

        return convertView;
    }

    private class AppViewHolder{
        public TextView pseudo;
        public TextView text;
        public ImageView avatar;
    }
}
