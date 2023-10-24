
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

class TreeNode<Item> {
    Item data;
    int score = 0;
    List<TreeNode<Item>> children;

    public TreeNode(Item data) {
        this.data = data;
        this.children = new ArrayList<>();
    }
    public TreeNode(Item data, int score) {
        this.data = data;
        this.score = score;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode<Item> child) { this.children.add(child); }

}
public class DecisionTree<Item> {
    private final TreeNode<Item> root;
    static final int minApplicationScore = 10;
    static final int medianApplicationScore = 15;
    static final int maxApplicationScore = 20;

    public DecisionTree(Item rootData) {
        root = new TreeNode<>(rootData);
    }

    private TreeNode<Item> getRoot() {
        return root;
    }

    private void addNode(TreeNode<Item> parent, Item data, int score) {
        TreeNode<Item> newNode = new TreeNode<>(data, score);
        parent.addChild(newNode);
    }

    private void addNode(TreeNode<Item> parent, Item data) {
        TreeNode<Item> newNode = new TreeNode<>(data);
        parent.addChild(newNode);
    }

    public static void evaluateMcatScore(Applicant applicant) {
        final int minMcatScore = 472;
        final int q1McatScore = 492;
        final int medianMcatScore = 511;
        final int q3McatScore = 520;
        final int maxMcatScore = 528;
        final int applicantMcatScore = applicant.getMcatScore();
        System.out.println("\tApplicant's MCAT Score: " + applicantMcatScore);

        if (applicantMcatScore > maxMcatScore || applicantMcatScore < minMcatScore) {
            System.out.println("\tMCAT score is not within the accepted range (497-528).\n");
        }

        else {
            DecisionTree<Integer> mcatScoreTree = new DecisionTree<>(0);
            TreeNode<Integer> root = mcatScoreTree.getRoot();

            mcatScoreTree.addNode(root, q3McatScore, maxApplicationScore);
            mcatScoreTree.addNode(root, medianMcatScore, medianApplicationScore);
            mcatScoreTree.addNode(root, q1McatScore, minApplicationScore);

            TreeNode<Integer> currentNode = root;

            while (!currentNode.children.isEmpty()) {
                boolean found = false;
                for (TreeNode<Integer> child : currentNode.children) {
                    if (applicantMcatScore >= child.data) {
                        found = true;
                        currentNode = child;
                        applicant.totalScore += child.score;
                        break;
                    }
                }
                if (!found) { break; }
            }

            System.out.println("\tScore received: " + currentNode.score + "/" + maxApplicationScore);
            System.out.println("\tTotal application score: " + applicant.totalScore + "\n");

        }
    }

    public static void evaluateGpa(Applicant applicant) {
        final double minGpa = 0.0;
        final double q1Gpa = 3.50;
        final double medianGpa = 3.75;
        final double q3Gpa = 3.90;
        final double maxGpa = 4.0;
        final double applicantGpa = applicant.getGpa();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        System.out.println("\tApplicant's GPA: " + decimalFormat.format(applicantGpa));

        if (applicantGpa > maxGpa || applicantGpa < minGpa) {
            System.out.println("\tGpa is not within the accepted range (0.0-4.0).\n");
        }

        else {
            DecisionTree<Double> gpaTree = new DecisionTree<>(0.0);
            TreeNode<Double> root = gpaTree.getRoot();

            gpaTree.addNode(root, q3Gpa, maxApplicationScore);
            gpaTree.addNode(root, medianGpa, medianApplicationScore);
            gpaTree.addNode(root, q1Gpa, minApplicationScore);

            TreeNode<Double> currentNode = root;

            while (!currentNode.children.isEmpty()) {
                boolean found = false;
                for (TreeNode<Double> child : currentNode.children) {
                    if (applicantGpa >= child.data) {
                        found = true;
                        currentNode = child;
                        applicant.totalScore += child.score;
                        break;
                    }
                }
                if (!found) { break; }
            }

            System.out.println("\tScore received: " + currentNode.score + "/" + maxApplicationScore);
            System.out.println("\tTotal application score: " + applicant.totalScore + "\n");

        }
    }

