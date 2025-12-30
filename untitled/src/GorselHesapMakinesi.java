import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GorselHesapMakinesi {
    private static double sayi1 = 0;
    private static String islem = "";
    private static boolean yeniSayiBasliyor = true;

    public static void main(String[] args) {
        JFrame pencere = new JFrame("Hesap Makinesi");
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setSize(350, 450);
        pencere.getContentPane().setBackground(Color.WHITE);
        pencere.setLayout(new BorderLayout());

        JTextField ekran = new JTextField();
        ekran.setFont(new Font("Arial", Font.BOLD, 35));
        ekran.setHorizontalAlignment(JTextField.RIGHT);
        ekran.setBackground(Color.WHITE);
        ekran.setEditable(false);
        ekran.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        pencere.add(ekran, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.WHITE);

        String[] tuslar = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "C", "0", "=", "+"};

        for (String tus : tuslar) {
            JButton buton = new JButton(tus);
            buton.setFont(new Font("Arial", Font.BOLD, 20));
            buton.setBackground(Color.WHITE);
            buton.setFocusPainted(false);

            buton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String basilan = e.getActionCommand();

                    if ("0123456789".contains(basilan)) {
                        if (yeniSayiBasliyor) {
                            ekran.setText(basilan);
                            yeniSayiBasliyor = false;
                        } else {
                            ekran.setText(ekran.getText() + basilan);
                        }
                    } else if (basilan.equals("C")) {
                        ekran.setText("");
                        sayi1 = 0;
                        islem = "";
                        yeniSayiBasliyor = true;
                    } else if (basilan.equals("=")) {
                        if (islem.isEmpty()) return;
                        double sayi2 = Double.parseDouble(ekran.getText());
                        double sonuc = 0;

                        switch (islem) {
                            case "+": sonuc = sayi1 + sayi2; break;
                            case "-": sonuc = sayi1 - sayi2; break;
                            case "*": sonuc = sayi1 * sayi2; break;
                            case "/":
                                if (sayi2 != 0) sonuc = sayi1 / sayi2;
                                else { ekran.setText("Hata!"); return; }
                                break;
                        }

                        // İŞTE KRİTİK NOKTA: .0 fırlatma kontrolü
                        if (sonuc == (long) sonuc) {
                            ekran.setText(String.format("%d", (long) sonuc));
                        } else {
                            ekran.setText(String.format("%s", sonuc));
                        }

                        yeniSayiBasliyor = true;
                        islem = "";
                    } else {
                        if (!ekran.getText().isEmpty()) {
                            sayi1 = Double.parseDouble(ekran.getText());
                            islem = basilan;
                            yeniSayiBasliyor = true;
                        }
                    }
                }
            });
            panel.add(buton);
        }

        pencere.add(panel, BorderLayout.CENTER);
        pencere.setLocationRelativeTo(null);
        pencere.setVisible(true);
    }
}