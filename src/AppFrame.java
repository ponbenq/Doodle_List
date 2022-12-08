import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Scanner;

public class AppFrame extends JFrame implements ActionListener {
    private JButton addButton, deleteButton, show,showPoint;
    private JList list;
    private DefaultListModel listModel;
    private JTextField text;
    private LinkedList linkedList = new LinkedList();
    private JScrollPane scrollPane;
    public AppFrame() throws Exception {
        //frame init
        this.setSize(400, 320);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Doodle-List");
        this.getContentPane().setBackground(new Color(191,225,246));
//        // Load the background image from a file
//        BufferedImage backgroundImage = null;
//        try {
//            backgroundImage = ImageIO.read(new File("/Users/b4enq/IdeaProjects/ToDoList/bg-1.jpeg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Create a JPanel with the background image
//        BufferedImage finalBackgroundImage = backgroundImage;
//        JPanel panel = new JPanel() {
//            @Override
//            public void paintComponent(Graphics g) {
//                super.paintComponent(g);
//                // Resize the background image to fit the size of the JFrame
//                Image resizedImage = finalBackgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
//                g.drawImage(resizedImage, 0, 0, null);
//            }
//
//            @Override
//            public Dimension getPreferredSize() {
//                return new Dimension(400, 320);
//            }
//        };
//        this.setContentPane(panel);


        //text field
        text = new JTextField();
        text.setBounds(20, 10, 100, 25);
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
        list.setVisibleRowCount(12);
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setBounds(20, 50, 360, 200);
        add(scrollPane);

        //show button that show current data in linked list
        show = new JButton("show");
        show.setBounds(20, 260, 80, 20);
        show.addActionListener(this);
        add(show);
        //showPoint is show the selected not node
        showPoint = new JButton("pointer");
        showPoint.setBounds(120, 260, 80, 20);
        showPoint.addActionListener(this);
        add(showPoint);

        //load all list to JList
        loadList();

        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
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

                //put the input to file
                File file = new File("allList/"+LoginFrame.folderName+"/list_"+LoginFrame.folderName+".txt");
                Scanner scanner = new Scanner(file);
                String originalString = "";

                while(scanner.hasNextLine()){
                    originalString += scanner.nextLine()+"\n";
                }

                scanner.close();
                PrintStream printStream = new PrintStream(file);
                printStream.println(originalString+inputText);

                //add data to linked list
                linkedList.insert(inputText);
                listModel.addElement(combineText);
            } else if (e.getSource() == deleteButton) {
                int index = list.getSelectedIndex();
                if (index != -1) {
                    //get text from 
                    String text = (String)listModel.get(index);
                    System.out.println(text);
                    //convert to Object type
                    Object obj = text;

                    linkedList.delete(obj);
                    listModel.remove(index);

                    //remove in file
                    Path filePath = Paths.get("/Users/b4enq/IdeaProjects/ToDoList/allList/"+LoginFrame.folderName+"/list_"+LoginFrame.folderName+".txt");
                    List<String> lines = Files.readAllLines(filePath);
                    lines.remove(index);
                    Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
                }
            } else if (e.getSource() == show) {
                if (linkedList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "List Empty!");
                } else {
                    JOptionPane.showMessageDialog(null, linkedList.retrieve());
                }
            } else if (e.getSource() == showPoint) {
                int index = list.getSelectedIndex();
                if(index != -1){
                    JOptionPane.showMessageDialog(null, listModel.get(index));
                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public void loadList(){
        try {
            File listDirectory = new File("/Users/b4enq/IdeaProjects/ToDoList/allList/"+LoginFrame.folderName);
            if(!listDirectory.exists()){
                //create directory
                listDirectory.mkdir();
                //create new file in recent created directory
                File listFile = new File(listDirectory, "/list_" + LoginFrame.folderName + ".txt");
                listFile.createNewFile();
            }else{
                //read data from file
                Path filePath = Paths.get("/Users/b4enq/IdeaProjects/ToDoList/allList/"+LoginFrame.folderName+"/list_"+LoginFrame.folderName+".txt");
                List<String> lines = Files.readAllLines(filePath);
                if(lines.size() != 0) {
                    for (int i = 0; i <= lines.size() - 1; i++) {
                        if (lines.get(i) != "\n" && lines.get(i) != " ") {
                            linkedList.insert(lines.get(i));
                            listModel.addElement(lines.get(i));
                        }
                    }
                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
