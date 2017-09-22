package study;

/**
 * Created by ios12 on 17/9/22.
 */

/**
 * 父类操作
 */
public class ParentClass {
    public String parentName;
    public int parentAge;

    public ParentClass() {
    }

    public ParentClass(String parentName, int parentAge) {
        this.parentName = parentName;
        this.parentAge = parentAge;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getParentAge() {
        return parentAge;
    }

    public void setParentAge(int parentAge) {
        this.parentAge = parentAge;
    }

    public void getParentInfo(){
        System.out.println("获取父类的信息：");
        System.out.println("姓名:"+parentName+"\n"+"年龄："+parentAge);
    }

    public static void main(String []args){
        ParentClass pa = new ParentClass();
        pa.getParentInfo();
    }
}
