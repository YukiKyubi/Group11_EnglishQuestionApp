package com.example.englishquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.englishquestion.model.Question;
import com.example.englishquestion.sqlite.QuestionDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class MainScreen extends AppCompatActivity implements View.OnClickListener{
    TextView txtQuestIndex, txtQuestTitle, txtAnswer1, txtAnswer2, txtAnswer3, txtAnswer4, exit, back;
    List<Question> list;
    Question currentQuestion;
    int currentQuestIndex = 0;
    String fileName = "";

    int questDrawable[] = {R.drawable.cat, R.drawable.chicken, R.drawable.tiger,
        R.drawable.dragon, R.drawable.mermaid, R.drawable.god, R.drawable.death, R.drawable.superman, R.drawable.unicorn, R.drawable.centaur, R.drawable.ninetailedfox, R.drawable.phoenix, R.drawable.ghost,
            R.drawable.shark, R.drawable.whale, R.drawable.dolphin, R.drawable.stingray, R.drawable.starfish, R.drawable.swordfish, R.drawable.seahorse, R.drawable.jellyfish, R.drawable.coral, R.drawable.turtle,
            R.drawable.christ_the_redeemer, R.drawable.eiffel, R.drawable.golden_bridge, R.drawable.great_wall, R.drawable.kremlin, R.drawable.merlion_park, R.drawable.one_pillar_pagoda, R.drawable.pisa, R.drawable.sphinx, R.drawable.statue_of_liberty,
            R.drawable.bed, R.drawable.bookshelves, R.drawable.clock ,R.drawable.chair, R.drawable.lamp, R.drawable.mirror, R.drawable.rug, R.drawable.sofa, R.drawable.table, R.drawable.window,
            R.drawable.bamboo, R.drawable.cactus, R.drawable.corn, R.drawable.fern, R.drawable.lily, R.drawable.mushroom, R.drawable.pine, R.drawable.rose, R.drawable.tulip, R.drawable.wheat,
            R.drawable.artist, R.drawable.astronaut, R.drawable.chef, R.drawable.doctor, R.drawable.engineer, R.drawable.fireman, R.drawable.nurse, R.drawable.police, R.drawable.teacher, R.drawable.worker,
            R.drawable.bicycle, R.drawable.car, R.drawable.coach, R.drawable.helicopter, R.drawable.motorbike, R.drawable.plane, R.drawable.sailboat, R.drawable.ship, R.drawable.train, R.drawable.truck,
            R.drawable.bag, R.drawable.board, R.drawable.book, R.drawable.calculator, R.drawable.crayon, R.drawable.notebook, R.drawable.pencil, R.drawable.pencil_sharpener, R.drawable.ruler, R.drawable.scissors,
            R.drawable.spear, R.drawable.claymore, R.drawable.catalyst, R.drawable.sword, R.drawable.bow, R.drawable.sheild, R.drawable.gun, R.drawable.hammer, R.drawable.axe, R.drawable.scythe};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        getWidgetsControl();
        list = getListQuestionByCatID();
        if(list.isEmpty()) {
            return;
        }

        //Đọc dữ liệu từ file
        String sdcard = this.getExternalFilesDir(null).getAbsolutePath() + "/" + fileName + ".txt";
        File file = new File(sdcard);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
                currentQuestIndex = Integer.parseInt(content + "");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setQuestionData(list.get(currentQuestIndex));

        //nút Back
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, CataList.class);
                writeFile();
                startActivity(intent);
            }
        });

        //nút Exit
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitDialog();
            }
        });
    }

    private void showExitDialog() {
        Dialog dialog = new Dialog(MainScreen.this);
        dialog.setContentView(R.layout.dialog_custom_exit);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.white_solid_radius_20dp));
        Button ok = dialog.findViewById(R.id.btnOK);
        Button cancel = dialog.findViewById(R.id.btnCancel);
        TextView message = dialog.findViewById(R.id.textView5);
        message.setText("Save your progress and exit?");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
                finishAffinity();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        writeFile();
        super.onBackPressed();
    }

    private void writeFile() {
        String sdcard = this.getExternalFilesDir(null).getAbsolutePath() + "/" + fileName + ".txt";
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard));
            writer.write(currentQuestIndex + "");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Lấy danh sách câu hỏi ở catalogy đã chọn
    private List<Question> getListQuestionByCatID() {
        List<Question> list;
        Intent intent = getIntent();
        String catID = intent.getStringExtra("catID");
        String catName = intent.getStringExtra("catName");
        fileName = catName;
        QuestionDAO questionDB = new QuestionDAO(this);
        questionDB.getQuestData();
        list = questionDB.getByCatID(catID);
        return list;
    }

    //Set dữ liệu câu hỏi và câu trả lời vào giao diện
    private void setQuestionData(Question question) {
        if(question == null) {
            return;
        }
        currentQuestion = question;
        txtAnswer1.setBackgroundResource(R.drawable.corners_radius_20dp_stroke_darkblue_blue);
        txtAnswer2.setBackgroundResource(R.drawable.corners_radius_20dp_stroke_darkblue_blue);
        txtAnswer3.setBackgroundResource(R.drawable.corners_radius_20dp_stroke_darkblue_blue);
        txtAnswer4.setBackgroundResource(R.drawable.corners_radius_20dp_stroke_darkblue_blue);
        int index = currentQuestIndex;
        String questIndex = "Question " + (index + 1);
        txtQuestIndex.setText(questIndex);
        txtQuestTitle.setText(question.getQuestTitle());
        txtAnswer1.setText(question.getAnswer1());
        txtAnswer2.setText(question.getAnswer2());
        txtAnswer3.setText(question.getAnswer3());
        txtAnswer4.setText(question.getAnswer4());

        rederAnimation();

        txtAnswer1.setOnClickListener(this);
        txtAnswer2.setOnClickListener(this);
        txtAnswer3.setOnClickListener(this);
        txtAnswer4.setOnClickListener(this);
    }

    private void rederAnimation() {
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        Animation drop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.drop);
        Animation fromLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_left);
        Animation fromRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.from_right);

        txtQuestIndex.startAnimation(drop);
        txtQuestTitle.startAnimation(fadeIn);
        txtAnswer1.startAnimation(fromLeft);
        txtAnswer2.startAnimation(fromRight);
        txtAnswer3.startAnimation(fromLeft);
        txtAnswer4.startAnimation(fromRight);
    }

    private void getWidgetsControl() {
        txtQuestIndex = findViewById(R.id.txtQuestIndex);
        txtQuestTitle = findViewById(R.id.txtQuestTitle);
        txtAnswer1 = findViewById(R.id.txtAnswer1);
        txtAnswer2 = findViewById(R.id.txtAnswer2);
        txtAnswer3 = findViewById(R.id.txtAnswer3);
        txtAnswer4 = findViewById(R.id.txtAnswer4);
        back = findViewById(R.id.back);
        exit = findViewById(R.id.exit);
    }

    //Khi click vào câu trả lời
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtAnswer1:
                txtAnswer1.setBackgroundResource(R.drawable.corners_radius_20dp_click);
                txtAnswer1.setTextColor(getResources().getColor(R.color.white));
                checkAnswer(txtAnswer1, currentQuestion, 1);
                break;
            case R.id.txtAnswer2:
                txtAnswer2.setBackgroundResource(R.drawable.corners_radius_20dp_click);
                txtAnswer2.setTextColor(getResources().getColor(R.color.white));
                checkAnswer(txtAnswer2, currentQuestion, 2);
                break;
            case R.id.txtAnswer3:
                txtAnswer3.setBackgroundResource(R.drawable.corners_radius_20dp_click);
                txtAnswer3.setTextColor(getResources().getColor(R.color.white));
                checkAnswer(txtAnswer3, currentQuestion, 3);
                break;
            case R.id.txtAnswer4:
                txtAnswer4.setBackgroundResource(R.drawable.corners_radius_20dp_click);
                txtAnswer4.setTextColor(getResources().getColor(R.color.white));
                checkAnswer(txtAnswer4, currentQuestion, 4);
                break;
        }
    }

    //Kiểm tra đáp án
    private void checkAnswer(TextView textView, Question question, int answer) {
        //Thực hiện sau 1s
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Nếu đúng thì sang câu hỏi tiếp theo, sai thì sẽ hiện thông báo
                if(question.getCorrectAnswer() == answer) {
                    textView.setBackgroundResource(R.drawable.corners_radius_20dp_true);
                    textView.setTextColor(getResources().getColor(R.color.white));
                    //Chuyển sang câu hỏi tiếp theo
                    nextQuestion();
                }
                else {
                    textView.setBackgroundResource(R.drawable.corners_radius_20dp_false);
                    textView.setTextColor(getResources().getColor(R.color.white));
                    //Hiện đáp án đúng trước, thực hiện thông báo game over sau 1s
                    showCorrectAnswer(question);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gameOver();
                        }
                    }, 1000);
                }
            }
        }, 1000);
    }

    //Thông báo game over
    private void gameOver() {
        Dialog dialog = new Dialog(MainScreen.this);
        dialog.setContentView(R.layout.dialog_custom_lose);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.white_solid_radius_20dp));
        Button ok = dialog.findViewById(R.id.btnOK);
        //Click ok và bắt đầu lại từ câu hỏi thứ nhất
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestIndex = 0;
                writeFile();
                dialog.dismiss();
                setQuestionData(list.get(currentQuestIndex));
                txtAnswer1.setTextColor(getResources().getColor(R.color.black));
                txtAnswer2.setTextColor(getResources().getColor(R.color.black));
                txtAnswer3.setTextColor(getResources().getColor(R.color.black));
                txtAnswer4.setTextColor(getResources().getColor(R.color.black));
            }
        });
        dialog.show();
    }

    //Hiện đáp án đúng
    private void showCorrectAnswer(Question question) {
        if(question == null) {
            return;
        }

        if(question.getCorrectAnswer() == 1) {
            txtAnswer1.setBackgroundResource(R.drawable.corners_radius_20dp_true);
            txtAnswer1.setTextColor(getResources().getColor(R.color.white));
        } else if(question.getCorrectAnswer() == 2) {
            txtAnswer2.setBackgroundResource(R.drawable.corners_radius_20dp_true);
            txtAnswer2.setTextColor(getResources().getColor(R.color.white));
        } else if(question.getCorrectAnswer() == 3) {
            txtAnswer3.setBackgroundResource(R.drawable.corners_radius_20dp_true);
            txtAnswer3.setTextColor(getResources().getColor(R.color.white));
        } else if(question.getCorrectAnswer() == 4) {
            txtAnswer4.setBackgroundResource(R.drawable.corners_radius_20dp_true);
            txtAnswer4.setTextColor(getResources().getColor(R.color.white));
        }
    }

    //Chuyển sang câu hỏi tiếp theo
    private void nextQuestion() {
        if(currentQuestIndex == list.size() - 1) {
            //Nếu là câu hỏi cuối cùng hiển thị thông báo cho câu đúng cuối cùng
            Dialog dialog = new Dialog(MainScreen.this);
            dialog.setContentView(R.layout.dialog_custom_the_last_true);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.white_solid_radius_20dp));
            TextView questImg = dialog.findViewById(R.id.questImg);
            TextView engMeaning = dialog.findViewById(R.id.engMeaning);
            TextView vietMeaning = dialog.findViewById(R.id.vietMeaning);
            String correctAnswer = getCorrectAnswer(list.get(currentQuestIndex));
            questImg.setBackgroundResource(questDrawable[list.get(currentQuestIndex).getQuestImg()]);
            Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            questImg.startAnimation(fade_in);
            engMeaning.setText(list.get(currentQuestIndex).getQuestTitle());
            vietMeaning.setText(correctAnswer);
            dialog.show();
            //Sau 3s hiển thị thông báo chiến thắng
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Dialog dialog = new Dialog(MainScreen.this);
                    dialog.setContentView(R.layout.dialog_custom_win);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.white_solid_radius_20dp));
                    Button ok = dialog.findViewById(R.id.btnOK);
                    //Click ok để trở về CatalogyList
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            currentQuestIndex = 0;
                            writeFile();
                            dialog.dismiss();
                            Intent intent = new Intent(MainScreen.this, CataList.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }
            }, 3000);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Hiển thị thông báo về câu đúng
                    Dialog dialog = new Dialog(MainScreen.this);
                    dialog.setContentView(R.layout.dialog_custom_true);
                    dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.white_solid_radius_20dp));
                    Button ok = dialog.findViewById(R.id.btnOK);
                    TextView questImg = dialog.findViewById(R.id.questImg);
                    TextView engMeaning = dialog.findViewById(R.id.engMeaning);
                    TextView vietMeaning = dialog.findViewById(R.id.vietMeaning);
                    questImg.setBackgroundResource(questDrawable[list.get(currentQuestIndex).getQuestImg()]);
                    Animation fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                    questImg.startAnimation(fade_in);
                    String correctAnswer = getCorrectAnswer(list.get(currentQuestIndex));
                    engMeaning.setText(list.get(currentQuestIndex).getQuestTitle());
                    vietMeaning.setText(correctAnswer);
                    //Click ok để chuyển sang câu tiếp theo
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            currentQuestIndex++;
                            setQuestionData(list.get(currentQuestIndex));
                            txtAnswer1.setTextColor(getResources().getColor(R.color.black));
                            txtAnswer2.setTextColor(getResources().getColor(R.color.black));
                            txtAnswer3.setTextColor(getResources().getColor(R.color.black));
                            txtAnswer4.setTextColor(getResources().getColor(R.color.black));
                        }
                    });
                    dialog.show();
                }

            }, 1000);
        }
    }

    //Trả về câu trả lời đúng của một Question
    private String getCorrectAnswer(Question question) {
        String correctAnswer = "";

        if(question.getCorrectAnswer() == 1) {
            correctAnswer = question.getAnswer1();
        } else if(question.getCorrectAnswer() == 2) {
            correctAnswer = question.getAnswer2();
        } else if(question.getCorrectAnswer() == 3) {
            correctAnswer = question.getAnswer3();
        } else if(question.getCorrectAnswer() == 4){
            correctAnswer = question.getAnswer4();
        }

        return correctAnswer;
    }
}