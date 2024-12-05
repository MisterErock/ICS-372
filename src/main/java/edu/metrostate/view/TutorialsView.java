package edu.metrostate.view;

import edu.metrostate.controller.ApplianceController;
import edu.metrostate.controller.NotificationController;
import edu.metrostate.controller.TutorialController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.List;

public class TutorialsView extends JPanel {
    private final TutorialController tutorialController;
    private final JFrame parentFrame;

    public TutorialsView(TutorialController tutorialController, JFrame parentFrame) {
        this.tutorialController = tutorialController;
        this.parentFrame = parentFrame;

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("<html><h1>Appliance Tutorials</h1></html>");
        add(titleLabel, BorderLayout.NORTH);

        List<edu.metrostate.model.Tutorial> tutorials = tutorialController.getAllTutorials();
        JPanel tutorialsPanel = new JPanel();
        tutorialsPanel.setLayout(new BoxLayout(tutorialsPanel, BoxLayout.Y_AXIS));

        for (edu.metrostate.model.Tutorial tutorial : tutorials) {
            JButton tutorialButton = new JButton(tutorial.getTitle());
            tutorialButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            tutorialButton.setToolTipText(tutorial.getDescription());
            tutorialButton.addActionListener(new TutorialLinkListener(tutorial.getLink()));
            tutorialsPanel.add(tutorialButton);
        }

        JScrollPane scrollPane = new JScrollPane(tutorialsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add back button to navigate back to home screen
        JButton backButton = new JButton("Back to Home");
        backButton.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new HomeScreenView(new ApplianceController(), new NotificationController(), tutorialController, parentFrame));
            parentFrame.revalidate();
        });
        add(backButton, BorderLayout.SOUTH);
    }

    private class TutorialLinkListener implements ActionListener {
        private final String link;

        public TutorialLinkListener(String link) {
            this.link = link;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(TutorialsView.this, "Failed to open link: " + link, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
