/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progressive_iterations;
import java.util.ArrayList;
import org.jfugue.*;


/**
 *
 * @author Owner
 */
public class IterationGenerator {
    public static void psequence() {
        ArrayList<Long>  dlist = new ArrayList<>();
        ArrayList<Integer>  plist = new ArrayList<>();
        Integer level = 1;
        Integer levelmax = 4;
        Long seed = new Long(1);
        Integer pitchseed = 1;
        Double increment = 2.0;
        Integer pitchoffset = 50;
        Pattern pattern = new Pattern();
        Integer list_counter = 0;
        Player player = new Player();
        Long msoffset = new Long(30);
        int tempo = 40;
        
        dlist.add(seed);
        plist.add(pitchseed);
        //System.out.println("about to run while loop");
        while (level < levelmax) {
            //System.out.println(level + " " + levelmax);
            ArrayList<Long>  append_list = new ArrayList<>();
            for(double i = 1; i <= increment; i++) {
                for (Long d : dlist) {
                    Double powered = Math.pow(increment, i);
                    Long poweredlong = powered.longValue();
                    append_list.add(poweredlong * d);    
                }
            }
            for (Long a: append_list) {
                dlist.add(a);
            }
            level++;
            
        }
        for (Long d : dlist) {
            System.out.print(d + ",");
        }
        System.out.println();
        level = 1;
        while (level < levelmax) {
            //System.out.println(level + " " + levelmax);
            ArrayList<Integer>  pappend_list = new ArrayList<>();
            for(int i = 0; i < increment; i++) {
                for (Integer p : plist) {
                    pappend_list.add(increment.intValue() + p);    
                }
            }
            for (Integer a: pappend_list) {
                plist.add(a);
            }
            level++;
        }
        pattern.addElement(new Tempo(tempo));
        byte instbyte = 70;
        pattern.addElement(new Instrument(instbyte));
        for (Integer p : plist) {
            //System.out.print(p + ",");
            byte pitchbyte = (byte)(p + pitchoffset);
            //System.out.print(dlist.get(list_counter) * msoffset + ",");
            double duration = dlist.get(list_counter) * 0.03125;
            Note note = new Note(pitchbyte, duration);
            pattern.addElement(note);
            list_counter++;
        }
        System.out.println();
        System.out.println(pattern.getMusicString());
        player.play(pattern);
        return;
        
    }
    
//    public static void main(String[] args) {
//        System.out.println("starting to run psequence");
//        psequence();
//    }        
}
