package dz1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class ChatClient extends JFrame {
    private static final Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
    private static final int WINDOW_HEIGHT = 400;
    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_POSX = (displaySize.width - WINDOW_WIDTH) / 2;
    private static final int WINDOW_POSY = (displaySize.height - WINDOW_HEIGHT) / 2;

    final JPanel panelTop = new JPanel(new BorderLayout());
    final JPanel panelServer = new JPanel(new GridLayout(5, 2, 5, 5));
    final JTextField txtFieldLogin = new JTextField(15);
    final JPasswordField txtFieldPassword = new JPasswordField(15);
    final JTextField txtFieldIP = new JTextField(15);
    final JTextField txtFieldPort = new JTextField(15);

    JTextArea areaChat = new JTextArea();
    JPanel panelClient = new JPanel(new BorderLayout());
    JTextField txtFieldMessage = new JTextField();
    JButton btnSend = new JButton("Отправить");

    private static User[] users;
    private String activeUserName;

    ChatClient() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Chat Client");
        setResizable(false);

        JList<User> userJList = getUserJList();
        JScrollPane scrollPaneList = new JScrollPane(userJList);
        scrollPaneList.setPreferredSize(new Dimension(150, 150));
        JPanel flowListPanel = new JPanel(new FlowLayout());
        flowListPanel.add(scrollPaneList);
        panelTop.add(flowListPanel, BorderLayout.WEST);

        panelServer.add(txtFieldLogin);
        panelServer.add(new JLabel("Введите логин"));
        panelServer.add(txtFieldPassword);
        panelServer.add(new JLabel("Введите пароль"));
        panelServer.add(txtFieldIP);
        panelServer.add(new JLabel("Введите IP адрес"));
        panelServer.add(txtFieldPort);
        panelServer.add(new JLabel("Введите порт"));
        JButton loginButton = new JButton("Войти");
        panelServer.add(loginButton);
        JPanel flowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flowPanel.add(panelServer);
        panelTop.add(flowPanel);
        add(panelTop, BorderLayout.NORTH);

        panelClient.add(txtFieldMessage, BorderLayout.CENTER);
        panelClient.add(btnSend, BorderLayout.EAST);
        add(panelClient, BorderLayout.SOUTH);

        areaChat.setEditable(false);
        areaChat.setLineWrap(true);
        areaChat.setWrapStyleWord(true);
        add(new JScrollPane(areaChat));

        loginButton.addActionListener(e -> {
            if (txtFieldLogin.getText().isEmpty() || txtFieldPassword.getPassword().length == 0
                    || txtFieldIP.getText().isEmpty() || txtFieldPort.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Не все поля заполнены", "ВХОД НЕ ВЫПОЛНЕН", JOptionPane.WARNING_MESSAGE);
            } else downloadLog();
        });
        txtFieldMessage.addActionListener(e -> sendMessage());
        btnSend.addActionListener(e -> sendMessage());
        setVisible(true);
    }

    private JList<User> getUserJList() {
        JList<User> userJList = new JList<>();
        userJList.setListData(users);
        userJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    User user = users[userJList.locationToIndex(e.getPoint())];
                    activeUserName = user.name();
                    txtFieldIP.setText(user.ip());
                    txtFieldLogin.setText(user.login());
                    txtFieldPassword.setText(user.password());
                    txtFieldPort.setText(user.port());
                }
            }
        });
        return userJList;
    }

    private void sendMessage() {
        areaChat.append(activeUserName + ": " + txtFieldMessage.getText() + "\n");
        txtFieldMessage.setText(null);
        saveLog();
    }

    private void saveLog() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt"))) {
            bw.write(areaChat.getText());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void downloadLog() {
        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {
            areaChat.setText(null);
            String line;
            while ((line = br.readLine()) != null) {
                areaChat.append(line + '\n');
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Создан новый чат. Ваше сообщение будет первым", "НОВЫЙ ЧАТ", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        users = new User[]{
                new User("Денис", "Danon", "123", "1.1.1.1", "8081"),
                new User("Павел", "Pavlin", "2345", "2.2.2.2", "8082"),
                new User("Филипп", "Filllll", "34567", "3.3.3.3", "8083"),
                new User("Екатерина", "Kattet", "456789", "4.4.4.4", "8084"),
                new User("Марина", "Marryarty", "5678909", "5.5.5.5", "8085")
        };
        new ChatClient();
    }

    record User(String name, String login, String password, String ip, String port) {
        @Override
        public String toString() {
            return name;
        }
    }
}


