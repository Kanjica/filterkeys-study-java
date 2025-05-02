package k;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener; 
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

	public static void main(String[] args) {

        Registro registro = new Registro();
        Campos valoresPadrao = new Campos();
        
        valoresPadrao.setAutoRepeatDelay("1000");
        valoresPadrao.setAutoRepeatRate("500");
        valoresPadrao.setBounceTime("0");
        valoresPadrao.setDelayBeforeAcceptance("1000");
        valoresPadrao.setFlags("122");
        
		JFrame janela = new JFrame();
		janela.setSize(360, 464);
		janela.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		janela.setLocationRelativeTo(null);
		janela.setLayout(null);
		janela.setResizable(false);
		janela.setMaximizedBounds(new Rectangle(0, 0, 1, 1));
		
		janela.setTitle("Filter Keys Setter");
		
		UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 11));
		UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 11));
		UIManager.put("RadioButton.font", new Font("Arial", Font.PLAIN, 11));
		UIManager.put("CheckBox.font", new Font("Arial", Font.PLAIN, 11));
		UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 11));
		
		
		
		//settings
		JPanel settings = new JPanel();
		settings.setLayout(null);
		settings.setBounds(10,8,190,260);
		
		TitledBorder bordaSettings = BorderFactory.createTitledBorder(" Settings ");
		bordaSettings.setTitleFont(new Font("Arial", Font.PLAIN, 11));
        bordaSettings.setTitleJustification(TitledBorder.LEFT); 
        bordaSettings.setTitlePosition(TitledBorder.TOP); 
        settings.setBorder(bordaSettings);
        
        JRadioButton textoA = new JRadioButton("<html> Ignore quick keystrokes<br>and/or set the repeat rate </html>");
        textoA.setBounds(10, 15, 170, 35);
        textoA.setSelected(true);

        JLabel ms1Txt = new JLabel(" Ignore under:                    ms");
        ms1Txt.setBounds(32, 38, 400, 60);
        
        JLabel ms2Txt = new JLabel("Repeat delay:                     ms");
        ms2Txt.setBounds(32, 68, 400, 60);
        
        JLabel ms3Txt = new JLabel("  Repeat rate:                     ms");
        ms3Txt.setBounds(32, 98, 400, 60);
        
        JTextField ms1 = new JTextField(registro.getCampos().getDelayBeforeAcceptance());
        ms1.setBounds(105,60,50,20);
        JTextField ms2 = new JTextField(registro.getCampos().getAutoRepeatDelay());
        ms2.setBounds(105,90,50,20);
        JTextField ms3 = new JTextField(registro.getCampos().getAutoRepeatRate());
        ms3.setBounds(105,120,50,20);
        
        double x = 2;
        String labelText = "(" + String.valueOf(x) + " per second)";
        
        JLabel perSecond = new JLabel(labelText);
        perSecond.setHorizontalAlignment(SwingConstants.RIGHT);
        perSecond.setBounds(72, 160, 100, 20);
        
        JRadioButton textoB = new JRadioButton("<html> Ignore keystrokes repeated<br>faster than:</html>");
        textoB.setBounds(10, 180, 170, 35);
        
        JLabel ms4Txt = new JLabel("ms");
        ms4Txt.setBounds(160, 220, 50, 20);
        
        	
        JTextField ms4 = new JTextField("0");
        ms4.setBounds(105,220,50,20);
        
        if(textoB.isSelected()) ms4.setEnabled(false);
        else ms4.setEnabled(true);
        
        ButtonGroup grupo= new ButtonGroup();
        grupo.add(textoA);
        grupo.add(textoB);
        
        
        ActionListener abc = new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		try {
	        		JRadioButton local = (JRadioButton) e.getSource();
	        		if(local == textoA) {
	        			ms1.setEnabled(true);
	        			ms2.setEnabled(true);
	        			ms3.setEnabled(true);
	        			ms4.setEnabled(false);
	        		}
	        		else {
	        			ms1.setEnabled(false);
	        			ms2.setEnabled(false);
	        			ms3.setEnabled(false);
	        			ms4.setEnabled(true);
	        		}
	        	}catch(NullPointerException f) {
	        		ms1.setEnabled(true);
        			ms2.setEnabled(true);
        			ms3.setEnabled(true);
        			ms4.setEnabled(false);
	        	}
        	}
        };
        
        abc.actionPerformed(null);
        textoA.addActionListener(abc);
        textoB.addActionListener(abc);
        
        ms3.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {atualizarLabel();}

            @Override
            public void removeUpdate(DocumentEvent e) {atualizarLabel();}

            @Override
            public void changedUpdate(DocumentEvent e) {atualizarLabel();}

            private void atualizarLabel() {
            
                String txt = ms3.getText();
                if(!txt.isEmpty()){
                    double nx = Double.parseDouble(txt) / 1000;
                    String newLabelText = ("(" + String.valueOf(nx) + " per second)");
                    
                    perSecond.setText(newLabelText);
                }
                else{
                    perSecond.setText("(" + 0 + " per second)");
                }
            }
        });

        ms1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }
        });
        
        ms2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); 
                }
            }
        });
        
        ms3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); 
                }
            }
        });
        
        settings.add(textoA);
        settings.add(textoB);
        settings.add(ms1);
        settings.add(ms2);
        settings.add(ms3);
        
        settings.add(ms1Txt);
        settings.add(ms2Txt);
        settings.add(ms3Txt);
        
        settings.add(perSecond);
        
        settings.add(ms4);
        settings.add(ms4Txt);
        
        //flags
        JPanel flags = new JPanel();
        
        flags.setLayout(null);
        flags.setBounds(205,8,132,180);
		
		TitledBorder bordaFlags = BorderFactory.createTitledBorder(" Flags ");
		bordaFlags.setTitleFont(new Font("Arial", Font.PLAIN, 11));
        bordaFlags.setTitleJustification(TitledBorder.LEFT); 
        bordaFlags.setTitlePosition(TitledBorder.TOP); 
        flags.setBorder(bordaFlags);
        
        JCheckBox[] checkboxs = {
        	    new JCheckBox("On"),
        	    new JCheckBox("Available"),
        	    new JCheckBox("Use shortcut"),
        	    new JCheckBox("Confirm activation"),
        	    new JCheckBox("Activation sound"),
        	    new JCheckBox("Show status"),
        	    new JCheckBox("Key Click")
        	};

        	int xx = 8;
        	int y = 18;
        	int width = 115;
        	int height = 25;
        	int spacing = 20; 

        	for (int i = 0; i < checkboxs.length; i++) {
        	    checkboxs[i].setBounds(xx, y + (i * spacing), width, height);
        	}
        	
    	    int[] valores = {1, 2, 4, 8, 16, 32, 64};

    	    for (int i = 0; i < checkboxs.length; i++) {
    	        checkboxs[i].setSelected((registro.flagsRegistro() / valores[i]) % 2 != 0);
    	    }
        	


        int flagsNum = 0;
        JLabel numFlags = new JLabel("("+String.valueOf(flagsNum)+")");
        numFlags.setBounds(12, 155, 60, 28);
        
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int flagsNum = 0;

            	for (int i = 0; i < checkboxs.length; i++) {
            	    if (checkboxs[i].isSelected()) {
            	        flagsNum += (1 << i);
            	        /*
            	        i    1 << i   Binário
            	        0    1        0000001 (2⁰ = 1)
            	        1    2        0000010 (2¹ = 2)
            	        2    4        0000100 (2² = 4)
            	        3    8        0001000 (2³ = 8)
            	        4    16       0010000 (2⁴ = 16)
            	        5    32       0100000 (2⁵ = 32)
            	        6    64       1000000 (2⁶ = 64)
            	        */
            	    }
            	}

                numFlags.setText("(" + flagsNum + ")");
            }
        };
        
        for (int i = 0; i < checkboxs.length; i++) {
    	    checkboxs[i].addActionListener(listener);

    	    }
        
        
        checkboxs[0].setSelected(true);
        
        if(checkboxs[0].isSelected()) numFlags.setText("(" + (flagsNum+1) + ")");
        
        for (int i = 0; i < checkboxs.length; i++) {
        	flags.add(checkboxs[i]);
        }
        
        listener.actionPerformed(null);
        
        flags.add(numFlags);
        
        
        //whenApllied
        
        JPanel whenApplied = new JPanel();
        
        whenApplied.setLayout(null);
        whenApplied.setBounds(205,190,132,78);
		
		TitledBorder bordaWhenApplied = BorderFactory.createTitledBorder(" When apllied ");
		bordaWhenApplied.setTitleFont(new Font("Arial", Font.PLAIN, 11));
        bordaWhenApplied.setTitleJustification(TitledBorder.LEFT); 
        bordaWhenApplied.setTitlePosition(TitledBorder.TOP); 
        whenApplied.setBorder(bordaWhenApplied);
        
        JCheckBox checkBoxApplied1 = new JCheckBox("Save to registry");
        JCheckBox checkBoxApplied2 = new JCheckBox("Broadcast change");
        
        checkBoxApplied1.setBounds(8, 15, 120, 22);
        checkBoxApplied2.setBounds(8, 35, 120, 22);
        
        whenApplied.add(checkBoxApplied1);
        whenApplied.add(checkBoxApplied2);
        
        //loadSettings
        
        JPanel loadSettings = new JPanel();
        
        loadSettings.setLayout(null);
        loadSettings.setBounds(10,270,327,50);
		
		TitledBorder bordaLoadSettings = BorderFactory.createTitledBorder(" Load Settings ");
		bordaLoadSettings.setTitleFont(new Font("Arial", Font.PLAIN, 11));
        bordaLoadSettings.setTitleJustification(TitledBorder.LEFT); 
        bordaLoadSettings.setTitlePosition(TitledBorder.TOP); 
        loadSettings.setBorder(bordaLoadSettings);
        
        JButton currentb = new JButton("Current");
        JButton registryb = new JButton("Registry");
        JButton keyboardb = new JButton("Keyboard");
        JButton defaultb = new JButton("Default");
        JButton originalb = new JButton("Original");
        
        currentb.setBounds(10, 18, 58,20);
        registryb.setBounds(72, 18, 58, 20);
        keyboardb.setBounds(134, 18, 58, 20);
        defaultb.setBounds(196, 18, 58, 20);
        originalb.setBounds(258, 18, 58, 20);

        currentb.setHorizontalAlignment(SwingConstants.CENTER);
        currentb.setMargin(new Insets(2, 3, 2, 3));
        currentb.setFont(new Font("Arial", Font.PLAIN, 10));
        currentb.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent e) {
				ms1.setText(registro.getCampos().getDelayBeforeAcceptance());
				ms2.setText(registro.getCampos().getAutoRepeatDelay());
				ms3.setText(registro.getCampos().getAutoRepeatRate());
			}});
        
        registryb.setHorizontalAlignment(SwingConstants.CENTER);
        registryb.setMargin(new Insets(2, 3, 2, 3));
        registryb.setFont(new Font("Arial", Font.PLAIN, 10));

        keyboardb.setHorizontalAlignment(SwingConstants.CENTER);
        keyboardb.setMargin(new Insets(2, 3, 2, 3));
        keyboardb.setFont(new Font("Arial", Font.PLAIN, 10));

        defaultb.setHorizontalAlignment(SwingConstants.CENTER);
        defaultb.setMargin(new Insets(2, 3, 2, 3));
        defaultb.setFont(new Font("Arial", Font.PLAIN, 10));
        defaultb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ms1.setText(valoresPadrao.getDelayBeforeAcceptance());
				ms2.setText(valoresPadrao.getAutoRepeatDelay());
				ms3.setText(valoresPadrao.getAutoRepeatRate());
			}});
        
        originalb.setHorizontalAlignment(SwingConstants.CENTER);
        originalb.setMargin(new Insets(2, 3, 2, 3));
        originalb.setFont(new Font("Arial", Font.PLAIN, 10));
        
        loadSettings.add(currentb);
        loadSettings.add(registryb);
        loadSettings.add(keyboardb);
        loadSettings.add(defaultb);
        loadSettings.add(originalb);
        
        //textArea

        JPanel testArea = new JPanel();

        testArea.setLayout(null);
        testArea.setBounds(10,325,327,50);
		
		TitledBorder bordaTestArea = BorderFactory.createTitledBorder(" Test area(click apply first) ");
		bordaTestArea.setTitleFont(new Font("Arial", Font.PLAIN, 11));
        bordaTestArea.setTitleJustification(TitledBorder.LEFT); 
        bordaTestArea.setTitlePosition(TitledBorder.TOP); 
        testArea.setBorder(bordaTestArea);

        JTextField testApply = new JTextField();
        testApply.setBounds(14,20, 300, 20);

        testArea.add(testApply);
        
        // bara de baixo
        JButton ok = new JButton("OK");
        ok.addActionListener(e -> System.exit(0));
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> System.exit(0));	
        JButton apply = new JButton("Apply");
        apply.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBoxApplied1.isSelected()) {
					registro.setRegistryValue("DelayBeforeAcceptance", ms1.getText());
					registro.setRegistryValue("AutoRepeatDelay", ms2.getText());
					registro.setRegistryValue("AutoRepeatRate", ms3.getText());
					registro.setRegistryValue("Flags", String.valueOf(flagsNum));
					
					JOptionPane.showMessageDialog(null, "Settings applied successfully!", "Apply", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		});	
        
        ok.setBounds(75, 390, 80, 20);
        cancel.setBounds(165, 390, 80, 20);
        apply.setBounds(255, 390, 80, 20);
        
        ok.setBackground(Color.CYAN);
        //janela
        janela.add(settings);
        
        janela.add(flags);
        
        janela.add(whenApplied);
        
        janela.add(loadSettings);
        
        janela.add(testArea);

        janela.add(ok);
        janela.add(cancel);
        janela.add(apply);
        
		janela.setVisible(true);
	}
}
