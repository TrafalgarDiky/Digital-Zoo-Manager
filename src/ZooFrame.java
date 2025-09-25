import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ZooFrame extends JFrame {
    // Form fields
    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> typeBox;
    private JTextField furColorField;   // khusus Mammal
    private JCheckBox canFlyCheck;      // khusus Bird

    // Log
    private JTextArea logArea;

    // Storage
    private final List<Animal> zoo = new ArrayList<>();

    public ZooFrame() {
        super("Digital Zoo Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== Root =====
        var root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        setContentPane(root);

        // ====== FORM (panel atas) ======
        var formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Animal Form"));
        root.add(formPanel, BorderLayout.NORTH);

        GridBagConstraints g = baseGbc();

        // row 0: Name
        addLabel(formPanel, "Name:", g, 0, 0);
        nameField = wideTextField(22);
        addField(formPanel, nameField, g, 1, 0);

        // row 1: Age
        addLabel(formPanel, "Age:", g, 0, 1);
        ageField = wideTextField(8);
        addField(formPanel, ageField, g, 1, 1);

        // row 2: Animal Type
        addLabel(formPanel, "Animal Type:", g, 0, 2);
        typeBox = new JComboBox<>(new String[]{"Mammal", "Bird"});
        addField(formPanel, typeBox, g, 1, 2);

        // row 3: Fur Color (for Mammal)
        addLabel(formPanel, "Fur Color (for Mammal):", g, 0, 3);
        furColorField = wideTextField(16);
        addField(formPanel, furColorField, g, 1, 3);

        // row 4: Can Fly? (for Bird)
        addLabel(formPanel, "Can Fly? (for Bird):", g, 0, 4);
        canFlyCheck = new JCheckBox();
        addField(formPanel, canFlyCheck, g, 1, 4);

        // row 5: Buttons bar (kanan)
        var buttonsBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        var addBtn = new JButton("Add Animal");
        addBtn.setMnemonic('A');
        addBtn.putClientProperty("JButton.buttonType", "default"); // di beberapa L&F akan bold
        buttonsBar.add(addBtn);

        g.gridx = 0; g.gridy = 5;
        g.gridwidth = 2;
        g.weightx = 1;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.EAST;
        g.insets = new Insets(10, 12, 0, 12);
        formPanel.add(buttonsBar, g);

        // ====== LOG (panel bawah) ======
        logArea = new JTextArea(12, 70);
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        var logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder("Zoo Log"));
        root.add(logScroll, BorderLayout.CENTER);

        // ====== Interaksi ======
        typeBox.addActionListener(e -> toggleTypeFields());
        toggleTypeFields();

        addBtn.addActionListener(e -> onAddAnimal());

        // ====== Finish ======
        pack();
        setMinimumSize(new Dimension(Math.max(getWidth(), 820), getHeight()));
        setLocationRelativeTo(null);
    }

    // ---------- Helpers UI ----------
    private GridBagConstraints baseGbc() {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 12, 6, 12);
        g.anchor = GridBagConstraints.LINE_START;
        return g;
    }

    private void addLabel(JPanel p, String text, GridBagConstraints g, int x, int y) {
        g.gridx = x; g.gridy = y;
        g.gridwidth = 1; g.weightx = 0; g.fill = GridBagConstraints.NONE;
        p.add(new JLabel(text), g);
    }

    private void addField(JPanel p, JComponent c, GridBagConstraints g, int x, int y) {
        g.gridx = x; g.gridy = y;
        g.gridwidth = 1; g.weightx = 1; g.fill = GridBagConstraints.HORIZONTAL;
        p.add(c, g);
    }

    private JTextField wideTextField(int columns) {
        var tf = new JTextField(columns);
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, tf.getPreferredSize().height));
        return tf;
    }

    private void toggleTypeFields() {
        boolean mammal = "Mammal".equals(typeBox.getSelectedItem());
        furColorField.setEnabled(mammal);
        canFlyCheck.setEnabled(!mammal);
    }

    private void onAddAnimal() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Validation", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age < 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Age must be a non-negative number.", "Validation", JOptionPane.WARNING_MESSAGE);
            ageField.requestFocus();
            return;
        }

        String type = (String) typeBox.getSelectedItem();
        Animal a;
        if ("Mammal".equals(type)) {
            String fur = furColorField.getText().trim();
            if (fur.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fur color is required for Mammal.", "Validation", JOptionPane.WARNING_MESSAGE);
                furColorField.requestFocus();
                return;
            }
            a = new Mammal(name, age, fur);
        } else {
            boolean canFly = canFlyCheck.isSelected();
            a = new Bird(name, age, canFly);
        }

        zoo.add(a);
        logArea.append(
                "Added a new " + type + "!\n" +
                        "Info: " + a.getInfo() + "\n" +
                        "Sound: " + a.makeSound() + "\n\n"
        );

        // Reset ringan
        nameField.setText("");
        ageField.setText("");
        furColorField.setText("");
        canFlyCheck.setSelected(false);
        nameField.requestFocus();
    }
}