    public static void evaluateCoursework(Applicant applicant) {
        final String applicantCoursework = applicant.getCoursework();
        System.out.println("\tApplicant's coursework: " + applicantCoursework);
        int tempScore = evaluatePsuScore(applicantCoursework);
        applicant.totalScore += tempScore;
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");
    }

    public static void evaluateLettersOfRecommendation(Applicant applicant) {
        final String applicantLettersOfRecommendation = applicant.getLettersOfRecommendation();
        System.out.println("\tApplicant's letters of recommendation: " + applicantLettersOfRecommendation);
        int tempScore = evaluatePsuScore(applicantLettersOfRecommendation);
        applicant.totalScore += tempScore;
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");
    }

    public static void evaluateWorkExperience(Applicant applicant) {
        final String applicantWorkExperience = applicant.getWorkExperience();
        System.out.println("\tApplicant's work experience: " + applicantWorkExperience);
        int tempScore = evaluatePsuScore(applicantWorkExperience);
        applicant.totalScore += tempScore;
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");
    }

    public static void evaluateEssay(Applicant applicant) {
        final String applicantEssay = applicant.getEssay();
        System.out.println("\tApplicant's essay: " + applicantEssay);
        int tempScore = evaluatePsuScore(applicantEssay);
        applicant.totalScore += tempScore;
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");
    }

    public static int evaluatePsuScore(String applicantPsu) {
        int score = 0;
        DecisionTree<String> psuTree = new DecisionTree<>("");
        TreeNode<String> root = psuTree.getRoot();

        psuTree.addNode(root, "Perfect", 10);
        psuTree.addNode(root, "Satisfactory", 5);

        TreeNode<String> currentNode = root;
        while (!currentNode.children.isEmpty()) {
            boolean found = false;
            for (TreeNode<String> child : currentNode.children) {
                if (applicantPsu.equals(child.data)) {
                    found = true;
                    currentNode = child;
                    score += child.score;
                    break;
                }
            }
            if (!found) { break; }
        }

        System.out.println("\tScore received: " + currentNode.score + "/" + 10);
        return score;
    }

    public static void evaluateDegree(Applicant applicant) {
        DecisionTree<String> degreeTree = new DecisionTree<>("");
        TreeNode<String> root = degreeTree.getRoot();
        System.out.println("\tApplicant's highest degree: " + applicant.getHighestDegree());

        degreeTree.addNode(root, "Doctorate", 10);
        degreeTree.addNode(root, "Masters", 5);

        TreeNode<String> currentNode = root;
        while (!currentNode.children.isEmpty()) {
            boolean found = false;
            for (TreeNode<String> child : currentNode.children) {
                if (applicant.getHighestDegree().equals(child.data)) {
                    found = true;
                    currentNode = child;
                    applicant.totalScore += child.score;
                    break;
                }
            }
            if (!found) { break; }
        }

        System.out.println("\tScore received: " + currentNode.score + "/" + 10);
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");

    }

    public static void evaluateSchool(Applicant applicant) {
        DecisionTree<String> schoolTree = new DecisionTree<>("");
        TreeNode<String> root = schoolTree.getRoot();
        System.out.println("\tApplicant's school: " + applicant.getSchoolAttended());

        schoolTree.addNode(root, "Tier 1", 5);
        schoolTree.addNode(root, "Tier 2", 3);

        TreeNode<String> currentNode = root;
        while (!currentNode.children.isEmpty()) {
            boolean found = false;
            for (TreeNode<String> child : currentNode.children) {
                if (applicant.getSchoolAttended().equals(child.data)) {
                    found = true;
                    currentNode = child;
                    applicant.totalScore += child.score;
                    break;
                }
            }
            if (!found) { break; }
        }

        System.out.println("\tScore received: " + currentNode.score + "/" + 5);
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");

    }

    public static void evaluateFirstGen(Applicant applicant) {
        if (applicant.isFirstGeneration()) {
            System.out.println("\tApplicant is a first generation student.");
            applicant.totalScore += 5;
            System.out.println("\tScore received: 5/5");
        }
        else {
            System.out.println("\tApplicant is not a first generation student.");
            System.out.println("\tScore received: 0/5");
        }
        System.out.println("\tTotal application score: " + applicant.totalScore + "\n");
    }

