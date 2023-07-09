package sk.jsontorecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    LayoutInflater layoutInflater;

    private final List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Book book = bookList.get(position);

        viewHolder.textViewBookName.setText(book.getBookName());
        viewHolder.textViewBookAuthor.setText(book.getBookAuthor());
        viewHolder.textViewBookPublisher.setText(book.getBookPublisher());
        viewHolder.txtBookISBN.setText(book.getBookISBN());
        viewHolder.checkBoxBookIsRead.setChecked(book.getBookIsRead());
        viewHolder.checkBoxBookInLibrary.setChecked(book.getBookInLibrary());

        if (book.getBookImageURL() == null || book.getBookImageURL().equals("")) {
            Glide.with(layoutInflater.getContext()).asDrawable().load(R.drawable.no_img).into(viewHolder.imageView);
        } else {
            Glide
                    .with(layoutInflater.getContext())
                    .load(book.getBookImageURL())
                    .apply(new RequestOptions()
                            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .format(DecodeFormat.PREFER_ARGB_8888)).into(viewHolder.imageView);
        }

        viewHolder.itemCardView.startAnimation(AnimationUtils.loadAnimation(layoutInflater.getContext(),R.anim.anim_four ));

        viewHolder.itemView.setOnClickListener(view ->
                Toast.makeText(layoutInflater.getContext(), "Clicked to item " + position, Toast.LENGTH_SHORT).show());
    }


    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewBookName;
        private final TextView textViewBookAuthor;
        private final TextView textViewBookPublisher;
        private final TextView txtBookISBN;
        private final CheckBox checkBoxBookIsRead;
        private final CheckBox checkBoxBookInLibrary;
        private final ImageView imageView;
        private final CardView itemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemCardView=itemView.findViewById(R.id.itemCardView);

            textViewBookName = itemView.findViewById(R.id.textBookName);
            textViewBookAuthor = itemView.findViewById(R.id.textBookAuthor);
            textViewBookPublisher = itemView.findViewById(R.id.textBookPublisher);
            imageView = itemView.findViewById(R.id.imageView);
            txtBookISBN = itemView.findViewById(R.id.txtBookISBN);
            checkBoxBookIsRead = itemView.findViewById(R.id.checkBoxBookIsRead);
            checkBoxBookInLibrary = itemView.findViewById(R.id.checkBoxBookInLibrary);
        }
    }
}

