package ee.bcs.valiit.controller;


import ee.bcs.valiit.tasks.Lesson2b;
import ee.bcs.valiit.tasks.Lesson2c;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Lesson2cController {
    @GetMapping("Lesson2c/sequence3n")
    public int sequence3n(@RequestParam("a") int a, @RequestParam("b") int b) {
        return Lesson2c.sequence3n(a, b); }

    @GetMapping("Lesson2c/getSeqLength")
    public int getSeqLength(@RequestParam("a") int a) {
    return Lesson2c.getSeqLength(a);
    }

    @GetMapping("Lesson2c/nextElement/{a}")
    public int nextElement(@PathVariable("a") int a) {
        return Lesson2c.nextElement(a);
    }

}

