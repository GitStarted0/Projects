package Logic;

import java.time.LocalDate;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagement {

    private static String pathname = "C:\\Users\\user\\Documents\\Projekt-Semesterferien\\files\\";
    private static final Path subjectsPath = Paths.get(pathname + "subjects.txt");
    private static final Path topicGroupsPath = Paths.get(pathname + "topic-groups.txt");
    private static final Path topicsPath = Paths.get(pathname + "topics.txt");
    private static List<String> contentsToWrite = new ArrayList<>();

    /**
     * This method writes a subject into the 'subjects.txt' file
     * to save the subjects until the next session.
     *
     * @param subject subject to be written into file.
     */
    public static void writeSubject(Subject subject) {
        // create file if not already existing
        try {
            File subjects = new File(pathname + "subjects.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // prepare data to be written into file. correct formatting is important for
        // recreating the subjects and topics when the next session starts
        try {
            contentsToWrite = Files.readAllLines(subjectsPath, StandardCharsets.UTF_8);
            contentsToWrite.add("S: " + subject.getTitle());
            contentsToWrite.add("S-Priority: " + subject.getPriority());
            if (subject.getDateOfExam() != null) {
                contentsToWrite.add("S-Date: " + subject.getDateOfExam().toString());
            } else {
                contentsToWrite.add("S-Date: //");
            }
            contentsToWrite.add("");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // write formatted data in file
        try {
            Files.write(subjectsPath, contentsToWrite, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method writes topic groups into the 'topic-group.txt' file
     * and topics into the 'topic.txt' file
     *
     * @param topic topic or topic group to be written in appropriate file
     */
    public static void writeTopic(Topic topic) {
        // determine whether 'topic' is a topic or a topic group and appropriate filepath
        Path pathToUse;
        if (topic.getAnswer() == null) {
            pathToUse = topicGroupsPath;
            // create file if it is not already existing
            try {
                File topicGroups = new File(pathname + "topic-groups.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pathToUse = topicsPath;
            try {
                File topics = new File(pathname + "topics.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // prepare data to be written into file. correct formatting is important for
        // recreating the subjects and topics when the next session starts
        try {
            if (pathToUse == topicGroupsPath) {
                if (Files.exists(topicGroupsPath)) {
                    contentsToWrite = Files.readAllLines(topicGroupsPath, StandardCharsets.UTF_8);
                }
                contentsToWrite.add("TG: " + topic.getTitle());
                contentsToWrite.add("TG-Priority: " + topic.getPriority());
                contentsToWrite.add("TG-Subject: " + topic.getSubject().getTitle());
                if (topic.getTopicGroup() != null) {
                    contentsToWrite.add("TG-Sub-TG: " + topic.getTopicGroup().getTitle());
                } else {
                    contentsToWrite.add("TG-Sub-TG: //");
                }
                // Only difference between topic groups and topics (on a data basis)
                // is that topic groups have no answers
            } else {
                if (Files.exists(topicsPath)) {
                    contentsToWrite = Files.readAllLines(topicsPath, StandardCharsets.UTF_8);
                }
                contentsToWrite.add("T: " + topic.getTitle());
                contentsToWrite.add("T-Answer: " + topic.getAnswer());
                contentsToWrite.add("T-Priority: " + topic.getPriority());
                contentsToWrite.add("T-Subject: " + topic.getSubject().getTitle());
                if (topic.getTopicGroup() != null) {
                    contentsToWrite.add("T-TopicGroup: " + topic.getTopicGroup().getTitle());
                } else {
                    contentsToWrite.add("T-TopicGroup: //");
                }
            }
            contentsToWrite.add("");
            // write formatted data into appropriate file
            Files.write(pathToUse, contentsToWrite, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates subjects from the 'subjects.txt' file
     * when the application is started and the file is not empty
     */
    public static void createSubjectsFromFile() {
        // Extract all important information from file and put into List for easier access
        try {
            List<String> contents = Files.readAllLines(subjectsPath);
            ArrayList<String> subjectsInfos = new ArrayList<>();
            for (String content : contents) {
                if (!content.equals("")) {
                    subjectsInfos.add(extractInfo(content));
                }
            }

            // Thanks to the formatting, extracting whole subjects is not that complicated
            String title = "";
            int priority = 0;
            LocalDate dateOfExam = null;
            for (int position = 0; position < subjectsInfos.size(); position++) {
                if (position % 3 == 0) {
                    title = subjectsInfos.get(position);
                } else if (position % 3 == 1) {
                    priority = Integer.parseInt(subjectsInfos.get(position));
                } else {
                    dateOfExam = convertToDate(subjectsInfos.get(position));
                    new Subject(title, priority, dateOfExam);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a topic group from the 'topic-groups.txt' file
     * when the application is started and the file is not empty
     */
    public static void createTopicGroupsFromFile() {
        // Extract all important information from file and put into List for easier access
        try {
            List<String> contents = Files.readAllLines(topicGroupsPath);
            ArrayList<String> topicGroupsInfos = new ArrayList<>();
            for (String content : contents) {
                if (!content.equals("")) {
                    topicGroupsInfos.add(extractInfo(content));
                }
            }

            // Thanks to the formatting, extracting whole topic groups is not that complicated
            String title = "";
            int priority = 0;
            Subject subject = null;
            Topic topicGroup = null;
            for (int position = 0; position < topicGroupsInfos.size(); position++) {
                if (position % 4 == 0 && topicGroupsInfos.get(position) != null) {
                    title = topicGroupsInfos.get(position);
                } else if (position % 4 == 1 && topicGroupsInfos.get(position) != null) {
                    priority = Integer.parseInt(topicGroupsInfos.get(position));
                } else if (position % 4 == 2 && topicGroupsInfos.get(position) != null) {
                    subject = findSubject(topicGroupsInfos.get(position));
                } else if (topicGroupsInfos.get(position) != null && subject != null) {
                    topicGroup = findTopicGroup(topicGroupsInfos.get(position), subject);
                    new Topic(title, priority, subject, topicGroup);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a topic from the 'topics.txt' file
     * when the application is started and the file is not empty
     */
    public static void createTopicsFromFile() {
        // Extract all important information from file and put into List for easier access
        try {
            List<String> contents = Files.readAllLines(topicsPath);
            ArrayList<String> topicInfos = new ArrayList<>();
            for (String content : contents) {
                if (!content.equals("")) {
                    topicInfos.add(extractInfo(content));
                }
            }

            // Thanks to the formatting, extracting whole topics is not that complicated
            String title = "";
            String answer = "";
            int priority = 0;
            Subject subject = null;
            Topic topicGroup = null;
            for (int position = 0; position < topicInfos.size(); position++) {
                if (position % 5 == 0 && topicInfos.get(position) != null) {
                    title = topicInfos.get(position);
                } else if (position % 5 == 1 && topicInfos.get(position) != null) {
                    answer = topicInfos.get(position);
                } else if (position % 5 == 2 && topicInfos.get(position) != null) {
                    priority = Integer.parseInt(topicInfos.get(position));
                } else if (position % 5 == 3 && topicInfos.get(position) != null) {
                    subject = findSubject(topicInfos.get(position));
                } else if (topicInfos.get(position) != null && subject != null) {
                    topicGroup = findTopicGroup(topicInfos.get(position), subject);
                    new Topic(title, answer, priority, subject, topicGroup);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method finds the appropriate subject matching the title, extracted from the file
     *
     * @param title name of subject
     * @return data type Subject with same title
     */
    public static Subject findSubject(String title) {
        ArrayList<Subject> subjects = Subject.getSubjectList();
        for (Subject subject : subjects) {
            if (title.equals(subject.getTitle())) {
                return subject;
            }
        }
        return null;
    }

    /**
     * This method finds the appropriate topic group matching the title, extracted from the file
     *
     * @param title name of the topic group
     * @param subject connected subject
     * @return data type Topic with same title
     */
    private static Topic findTopicGroup(String title, Subject subject) {
        ArrayList<Topic> topicGroups = subject.getTopicGroupList();
        for (Topic topicGroup : topicGroups) {
            if (title.equals(topicGroup.getTitle())) {
                return topicGroup;
            }
        }
        return null;
    }

    /**
     * This method converts the date into the right data type to construct a Subject
     *
     * @param date date format as String
     * @return date format as LocalDate
     */
    public static LocalDate convertToDate(String date) {
        String[] dateComponents = date.split("-");
        int year = Integer.parseInt(dateComponents[0]);
        int month = Integer.parseInt(dateComponents[1]);
        int day = Integer.parseInt(dateComponents[2]);
        return LocalDate.of(year, month, day);
    }

    /**
     * This method splits every line in a String array.
     * That way important data can be separated.
     *
     * @param line from .txt file
     * @return important data like title, priority etc. as String
     */
    private static String extractInfo(String line) {
        String[] lineProperties = line.split(": ");
        System.out.println(Arrays.toString(lineProperties));
        return lineProperties[1];
    }

    /**
     * This method is for testing. It prints all the whole file onto the console
     *
     * @param path to the file that shall be printed onto the console
     */
    public static void printFile(Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getContentsToWrite() {
        return contentsToWrite;
    }

    public static Path getSubjectsPath() {
        return subjectsPath;
    }

    public static Path getTopicGroupsPath() {
        return topicGroupsPath;
    }

    public static Path getTopicsPath() {
        return topicsPath;
    }
}
