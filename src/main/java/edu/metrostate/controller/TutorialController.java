package edu.metrostate.controller;

import edu.metrostate.model.Tutorial;
import java.util.ArrayList;
import java.util.List;

public class TutorialController {  // Updated class name
    private List<Tutorial> tutorials;

    public TutorialController() {
        this.tutorials = new ArrayList<>();
        loadTutorials();
    }

    private void loadTutorials() {
        // Add sample tutorials (could also be loaded from a database or file)
        tutorials.add(new Tutorial("General Refrigerator Maintenance Tips", "General maintenance for your refrigerator.", "https://safetyculture.com/topics/refrigerator-maintenance/"));
        tutorials.add(new Tutorial("Refrigerator Cleaning", "How to clean and maintain your refrigerator.", "https://youtu.be/3eiYfVrXu8o?si=W6o9KIwORcXxFZhB"));
        tutorials.add(new Tutorial("Washing Machine Troubleshooting", "Common issues with washing machines and how to fix them.", "https://www.expresssewer.com/blog/washing-machine-problems-and-solutions"));
        tutorials.add(new Tutorial("Common Washer Machine Fix", "Most common washer machine issue and fix", "https://www.youtube.com/watch?v=KmPlBZ5nT1Q"));
        tutorials.add(new Tutorial("Dryer Machine Troubleshooting", "Common issues with drying machines and how to fix them", "https://www.searshomeservices.com/blog/5-most-common-dryer-problems-solutions"));
        tutorials.add(new Tutorial("Common Dryer Machine Fix", "Most common drying machine issues and fix", "https://www.youtube.com/watch?v=lAbtibZhqkw"));
        tutorials.add(new Tutorial("Dish Washer Troubleshooting", "Common issues with dishwashers and how to fix", "https://www.coastappliances.ca/blogs/learn/common-dishwasher-problems?srsltid=AfmBOor_T5s9fQPwIYDf6F0Zrokbcq0ZOzww8s1_I0WWc-_OGkpjhZsS"));
        tutorials.add(new Tutorial("Common Dish Washer Fix", "Most dishwasher issue and fix", "https://www.youtube.com/watch?v=o2cQx0lLVRc"));
        tutorials.add(new Tutorial("General Oven Maintenance Tips", "General maintenance for you oven", "https://homewarranty.firstam.com/blog/guide-to-oven-maintenance-101"));
        tutorials.add(new Tutorial("Oven Cleaning", "How to clean and maintain your oven", "https://www.youtube.com/watch?v=RiI4i0lLs3E"));
        tutorials.add(new Tutorial("General Microwave Maintenance Tips", "General maintenance for your microwave", "https://wilshirerefrigeration.com/microwave-maintenance-9-tips/"));
        tutorials.add(new Tutorial("Microwave Cleaning", "How to clean your microwave", "https://www.youtube.com/watch?v=WrlajqOJKJA"));
    }


    public List<Tutorial> getAllTutorials() {
        return new ArrayList<>(tutorials);
    }
}
