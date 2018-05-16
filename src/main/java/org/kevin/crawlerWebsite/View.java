package org.kevin.crawlerWebsite;

import javafx.scene.layout.BorderPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Kevin.Z on 2018/5/10.
 */
//border: https://blog.csdn.net/xietansheng/article/details/78389211?utm_source=gold_browser_extension
public class View extends JFrame {
    private JTextField websiteField = new JTextField(); // 爬取网址
    private JTextField locationField = new JTextField(); // 本地保存路径
    private JTextArea processArea = new JTextArea(); // 爬取进程展示页面
    private String url;
    private String basePath;
    private static String hw = "hello world! 我是中文";

    public View() {
        JFrame frame = new JFrame();
        frame.setTitle("Crawler Website");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        // 页面相关控件
        JPanel basicInfoPanel = new JPanel(new BorderLayout());
        JPanel processPanel;
        JLabel websiteLabel = new JLabel(" website: ");
        JButton startCrawlerButton = new JButton(" Crawl  ");
        JLabel locationLabel = new JLabel(" location: ");
        JButton chooseLocationButton = new JButton("Choose");

        // 信息输入面板
        JPanel websiteInfoPanel = new JPanel(new BorderLayout());
        websiteInfoPanel.add(websiteLabel, BorderLayout.WEST);
        websiteInfoPanel.add(websiteField, BorderLayout.CENTER);
        websiteInfoPanel.add(startCrawlerButton, BorderLayout.EAST);
        basicInfoPanel.add(websiteInfoPanel, BorderLayout.NORTH);

        // 信息展示面板
        JPanel locationPanel = new JPanel(new BorderLayout());
        locationPanel.add(locationLabel, BorderLayout.WEST);
        locationField.setEnabled(false);
        locationPanel.add(locationField, BorderLayout.CENTER);
        locationPanel.add(chooseLocationButton, BorderLayout.EAST);
        basicInfoPanel.add(locationPanel, BorderLayout.SOUTH);

        // 主面板
        processPanel = new JPanel(new BorderLayout());
        processArea.setEnabled(false);
        processPanel.add(processArea, BorderLayout.CENTER);
        processPanel.setBorder(BorderFactory.createTitledBorder("processing"));
        basicInfoPanel.setBorder(BorderFactory.createLoweredBevelBorder());

        frame.add(basicInfoPanel, BorderLayout.NORTH);
        frame.add(processPanel, BorderLayout.CENTER);
        frame.setVisible(true);

        // 选择本地存储路径
        chooseLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showDialog(new JLabel(), "choose the path");
                if (result == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                File file = fileChooser.getSelectedFile();
                String basePath = file.getAbsolutePath();
                locationField.setText(basePath);
                locationField.revalidate();
            }
        });

        // 开始爬取网站
        startCrawlerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                url = websiteField.getText();
                if(url == null || url.trim().equals("")){
                    JOptionPane.showMessageDialog(null, "please input the location of the website.", "Before crawl", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!url.contains("http")){
                    JOptionPane.showMessageDialog(null, "please input correct format of the website.", "Before crawl", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                basePath = locationField.getText();
                if (basePath == null || basePath.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "please choose the location to store the website.", "Before crawl", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                crawl();
            }
        });
    }

    public void crawl(){
        //1. tell caller the resource has downloaded(css, html, js, ...): String returnProcess
            //2. get the link from HTML
                //3. get the HTML
        processArea.append(hw + "\n");
        processArea.revalidate();
    }
}
