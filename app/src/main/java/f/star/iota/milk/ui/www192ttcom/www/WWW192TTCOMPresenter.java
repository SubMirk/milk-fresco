package f.star.iota.milk.ui.www192ttcom.www;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;

public class WWW192TTCOMPresenter extends StringPresenter<List<WWW192TTCOMBean>> {


    public WWW192TTCOMPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected String dealUrl(String url) {
        if (url.contains("-0.html")) {
            if (url.contains("listinfo-1-")) {
                url = "http://www.192tt.com/new/";
            } else if (url.contains("listinfo-34-")) {
                url = "http://www.192tt.com/gq/";
            }
        }
        return url;
    }

    @Override
    protected List<WWW192TTCOMBean> dealResponse(String s, HashMap<String, String> headers) {
        List<WWW192TTCOMBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.mainer > div.piclist > ul > li");
        for (Element element : select) {
            WWW192TTCOMBean bean = new WWW192TTCOMBean();
            String preview = element.select("a > img").attr("lazysrc");
            bean.setPreview(preview);
            String url = element.select("a").attr("href");
            bean.setUrl(url);
            bean.setHeaders(headers);
            String description = element.select("a > span").text();
            bean.setDescription(description);
            String date = element.select("b.b1").text();
            bean.setDate(date);
            list.add(bean);
        }
        return list;
    }
}
