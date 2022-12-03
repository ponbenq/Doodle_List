import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppFrame extends JFrame implements ActionListener {
    JButton addButton, deleteButton, show;
    JList list;
    DefaultListModel listModel;
    JTextField text;
    LinkedList linkedList = new LinkedList();
    public AppFrame() throws Exception {
        //frame init
        this.setSize(400, 320);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //text field
        text = new JTextField();
        text.setBounds(20, 10, 100, 20);
        add(text);
        //add button
        addButton = new JButton("Add");
        addButton.setBounds(140, 10, 80, 20);
        addButton.addActionListener(this);
        add(addButton);
        //delete button
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(240, 10, 80, 20);
        deleteButton.addActionListener(this);
        add(deleteButton);
        //list
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setBounds(20, 50, 360, 200);
        add(list);
        //show button that show current data in linked list
        show = new JButton("show");
        show.setBounds(20, 260, 80, 20);
        show.addActionListener(this);
        add(show);

        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        try {
            if (e.getSource() == addButton) {
                String inputText = text.getText();

                //Create a Date format
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //get Current Date and time
                Date date = new Date();
                //combine format and string
                String formattedDate = dateFormat.format(date);
                String combineText = inputText + ": " + formattedDate;

                //add data to linked list
                linkedList.insert(inputText);
                listModel.addElement(combineText);
            } else if (e.getSource() == deleteButton) {
                int index = list.getSelectedIndex();
                if (index != -1) {
                    //concat string before use it as a key because I put time in str,
                    String text = "";
                    for(int i = 0; i < ((String)listModel.get(index)).length(); i++){
                        //separate with colon
                        if(((String)listModel.get(index)).charAt(i) == ':')
                            break;
                        text +=((String)listModel.get(index)).charAt(i);
                    }
                    //convert to Object type
                    Object obj = text;

                    linkedList.delete(obj);
                    listModel.remove(index);
                }
            } else if (e.getSource() == show) {
                if (linkedList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "List Empty!");
                } else {
                    JOptionPane.showMessageDialog(null, linkedList.retrieve());
                }
            }

        }catch (Exception ex){

        }
    }
}
