package study;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ios12 on 17/9/27.
 * BaseAdapter 适配器的使用
 */

public class BaseAdapterStudy<T> extends BaseAdapter{
    private T obj;//指定对象类型
    private List<T> mList;//数据源
    private LayoutInflater mInfalater;//布局装载器

    @Override
    public int getCount() {//获取适配器中数据集的个数
        return mList.size();
    }

    @Override
    public Object getItem(int i) {//获取数据集中与索引对应的数据项
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {//获取指定对应的 id
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {//获取一行 显示内容
        return null;
    }
}
