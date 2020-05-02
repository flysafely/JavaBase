package JMM.Visibility;

public class ResourcesClass {
    volatile Integer num = 10;

    public void ChangeNum(int num){
        this.num = num;
    }
}
