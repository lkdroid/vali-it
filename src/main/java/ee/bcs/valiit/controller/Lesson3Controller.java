package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson3;
import org.springframework.web.bind.annotation.*;

@RestController
public class Lesson3Controller {

    @GetMapping("Lesson3/factorial")
    public int factorial(@RequestParam("a") int a) {
        return Lesson3.factorial(a);
    }

    @GetMapping("Lesson3/reverseString")
    public String reverseString(@RequestParam("a") String a) {
        return Lesson3.reverseString(a);
    }

    @GetMapping("Lesson3/isPrime")
    public boolean isPrime(@RequestParam("a") int a) {
        return Lesson3.isPrime(a);
    }

    @GetMapping("Lesson3/sort")
    public int[] sort(@RequestParam("a") int[] a) {
        return Lesson3.sort(a);
    }

    @GetMapping("Lesson3/evenFibonacci/{a}")
    public int evenFibonacci(@PathVariable("a") int a) {
        return Lesson3.evenFibonacci(a);
    }
    @GetMapping("Lesson3/morseCode")
    public String morseCode(@RequestParam("a") String a){
        return Lesson3.morseCode(a);
    }
}
