import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ApplicantDriver {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to apply? Y/n ");
        if (scanner.nextLine().equals("Y")) {
            Applicant applicant = new Applicant();
            System.out.print("Welcome! Please respond to the following prompts: ");
            System.out.print("\n\tEnter your name: ");
            applicant.setName(scanner.nextLine());
            System.out.print("\n\tEnter your country of origin: ");
            applicant.setCountryOfOrigin(scanner.nextLine());
            if (!applicant.getCountryOfOrigin().equals("United States")){
                System.out.print("\n\tHave you studied for at least one year in the United States? Y/n ");
                applicant.setStudiedInUs(scanner.nextLine().equals("Y"));
            }
            else { applicant.setStudiedInUs(true); }
            System.out.print("\n\tEnter your highest level of degree: ");
            applicant.setHighestDegree(scanner.nextLine());
            System.out.print("\n\tHave you taken the MCAT? Y/n ");
            applicant.setMcatTaken(scanner.nextLine().equals("Y"));
            if(applicant.isMcatTaken()){
                System.out.print("\n\tEnter your MCAT score: ");
                applicant.setMcatScore(Integer.parseInt(scanner.nextLine()));
            }
            System.out.print("\n\tHave you previously attended medical school? Y/n ");
            applicant.setPrevMatriculation(scanner.nextLine().equals("Y"));
            System.out.print("\n\tEnter college GPA: ");
            applicant.setGpa(Double.parseDouble(scanner.nextLine()));
            System.out.print("\n\tEnter coursework: ");
            applicant.setCoursework(scanner.nextLine());
            System.out.print("\n\tEnter letters of recommendation: ");
            applicant.setLettersOfRecommendation(scanner.nextLine());
            System.out.print("\n\tEnter work experience: ");
            applicant.setWorkExperience(scanner.nextLine());
            System.out.print("\n\tEnter essay: ");
            applicant.setEssay(scanner.nextLine());
            System.out.print("\n\tEnter last college attended: ");
            applicant.setSchoolAttended(scanner.nextLine());
            System.out.print("\n\tAre you a first generation student? Y/n ");
            applicant.setFirstGeneration(scanner.nextLine().equals("Y"));
            System.out.println("\nThank you for your application!");
            System.out.println("-----------------------------\n");
            applicant.setCoursework("Perfect");
            applicant.setLettersOfRecommendation("Perfect");
            applicant.setWorkExperience("Perfect");
            applicant.setEssay("Perfect");
            applicant.setSchoolAttended("Tier 1");

            DecisionTree.evaluate(applicant);
        }
        else {
            System.out.print("How many applicants would you like generate? ");
            int totalApplications = scanner.nextInt();

            Gson gson = new GsonBuilder().setDateFormat("MM-dd-yyyy").create();

            GenerateApplicants.create(totalApplications);

            List<Applicant> bestApplicants = new ArrayList<>();

            try (FileReader reader = new FileReader("applicants.json")) {
                Applicant[] applicants = gson.fromJson(reader, Applicant[].class);

                System.out.println("-----------------------------\n");
                for (Applicant applicant : applicants) {
                    DecisionTree.evaluate(applicant);
                    if (applicant.totalScore > 65) { bestApplicants.add(applicant); }
                }

                if (!bestApplicants.isEmpty()) {
                    System.out.println("Top candidates: ");
                    for (Applicant applicant : bestApplicants) {
                        System.out.println("\t" + applicant.getName() + " : " + applicant.totalScore + "/100");
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
