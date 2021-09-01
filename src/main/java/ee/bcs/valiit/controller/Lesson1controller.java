package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson1;
import ee.bcs.valiit.tasks.Lesson2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;

@RestController
public class Lesson1controller {

    @GetMapping("lesson1/somestring")
    public String someString() {
        return Lesson1.someString();
    }

    @GetMapping("lesson1/min/{a}/{b}")
    public int min(@PathVariable("a") int a, @PathVariable("b") int b) {
        return Lesson1.min(a, b);
    }

    @GetMapping("lesson1/max")
    public int max(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson1.max(a, b);
    }

    @GetMapping("lesson1/abs/{a}")
    public int abs(@PathVariable("a") int a) {
        return Lesson1.abs(a);
    }

    @GetMapping("lesson1/iseven")
    public boolean isEven(@RequestParam("a") int a) {
        return Lesson1.isEven(a);
    }

    @GetMapping("lesson1/min3/{a}/{b}/{c}")
    public int min3(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        return Lesson1.min3(a, b, c);
    }

    @GetMapping("lesson1/max3/{a}/{b}/{c}")
    public int max3(@PathVariable("a") int a, @PathVariable("b") int b, @PathVariable("c") int c) {
        return Lesson1.max3(a, b, c);
    }

    @GetMapping("Lesson2/sampleArray")
    public int[] sampleArray() {
        return Lesson2.sampleArray();
    }

    @GetMapping("Lesson2/firstn/{a}")
    public int[] firstN(@PathVariable("a") int a) {
        return Lesson2.firstN(a);
    }

    @GetMapping("Lesson2/generateArray")
    public int[] generateArray(@RequestParam("a") int a) {
        return Lesson2.generateArray(a);
    }
    @GetMapping("Lesson2/decreasingArray")
    public int[] decreasingArray(@RequestParam("a") int a) {
        return Lesson2.decreasingArray(a);
    }

    @GetMapping("Lesson2/yl3/{a}")
    public int[] yl3(@PathVariable("a") int a) {
        return Lesson2.yl3(a);
    }

}
