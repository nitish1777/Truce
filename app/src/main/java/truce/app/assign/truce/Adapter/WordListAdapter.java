package truce.app.assign.truce.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import truce.app.assign.truce.DataClass.VocabData;
import truce.app.assign.truce.R;
import truce.app.assign.truce.connction.CustomVolleyRequest;

/**
 * Created by USER on 12-06-2016.
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private ImageLoader imageLoader;
    private Context context;

    //List of superHeroes
    List<VocabData> listVocab;

    public WordListAdapter(List<VocabData> listVocab, Context context) {
        super();
        //Getting all the superheroes
        this.listVocab = listVocab;
        this.context = context;
    }

    /*@Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_listitem, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_listitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        VocabData data = listVocab.get(position);

      //  Log.d("Adapter","Value of Ratio:-"+data.getRatio());
       // if(data.getRatio() < 0) {
            Log.d("Adapter","Hit");
            imageLoader = CustomVolleyRequest.getInstance(context).getImageLoader();
            imageLoader.get("http://appsculture.com/vocab/images/" + data.getId() + ".png", ImageLoader.getImageListener(holder.iv_image, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

            holder.iv_image.setImageUrl("http://appsculture.com/vocab/images/" + data.getId() + ".png", imageLoader);
            holder.tv_word.setText(data.getWord());
            holder.tv_meaning.setText(data.getMeaning());
       // }

    }


    @Override
    public int getItemCount() {
        return listVocab.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public NetworkImageView iv_image;
        public TextView tv_word;
        public TextView tv_meaning;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = (NetworkImageView) itemView.findViewById(R.id.iv_image);
            tv_word = (TextView) itemView.findViewById(R.id.tv_word);
            tv_meaning = (TextView) itemView.findViewById(R.id.tv_meaning);


        }
    }
}
