package study;

import ListenerIm.SetOnListenerIm;

/**
 * Created by ios12 on 17/9/22.
 * 这是一个子类
 */

public class SonClass extends ParentClass {
    public String sonName;
    public int sonAge;
    public String sonInfo;

    public SonClass(){}

    public SonClass(String name,int age,String info){
        this.sonAge = age;
        this.sonInfo = info;
        this.sonName = name;
    }

    public void getSonInfo(){
        System.out.println("获取父类的信息：");
        System.out.println("姓名:"+this.sonName+"\n"+"年龄："+this.sonAge+"\n"+"子类信息："+this.sonInfo);
    }

    @Override
    public void getParentInfo() {
//        this.setParentAge(110);
//        this.setParentName("修改父类姓名");
        super.getParentInfo();
    }

    public SonClass(String parentName, int parentAge) {
        super(parentName, parentAge);//super 关键字，用来重写了父类的构造方法
    }

    public void useListenerIm(SetOnListenerIm fun){
        System.out.println("子类");
    };

    public static void main(String []args){
//        SonClass pa = new SonClass("我是子类",22,"我是子类，继承了父类");
        SonClass pa = new SonClass("我是子类,修改了父类",22);
        pa.getSonInfo();
        pa.getParentInfo();
        pa.useListenerIm(new SetOnListenerIm(){
            @Override
            public void setOnListener(String string) {
                super.setOnListener(string);
            }
        });
    }
}
