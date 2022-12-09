package com.example.englishquestion.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.englishquestion.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
    private SQLiteDatabase db;
    public QuestionDAO(Context context){
        DBHelper helper= new DBHelper(context);

        db=helper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Question> get(String sql, String ...selectArgs){
        List<Question> list= new ArrayList<>();
        Cursor cursor= db.rawQuery(sql,selectArgs);

        while (cursor.moveToNext()){
            Question question= new Question();
            question.setQuestIndex(cursor.getString(cursor.getColumnIndex("questIndex")));
            question.setQuestTitle(cursor.getString(cursor.getColumnIndex("questTitle")));
            question.setAnswer1(cursor.getString(cursor.getColumnIndex("answer1")));
            question.setAnswer2(cursor.getString(cursor.getColumnIndex("answer2")));
            question.setAnswer3(cursor.getString(cursor.getColumnIndex("answer3")));
            question.setAnswer4(cursor.getString(cursor.getColumnIndex("answer4")));
            question.setCorrectAnswer(cursor.getInt(cursor.getColumnIndex("correctAnswer")));
            question.setAtCatID(cursor.getString(cursor.getColumnIndex("atCatID")));
            question.setQuestImg(cursor.getInt(cursor.getColumnIndex("questImg")));
            list.add(question);
        }
        return  list;
    }

    public  List<Question> getByCatID(String atCatID){
        String sql="SELECT * FROM Question WHERE atCatID = ? ";
        return get(sql, atCatID);
    }

    public  List<Question> getAll(){
        String sql="SELECT * FROM Question";
        return get(sql);
    }



    public long insert(Question question){
        ContentValues values = new ContentValues();
        values.put("questIndex", question.getQuestIndex());
        values.put("questTitle", question.getQuestTitle());
        values.put("answer1", question.getAnswer1());
        values.put("answer2", question.getAnswer2());
        values.put("answer3", question.getAnswer3());
        values.put("answer4", question.getAnswer4());
        values.put("correctAnswer", question.getCorrectAnswer());
        values.put("atCatID", question.getAtCatID());
        values.put("questImg", question.getQuestImg());


        return  db.insert("Question", null, values);
    }

    public void getQuestData(){
        //Animal
        Question question1 = new Question("1","Cat","Con mèo","Con chó","Con lợn","Con hổ",1,"1",0);
        insert(question1);

        Question question2 = new Question("2","Chicken","Con mèo","Con gà","Con lợn","Con hổ",2,"1",1);
        insert(question2);

        Question question3 = new Question("3","Tiger","Con lợn","Con chó","Con chim","Con hổ",4,"1",2);
        insert(question3);

        //Fiction
        Question question4 = new Question("4","Dragon","Rắn ba đầu","Khủng long","Rồng","Đại bàng",3,"2",3);
        insert(question4);

        Question question5 = new Question("5","Mermaid","Người cá","Cá voi","Người nhện","Người dơi",1,"2",4);
        insert(question5);

        Question question6 = new Question("6","God","Vua","Thần","Người hầu","Nữ thần",2,"2",5);
        insert(question6);

        Question question7 = new Question("7","Death","Địa ngục","Ma thần","Lưỡi hái","Tử thần",4,"2",6);
        insert(question7);

        Question question8 = new Question("8","Superman","Người sắt","Người chim","Siêu nhân","Dân cư",3,"2",7);
        insert(question8);

        Question question9 = new Question("9","Unicorn","Ma sói","Kỳ lân","Phượng hoàng","Rồng",2,"2", 8);
        insert(question9);

        Question question10 = new Question("10","Centaur","Người ngựa","Ngựa 1 sừng","Ngựa bay","Cá ngựa",1,"2",9);
        insert(question10);

        Question question11 = new Question("11","Nine-tailed fox","Răn 9 đầu","Rồng 3 đầu","Cáo 9 đuôi","Cho 3 đầu",3,"2",10);
        insert(question11);

        Question question12 = new Question("12","Phoenix","Chim ưng","Khủng long bay","Phượng hoàng","Người chim",3,"2",11);
        insert(question12);

        Question question13 = new Question("13","Ghost","Thần chết","Quỷ đầu loa","Rết tinh","Ma",4,"2",12);
        insert(question13);

        //Ocean
        Question question14 = new Question("14","Shark","Cá mập","Rắn nước","Rùa biển","Bạch tuộc",1,"3",13);
        insert(question14);

        Question question15 = new Question("15","Whale","Rùa biển","Cá ngựa","Mực ống", "Cá voi", 4,"3",14);
        insert(question15);

        Question question16 = new Question("16","Dolphin","Cá heo","Rắn nước","Rùa biển","Cá mập",1,"3",15);
        insert(question16);

        Question question17 = new Question("17","Stingray","Cá heo","Cá đuối","Cá voi","Cá chim",2,"3",16);
        insert(question17);

        Question question18 = new Question("18","Starfish","Cua","Cá heo","Sao biển","Cá đuối",3,"3",17);
        insert(question18);

        Question question19 = new Question("19","Swordfish","Rùa biển","Cá ngựa","Cá kiếm", "San hô", 3,"3",18);
        insert(question19);

        Question question20 = new Question("20","Seahorse","Cá ngừ","Cá ngựa","Sao biển","Cá trình",2,"3",19);
        insert(question20);

        Question question21 = new Question("21","Jellyfish","Cầu gai","Rắn nước","Con sứa","Cá mập",3,"3",20);
        insert(question21);

        Question question22 = new Question("22","Coral","San hô","Cá heo","Sao biển","Cá đuối",1,"3",21);
        insert(question22);

        Question question23 = new Question("23","Turtle","Cá hề","Cá lồng đèn","Sao biển","Rùa biển",4,"3",22);
        insert(question23);

        //Place
        Question q1= new Question("24","Christ The Redeemer","Tượng chúa Kaito","Tháp Eiffel","Vạn lý trường thành","Tháp nghiêng Pisa",1,"4",23);
        insert(q1);

        Question q2= new Question("25","Eiffel","Cung điện mùa đông","Tháp Eiffel","Tượng nhân sư","Khải hoàn môn",2,"4",24);
        insert(q2);

        Question q3= new Question("26","Golden Bridge","Cầu Chương Dương","Cầu Nhật Tân","Cầu Long Biên","Cầu Vàng",4,"4",25);
        insert(q3);

        Question q4= new Question("27","Great Wall","Vạn lý trường thành","Tháp Eiffel","Tượng nhân sư","Khải hoàn môn",1,"4",26);
        insert(q4);

        Question q5= new Question("28","Kremlin","Tượng nhân sư","Tháp Eiffel","Cung điện mùa đông","Khải hoàn môn",3,"4",27);
        insert(q5);

        Question q6= new Question("29","Merlion Park","Cầu vàng","Tượng nhân sư","Công viên Merlion","Khải hoàn môn",3,"4",28);
        insert(q6);

        Question q7= new Question("30","One Pillar Pagoda","Chùa Một Cột","Chùa Trấn Quốc","Chùa Phật Tích","Chùa Dâu",1,"4",29);
        insert(q7);

        Question q8= new Question("31","Pisa","Cung điện mùa đông","Tháp Eiffel","Tượng nhân sư","Tháp nghiêng Pisa",4,"4",30);
        insert(q8);

        Question q9= new Question("32","Sphinx","Cung điện mùa đông","Tháp Eiffel","Tượng nhân sư","Khải hoàn môn",3,"4",31);
        insert(q9);

        Question q10= new Question("33","Statue Of Liberty","Tượng nữ thần tự do","Tháp Eiffel","Chùa Một Cột","Tháp nghiêng Pisa",1,"4",32);
        insert(q10);

        //Home
        Question q11= new Question("34","Bed","Cái giường","Cái gương","Cái bàn","Cái cửa sổ",1,"5",33);
        insert(q11);

        Question q12= new Question("35","Bookshelves","Cái ghế","Ghế sofa","Cái giá sách","Cái giường",3,"5",34);
        insert(q12);

        Question q13= new Question("36","Clock","Cái tivi","Cái rèm","Cái thảm","Cái đồng hồ",4,"5",35);
        insert(q13);

        Question q14= new Question("37","Chair","Cái giường","Cái ghế","Cái bàn","Cái thảm",2,"5",36);
        insert(q14);

        Question q15= new Question("38","Lamp","Cái đèn ngủ","Cái gương","Cái giá sách","Cái bàn",1,"5",37);
        insert(q15);

        Question q16= new Question("39","Mirror","Cái giường","Cái gương","Cái bàn","Cái cửa sổ",2,"5",38);
        insert(q16);

        Question q17= new Question("40","Rug","Cái ghế","Cái đèn","Cái cửa sổ","Cái thảm",4,"5",39);
        insert(q17);

        Question q18= new Question("41","Sofa","Cái tivi","Cái bếp","Cái ghế sofa","Cái đèn",3,"5",40);
        insert(q18);

        Question q19= new Question("42","Table","Cái bàn","Cái ghế","Cái đồng hồ","Cái kính",1,"5",41);
        insert(q19);

        Question q20= new Question("43","Window","Cái giường","Cái gương","Cái bàn","Cái cửa sổ",4,"5",42);
        insert(q20);

        //Plant
        Question q21= new Question("44","Bamboo","Cây tre","Cây nấm","Hoa hồng","Hoa huệ",1,"6",43);
        insert(q21);

        Question q22= new Question("45","Cactus","Cây tre","Cây xương rồng","Cây thông","Cây táo",2,"6",44);
        insert(q22);

        Question q23= new Question("46","Corn","Cây nhãn","Cây vải","Cây lúa","Cây ngô",4,"6",45);
        insert(q23);

        Question q24= new Question("47","Fern","Cây dương xỉ","Cây lúa mạch","Hoa hồng","Hoa ly",1,"6",46);
        insert(q24);

        Question q25= new Question("48","Lily","Cây lê","Cây nấm","Hoa ly","Hoa huệ",3,"6",47);
        insert(q25);

        Question q26= new Question("49","Mushroom","Cây tre","Cây nấm","Hoa hồng","Hoa sen",2,"6",48);
        insert(q26);

        Question q27= new Question("50","Pine","Cây thông","Cây ô liu","Cây nho","Cây nêu",1,"6",49);
        insert(q27);

        Question q28= new Question("51","Rose","Hoa sen","Hoa cúc","Hoa tulip","Hoa hồng",4,"6",50);
        insert(q28);

        Question q29= new Question("52","Tulip","Hoa mai","Cây tulip","Hoa đào","Hoa huệ",2,"6",51);
        insert(q29);

        Question q30= new Question("53","Wheat","Cây tre","Cây lúa mạch","Cây táo","Cây khế",2,"6",52);
        insert(q30);

        //Job
        Question q31= new Question("54","Artist","Bác sĩ","Kỹ sư","Nông dân","Họa sĩ",4,"7",53);
        insert(q31);

        Question q32= new Question("55","Astronaut","Phi hành gia","Bác sĩ","Công nhân","Họa sĩ",1,"7",54);
        insert(q32);

        Question q33= new Question("56","Chef","Bác sĩ","Kỹ sư","Đầu bếp","Họa sĩ",3,"7",55);
        insert(q33);

        Question q34= new Question("57","Doctor","Bác sĩ","Kỹ sư","Nông dân","Họa sĩ",1,"7",56);
        insert(q34);

        Question q35= new Question("58","Engineer","Đầu bếp","Kỹ sư","Cảnh sát","Y tá",2,"7",57);
        insert(q35);

        Question q36= new Question("59","Fireman","Lính cứu hỏa","Công nhân","Phi hành gia","Đầu bếp",1,"7",58);
        insert(q36);

        Question q37= new Question("60","Nurse","Bác sĩ","Kỹ sư","Y tá","Họa sĩ",3,"7",59);
        insert(q37);

        Question q38= new Question("61","Police","Phi hành gia","Lính cứu hỏa","Đầu bếp","Cảnh sát",4,"7",60);
        insert(q38);

        Question q39= new Question("62","Teacher","Giáo viên","Cảnh sát","Kỹ sư","Họa sĩ",1,"7",61);
        insert(q39);

        Question q40= new Question("63","Worker","Bác sĩ","Kỹ sư","Nông dân","Công nhân",4,"7",62);
        insert(q40);

        //Transport
        Question q41= new Question("64","Bicycle","Xe máy","Xe ô tô","Xe đạp","Máy bay",3,"8",63);
        insert(q41);

        Question q42= new Question("65","Car","Tàu hỏa","Xe ô tô","Thuyền buồm","Xe buýt",2,"8",64);
        insert(q42);

        Question q43= new Question("66","Coach","Xe khách","Xe đạp","Xe taxi","Máy bay",1,"8",65);
        insert(q43);

        Question q44= new Question("67","Helicopter","Xe máy","Trực thăng","Xe đạp","Tàu hỏa",2,"8",66);
        insert(q44);

        Question q45= new Question("68","Motorbike","Xe máy","Xe ô tô","Xe đạp","Máy bay",1,"8",67);
        insert(q45);

        Question q46= new Question("69","Plane","Thuyền buồm","Tàu thủy","Tàu hỏa","Máy bay",4,"8",68);
        insert(q46);

        Question q47= new Question("70","Sailboat","Xe đạp","Thuyền buồm","Xe buýt","Xe taxi",2,"8",69);
        insert(q47);

        Question q48= new Question("71","Ship","Tàu thủy","Xe khách","Xe tải","Xe ô tô",1,"8",70);
        insert(q48);

        Question q49= new Question("72","Train","Xe taxi","Tàu hỏa","Xe máy","Thuyền buồm",2,"8",71);
        insert(q49);

        Question q50= new Question("73","Truck","Xe máy","Xe ô tô","Xe đạp","Xe tải",4,"8",72);
        insert(q50);

        //School stationery
        Question q51= new Question("74","Bag","Thước kẻ","Cặp sách","Bút chì","Cục tẩy",2,"9",73);
        insert(q51);

        Question q52= new Question("75","Board","Bảng đen","Bút màu","Gọt bút chì","Cục tẩy",1,"9",74);
        insert(q52);

        Question q53= new Question("76","Book","Thước kẻ","Máy tính","Quyển sách","Cặp sách",3,"9",75);
        insert(q53);

        Question q54= new Question("77","Calculator","Quyển sách","Cặp sách","Bút màu","Máy tính",4,"9",76);
        insert(q54);

        Question q55= new Question("78","Crayon","Bút màu","Cái kéo","Bút chì","Cục tẩy",1,"9",77);
        insert(q55);

        Question q56= new Question("79","Notebook","Quyển vở","Cặp sách","Quyển sách","Bảng đen",1,"9",78);
        insert(q56);

        Question q57= new Question("80","Pencil","Thước kẻ","Cặp sách","Bút chì","Cục tẩy",3,"9",79);
        insert(q57);

        Question q58= new Question("81","Pencil Sharpener","Cái kéo","Bảng đen","Gọt bút chì","Máy tính",3,"9",80);
        insert(q58);

        Question q59= new Question("82","Ruler","Thước kẻ","Quyển sách","Quyển vở","Cái kéo",1,"9",81);
        insert(q59);

        Question q60= new Question("83","Scissors","Cái thước","Cặp sách","Bút chì","Cái kéo",4,"9",82);
        insert(q60);

        //Weapon
        Question q84= new Question("84","Spear","Que gỗ","Kiếm","Súng","Thương",4,"10",83);
        insert(q84);

        Question q85= new Question("85","Claymore","Cung tên","Trọng kiếm","Trảo thích","Khiên",2,"10",84);
        insert(q85);

        Question q86= new Question("86","Catalyst","Rìu","Kiếm","Pháp khí","Ám khí",3,"10",85);
        insert(q86);

        Question q87= new Question("87","Sword","Nỏ","Ám khí","Hộ giáp","Kiếm",4,"10",86);
        insert(q87);

        Question q88= new Question("88","Bow","Cung tên","Đao","Hộ giáp","Thương",1,"10",87);
        insert(q88);

        Question q89= new Question("89","Sheild","Khiên","Trọng kiếm","Trảo thích","Nỏ",1,"10",88);
        insert(q89);

        Question q90= new Question("90","Gun","Nỏ","Ám khí","Hộ giáp","Súng",4,"10",89);
        insert(q90);

        Question q91= new Question("91","Hammer","Trọng kiếm","Búa","Nón","Kiếm",2,"10",90);
        insert(q91);

        Question q92= new Question("92","Axe","Trọng kiếm","Nón","Rìu","Kiếm",3,"10",91);
        insert(q92);

        Question q93= new Question("93","Scythe","Lưỡi hái","Đao","Hộ giáp","Thương",1,"10",92);
        insert(q93);
    }
}
