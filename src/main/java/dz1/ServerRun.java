package dz1;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerRun extends JFrame {
    private static final Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WINDOW_HEIGHT = 300;
    private static final int WINDOW_WIDTH = 450;
    private static final int WINDOW_POSX = (displaySize.width - WINDOW_WIDTH) / 2;
    private static final int WINDOW_POSY = (displaySize.height - WINDOW_HEIGHT) / 2;
    private final JTextArea log = new JTextArea();
    private final JButton btnStart = new JButton("Start Server");
    private final JButton btnStop = new JButton("Stop Server");
    private boolean isServerWorking;

    ServerRun() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(WINDOW_POSX, WINDOW_POSY, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Server");
        setResizable(false);
        btnStart.addActionListener(e -> {
            if (!isServerWorking) {
                isServerWorking = true;
                log.append(serverStatus());
            } else JOptionPane.showMessageDialog(this,
                    "Команда '" + btnStart.getText() + "' невозможна, т.к. сервер уже запущен",
                    "ВНИМАНИЕ", JOptionPane.WARNING_MESSAGE);
        });
        btnStop.addActionListener(e -> {
            if (isServerWorking) {
                isServerWorking = false;
                log.append(serverStatus());
            } else JOptionPane.showMessageDialog(this,
                    "Команда '" + btnStop.getText() + "' невозможна, т.к. сервер не запущен",
                    "ВНИМАНИЕ", JOptionPane.WARNING_MESSAGE);
        });

        JPanel btnPanel = new JPanel(new GridLayout(1, 2));
        btnPanel.add(btnStart);
        btnPanel.add(btnStop);
        btnStart.setToolTipText("Запустить сервер");
        btnStop.setToolTipText("Остановить сервер");
        add(btnPanel, BorderLayout.SOUTH);

        log.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(log);
        add(jScrollPane);
        setVisible(true);
    }

    private String serverStatus() {
        return String.format("Статус сервера: %s (%s)\n",
                isServerWorking ? "Запущен" : "Остановлен",
                new SimpleDateFormat().format(new Date()));
    }

    public static void main(String[] args) {
        new ServerRun();
    }
}