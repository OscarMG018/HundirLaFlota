package com.example.hundirlaflotaoscar;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.*;
import android.view.*;
import android.content.Context;

class Cell {
    
    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int row;
    private int column;
    private Context context;

    public Cell(Context context, int row, int column) {
        this.context = context;
        this.row = row;
        this.column = column;
    }
    
    public View getUI() {
        Button button = new Button(context);
        button.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        button.setOnClickListener(getActionListener());
        return button;
    }

    private String getCellText() {
        return String.valueOf(alphabet.charAt(row-1)) + column;
    }

    private View.OnClickListener getActionListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked on Cell at " + getCellText(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}

class EdgeChar {

    private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Context context;

    public EdgeChar(Context context) {
        this.context = context;
    }

    private String GetCharForPosition(int row, int column) {
        if (row == 0) {
            return String.valueOf(column);
        }
        return String.valueOf(alphabet.charAt(row-1));
    }

    public View getUI(int row, int column) {
        TextView textView = new TextView(context);
        textView.setText(GetCharForPosition(row, column));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        textView.setMinimumHeight(60);
        return textView;
    }
}

public class MainActivity extends AppCompatActivity {

    public final int COLUMNS = 9;
    public final int ROWS = 9;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableLayout table = findViewById(R.id.table);
        table.setStretchAllColumns(true);
        TableRow headerchars = new TableRow(this);
        Space space = new Space(this);
        headerchars.addView(space);
        headerchars.setMinimumHeight(60);
        for (int i = 1; i < COLUMNS+1; i++) {
            EdgeChar label = new EdgeChar(this);
            headerchars.addView(label.getUI(0, i));
        }
        headerchars.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
        table.addView(headerchars);
        for (int i = 1; i < ROWS+1; i++) {
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            for (int j = 0; j < COLUMNS+1; j++) {
                if (j == 0) {
                    EdgeChar label = new EdgeChar(this);
                    row.addView(label.getUI(i, j));
                } else {
                    Cell cell = new Cell(this,i,j);
                    row.addView(cell.getUI());
                }
            }
            table.addView(row);
        }
    }
}