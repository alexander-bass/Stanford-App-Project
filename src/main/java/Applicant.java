
public class Applicant {

    // fluff
    private String name;
    private String dateOfBirth;

    // eligibility
    private String countryOfOrigin;
    private boolean studiedInUs;
    private String highestDegree;
    private boolean mcatTaken;
    private boolean prevMatriculation;

    // variables to score
    private double gpa;
    private int mcatScore;
    private String coursework;
    private String lettersOfRecommendation;
    private String workExperience;
    private String essay;
    private String schoolAttended;
    private boolean firstGeneration;


    // total score
    public int totalScore = 0;


    // default constructor
    public Applicant() {}


    public Applicant(
            String name,
            String dateOfBirth,
            String countryOfOrigin,
            boolean studiedInUs,
            String highestDegree,
            boolean mcatTaken,
            boolean prevMatriculation,
            double gpa,
            int mcatScore,
            String coursework,
            String lettersOfRecommendation,
            String workExperience,
            String essay,
            String schoolAttended,
            boolean firstGeneration
            ) {

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.countryOfOrigin = countryOfOrigin;
        this.studiedInUs = studiedInUs;
        this.highestDegree = highestDegree;
        this.mcatTaken = mcatTaken;
        this.prevMatriculation = prevMatriculation;
        this.gpa = gpa;
        this.mcatScore = mcatScore;
        this.coursework = coursework;
        this.lettersOfRecommendation = lettersOfRecommendation;
        this.workExperience = workExperience;
        this.essay = essay;
        this.schoolAttended = schoolAttended;
        this.firstGeneration = firstGeneration;
    }

    // WE ARE GETTING AND SETTING

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }
    public boolean isStudiedInUs() { return studiedInUs; }
    public void setStudiedInUs(boolean studiedInUs) { this.studiedInUs = studiedInUs; }
    public String getHighestDegree() { return highestDegree; }
    public void setHighestDegree(String highestDegree) { this.highestDegree = highestDegree; }
    public boolean isMcatTaken() { return mcatTaken; }
    public void setMcatTaken(boolean mcatTaken) { this.mcatTaken = mcatTaken; }
    public boolean isPrevMatriculation() { return prevMatriculation; }
    public void setPrevMatriculation(boolean prevMatriculation) { this.prevMatriculation = prevMatriculation; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }
    public int getMcatScore() { return mcatScore; }
    public void setMcatScore(int mcatScore) { this.mcatScore = mcatScore; }
    public String getCoursework() { return coursework; }
    public void setCoursework(String coursework) { this.coursework = coursework; }
    public String getLettersOfRecommendation() { return lettersOfRecommendation; }
    public void setLettersOfRecommendation(String lettersOfRecommendation) { this.lettersOfRecommendation = lettersOfRecommendation; }
    public String getWorkExperience() { return workExperience; }
    public void setWorkExperience(String workExperience) { this.workExperience = workExperience; }
    public String getEssay() { return essay; }
    public void setEssay(String essay) { this.essay = essay; }
    public String getSchoolAttended() { return schoolAttended; }
    public void setSchoolAttended(String schoolAttended) { this.schoolAttended = schoolAttended; }
    public boolean isFirstGeneration() { return firstGeneration; }
    public void setFirstGeneration(boolean firstGeneration) { this.firstGeneration = firstGeneration; }

    // WE ARE DONE GETTING AND SETTING :triumph:

    // to print an applicant


    @Override
    public String toString() {
        return "Applicant{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                ", studiedInUs=" + studiedInUs +
                ", highestDegree='" + highestDegree + '\'' +
                ", mcatTaken=" + mcatTaken +
                ", prevMatriculation=" + prevMatriculation +
                ", gpa=" + gpa +
                ", mcatScore=" + mcatScore +
                ", coursework='" + coursework + '\'' +
                ", lettersOfRecommendation='" + lettersOfRecommendation + '\'' +
                ", workExperience='" + workExperience + '\'' +
                ", essay='" + essay + '\'' +
                ", schoolAttended='" + schoolAttended + '\'' +
                ", firstGeneration=" + firstGeneration +
                '}';
    }
}
