package com.webpage.checker;

import com.webpage.checker.helper.CheckerHelper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class CheckerController {

    @GetMapping("/index")
    public String index() throws IOException {
        String url = Checker.TWO_BEDROOM;
//        String url = Checker.THREE_BEDROOM;
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.getElementsByClass("property-calendar-extreme-outer mrg-left-0 mrg-right-neg-50 row");
        return CheckerHelper.removeStuff(els.toString());
    }

}
