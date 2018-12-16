package TP_Jėcka_Ernestas;

import java.awt.Color;

public class GuitarHeroLite {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        double StartFreq = 440.0;
        double FinalFreq;
        GuitarString string = new GuitarString(440.0);
        StdDraw.picture(0.5, 0.5, "C:\\Users\\Ernestas\\Documents\\NetBeansProjects\\MultisetProjektas\\Klavisai.png");

        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
 
                char key = StdDraw.nextKeyTyped();
                if(keyboard.contains(String.valueOf(key))){
                    int i = keyboard.indexOf(key);
                    FinalFreq = StartFreq * Math.pow(1.05956, i - 24);
                    string = new GuitarString(FinalFreq);
                    string.pluck();
                }
                
            }

            StdAudio.play(string.sample());

            string.tic();
        }
    }

}
