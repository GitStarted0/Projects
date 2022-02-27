package Logic;

import org.junit.After;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;


public class Tests {

//    @Test
//    public void subjectCreation() {
//        // Arrange
//        String title = "Software Development I";
//        int prio = 2;
//        LocalDate date = LocalDate.of(2022, 3, 14);
//        // Act
//        Subject subject = new Subject(title, prio, date);
//        // Assert
//        Assertions.assertNotNull(subject);
//        Assertions.assertEquals(title, subject.getTitle());
//        Assertions.assertEquals(prio, subject.getPriority());
//        Assertions.assertEquals(date, subject.getDateOfExam());
//    }
//
//    @Test
//    public void subjectCreationWithoutPriority() {
//        // Arrange
//        String title = "Software Development I";
//        LocalDate date = LocalDate.of(2022, 3, 14);
//        // Act
//        Subject subject = new Subject(title, date);
//        // Assert
//        Assertions.assertNotNull(subject);
//        Assertions.assertEquals(title, subject.getTitle());
//        Assertions.assertEquals(0, subject.getPriority());
//        Assertions.assertEquals(date, subject.getDateOfExam());
//    }
//
//    @Test
//    public void subjectCreationWithoutDateOfExam() {
//        // Arrange
//        String title = "Software Development I";
//        int prio = 2;
//        // Act
//        Subject subject = new Subject(title, prio);
//        // Assert
//        Assertions.assertNotNull(subject);
//        Assertions.assertEquals(title, subject.getTitle());
//        Assertions.assertEquals(prio, subject.getPriority());
//        Assertions.assertNull(subject.getDateOfExam());
//    }
//
//    @Test
//    public void subjectCreationTitleOnly() {
//        // Act
//        Subject subject = new Subject("Software Development I");
//        // Assert
//        Assertions.assertNotNull(subject);
//        Assertions.assertNull(subject.getDateOfExam());
//    }
//
//    @Test
//    public void subjectSetPriority() {
//        // Arrange
//        Subject subject = new Subject("Software Development I", 2, LocalDate.now());
//        // Act
//        subject.setPriority(1);
//        // Assert
//        Assertions.assertEquals(1, subject.getPriority());
//    }
//
//    @Test
//    public void subjectSetTitle() {
//        // Arrange
//        Subject subject = new Subject("Software Development I", 2, LocalDate.now());
//        // Act
//        subject.setTitle("physics");
//        // Assert
//        Assertions.assertEquals("physics", subject.getTitle());
//    }
//
//    @Test
//    public void subjectSetDate() {
//        // Arrange
//        LocalDate date = LocalDate.of(2022, 5, 12);
//        Subject subject = new Subject("Software Development I", 2, LocalDate.now());
//        // Act
//        subject.setDateOfExam(date);
//        // Assert
//        Assertions.assertEquals(date, subject.getDateOfExam());
//    }
//
//    @Test
//    public void subjectList() {
//        // Arrange
//        int year = 2022;
//        int month = 10;
//        int day = 10;
//        // Act
//        for (int i = 0; i < 5; i++) {
//            new Subject("subject" + (i + 1), LocalDate.of(year - i, month - i, day - i));
//        }
//        // Assert
//        System.out.println(Subject.getSubjectList().toString());
//        Assertions.assertNotNull(Subject.getSubjectList());
//        Assertions.assertEquals(5, Subject.getSubjectList().size());
//
//    }
//
//    @Test
//    public void topicCreation() {
//        // Arrange
//        String title = "String";
//        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
//        int priority = 3;
//        Subject subject = new Subject("Software Development I");
//        Topic topicGroup = new Topic("Primitive Data Types", subject);
//        // Act
//        Topic topic = new Topic(title, answer, priority, subject, topicGroup);
//        // Assert
//        Assertions.assertNotNull(topic);
//        Assertions.assertEquals(title, topic.getTitle());
//        Assertions.assertEquals(answer, topic.getAnswer());
//        Assertions.assertEquals(priority, topic.getPriority());
//        Assertions.assertEquals(subject, topic.getSubject());
//        Assertions.assertEquals(topicGroup, topic.getTopicGroup());
//    }
//
//    @Test
//    public void topicCreationWithoutPriority() {
//        // Arrange
//        String title = "String";
//        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
//        Subject subject = new Subject("Software Development I");
//        Topic topicGroup = new Topic("Primitive Data Types", subject);
//        // Act
//        Topic topic = new Topic(title, answer, subject, topicGroup);
//        // Assert
//        Assertions.assertNotNull(topic);
//        Assertions.assertEquals(title, topic.getTitle());
//        Assertions.assertEquals(answer, topic.getAnswer());
//        Assertions.assertEquals(0, topic.getPriority());
//        Assertions.assertEquals(subject, topic.getSubject());
//        Assertions.assertEquals(topicGroup, topic.getTopicGroup());
//    }
//
//    @Test
//    public void topicCreationWithoutTopicGroup() {
//        // Arrange
//        String title = "String";
//        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
//        int priority = 3;
//        Subject subject = new Subject("SE-II");
//        // Act
//        Topic topic = new Topic(title, answer, priority, subject);
//        // Assert
//        Assertions.assertNotNull(topic);
//        Assertions.assertEquals(title, topic.getTitle());
//        Assertions.assertEquals(answer, topic.getAnswer());
//        Assertions.assertEquals(priority, topic.getPriority());
//        Assertions.assertEquals(subject, topic.getSubject());
//        Assertions.assertNull(topic.getTopicGroup());
//    }
//
//    @Test
//    public void topicCreationWithoutAnswerButWithTopicGroup() {
//        // Arrange
//        Subject test = new Subject("Software Development I");
//        Topic test2 = new Topic("Topic Group", test);
//        String title = "Primitive Data Types";
//        int priority = 3;
//        Subject subject = test;
//        Topic topicGroup = test2;
//        // Act
//        Topic topic = new Topic(title, "Answer", priority, subject, topicGroup);
//        // Assert
//        Assertions.assertNotNull(topic);
//        Assertions.assertEquals(title, topic.getTitle());
//        Assertions.assertNull(topic.getAnswer());
//        Assertions.assertEquals(priority, topic.getPriority());
//        Assertions.assertEquals(subject.getTitle(), topic.getSubject().getTitle());
//        Assertions.assertEquals(topicGroup.getTitle(), topic.getTopicGroup().getTitle());
//    }
//
//    @Test
//    public void topicList() {
//        // Arrange
//        Subject subject = new Subject("Software Development I");
//        Topic topicGroup = new Topic("Topic Group", subject);
//        for (int i = 0; i < 5; i++) {
//            new Topic("Topic " + (i + 1), "Answer" + (i + 1), subject, topicGroup);
//        }
//        // Assert
//        System.out.println(subject.getAlltopicsList().toString());
//        Assertions.assertNotNull(subject.getAlltopicsList());
//        Assertions.assertEquals(5, subject.getAlltopicsList().size());
//    }
//
//    @Test
//    public void setterMethodsTopic() {
//        // Arrange
//        Subject atFirst = new Subject("Software Development I", 0, LocalDate.now());
//        Subject then = new Subject("New Title");
//        String title = "String";
//        String answer = "A character string, protected in quotation marks\nexample:\nexample = 'example';";
//        int priority = 3;
//        Topic tG1 = new Topic("Primitive Data Types", atFirst);
//        Topic tG2 = new Topic("New Topic Group", then);
//        Topic topic = new Topic(title, answer, priority, atFirst, tG1);
//        // Act
//        topic.setTitle("New Title");
//        topic.setAnswer("New Answer");
//        topic.setPriority(1);
//        topic.setSubject(then);
//        topic.setTopicGroup(tG2);
//        // Assert
//        Assertions.assertEquals("New Title", topic.getTitle());
//        Assertions.assertEquals("New Answer", topic.getAnswer());
//        Assertions.assertEquals(1, topic.getPriority());
//        Assertions.assertEquals("New Subject", topic.getSubject().getTitle());
//        Assertions.assertEquals("New Topic Group", topic.getTopicGroup().getTitle());
//    }
//
//    @Test
//    public void dataTypesCreatedCorrectly() {
//        // Arrange
//        Subject subject = new Subject("Software Development I", 2, LocalDate.of(2022, 3, 14));
//        // Act
//        String[] titles = new String[] {"String", "Integer", "Boolean", "Primitive Data Types"};
//        String[] answers = new String[] {"Text", "Number", "True/False"};
//        for (int i = 0; i < titles.length; i++) {
//            if (i < answers.length){
//                new Topic(titles[i], answers[i], 2, subject);
//            } else {
//                new Topic(titles[i], 2, subject);
//            }
//        }
//        // Assert
//        Assertions.assertEquals(subject.getAlltopicsList().get(0).getTitle(), titles[0]);
//        Assertions.assertEquals(subject.getAlltopicsList().get(1).getTitle(), titles[1]);
//        Assertions.assertEquals(subject.getAlltopicsList().get(2).getTitle(), titles[2]);
//        Assertions.assertEquals(subject.getAllTopicGroupsList().get(0).getTitle(), titles[3]);
//        System.out.println("Subjects:");
//        for (Subject sub : Subject.getSubjectList()) {
//            System.out.println(sub.getDateOfExam());
//        }
//        System.out.println("Topic Groups:");
//        for (Topic topicGroup : subject.getAllTopicGroupsList()) {
//            System.out.println(topicGroup.getTitle());
//        }
//        System.out.println("Topics:");
//        for (Topic topic : subject.getAlltopicsList()) {
//            System.out.println(topic.getTitle());
//        }
//        String[] contents = new String[] {subject.getTitle(), Integer.toString(subject.getPriority()),
//                subject.getDateOfExam().toString()};
//    }
//
//    @Test
//    public void writeToFileCorrectly() {
//        // Arrange
//        setupObjects();
//        // Assert
//        FileManagement.printFile(FileManagement.getSubjectsPath());
//        FileManagement.printFile(FileManagement.getTopicGroupsPath());
//        FileManagement.printFile(FileManagement.getTopicsPath());
////        clearFiles();
//    }
//
//    @Test
//    public void createObjectsFromFile() {
//        // Arrange
//        // Act
//        FileManagement.createSubjectsFromFile();
//        FileManagement.createTopicGroupsFromFile();
//        FileManagement.createTopicsFromFile();
//        // Assert
//        // Two subjects were created from subjects.txt
//        for (Subject s : Subject.getSubjectList()) {
//            System.out.println("Subject: " + s.getTitle() + ", " + s.getPriority() + ", " + s.getDateOfExam());
//        }
//        Subject s1 = Subject.getSubjectList().get(0);
//        Subject s2 = Subject.getSubjectList().get(1);
//        System.out.println("Topic Group: " + s1.getAllTopicGroupsList().get(0).getTitle()
//                + ", " + s1.getAllTopicGroupsList().get(0).getPriority() + ", "
//                + s1.getAllTopicGroupsList().get(0).getSubject().getTitle());
//        System.out.println("Topic Group: " + s2.getAllTopicGroupsList().get(0).getTitle()
//                + ", " + s2.getAllTopicGroupsList().get(0).getPriority() + ", "
//                + s2.getAllTopicGroupsList().get(0).getSubject().getTitle());
//        System.out.println("Topic: " + s1.getAlltopicsList().get(0).getTitle() + ", "
//                + s1.getAlltopicsList().get(0).getAnswer() + ", "
//                + s1.getAlltopicsList().get(0).getPriority() + ", "
//                + s1.getAlltopicsList().get(0).getSubject().getTitle() + ", "
//                + s1.getAlltopicsList().get(0).getTopicGroup().getTitle());
//        System.out.println("Topic: " + s2.getAlltopicsList().get(0).getTitle() + ", "
//                + s2.getAlltopicsList().get(0).getAnswer() + ", "
//                + s2.getAlltopicsList().get(0).getPriority() + ", "
//                + s2.getAlltopicsList().get(0).getSubject().getTitle() + ", "
//                + s2.getAlltopicsList().get(0).getTopicGroup().getTitle());
//        Assertions.assertEquals(2, Subject.getSubjectList().size());
//    }
//
//    @Test
//    public void objectsClearedFromFileWhenDeleted() {
//        // Arrange
//        FileManagement.createSubjectsFromFile();
//        FileManagement.createTopicGroupsFromFile();
//        FileManagement.createTopicsFromFile();
//        // Act
//        FileManagement.deleteSubject(Subject.getSubjectList().get(0));
//        // Assert
//        Assertions.assertEquals(1, Subject.getSubjectList().size());
//        System.out.println("---");
//        FileManagement.printFile(FileManagement.getSubjectsPath());
//        System.out.println("---");
//        FileManagement.printFile(FileManagement.getTopicGroupsPath());
//        System.out.println("---");
//        FileManagement.printFile(FileManagement.getTopicsPath());
//        System.out.println("---");
//    }

