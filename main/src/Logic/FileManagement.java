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
    private static final Path statisticsPath = Paths.get(pathname + "statistics.txt");
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
                    new Topic(title, priority, subject);
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
        ArrayList<Topic> topicGroups = subject.getAllTopicGroupsList();
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
//        System.out.println(Arrays.toString(lineProperties));
        return lineProperties[1];
    }

    public static void deleteSubject(Subject subject) {
        try {
            // Delete all topics
            for (Topic topic : subject.getAlltopicsList()) {
                deleteTopic(topic);
            }

            // Delete all topic groups
             for (Topic topicGroup : subject.getAllTopicGroupsList()) {
                  deleteTopicGroup(topicGroup);
             }

            // Delete actual subject
            List<String> subjects = Files.readAllLines(subjectsPath);
            System.out.println(subjects);
            int position = 0;
            for (int i = 0; i < subjects.size(); i++) {
                String[] line = subjects.get(i).split(": ");
                if (line[line.length - 1].equals(subject.getTitle())) {
                    position = i;
                }
            }
            for (int j = position; j < 4; j++) {
                subjects.remove(position);
            }
            Subject.getSubjectList().remove(subject);
            Files.write(subjectsPath, subjects, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteTopicGroup(Topic topicGroup) {
        try {
            List<String> topicGroups = Files.readAllLines(topicGroupsPath);
            int position = 0;
            for (int i = 0; i < topicGroups.size(); i++) {
                if (!topicGroups.get(i).equals("")) {
                    String[] line = topicGroups.get(i).split(": ");
                    if (line[line.length - 1].equals(topicGroup.getTitle())) {
                        position = i;
                    }
                }
            }
            for (int j = position; j < 5; j++) {
                topicGroups.remove(position);
            }
            Files.write(topicGroupsPath, topicGroups, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteTopic(Topic topic) {
        try {
            List<String> topics = Files.readAllLines(topicsPath);
            int position = 0;
            for (int i = 0; i < topics.size(); i++) {
                if (!topics.get(i).equals("")) {
                    String[] line = topics.get(i).split(": ");
                    if (line[line.length - 1].equals(topic.getTitle())) {
                        position = i;
                    }
                }
            }
            for (int j = position; j < 6; j++) {
                topics.remove(position);
            }
            Files.write(topicsPath, topics, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method determines the overall statistics about the contents of the application.
     *
     * @return ArrayList with the overall statistics
     */
    public static ArrayList<String> overallStatistics() {
        ArrayList<String> overallStatistics = new ArrayList<>();
        int totalTimeStudied = 0;
        int subjects = Subject.getSubjectList().size();
        int easySubjects = Selecting.getSubjectPriorityEasy().size();
        int mediumSubjects = Selecting.getSubjectPriorityMedium().size();
        int hardSubjects = Selecting.getSubjectPriorityHard().size();
        int unsetSubjects = Selecting.getSubjectPriorityUnset().size();
        int topicGroups = 0;
        int easyTopicGroups = 0;
        int mediumTopicGroups = 0;
        int hardTopicGroups = 0;
        int unsetTopicGroups = 0;
        int topics = 0;
        int easyTopics = 0;
        int mediumTopics = 0;
        int hardTopics = 0;
        int unsetTopics = 0;
        for (Subject subject : Subject.getSubjectList()) {
            totalTimeStudied += subject.getTimeStudied();
            topicGroups += subject.getAllTopicGroupsList().size();
            easyTopicGroups += subject.getTopicGroupPriorityEasy().size();
            mediumTopicGroups += subject.getTopicGroupPriorityMedium().size();
            hardTopicGroups += subject.getTopicGroupPriorityHard().size();
            unsetTopicGroups += subject.getTopicGroupPriorityUnset().size();
            topics += subject.getAlltopicsList().size();
            easyTopics += subject.getTopicPriorityEasy().size();
            mediumTopics += subject.getTopicPriorityMedium().size();
            hardTopics += subject.getTopicPriorityHard().size();
            unsetTopics += subject.getTopicPriorityUnset().size();
        }
        overallStatistics.add(Integer.toString(totalTimeStudied));
        addPriorities(overallStatistics, subjects, easySubjects, mediumSubjects, hardSubjects,unsetSubjects);
        addPriorities(overallStatistics, topicGroups, easyTopicGroups, mediumTopicGroups, hardTopicGroups,unsetTopicGroups);
        addPriorities(overallStatistics, topics, easyTopics, mediumTopics, hardTopics, unsetTopics);
        return overallStatistics;
    }

    /**
     * This method determines the statistics of a certain subject.
     *
     * @param subject subject for which the statistics shall be determined
     * @return ArrayList with subject specific statistics
     */
    public static ArrayList<String> subjectSpecificStatistics(Subject subject) {
        ArrayList<String> subjectSpecififcStatistics = new ArrayList<>();
        String title = subject.getTitle();
        int timeStudied = 0;
        timeStudied += subject.getTimeStudied();
        int topicGroups = subject.getAllTopicGroupsList().size();
        int easyTopicGroups = subject.getTopicGroupPriorityEasy().size();
        int mediumTopicGroups = subject.getTopicGroupPriorityMedium().size();
        int hardTopicGroups = subject.getTopicGroupPriorityHard().size();
        int unsetTopicGroups = subject.getTopicGroupPriorityUnset().size();
        int topics = subject.getAlltopicsList().size();
        int easyTopics = subject.getTopicPriorityEasy().size();
        int mediumTopics = subject.getTopicPriorityMedium().size();
        int hardTopics = subject.getTopicPriorityHard().size();
        int unsetTopics = subject.getTopicPriorityUnset().size();
        subjectSpecififcStatistics.add(title);
        subjectSpecififcStatistics.add(Integer.toString(timeStudied));
        addPriorities(subjectSpecififcStatistics, topicGroups, easyTopicGroups, mediumTopicGroups,
                hardTopicGroups, unsetTopicGroups);
        addPriorities(subjectSpecififcStatistics, topics, easyTopics, mediumTopics, hardTopics, unsetTopics);
        return subjectSpecififcStatistics;
    }

    /**
     * This method determines the statistics of a certain topic group.
     *
     * @param topicGroup topic group for which the statistics shall be determined
     * @return ArrayList with topic group specific statistics
     */
    public static ArrayList<String> topicGroupSpecificStatistics(Topic topicGroup) {
        ArrayList<String> topicGroupSpecificStatistics = new ArrayList<>();
        String title = topicGroup.getTitle();
        int timeStudied = 0;
        timeStudied += topicGroup.getTimeStudied();
        int topics = topicGroup.getTopicList().size();
        int easyTopics = topicGroup.getTopicPriorityEasy().size();
        int mediumTopics = topicGroup.getTopicPriorityMedium().size();
        int hardTopics = topicGroup.getTopicPriorityHard().size();
        int unsetTopics = topicGroup.getTopicPriorityUnset().size();
        topicGroupSpecificStatistics.add(title);
        topicGroupSpecificStatistics.add(Integer.toString(timeStudied));
        addPriorities(topicGroupSpecificStatistics, topics, easyTopics, mediumTopics, hardTopics, unsetTopics);
        return topicGroupSpecificStatistics;
    }

    /**
     * This method expands the overall statistics by explanation statements.
     * It formats the statistics to be written into the 'statistics.txt' file.
     *
     * @param rawStatistics ArrayList with numbers only without explanation
     * @return ArrayList with formatted overall statistics
     */
    public static ArrayList<String> formattedOverallStatistics(ArrayList<String> rawStatistics) {
        ArrayList<String> formattedOverallStatistics = new ArrayList<>();
        formattedOverallStatistics.add("total time studied: " + rawStatistics.get(0));
        formattedOverallStatistics.add("total subjects: " + rawStatistics.get(1));
        formattedOverallStatistics.add("easy subjects: " + rawStatistics.get(2));
        formattedOverallStatistics.add("medium subjects: " + rawStatistics.get(3));
        formattedOverallStatistics.add("hard subjects: " + rawStatistics.get(4));
        formattedOverallStatistics.add("unset subjects: " + rawStatistics.get(5));
        formattedOverallStatistics.add("---");
        formattedOverallStatistics.add("topic groups: " + rawStatistics.get(6));
        formattedOverallStatistics.add("easy topic groups: " + rawStatistics.get(7));
        formattedOverallStatistics.add("medium topic groups: " + rawStatistics.get(8));
        formattedOverallStatistics.add("hard topic groups: " + rawStatistics.get(9));
        formattedOverallStatistics.add("unset topic groups: " + rawStatistics.get(10));
        formattedOverallStatistics.add("---");
        formattedOverallStatistics.add("topics: " + rawStatistics.get(11));
        formattedOverallStatistics.add("easy topics: " + rawStatistics.get(12));
        formattedOverallStatistics.add("medium topics: " + rawStatistics.get(13));
        formattedOverallStatistics.add("hard topics: " + rawStatistics.get(14));
        formattedOverallStatistics.add("unset topics: " + rawStatistics.get(15));
        formattedOverallStatistics.add("---");
        return formattedOverallStatistics;
    }

    /**
     * This method expands the subject specific statistics by explanation statements.
     * It formats the statistics to be written into the 'statistics.txt' file.
     *
     * @param rawStatistics ArrayList with numbers only without explanation
     * @return ArrayList with formatted subject statistics
     */
    public static ArrayList<String> formattedSubjectStatistics(ArrayList<String> rawStatistics) {
        ArrayList<String> formattedSubjectStatistics = new ArrayList<>();
        formattedSubjectStatistics.add("subject: " + rawStatistics.get(0));
        formattedSubjectStatistics.add("time studied: " + rawStatistics.get(1));
        formattedSubjectStatistics.add("total topic groups: " + rawStatistics.get(2));
        formattedSubjectStatistics.add("easy topic groups: " + rawStatistics.get(3));
        formattedSubjectStatistics.add("medium topic groups: " + rawStatistics.get(4));
        formattedSubjectStatistics.add("hard topic groups: " + rawStatistics.get(5));
        formattedSubjectStatistics.add("unset topic groups: " + rawStatistics.get(6));
        formattedSubjectStatistics.add("total topics: " + rawStatistics.get(7));
        formattedSubjectStatistics.add("easy topics: " + rawStatistics.get(8));
        formattedSubjectStatistics.add("medium topics: " + rawStatistics.get(9));
        formattedSubjectStatistics.add("hard topics: " + rawStatistics.get(10));
        formattedSubjectStatistics.add("unset topics: " + rawStatistics.get(11));
        formattedSubjectStatistics.add("---");
        return formattedSubjectStatistics;
    }

    /**
     * This method expands the topic group specific statistics by explanation statements.
     * It formats the statistics to be written into the 'statistics.txt' file.
     *
     * @param rawStatistics ArrayList with numbers only without explanation
     * @return ArrayList with formatted topic group statistics
     */
    public static ArrayList<String> formattedTopicGroupStatistics(ArrayList<String> rawStatistics) {
        ArrayList<String> formattedTopicGroupStatistics = new ArrayList<>();
        formattedTopicGroupStatistics.add("topic group: " + rawStatistics.get(0));
        formattedTopicGroupStatistics.add("time studied: " + rawStatistics.get(1));
        formattedTopicGroupStatistics.add("total topics: " + rawStatistics.get(2));
        formattedTopicGroupStatistics.add("easy topics: " + rawStatistics.get(3));
        formattedTopicGroupStatistics.add("medium topics: " + rawStatistics.get(4));
        formattedTopicGroupStatistics.add("hard topics: " + rawStatistics.get(5));
        formattedTopicGroupStatistics.add("unset topics: " + rawStatistics.get(6));
        formattedTopicGroupStatistics.add("---");
        return formattedTopicGroupStatistics;
    }

    /**
     * This method adds the different Object types (subject, topic groups, topics)
     * in total digits and priority specific to an ArrayList.
     *
     * @param arrayList ArrayList to which numbers shall be added.
     * @param totalTypes total amount of object type
     * @param easyTypes amount of object types with priority 'easy'
     * @param mediumTypes amount of object types with priority 'medium'
     * @param hardTypes amount of object types with priority 'hard'
     * @param unsetTypes amount of object types with priority 'unset'
     */
    private static void addPriorities(ArrayList<String> arrayList, int totalTypes, int easyTypes,
                                      int mediumTypes, int hardTypes, int unsetTypes) {
        arrayList.add(Integer.toString(totalTypes));
        arrayList.add(Integer.toString(easyTypes));
        arrayList.add(Integer.toString(mediumTypes));
        arrayList.add(Integer.toString(hardTypes));
        arrayList.add(Integer.toString(unsetTypes));
    }

    /**
     * This method writes all statistics to the 'statistics.txt' file.
     * By this statistics can be saved, read and expanded.
     */
    public static void writeStatistics() {
        try {
            File statistics = new File(pathname + "statistics.txt");
            ArrayList<String> rawOverallStatistics = overallStatistics();
            ArrayList<String> allStatistics = new ArrayList<>(formattedOverallStatistics(rawOverallStatistics));

            // add all subject specific statistics per subject
            for (Subject subject : Subject.getSubjectList()) {
                ArrayList<String> rawSubjectStatistics = subjectSpecificStatistics(subject);
                allStatistics.addAll(formattedSubjectStatistics(rawSubjectStatistics));
            }
            // add all topic group specific statistics per topic group
            for (Subject subject : Subject.getSubjectList()) {
                for (Topic topicGroup : subject.getAllTopicGroupsList()) {
                    ArrayList<String> rawTopicGroupStatistics = topicGroupSpecificStatistics(topicGroup);
                    allStatistics.addAll(formattedTopicGroupStatistics(rawTopicGroupStatistics));
                }
            }
            Files.write(statisticsPath, allStatistics, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static Path getStatisticsPath() {
        return statisticsPath;
    }
}
