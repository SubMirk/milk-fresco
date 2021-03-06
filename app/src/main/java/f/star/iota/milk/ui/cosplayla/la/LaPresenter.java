package f.star.iota.milk.ui.cosplayla.la;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class LaPresenter extends StringPresenter<List<LaBean>> {
    public LaPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<LaBean> dealResponse(String s, HashMap<String, String> headers) {
        List<LaBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("body > div.box_talk > div.fleft > div:nth-child(1) > div.talk_pic.hauto > p > a > img");
        for (Element element : select) {
            LaBean bean = new LaBean();
            String url = element.attr("src");
            if (url.contains("?imageView")) {
                url = url.replace(url.substring(url.indexOf("?imageView"), url.length()), "").trim();
            }
            bean.setUrl(url);
            bean.setHeaders(headers);
            list.add(bean);
        }
        return list;
    }
}
