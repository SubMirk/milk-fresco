package f.star.iota.milk.ui.eshuushuu;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.rubensousa.floatingtoolbar.FloatingToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.PCBean;
import f.star.iota.milk.fresco.FrescoLoader;

public class ESHUUSHUUViewHolder extends BaseViewHolder<ESHUUSHUUBean> {

    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;
    @BindView(R.id.text_view_tag)
    TextView mTextViewTag;
    @BindView(R.id.card_view)
    CardView mCardView;

    public ESHUUSHUUViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<ESHUUSHUUBean> beans) {
        final ESHUUSHUUBean bean = beans.get(getAdapterPosition());
        FrescoLoader.load(mSimpleDraweeView, bean.getPreview());
        mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否下载")
                        .setNeutralButton("复制地址", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copy(bean.getUrl());
                            }
                        })
                        .setNegativeButton("浏览器打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                open(bean.getUrl());
                            }
                        })
                        .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                download(bean.getUrl(), bean.getPreview(),
                                        Menus.MENU_E_SHUUSHUU, null);
                            }
                        })
                        .show();
                return true;
            }
        });
        mTextViewTag.setText(bean.getSize());
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(getProcessingCompletedBeans(beans));
            }
        });
        ((FloatingToolbar) ButterKnife.findById((Activity) mContext, R.id.floating_toolbar)).setClickListener(new FloatingToolbar.ItemClickListener() {
            @Override
            public void onItemClick(MenuItem menuItem) {
                batchDownload(getProcessingCompletedBeans(beans));
            }

            @Override
            public void onItemLongClick(MenuItem menuItem) {

            }
        });
    }

    @Override
    protected List<PCBean> getProcessingCompletedBeans(List<ESHUUSHUUBean> beans) {
        List<PCBean> imgs = new ArrayList<>();
        for (ESHUUSHUUBean bean : beans) {
            imgs.add(new PCBean(bean.getUrl(), bean.getPreview(), Menus.MENU_E_SHUUSHUU,
                    "大小：" + bean.getSize() + "\n\n" +
                            "下载地址：" + bean.getUrl()));
        }
        return imgs;
    }
}
