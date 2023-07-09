package sk.jsontorecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<Book> bookList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private NestedScrollView nestedSV;
    private ProgressBar pbLoading;

    private static final String TAG = "MainActivity";

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this::SwipeOnRefresh);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        BookAdapter mAdapter = new BookAdapter(this, bookList);
        mRecyclerView.setAdapter(mAdapter);


        getItemsFromJSON();
    }

    private void SwipeOnRefresh() {
        getItemsFromJSON();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void getItemsFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Book book = new Book(this);
                book.setBookImageURL(jsonObject.getString("BookImageURL"));
                book.setBookISBN(jsonObject.getString("BookISBN"));
                book.setBookName(jsonObject.getString("BookName"));
                book.setBookAuthor(jsonObject.getString("BookAuthor"));
                book.setBookPublisher(jsonObject.getString("BookPublisher"));
                book.setBookInLibrary(jsonObject.getString("BookInLibrary").equals("1"));
                book.setBookIsRead(jsonObject.getString("BookRead").equals("1"));
                bookList.add(book);
            }
        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {
        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.books);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }
        } finally {
            if (inputStream == null)
                inputStream.close();
        }
        return new String(builder);
    }
}