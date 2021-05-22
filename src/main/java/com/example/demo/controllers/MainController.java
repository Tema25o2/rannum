package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    int rnd;
    int min;
    int max;

    @GetMapping("/hello")
    public String helloPage() { return "hellopage"; }

    @GetMapping("/main")
    public String mainPage() { return "mainpage"; }

    @PostMapping("/main")
    public String mainPagePost(@RequestParam String min, @RequestParam String max, Model model) {
        boolean iin = isItNumber(min, max);
        if(!iin) {
            model.addAttribute("errorvalue", "Слушай, если тебе нужен рандомный символ, иди дальше");
            return "errorpage";
        } else {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        if(this.min == this.max) {
            model.addAttribute("errorvalue", "Максимальное значение равно минимальному? Чел, это рандомайзер");
            return "errorpage";
        }
        if(this.min > this.max) {
            model.addAttribute("errorvalue", "Максимальное значение меньше минимального? Слушай, рыбка задом не плывёт");
            return "errorpage";
        }

        rnd = rnd(this.min, this.max);
        System.out.println("This you're random number: " + rnd);
        model.addAttribute("result", rnd);

        return "resultpage";
    }

    @GetMapping("/main/result")
    public String resultPage(Model model) {
        rnd = rnd(this.min, this.max);
        System.out.println("This you're random number: " + rnd + " (re-result)");
        model.addAttribute("result", rnd);

        return "resultpage";
    }

    public static boolean isItNumber(String min, String max) throws NumberFormatException {
        try {
            Integer.parseInt(min);
            Integer.parseInt(max);
            System.out.println("Yes, this is a number");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("No, this not a number");
            return false;
        }
    }

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
