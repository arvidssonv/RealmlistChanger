import java.awt.LayoutManager;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class RealmlistChanger {
    public static void switchServer(String server, String realmlistPath) {
        try {
            BufferedReader file = new BufferedReader(new FileReader(realmlistPath));
            StringBuffer inputBuffer = new StringBuffer();
            String line = "";
    
            while ((line = file.readLine()) != null) {
                if(line.contains(server)) {
                    line = line.replaceAll("#","");
                }
                else if (line.charAt(0) != '#') {
                    line = "#" + line;
                }
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            file.close();
            FileOutputStream fileOut = new FileOutputStream(realmlistPath);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } 
        
        catch (Exception e) {
            System.out.println("Problem reading realmlist.wtf");
        }
    }
    public static void main(String[] args) {
        String wowPath = "E:\\Games\\1.12 WoW\\"; // Path to WoW folder, in the format "C:\\Program Files (x86)\\World of Warcraft\\" 
        String realmlistPath = wowPath + "realmlist.wtf";
        String wowExecutablePath = wowPath + "WoW.exe";
        String line = "";

        JFrame frame = new JFrame("Realmist Changer");
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Realmlist");
        final JComboBox<String> cb = new JComboBox<String>();
        JButton btn = new JButton("Run");

        try {
            FileReader fileReader = new FileReader(realmlistPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("set realmlist"))
                    cb.addItem(line.replace("#",""));                
            }
            bufferedReader.close();
        }

        catch (FileNotFoundException ex) {
            System.out.println("Problem reading realmlist.wtf");
        }
        
        catch (IOException ex) {
            ex.printStackTrace();
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.add(lbl);        
        panel.add(cb);
        panel.add(btn);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String server = cb.getSelectedItem().toString();
                    switchServer(server, realmlistPath);
                    Runtime.getRuntime().exec(wowExecutablePath, null, new File(wowPath));                                            
                    System.exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }            
            }
        });
        cb.setVisible(true);
        lbl.setVisible(true);
        frame.setVisible(true);
        frame.pack();            
    }
}