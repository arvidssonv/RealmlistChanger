import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
 
public class RealmlistChanger {
    
    // edits the realmlist.wtf file in order to make the game read our selected servers address.
    // using the '#' character, we make the game ignore the other lines in the file at launch.
    public static void selectServer(String selectedServer, String realmlistPath) {
        try {
        Files.write(Paths.get(realmlistPath), Files.lines(Paths.get(realmlistPath))
            .map(line -> {
                if (line.contains(selectedServer))
                    return new String(line.replace("#",""));
                else if (line.charAt(0) != '#')
                    return new String("#" + line);
                else
                    return line;
            })
            .collect(Collectors.joining("\n"))
            .toString()
            .getBytes());
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find " + realmlistPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // returns a list of all servers contained in the realmlist.wtf file.
    public static List<String> getListOfServers(String realmlistPath) {
        List<String> serverList = new ArrayList<>();
        try (Stream<String> realmlistStream = Files.lines(Paths.get(realmlistPath))) {
            serverList = realmlistStream
                .filter(line -> line.contains("set realmlist"))
                .map(line -> line.replace("#",""))
                .collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            System.out.println("Could not find " + realmlistPath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return serverList;
    }

    public RealmlistChanger () {
        String wowPath = "E:\\Games\\1.12 WoW\\";
        String realmlistPath = wowPath + "realmlist.wtf";
        String wowExecutablePath = wowPath + "WoW.exe";

        JFrame frame = new JFrame("Realmlist Changer");
        JPanel panel = new JPanel();
        JLabel lbl = new JLabel("Realmlist");
        final JComboBox<String> cb = new JComboBox<String>();
        JButton btn = new JButton("Launch");

        // populate the JComboBox with the available servers
        getListOfServers(realmlistPath).forEach(cb::addItem);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.add(lbl);        
        panel.add(cb);
        panel.add(btn);

        // launches the game with the selected servers address and then exits the program.
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedServer = cb.getSelectedItem().toString();
                    selectServer(selectedServer, realmlistPath);
                    Runtime.getRuntime().exec(wowExecutablePath, null, new File(wowPath));                                            
                    System.exit(0);
                } catch (FileNotFoundException ex) {
                    System.out.println("Could not find executable: " + wowExecutablePath);
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

    public static void main(String[] args) {
        new RealmlistChanger();               
    }
}