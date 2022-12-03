import javax.swing.*;

public class LinkedList implements ListADT{
    Object e;
    MyNode first, current;
    JButton button[];

    public LinkedList(){
        List();
    }
    @Override
    public void List() {
        first = current = null;
    }

    @Override
    public void insert(Object e) throws Exception {
        MyNode p = new MyNode(e);
        if(isEmpty()){
            first = p;
            current = p;
        }
        else{
            p.setNextNode(current.getNextNode());
            current.setNextNode(p);
            current = p;
        }
    }

    @Override
    public void delete(Object key) throws Exception {
        MyNode previous;
        if(isEmpty()){
            throw  new Exception("list is Empty");
        }
        else {
            //noting to delete
            if (first == null) {
                return;
            }
            //if the node to delete is first node
            if (first.getData().equals(key)) {
                first = first.getNextNode();
                current = first;
                return;
            }
            //find previous and current node
            previous = first;
            current = first.getNextNode();
            while (current != null && !current.getData().equals(key)) {
                previous = current;
                current = current.getNextNode();
            }
            //if the node was not found
            if (current == null)
                return;
            previous.setNextNode(current.getNextNode());
            current = first;
        }
    }

    @Override
    public Object retrieve() throws Exception {
        if(isEmpty())
            throw new Exception("List is empty!");
        else
            return current.getData();
    }

    @Override
    public void update(Object e) throws Exception {
        if(isEmpty())
            throw  new Exception("list is Empty!");
        else{
            current.setData(e);
        }
    }

    @Override
    public void findFirst() throws Exception {
        if (isEmpty())
            throw new Exception("list is Empty!");
        else{
            current = first;
        }
    }

    @Override
    public void findNext() throws Exception {
        if(isEmpty())
            throw new Exception("list is Empty!");
        else{
            if(current.getNextNode() != null)
                current = current.getNextNode();
        }
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public MyNode findKey(Object key) {
        MyNode iterateNode = first;
        while(iterateNode != null){
            if(iterateNode.getData() == key){
                return iterateNode;
            }
            iterateNode = iterateNode.getNextNode();
        }
        return null;
    }
}
