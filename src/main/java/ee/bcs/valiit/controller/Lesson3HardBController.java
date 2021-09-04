package ee.bcs.valiit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Lesson3HardBController {

    Map<String, Double> pangaandmed = new HashMap<>();
    int x = 99;
    int y = 0;

    @GetMapping("Lesson3HardBController/{p}")
    public String sohimäng(@PathVariable("p") int p) {
        if (p > x) {
            String ylemine = String.valueOf(x);
            return "Sa juba pakkusid sellest väiksemat numbrit (" + ylemine + ") ja see oli liiga suur!";
        } else if (p < y) {
            String alumine = String.valueOf(y);
            return "Sa juba pakkusid sellest suuremat numbrit (" + alumine + ") ja see oli liiga väike!";
        } else if (x - p > p - y) {
            y = p;
            String vaike1 = String.valueOf(p);
            return vaike1 + " on liiga väike";
        } else if (x - p < p - y) {
            x = p;
            String suur = String.valueOf(p);
            return suur + " on liiga suur";
        } else if (x - p == p - y && x - p != 1) {
            y = p;
            String vaike = String.valueOf(p);
            return vaike + " on liiga väike";
        } else {
            String oige = String.valueOf(p);
            return p + " on õige!";
        }
    }
}
