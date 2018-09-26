import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Main extends JFrame {
    private int timeRun = 0;
    private String lc;
    private ShowForecast page2;

    Main() {

        setTitle("Weather Forecaster");

        setSize(5000, 5000);
        initComponents();
        new Thread(() -> {
            while (timeRun == 0) {
                Calendar cal = new GregorianCalendar();
                int hour = cal.get(Calendar.HOUR);
                int min = cal.get(Calendar.MINUTE);
                int sec = cal.get(Calendar.SECOND);
                int AM_PM = cal.get(Calendar.AM_PM);
                String day_night;
                if (AM_PM == 1) {
                    day_night = "PM";
                } else {
                    day_night = "AM";
                }
                String time = hour + ":" + min + ":" + sec + " " + day_night;
                jLabel3.setText(time);
            }
        }).start();

    }

    private void initComponents() {

        JButton jButton1 = new JButton();
        JComboBox<String> jComboBox1 = new JComboBox<>();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        JTextArea search = new JTextArea();
        search.setPreferredSize(new Dimension(75, 50));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jButton1.setFont(new Font("Tahoma", 1, 11));
        jButton1.setForeground(new Color(102, 102, 255));
        jButton1.setText("Forecast");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (search.getText().equals("")) {
                    try {
                        page2 = new ShowForecast(lc);
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        page2 = new ShowForecast(search.getText());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
                page2.setVisible(true);
            }
        });

        jComboBox1.setModel(new DefaultComboBoxModel<>(
                new String[]{"---Select City---", "Ahmadabad", "Bangalore", "Chennai", "Hyderabad", "Kolkata", "Lucknow", "Mumbai", "Nagpur", "New Delhi", "Patna", "Ranchi", " ", " "}));
        jComboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Location:");
        jLabel2.setText("Search:");
        jLabel3.setText("Clock");


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addComponent(jButton1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(search, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                                        .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addComponent(jLabel3, GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(search, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                                        .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(54, 54, 54)
                                .addComponent(jButton1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addContainerGap())

        );

        pack();
    }

    private void jComboBox1ActionPerformed(ActionEvent evt) {
        JComboBox cb = (JComboBox) evt.getSource();
        lc = (String) cb.getSelectedItem();


    }


    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private JLabel jLabel3;

}
