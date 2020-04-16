package Singleton;

import java.io.Serializable;

public enum  SingletonEnumDemo implements Serializable {
    INSTANCE;
    private String content;
    private SingletonEnumDemo(){
        System.out.println("枚举单例构造器执行....");
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return content;
    }

}
