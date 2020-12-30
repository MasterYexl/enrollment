package com.yxl.enrollment.Tool;




import com.yxl.enrollment.Module.VCMod;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class VerificationCode {
    private static char[] strs;
    private static Random random = new Random();

    private static void doStrs(){
        strs=new char[59];
        int nub=0;
        for (char i='2';i<='9';i++){
            strs[nub++]=i;
        }
        for (char i='A';i<='Z';i++){
            strs[nub++]=i;
        }
        for (char i='a';i<='z';i++){
            if(i=='l') continue;
            strs[nub++]=i;
        }
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    public static int getRodom(int bottom, int up){
        return (int)Math.floor(Math.random()*(up-bottom))+bottom;
    }

    public static VCMod get() throws IOException {
        BufferedImage img = new BufferedImage(100,40,BufferedImage.TYPE_INT_RGB);
        Graphics g = img.getGraphics();
        g.setColor(new Color(244,246,250));
        g.fillRect(0,0,100,40);
        int wi = 0;
        if (strs==null) doStrs();
        StringBuilder code = new StringBuilder();
        for (int i=0;i<4;i++){
            int fontSize = (int)Math.floor(Math.random()*20)+14;
            g.setColor(getRandColor(0,255));
            int zf;
            if (i%2==0) zf=1;
            else zf=-1;
            g.drawOval(zf*getRodom(0,60),zf*getRodom(0,20),getRodom(80,160),getRodom(80,160));
            int sz = (int) Math.floor(Math.random() * strs.length);
            char c = strs[sz];
            int hit = (int)Math.floor(Math.random()*(40-fontSize))+fontSize;
            wi+=(int) Math.floor(Math.random()*10);
            code.append(c);
            g.setFont(new Font("Tahoma",Font.ITALIC, fontSize));
            g.drawString(c+"",wi,hit);
            wi+=15;
        }
        VCMod vcMod = new VCMod();
        vcMod.setCode(code.toString());
        vcMod.setImg(img);
        return vcMod;
    }

    public static void main(String[] args) {
        try {
            get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
