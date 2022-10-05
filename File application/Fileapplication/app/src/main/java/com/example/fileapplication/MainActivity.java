package com.example.fileapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    Button btncreate, btnopen, btnsave;
    EditText txtinput;
    TextView wordCount;

    private static final String FILE_NAME = "example.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtinput = findViewById(R.id.editText);
        btncreate = findViewById(R.id.buttoncreate);
        btnopen = findViewById(R.id.buttonopen);
        btnsave = findViewById(R.id.buttonsave);
        wordCount = findViewById(R.id.wordCount);

        txtinput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = txtinput.getText().toString();
                text = text.replace("\n"," ");
                String[] textArray = text.split(" ");
                if(text.isEmpty())
                    wordCount.setText("Word count : 0");
                else
                    wordCount.setText("Word count : " + textArray.length);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = txtinput.getText().toString();
                FileOutputStream fos = null;

                try {
                    fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
                    fos.write(text.getBytes());

                    txtinput.getText().clear();

                    Toast.makeText(MainActivity.this,"Saved to "+getFilesDir() + "/" + FILE_NAME,Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(fos != null)
                    {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        btnopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while ((text = br.readLine()) != null)
                    {
                        sb.append(text).append("\n");

                    }
                    txtinput.setText(sb.toString());


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if (fis != null)
                    {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtinput.getText().toString();
                if (text.isEmpty()) {
                    Toast.makeText(MainActivity.this, "First Create the File", Toast.LENGTH_LONG).show();
                } else {

                    FileOutputStream fss = null;
                    try {
                        fss = openFileOutput(FILE_NAME, MODE_PRIVATE);

                        fss.write(text.getBytes());

                        txtinput.getText().clear();

                        Toast.makeText(MainActivity.this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (fss != null) {
                            try {
                                fss.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }

        });
    }
}