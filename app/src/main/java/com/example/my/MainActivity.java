package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nineimagelib.NineImageEntity;
import com.example.nineimagelib.QFNineImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<NineImageEntity> oneList=new ArrayList<>();
    List<NineImageEntity> twoList=new ArrayList<>();
    List<NineImageEntity> threeList=new ArrayList<>();
    List<NineImageEntity> fourList=new ArrayList<>();
    List<NineImageEntity> fiveList=new ArrayList<>();
    List<NineImageEntity> sixList=new ArrayList<>();
    List<NineImageEntity> sevenList=new ArrayList<>();
    List<NineImageEntity> eightList=new ArrayList<>();
    List<NineImageEntity> nineList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

        QFNineImage img1=findViewById(R.id.iv1);
        img1.setData(oneList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img2=findViewById(R.id.iv2);
        img2.setData(twoList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img3=findViewById(R.id.iv3);
        img3.setData(threeList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img4=findViewById(R.id.iv4);
        img4.setData(fourList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img5=findViewById(R.id.iv5);
        img5.setData(fiveList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {
                Toast.makeText(MainActivity.this,"点击了第"+(position+1)+"张图片",Toast.LENGTH_SHORT).show();
            }
        });
        QFNineImage img6=findViewById(R.id.iv6);
        img6.setData(sixList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img7=findViewById(R.id.iv7);
        img7.setData(sevenList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img8=findViewById(R.id.iv8);
        img8.setData(eightList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
        QFNineImage img9=findViewById(R.id.iv9);
        img9.setData(nineList, new QFNineImage.NineClicklistener() {
            @Override
            public void imageClick(List<NineImageEntity> infos, int position) {

            }
        });
    }

    private void initData() {
        DataEntity one=new DataEntity(1080,1080,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472139.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity two=new DataEntity(440,440,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472173.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity three=new DataEntity(1280,960,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472181.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity four=new DataEntity(933,700,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472367.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity five=new DataEntity(805,690,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472383.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity six=new DataEntity(640,640,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472394.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity seven=new DataEntity(640,640,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472404.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity eight=new DataEntity(667,1000,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472414.jpg?imageslim|imageView2/1/w/288/h/288");
        DataEntity nine=new DataEntity(640,640,"https://ojpoc641y.qnssl.com/20191018_2524_1571386472429.jpg?imageslim|imageView2/1/w/288/h/288");

        oneList.add(one);

        twoList.add(one);
        twoList.add(two);

        threeList.add(one);
        threeList.add(two);
        threeList.add(three);

        fourList.add(one);
        fourList.add(two);
        fourList.add(three);
        fourList.add(four);

        fiveList.add(one);
        fiveList.add(two);
        fiveList.add(three);
        fiveList.add(four);
        fiveList.add(five);

        sixList.add(one);
        sixList.add(two);
        sixList.add(three);
        sixList.add(four);
        sixList.add(five);
        sixList.add(six);

        sevenList.add(one);
        sevenList.add(two);
        sevenList.add(three);
        sevenList.add(four);
        sevenList.add(five);
        sevenList.add(six);
        sevenList.add(seven);

        eightList.add(one);
        eightList.add(two);
        eightList.add(three);
        eightList.add(four);
        eightList.add(five);
        eightList.add(six);
        eightList.add(seven);
        eightList.add(eight);

        nineList.add(one);
        nineList.add(two);
        nineList.add(three);
        nineList.add(four);
        nineList.add(five);
        nineList.add(six);
        nineList.add(seven);
        nineList.add(eight);
        nineList.add(nine);
    }
}