    public static boolean evaluateEligibility(Applicant applicant) {
        DecisionTree<String> eligibilityTree = new DecisionTree<>("Determining eligibility...");
        TreeNode<String> root = eligibilityTree.getRoot();

        eligibilityTree.addNode(root, "Pass.");
        eligibilityTree.addNode(root,"Applicant is a foreign citizen who has not studied at least one year in the United States. ");

        eligibilityTree.addNode(root.children.get(0), "Pass.");
        eligibilityTree.addNode(root.children.get(0),"Applicant does not possess at least a Bachelors degree.");

        eligibilityTree.addNode(root.children.get(0).children.get(0), "Pass.");
        eligibilityTree.addNode(root.children.get(0).children.get(0),"Applicant has not taken the MCAT exam.");

        eligibilityTree.addNode(root.children.get(0).children.get(0).children.get(0), "Pass.");
        eligibilityTree.addNode(root.children.get(0).children.get(0).children.get(0),"Applicant has previously attended medical school.");

        TreeNode<String> currentNode = root;

        // check country of origin
        if (applicant.getCountryOfOrigin().equals("United States")) {
            currentNode = currentNode.children.get(0);
        }
        else if (applicant.isStudiedInUs()) {
            currentNode = currentNode.children.get(0);
        }
        else {
            currentNode = currentNode.children.get(1);
            System.out.println("Application denied.\n" + "\tReason: " + currentNode.data + "\n");
            return false;
        }
            // check degree
        if (applicant.getHighestDegree().equals("Doctorate") ||
                applicant.getHighestDegree().equals("Masters") ||
                applicant.getHighestDegree().equals("Bachelors")) {
            currentNode = currentNode.children.get(0);
        }
        else {
            currentNode = currentNode.children.get(1);
            System.out.println("Application denied.\n" + "\tReason: " + currentNode.data + "\n");
            return false;
        }

        // check MCAT taken
        if (applicant.isMcatTaken()) {
            currentNode = currentNode.children.get(0);
        }
        else {
            currentNode = currentNode.children.get(1);
            System.out.println("Application denied.\n" + "\tReason: " + currentNode.data + "\n");
            return false;
        }

        // check previous matriculation
        if (!applicant.isPrevMatriculation()) {
            currentNode = currentNode.children.get(0);
            return true;
        }
        else {
            currentNode = currentNode.children.get(1);
            System.out.println("Application denied.\n" + "\tReason: " + currentNode.data + "\n");
            return false;
        }
    }

    public static void evaluate(Applicant applicant) {

        System.out.println("Applicant: \n"
                + "\tName: " + applicant.getName() + "\n"
                + "\tDOB: " + applicant.getDateOfBirth() + "\n"
                + "\tCountry of Origin: " + applicant.getCountryOfOrigin() + "\n");

        if (DecisionTree.evaluateEligibility(applicant)) {
            System.out.println("Applicant is eligible for admission.\n");
            System.out.println("Processing MCAT score...");
            DecisionTree.evaluateMcatScore(applicant);
            System.out.println("Processing GPA...");
            DecisionTree.evaluateGpa(applicant);
            System.out.println("Processing Coursework...");
            DecisionTree.evaluateCoursework(applicant);
            System.out.println("Processing Letters of Recommendation...");
            DecisionTree.evaluateLettersOfRecommendation(applicant);
            System.out.println("Processing Work Experience...");
            DecisionTree.evaluateWorkExperience(applicant);
            System.out.println("Processing Essay...");
            DecisionTree.evaluateEssay(applicant);
            System.out.println("Processing Degree...");
            DecisionTree.evaluateDegree(applicant);
            System.out.println("Processing School...");
            DecisionTree.evaluateSchool(applicant);
            System.out.println("Processing First Generation...");
            DecisionTree.evaluateFirstGen(applicant);
            System.out.println("-----------------------------");
            System.out.println("Total score: " + applicant.totalScore + "/100");
            System.out.println("-----------------------------\n");
        }
        else {
            System.out.println("-----------------------------\n");
        }
    }
}