    @Test
    public void selectTopicFromSubjectWithPriorityUnset() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        System.out.println(Subject.getSubjectList().size());
        // Act
        Subject selected = Selecting.selectSubFromAll();
        // Assert
        Assertions.assertEquals(0, selected.getPriority());
        clearEverything();
    }

    @Test
    @Order(1)
    public void selectTopicFromSubjectWithPriorityHard() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        rearrangeSubjectWithPriorityUnset();
        System.out.println(Subject.getSubjectList().size());
        // Act
        Subject selected = Selecting.selectSubFromAll();
        selected = Selecting.selectSubFromAll();
        // Assert
        Assertions.assertEquals(1, selected.getPriority());
        clearFiles();
        Selecting.setSubjectCounter(0);
    }

    @Test
    @Order(2)
    public void selectTopicFromSubjectWithPriorityMedium() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        rearrangeSubjectWithPriorityUnset();
        System.out.println(Subject.getSubjectList().size());
        // Act
        Subject selected = Selecting.selectSubFromAll();
        for (int i = 0; i < 3; i++) {
            selected = Selecting.selectSubFromAll();
        }
        // Assert
        Assertions.assertEquals(2, selected.getPriority());
        clearFiles();
        Selecting.setSubjectCounter(0);
    }

    @Test
    @Order(3)
    public void selectTopicFromSubjectWithPriorityEasy() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        rearrangeSubjectWithPriorityUnset();
        System.out.println(Subject.getSubjectList().size());
        // Act
        Subject selected = Selecting.selectSubFromAll();
        for (int i = 0; i < 5; i++) {
            selected = Selecting.selectSubFromAll();
        }
        // Assert
        Assertions.assertEquals(3, selected.getPriority());
        clearFiles();
        Selecting.setSubjectCounter(0);
    }

    @Test
    public void selectTopicFromTopicGroupWithPriorityUnset() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        arrangeTopicGroupSelection();
        // Act
        Topic selected = Selecting.selectTGFromAll();
        // Assert
        Assertions.assertEquals(0, selected.getPriority());
    }

    @Test
    @Order(4)
    public void selectTopicFromTopicGroupWithPriorityHard() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        if (!Selecting.getSubjectPriorityUnset().isEmpty()) {
            rearrangeSubjectWithPriorityUnset();
        }
        arrangeTopicGroupSelection();
        rearrangeTopicGroupWithPriorityUnset();
        // Act
        Topic selected = Selecting.selectTGFromAll();
        selected = Selecting.selectTGFromAll();
        // Assert
        Assertions.assertEquals(1, selected.getPriority());
        clearFiles();
        Selecting.setSubjectCounter(0);
        Selecting.setTopicGroupCounter(0);
        Subject.getSubjectList().clear();
    }

    @Test
    @Order(5)
    public void selectTopicFromTopicGroupWithPriorityMedium() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        if (!Selecting.getSubjectPriorityUnset().isEmpty()) {
            rearrangeSubjectWithPriorityUnset();
        }
        arrangeTopicGroupSelection();
        rearrangeTopicGroupWithPriorityUnset();
        // Act
        Topic selected = Selecting.selectTGFromAll();
        for (int i = 0; i < 3; i++) {
            selected = Selecting.selectTGFromAll();
        }
        // Assert
        Assertions.assertEquals(2, selected.getPriority());
    }

    @Test
    @Order(6)
    public void selectTopicFromTopicGroupWithPriorityEasy() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        if (!Selecting.getSubjectPriorityUnset().isEmpty()) {
            rearrangeSubjectWithPriorityUnset();
        }
        arrangeTopicGroupSelection();
        rearrangeTopicGroupWithPriorityUnset();
        // Act
        Topic selected = Selecting.selectTGFromAll();
        for (int i = 0; i < 6; i++) {
            selected = Selecting.selectTGFromAll();
        }
        // Assert
        Assertions.assertEquals(3, selected.getPriority());
    }

    @Test
    public void selectTopicWithPriorityUnset() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        arrangeTopicGroupSelection();
        arrangeTopicSelection();
        // Act
        Topic selected = Selecting.selectTopicFromAll();
        // Assert
        Assertions.assertEquals(0, selected.getPriority());
    }

    @Test
    @Order(7)
    public void selectTopicWithPriorityHard() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        if (!Selecting.getSubjectPriorityUnset().isEmpty()) {
            rearrangeSubjectWithPriorityUnset();
        }
        arrangeTopicGroupSelection();
        rearrangeTopicGroupWithPriorityUnset();
        arrangeTopicSelection();
        rearrangeTopicWithPriorityUnset();
        // Act
        Topic selected = Selecting.selectTopicFromAll();
        selected = Selecting.selectTopicFromAll();
        // Assert
        Assertions.assertEquals(1, selected.getPriority());
        clearFiles();
    }

    @Test
    @Order(8)
    public void selectTopicWithPriorityMedium() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        if (!Selecting.getSubjectPriorityUnset().isEmpty()) {
            rearrangeSubjectWithPriorityUnset();
        }
        arrangeTopicGroupSelection();
        rearrangeTopicGroupWithPriorityUnset();
        arrangeTopicSelection();
        rearrangeTopicWithPriorityUnset();
        // Act
        Topic selected = Selecting.selectTopicFromAll();
        for (int i = 0; i < 3; i++) {
            selected = Selecting.selectTopicFromAll();
        }
        // Assert
        Assertions.assertEquals(2, selected.getPriority());
        clearFiles();
    }

    @Test
    @Order(9)
    public void selectTopicWithPriorityEasy() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        if (!Selecting.getSubjectPriorityUnset().isEmpty()) {
            rearrangeSubjectWithPriorityUnset();
        }
        arrangeTopicGroupSelection();
        rearrangeTopicGroupWithPriorityUnset();
        arrangeTopicSelection();
        rearrangeTopicWithPriorityUnset();
        // Act
        Topic selected = Selecting.selectTopicFromAll();
        for (int i = 0; i < 6; i++) {
            selected = Selecting.selectTopicFromAll();
        }
        // Assert
        Assertions.assertEquals(3, selected.getPriority());
        clearFiles();
    }

    @Test
    public void selectionWithMoreTopicsInSubject() {
        // Arrange
        clearEverything();
        Subject s = new Subject("Title", 0);
        Topic tg = new Topic("TG", 0, s);
        Topic t1 = new Topic("T-1", "A-1", 1, s, tg);
        Topic t2 = new Topic("T-2", "A-2", 0, s);
        Topic t3 = new Topic("T-3", "A-3", 0, s);
        Topic t4 = new Topic("T-4", "A-4", 0, s);
        // Act
        Topic selected = Selecting.selectFromSubject(s);
        // Assert
        Assertions.assertNull(selected.getTopicGroup());
    }

    @Test
    public void statisticsAmountOfSubjectsCorrect() {
        // Arrange
        clearEverything();
        arrangeSubjectSelection();
        // Act
        FileManagement.writeStatistics();
        // Assert
        FileManagement.printFile(FileManagement.getStatisticsPath());
        clearStatistics();
    }

    public void clearStatistics() {
        try {
            List<String> lines = Files.readAllLines(FileManagement.getStatisticsPath());
            lines.clear();
            Files.write(FileManagement.getStatisticsPath(), lines, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void arrangeSubjectSelection() {
        Subject s1 = new Subject("Title 0", 0);
        Subject s2 = new Subject("Title 1", 1);
        Subject s3 = new Subject("Title 2", 2);
        Subject s4 = new Subject("Title 3", 3);
    }

    public void arrangeTopicGroupSelection () {
        for (Subject subject : Subject.getSubjectList()) {
            for (int i = 0; i < 4; i++) {
                Topic t = new Topic("TG-" + i, i, subject);
            }
        }
    }

    public void arrangeTopicSelection() {
        for (Subject subject : Subject.getSubjectList()) {
            for (Topic topicGroup : subject.getAllTopicGroupsList()) {
                for (int i = 0; i < 4; i++) {
                    new Topic("T-" + i, "A-" + i, i, subject, topicGroup);
                }
            }
        }
    }

    public void rearrangeSubjectWithPriorityUnset() {
        System.out.println(Selecting.getSubjectPriorityUnset().size());
        System.out.println(Selecting.getSubjectPriorityMedium().size());
        Subject subjectToRearrange = Selecting.getSubjectPriorityUnset().get(0);
        subjectToRearrange.setPriority(2);
        Selecting.getSubjectPriorityMedium().add(subjectToRearrange);
        Selecting.getSubjectPriorityUnset().remove(0);
        if (Selecting.getSubjectPriorityUnset().isEmpty() && Selecting.getSubjectPriorityMedium().size() == 2) {
            System.out.println(subjectToRearrange.getTitle() + " Arrangement successful");
        } else {
            System.out.println(subjectToRearrange.getTitle() + " Arrangement not successful");
            System.out.println(Selecting.getSubjectPriorityUnset().size());
            System.out.println(Selecting.getSubjectPriorityMedium().size());
        }
    }

    public void rearrangeTopicGroupWithPriorityUnset() {
        System.out.println("Amount Subjects: " + Subject.getSubjectList().size());
        for (Subject subject : Subject.getSubjectList()) {
            System.out.println(subject.getTopicGroupPriorityUnset().size());
            if (subject.getTopicGroupPriorityUnset().size() != 0) {
                Topic topicGroupToRearrange = subject.getTopicGroupPriorityUnset().get(0);
                topicGroupToRearrange.setPriority(2);
                subject.getTopicGroupPriorityUnset().remove(0);
                subject.getTopicGroupPriorityMedium().add(topicGroupToRearrange);
                if (subject.getTopicGroupPriorityUnset().isEmpty() && subject.getTopicGroupPriorityMedium().size() == 2) {
                    System.out.println(topicGroupToRearrange.getTitle() + " Arrangement successful");
                } else {
                    System.out.println(topicGroupToRearrange.getTitle() + " Arrangement not successful");
                }
            }
        }
    }

    public void rearrangeTopicWithPriorityUnset() {
        for (Subject subject : Subject.getSubjectList()) {
            for (Topic topicGroup : subject.getAllTopicGroupsList()) {
                if (!topicGroup.getTopicPriorityUnset().isEmpty()) {
                    Topic topicToRearrange = topicGroup.getTopicPriorityUnset().get(0);
                    topicToRearrange.setPriority(2);
                    topicGroup.getTopicPriorityUnset().remove(topicToRearrange);
                    topicGroup.getTopicPriorityMedium().add(topicToRearrange);
                    if (topicGroup.getTopicPriorityUnset().isEmpty() && topicGroup.getTopicPriorityMedium().size() == 2) {
                        System.out.println(topicToRearrange.getTitle() + " Arrangement successful");
                    } else {
                        System.out.println(topicToRearrange.getTitle() + " Arrangement not successful");
                    }
                }
            }
        }
    }

    public void clearFiles() {
        try {
            File contents = new File("C:\\Users\\user\\Documents\\Projekt-Semesterferien\\files");
            File[] files = contents.listFiles();
            for (File file : files) {
                List<String> read = Files.readAllLines(Path.of(file.getPath()));
                read.clear();
                Files.write(Path.of(file.getPath()), read, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearSubjectPriorityArrays() {
        Selecting.getSubjectPriorityUnset().clear();
        Selecting.getSubjectPriorityEasy().clear();
        Selecting.getSubjectPriorityMedium().clear();
        Selecting.getSubjectPriorityHard().clear();
    }

    @After
    public void clearEverything() {
        clearFiles();
        Subject.getSubjectList().clear();
        Selecting.setSubjectCounter(0);
        Selecting.setTopicGroupCounter(0);
        Selecting.setTopicCounter(0);
        clearSubjectPriorityArrays();
    }

//    @Test
//    public void fillFiles() {
//        setupObjects();
//    }

//    @Test
//    public void timeStatisticsCorrect() throws InterruptedException {
//        // Arrange
//        Subject s = new Subject("SE-I", LocalDate.of(2022, 3, 14));
//        // Act
//        s.setStartedStudying(LocalDateTime.now());
//        TimeUnit.SECONDS.sleep(5);
//        s.setStoppedStudying(LocalDateTime.now());
//        s.setStartedStudying(LocalDateTime.now());
//        TimeUnit.SECONDS.sleep(5);
//        s.setStoppedStudying(LocalDateTime.now());
//        // Assert
//        Assertions.assertEquals(10, s.getTimeStudied());
//    }

    public void setupObjects() {
        Subject s1 = new Subject("SE-I", LocalDate.of(2022, 3, 14));
        Subject s2 = new Subject("SE-II", LocalDate.of(2021, 1, 3));
        Topic tg1 = new Topic("Topic Group 1", s1);
        Topic tg2 = new Topic("Topic Group 2", s2);
        Topic t1 = new Topic("Topic 1", "Answer 1", s1, tg1);
        Topic t2 = new Topic("Topic 2", "Answer 2", s2, tg2);
        FileManagement.writeSubject(s1);
        FileManagement.writeSubject(s2);
        FileManagement.writeTopic(tg1);
        FileManagement.writeTopic(tg2);
        FileManagement.writeTopic(t1);
        FileManagement.writeTopic(t2);
    }
}
