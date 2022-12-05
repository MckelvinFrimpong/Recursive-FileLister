import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;


public class RecursiveListerFrame extends JFrame{
    JFrame frame = new JFrame();
    JPanel mainPnl;
    JPanel topPnl;
    JPanel midPnl;
    JPanel botPnl;

    JLabel titleLbl;



    JLabel fileLabel;
    JTextArea fileDisplay;

    JButton quitButton;
    JButton addButton;




    public RecursiveListerFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        createTopPanel();
        mainPnl.add(topPnl, BorderLayout.NORTH);
        createMiddlePanel();
        mainPnl.add(midPnl, BorderLayout.CENTER);
        createBottomPanel();
        mainPnl.add(botPnl, BorderLayout.SOUTH);

        add(mainPnl);

        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createTopPanel()
    {
        topPnl = new JPanel();
        titleLbl = new JLabel("Recursive File Searcher",JLabel.CENTER);
        titleLbl.setFont(new Font("SansSerif", Font.BOLD, 42));


        topPnl.add(titleLbl);
    }

    private void createMiddlePanel()
    {
        midPnl = new JPanel();

        fileDisplay = new JTextArea(60,60);

        midPnl.add(fileDisplay);

    }

    private void createBottomPanel()
    {
        botPnl = new JPanel();

        addButton = new JButton("Search File");
        quitButton = new JButton("Quit");

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        addButton.addActionListener((ActionEvent ae) -> {
            JFileChooser chooser = new JFileChooser();
            File chosenFile;
            String rec = "";

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                chosenFile = chooser.getSelectedFile();
                Search(chosenFile);

            }



        });

        botPnl.add(addButton);
        botPnl.add(quitButton);
    }

    private void Search(File toSearch) {
        File[] files = toSearch.listFiles();
        if (toSearch.isDirectory()) {
            for (File file : files) {
                if (file.isDirectory()) {
                    fileDisplay.append((" \ndirectory: " + file.toPath()));
                    Search(file);
                } else {
                    fileDisplay.append("\nfile: " + file.toPath());
                }
            }
        } else {
            fileDisplay.append("\nfile: " + toSearch.toPath());
        }
    }


}
