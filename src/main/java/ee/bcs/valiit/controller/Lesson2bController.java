package ee.bcs.valiit.controller;

import ee.bcs.valiit.tasks.Lesson2b;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lesson2bController {


    //http://localhost:8080/lesson2b/reverseArray?inputArray=1,2,3,4,5
@GetMapping("Lesson2b/reverseArray")
    public int[] reverseArray(int [] inputArray) {
    return Lesson2b.reverseArray(inputArray);
    }

    @GetMapping("Lesson2b/evenNumbers")
    public int[] evenNumbers(@RequestParam("n") int n){
    return Lesson2b.evenNumbers(n);
    }

    //http://localhost:8080/Lesson2b/min?a=9,2,3,4,5
    @GetMapping("Lesson2b/min")
    public int min(@RequestParam("a") int [] a) {
    return Lesson2b.min(a);
    }

    //http://localhost:8080/Lesson2b/max?a=9,2,3,4,5
    @GetMapping("Lesson2b/max")
    public int max(@RequestParam("a") int [] a) {
    return Lesson2b.max(a);
    }

    //http://localhost:8080/Lesson2b/sum?a=9,2,3,4,5
    @GetMapping("Lesson2b/sum")
    public int sum(@RequestParam("a") int [] a) {
        return Lesson2b.sum(a);
    }

    @GetMapping("Lesson2b/fibonacci/{n}")
    public int fibonacci(@PathVariable("n") int n) {
    return Lesson2b.fibonacci(n);
    }

    @GetMapping("Lesson2b/sequence3n")
    public int sequence3n(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson2b.sequence3n(a, b); }




}
