package view;

import controller.Backup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class MainGui extends JFrame {
    private JFrame notice;
    private JPanel pnMain, pnHeader, pnSelection, pnTypes, pnInitials, pnProcess, pnDislpay, pnFooter;
    private JLabel lbInfo, lbTypesInfo, lbInitialsInfo, lbLocationInfo, lbCredits;
    private JComboBox<String> cmbPolicyTypes;
    private JButton btnBackup, btnRefresh, btnExit;
    private JTextField tfInitials, tfNewLocation;

    private void readyToBackup() {
        boolean val = !cmbPolicyTypes.getSelectedItem().equals("--") && !tfInitials.getText().trim().isEmpty();
        btnBackup.setEnabled(val);
    }

    private void copyLocation() {
        StringSelection stringselection = new StringSelection(tfNewLocation.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringselection, null);
    }

    public MainGui() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        pnMain = new JPanel();
        GridBagLayout gbMain = new GridBagLayout();
        GridBagConstraints gbcMain = new GridBagConstraints();
        cp.add(pnMain);
        pnMain.setLayout( gbMain );

        pnHeader = new JPanel();
        GridBagLayout gbHeader = new GridBagLayout();
        GridBagConstraints gbcHeader = new GridBagConstraints();
        pnHeader.setLayout( gbHeader );

        lbInfo = new JLabel( "Please select Policy Type and add initials then click 'Backup'!!!"  );
        Font font = new Font("serif", Font.BOLD,20);
        lbInfo.setFont(font);
        gbcHeader.gridx = 3;
        gbcHeader.gridy = 0;
        gbcHeader.gridwidth = 5;
        gbcHeader.gridheight = 1;
        gbcHeader.fill = GridBagConstraints.BOTH;
        gbcHeader.weightx = 1;
        gbcHeader.weighty = 1;
        gbcHeader.anchor = GridBagConstraints.NORTH;
        gbHeader.setConstraints( lbInfo, gbcHeader );
        pnHeader.add( lbInfo );
        gbcMain.gridx = 4;
        gbcMain.gridy = 0;
        gbcMain.gridwidth = 8;
        gbcMain.gridheight = 3;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1;
        gbcMain.weighty = 0;
        gbcMain.anchor = GridBagConstraints.NORTH;
        gbMain.setConstraints( pnHeader, gbcMain );
        pnMain.add( pnHeader );

        pnSelection = new JPanel();
        GridBagLayout gbSelection = new GridBagLayout();
        GridBagConstraints gbcSelection = new GridBagConstraints();
        pnSelection.setLayout( gbSelection );

        pnTypes = new JPanel();
        pnTypes.setBorder( BorderFactory.createTitledBorder( "Policy Types" ) );
        GridBagLayout gbTypes = new GridBagLayout();
        GridBagConstraints gbcTypes = new GridBagConstraints();
        pnTypes.setLayout( gbTypes );

        lbTypesInfo = new JLabel( "Select Policy to be backed up"  );
        gbcTypes.gridx = 0;
        gbcTypes.gridy = 0;
        gbcTypes.gridwidth = 1;
        gbcTypes.gridheight = 1;
        gbcTypes.fill = GridBagConstraints.BOTH;
        gbcTypes.weightx = 1;
        gbcTypes.weighty = 1;
        gbcTypes.anchor = GridBagConstraints.NORTH;
        gbTypes.setConstraints( lbTypesInfo, gbcTypes );
        pnTypes.add( lbTypesInfo );

        String []dataPolicyTypes = { "--", "--", "--", "--", "--", "--" };
        cmbPolicyTypes = new JComboBox<>(dataPolicyTypes);
        gbcTypes.gridx = 0;
        gbcTypes.gridy = 1;
        gbcTypes.gridwidth = 1;
        gbcTypes.gridheight = 1;
        gbcTypes.fill = GridBagConstraints.BOTH;
        gbcTypes.weightx = 1;
        gbcTypes.weighty = 0;
        gbcTypes.anchor = GridBagConstraints.NORTH;
        gbTypes.setConstraints( cmbPolicyTypes, gbcTypes );
        pnTypes.add( cmbPolicyTypes );
        gbcSelection.gridx = 0;
        gbcSelection.gridy = 0;
        gbcSelection.gridwidth = 1;
        gbcSelection.gridheight = 2;
        gbcSelection.fill = GridBagConstraints.BOTH;
        gbcSelection.weightx = 1;
        gbcSelection.weighty = 0;
        gbcSelection.anchor = GridBagConstraints.NORTH;
        gbSelection.setConstraints( pnTypes, gbcSelection );
        pnSelection.add( pnTypes );

        pnInitials = new JPanel();
        pnInitials.setBorder( BorderFactory.createTitledBorder( "Initials" ) );
        GridBagLayout gbInitials = new GridBagLayout();
        GridBagConstraints gbcInitials = new GridBagConstraints();
        pnInitials.setLayout( gbInitials );

        lbInitialsInfo = new JLabel( "Check your initials are correct"  );
        gbcInitials.gridx = 1;
        gbcInitials.gridy = 0;
        gbcInitials.gridwidth = 1;
        gbcInitials.gridheight = 1;
        gbcInitials.fill = GridBagConstraints.BOTH;
        gbcInitials.weightx = 1;
        gbcInitials.weighty = 1;
        gbcInitials.anchor = GridBagConstraints.NORTH;
        gbInitials.setConstraints( lbInitialsInfo, gbcInitials );
        pnInitials.add( lbInitialsInfo );
        tfInitials = new JTextField(Backup.getInitials());
        tfInitials.setToolTipText("auto-generated from system username");
        gbcInitials.gridx = 1;
        gbcInitials.gridy = 1;
        gbcInitials.gridwidth = 1;
        gbcInitials.gridheight = 1;
        gbcInitials.fill = GridBagConstraints.BOTH;
        gbcInitials.weightx = 1;
        gbcInitials.weighty = 1;
        gbcInitials.anchor = GridBagConstraints.NORTH;
        gbInitials.setConstraints( tfInitials, gbcInitials );
        pnInitials.add( tfInitials );
        gbcSelection.gridx = 2;
        gbcSelection.gridy = 0;
        gbcSelection.gridwidth = 1;
        gbcSelection.gridheight = 3;
        gbcSelection.fill = GridBagConstraints.BOTH;
        gbcSelection.weightx = 1;
        gbcSelection.weighty = 0;
        gbcSelection.anchor = GridBagConstraints.NORTH;
        gbSelection.setConstraints( pnInitials, gbcSelection );
        pnSelection.add( pnInitials );
        gbcMain.gridx = 4;
        gbcMain.gridy = 5;
        gbcMain.gridwidth = 8;
        gbcMain.gridheight = 5;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 1;
        gbcMain.weighty = 0;
        gbcMain.anchor = GridBagConstraints.CENTER;
        gbMain.setConstraints( pnSelection, gbcMain );
        pnMain.add( pnSelection );

        pnProcess = new JPanel();
        pnProcess.setBorder(BorderFactory.createTitledBorder("Process"));
        GridBagLayout gbProcess = new GridBagLayout();
        GridBagConstraints gbcProcess = new GridBagConstraints();
        pnProcess.setLayout( gbProcess );

        btnBackup = new JButton("Backup");
        gbcProcess.gridx = 1;
        gbcProcess.gridy = 0;
        gbcProcess.gridwidth = 1;
        gbcProcess.gridheight = 1;
        gbcProcess.fill = GridBagConstraints.BOTH;
        gbcProcess.weightx = 1;
        gbcProcess.weighty = 1;
        gbcProcess.anchor = GridBagConstraints.NORTH;
        gbProcess.setConstraints( btnBackup, gbcProcess );
        pnProcess.add(btnBackup);
        btnRefresh = new JButton("Refresh");
        gbcProcess.gridx = 1;
        gbcProcess.gridy = 2;
        gbcProcess.gridwidth = 1;
        gbcProcess.gridheight = 1;
        gbcProcess.fill = GridBagConstraints.BOTH;
        gbcProcess.weightx = 1;
        gbcProcess.weighty = 1;
        gbcProcess.anchor = GridBagConstraints.NORTH;
        gbProcess.setConstraints( btnRefresh, gbcProcess );
        pnProcess.add(btnRefresh);
        gbcMain.gridx = 20;
        gbcMain.gridy = 5;
        gbcMain.gridwidth = 1;
        gbcMain.gridheight = 5;
        gbcMain.fill = GridBagConstraints.EAST;
        gbcMain.weightx = 1;
        gbcMain.weighty = 0;
        gbcMain.anchor = GridBagConstraints.EAST;
        gbMain.setConstraints( pnProcess, gbcMain );
        pnMain.add( pnProcess );

        pnDislpay = new JPanel();
        pnDislpay.setBorder(BorderFactory.createTitledBorder("File copied to location below"));
        GridBagLayout gbDisplay = new GridBagLayout();
        GridBagConstraints gbcDisplay = new GridBagConstraints();
        pnDislpay.setLayout( gbDisplay );

        lbLocationInfo = new JLabel("click path to copy...");
        gbcDisplay.gridx = 1;
        gbcDisplay.gridy = 0;
        gbcDisplay.gridwidth = 1;
        gbcDisplay.gridheight = 1;
        gbcDisplay.fill = GridBagConstraints.BOTH;
        gbcDisplay.weightx = 1;
        gbcDisplay.weighty = 1;
        gbcDisplay.anchor = GridBagConstraints.NORTH;
        gbDisplay.setConstraints( lbLocationInfo, gbcDisplay );
        pnDislpay.add(lbLocationInfo);
        tfNewLocation = new JTextField();
        tfNewLocation.setEditable(false);
        gbcDisplay.gridx = 1;
        gbcDisplay.gridy = 2;
        gbcDisplay.gridwidth = 1;
        gbcDisplay.gridheight = 1;
        gbcDisplay.fill = GridBagConstraints.BOTH;
        gbcDisplay.weightx = 1;
        gbcDisplay.weighty = 1;
        gbcDisplay.anchor = GridBagConstraints.NORTH;
        gbDisplay.setConstraints( tfNewLocation, gbcDisplay );
        pnDislpay.add(tfNewLocation);
        gbcMain.gridx = 0;
        gbcMain.gridy = 10;
        gbcMain.gridwidth = 15;
        gbcMain.gridheight = 5;
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weightx = 2;
        gbcMain.weighty = 0;
        gbcMain.anchor = GridBagConstraints.WEST;
        gbMain.setConstraints( pnDislpay, gbcMain );
        pnMain.add( pnDislpay );


        pnFooter = new JPanel();
//		pnFooter.setBorder(BorderFactory.createTitledBorder(""));
        GridBagLayout gbFooter = new GridBagLayout();
        GridBagConstraints gbcFooter = new GridBagConstraints();
        pnFooter.setLayout( gbFooter );

        btnExit = new JButton("Exit");
        gbcFooter.gridx = 15;
        gbcFooter.gridy = 0;
        gbcFooter.gridwidth = 1;
        gbcFooter.gridheight = 1;
        gbcFooter.fill = GridBagConstraints.EAST;
        gbcFooter.weightx = 1;
        gbcFooter.weighty = 1;
        gbcFooter.anchor = GridBagConstraints.EAST;
        gbFooter.setConstraints( btnExit, gbcFooter );
        pnFooter.add(btnExit);
        lbCredits = new JLabel("\u00a9 2019 Autonet RTP Development v1.0");
        gbcFooter.gridx = 1;
        gbcFooter.gridy = 2;
        gbcFooter.gridwidth = 1;
        gbcFooter.gridheight = 1;
        gbcFooter.fill = GridBagConstraints.BOTH;
        gbcFooter.weightx = 1;
        gbcFooter.weighty = 1;
        gbcFooter.anchor = GridBagConstraints.SOUTH;
        gbFooter.setConstraints( lbCredits, gbcFooter );
        pnFooter.add(lbCredits);
        gbcMain.gridx = 0;
        gbcMain.gridy = 35;
        gbcMain.gridwidth = 15;
        gbcMain.gridheight = 5;
        gbcMain.fill = GridBagConstraints.SOUTH;
        gbcMain.weightx = 2;
        gbcMain.weighty = 0;
        gbcMain.anchor = GridBagConstraints.SOUTH;
        gbMain.setConstraints( pnFooter, gbcMain );
        pnMain.add( pnFooter );


        btnBackup.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                readyToBackup();
            }
            public void mouseExited(MouseEvent evt) {
                readyToBackup();
            }
        });

        btnBackup.addActionListener(evt -> {
            String location = Backup.backup(tfInitials.getText(), cmbPolicyTypes.getSelectedItem().toString());
            tfNewLocation.setText(location);
        });

        btnRefresh.addActionListener(evt -> {
            cmbPolicyTypes.setSelectedIndex(0);
            tfInitials.setText(Backup.getInitials());
            tfNewLocation.setText("");
        });

        tfNewLocation.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if(!tfNewLocation.getText().isEmpty()) {
                    copyLocation();
                    JOptionPane.showMessageDialog(notice, "Path copied to clipboard.");
                }
            }
            public void mousePressed(MouseEvent evt) {
                if(!tfNewLocation.getText().isEmpty()) {
                    copyLocation();
                    JOptionPane.showMessageDialog(notice, "Path copied to clipboard.");
                }
            }
        });

        btnExit.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnExit.setBackground(Color.RED);
            }
            public void mouseExited(MouseEvent evt) {
                btnExit.setBackground(UIManager.getColor("control"));
            }
        });

        btnExit.addActionListener(evt -> System.exit(1));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Backup Live Deloyment");
        setSize(700, 250);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        try {
            setIconImage(ImageIO.read(new File("resources/icon-small.png")));
        } catch (IOException e) {
//			e.printStackTrace();
        }
    }
}
