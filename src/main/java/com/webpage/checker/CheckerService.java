package com.webpage.checker;

import com.webpage.checker.helper.CheckerHelper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class CheckerService {

    int retrieveReviewCount(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.getElementsByClass(Checker.REVIEWS_ELEMENT);
        return els.size();
    }

    ArrayList<Integer> retrieveRoomNights(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements els = doc.getElementsByClass("table property-calendar-table");
        ArrayList<Integer> counts = new ArrayList<>();
        for (Element element : els) {
            int count = StringUtils.countMatches(element.toString(), "background-color:#FFCC66;");
            count += StringUtils.countMatches(element.toString(), "background: url('https://cdn.liverez.com/0/images/yahoo-calendar-departure-bg.gif')");
            count += StringUtils.countMatches(element.toString(), "background: url('https://cdn.liverez.com/0/images/yahoo-calendar-overlap-bg.gif')");
            counts.add(count);
        }
        return counts;
    }
}
