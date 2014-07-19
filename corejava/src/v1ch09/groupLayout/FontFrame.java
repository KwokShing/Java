package groupLayout;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import javax.swing.*;

/**
 * A frame that uses a group layout to arrange font selection components.
 */
public class FontFrame extends JFrame
{
   public static final int TEXT_ROWS = 10;
   public static final int TEXT_COLUMNS = 20;

   private JComboBox<String> face;
   private JComboBox<Integer> size;
   private JCheckBox bold;
   private JCheckBox italic;
   private JScrollPane pane;
   private JTextArea sample;

   public FontFrame()
   {
      ActionListener listener = EventHandler.create(ActionListener.class, this, "updateSample"); 

      // construct components

      JLabel faceLabel = new JLabel("Face: ");

      face = new JComboBox<>(new String[] { "Serif", "SansSerif", "Monospaced", "Dialog",
            "DialogInput" });

      face.addActionListener(listener);

      JLabel sizeLabel = new JLabel("Size: ");

      size = new JComboBox<>(new Integer[] { 8, 10, 12, 15, 18, 24, 36, 48 });

      size.addActionListener(listener);

      bold = new JCheckBox("Bold");
      bold.addActionListener(listener);

      italic = new JCheckBox("Italic");
      italic.addActionListener(listener);

      sample = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      sample.setText("The quick brown fox jumps over the lazy dog");
      sample.setEditable(false);
      sample.setLineWrap(true);
      sample.setBorder(BorderFactory.createEtchedBorder());

      pane = new JScrollPane(sample);

      GroupLayout layout = new GroupLayout(getContentPane());
      setLayout(layout);
      layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                  layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                              GroupLayout.Alignment.TRAILING,
                              layout.createSequentialGroup().addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                          .addComponent(faceLabel).addComponent(sizeLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                          layout.createParallelGroup(
                                                GroupLayout.Alignment.LEADING, false)
                                                .addComponent(size).addComponent(face)))
                              .addComponent(italic).addComponent(bold)).addPreferredGap(
                        LayoutStyle.ComponentPlacement.RELATED).addComponent(pane)
                        .addContainerGap()));

      layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] { face, size });

      layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                  layout.createSequentialGroup().addContainerGap().addGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                              pane, GroupLayout.Alignment.TRAILING).addGroup(
                              layout.createSequentialGroup().addGroup(
                                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                          .addComponent(face).addComponent(faceLabel))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                          layout.createParallelGroup(
                                                GroupLayout.Alignment.BASELINE).addComponent(size)
                                                .addComponent(sizeLabel)).addPreferredGap(
                                          LayoutStyle.ComponentPlacement.RELATED).addComponent(
                                          italic, GroupLayout.DEFAULT_SIZE,
                               [E:\resources]
errorlist=
fileid=
flag=-1
isfolder=1
path=E:\resources\computer\C++\C++ Primer code\14\README.TXT
pickcode=
quickid=
target=U_1_0
totalsize=2353562592
transfersize=406204179
[E:\me]
errorlist=
fileid=DFF99D11EDD8643F03D139534ABA8CD0EB126351
flag=-1
isfolder=1
path=E:\me\相册\北京\100_2642.JPG
pickcode=53ca3159cd05b2f86600000f
quickid=F3A3E8AA66780F50B96D8185E3958E782397D066
target=U_1_0
totalsize=1734145947
transfersize=105641942
[E:\Computer]
errorlist=
fileid=
flag=-1
isfolder=1
path=
pickcode=
quickid=
target=U_1_0
totalsize=0
transfersize=0
[E:\me.rar]
fileid=
flag=-1
isfolder=0
path=
pickcode=53ca322686a51f723d000003
quickid=41A0BD34E71780953DA90290A0073BB229883473
target=U_1_0
totalsize=1699470