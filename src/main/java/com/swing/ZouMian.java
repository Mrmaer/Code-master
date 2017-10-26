package com.swing;


import com.bean.Bean;
import com.desk.Code;
import com.openurl.Open;
import com.read.Use;
import com.sql.Shuju;
import com.sqlsession.Getsql;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ZouMian {
    private JPanel zhongbu;
    private JPanel jpanelxiugai;
    private static JCheckBox qingkong;
    private static JCheckBox qingkongwen;
    private static JCheckBox zhiding;
    private static JCheckBox openurl;
    private static JCheckBox rednow;
    private JButton shuaixing;
    private  JPanel panel1;
    private ButtonGroup urlname;
    private static JFrame frame;
    private JRadioButton extranet;
    private JRadioButton internal;
    private JButton shangchu;
    private JCheckBox xiugai[];
    public ZouMian(){

        zhiding.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zhiding.isSelected()){
                    frame.setAlwaysOnTop(true);
                }else {
                    frame.setAlwaysOnTop(false);
                }

            }
        });
        shuaixing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SqlSession getsql = Getsql.geSession();
                final Shuju shuju = getsql.getMapper(Shuju.class);

                panel1.removeAll();
                jpanelxiugai.removeAll();
                if (rednow.isSelected()) {
                    Use use = getUse();
                    use.read();
                }

                List<Bean> beans = shuju.selectall();
                JRadioButton jRadioButton[] = new JRadioButton[beans.size()];

                 xiugai = new JCheckBox[beans.size()];
                for (int i = 0; i <beans.size(); i++) {
                    final String name = beans.get(i).getName();

                    jRadioButton[i] = new JRadioButton(name);
                    xiugai[i] = new JCheckBox(name);
                    jRadioButton[i].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            JRadioButton temp = (JRadioButton) e.getSource();
                            String dizhi =temp.getText();
                            dizhi = shuju.select(dizhi);
                            if (openurl.isSelected()){
                                Open open = Open.getOpen();
                                String url = null;
                                if (internal.isSelected())
                                    url = "http://"+dizhi +".com";
                                if (extranet.isSelected())
                                    url = "https://"+dizhi +".com";
                                open.url(url);
                            }
                                Code code = Code.getCode();
                                code.setSysClipboardText(dizhi);
                        }
                    });
                    Font ziti = new Font("宋体", Font.PLAIN, 16);
                    jRadioButton[i].setFont(ziti);
                    xiugai[i].setFont(ziti);
                    panel1.add(jRadioButton[i]);
                    jpanelxiugai.add(xiugai[i]);
                    urlname.add(jRadioButton[i]);

                }

                zhongbu.validate();
                zhongbu.repaint();
            }


        });

        shangchu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SqlSession getsql = Getsql.geSession();
                 Shuju shuju = getsql.getMapper(Shuju.class);
                rednow.setSelected(false);
                for (JCheckBox aXiugai : xiugai) {
                    if (aXiugai.isSelected()) {
                        String name = aXiugai.getText();
                        shuju.delect(name);
                        getsql.commit();
                    }

                }
            }
        });


    }

    private static Use getUse(){
        BeanFactory factory = new ClassPathXmlApplicationContext("Opera.xml");
        return (Use) factory.getBean("useOperating");
    }

    @SneakyThrows(Exception.class)
    public static void main() {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        final SqlSession getsql = Getsql.geSession();
        frame = new JFrame("ZouMian");
        frame.setContentPane(new ZouMian().zhongbu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(500,300);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                Shuju shuju = getsql.getMapper(Shuju.class);
                if (qingkong.isSelected())
                {
                    shuju.delectall();
                    getsql.commit();

                }

                if(qingkongwen.isSelected()){
                    Use use = getUse();
                    use.qing();
                }
                getsql.close();
            }
        });
    }

    {

        $$$setupUI$$$();
    }


    private void $$$setupUI$$$() {
        zhongbu = new JPanel();
        zhongbu.setLayout(new BorderLayout(0, 0));


        qingkong = new JCheckBox("清空数据");
        qingkongwen = new JCheckBox("清空表格");
        zhiding = new JCheckBox("置顶");
        rednow = new JCheckBox("读配置文件",true);
        openurl = new JCheckBox("打开并复制");
        extranet = new JRadioButton("外网");
        internal = new JRadioButton("内网");
        shangchu = new JButton("删除");
        ButtonGroup tfurl = new ButtonGroup();
        tfurl.add(extranet);
        tfurl.add(internal);
        internal.setSelected(true);
        JPanel panel2 = new JPanel();
        panel2.add(zhiding);
        panel2.add(qingkong);
        panel2.add(qingkongwen);
        panel2.add(openurl);
        panel2.add(extranet);
        panel2.add(internal);
        panel2.add(shangchu);
        panel2.add(rednow);
        zhongbu.add(panel2,BorderLayout.NORTH);

        JTabbedPane feng = new JTabbedPane();
        String name[] = {"正常","修改"};
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

        panel1 = new JPanel();
        panel1.setLayout(flowLayout);

        feng.addTab(name[0],null,panel1,"正常");

        jpanelxiugai = new JPanel();
        jpanelxiugai.setLayout(flowLayout);
        feng.addTab(name[1], jpanelxiugai);
        zhongbu.add(feng, BorderLayout.CENTER);

        shuaixing = new JButton("添加");
        zhongbu.add(shuaixing,BorderLayout.SOUTH);
        urlname = new ButtonGroup();

    }

    public JComponent $$$getRootComponent$$$() {
        return zhongbu;
    }
}
