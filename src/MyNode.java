public class MyNode {
    private MyNode nextNode;
    private Object data;

    public MyNode(Object data){
        this(data, null);
    }
    public MyNode(Object data, MyNode node) {
        this.nextNode = node;
        this.data = data;
    }
    public Object getData(){
        return data;
    }
    public void setData(Object data){
        this.data = data;
    }
    public MyNode getNextNode(){
        return nextNode;
    }
    public void setNextNode(MyNode node){
        this.nextNode = node;
    }
}
