import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.util.ArrayList;

public class ManageScreen extends JPanel {
    private JComboBox<String> survivedComboBox;
    private JComboBox<Integer> survivedComboBox2;
    private JComboBox<String> survivedComboBox3;
    private JComboBox<String> survivedComboBox4;
    private JComboBox<String> survivedComboBox5;
    private JComboBox<String> survivedComboBox6;
    private JComboBox<String> survivedComboBox7;
    private JComboBox<String> survivedComboBox8;
    private JComboBox<String> survivedComboBox9;
    private JComboBox<String> survivedComboBox10;
    private final JButton filterButton = new JButton("Filter");
    private final JButton statisticsButton =new JButton("Statistics");
    private static JLabel filterLabel=new JLabel();

    private static String selectedClass="";
    private static String selectedNumber=("");
    private static String selectedName=("");
    private static String selectedGender="";
    private static String selectedSib=("");
    private static String selectedParch=("");
    private static String selectedTicket=("");
    private static String selectedFare=("");
    private static String selectedCabin=("");
    private static String selectedEmbarked=("");
    private static int totalRows;
    private static int survived;
    private static int deceased;
    private static int filterCount=0;
    private static FileWriter fileWriter;


    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE);
        if (file.exists()) {
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
            JLabel survivedLabel = new JLabel("Passenger Class: ");
            survivedLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel);
            this.survivedComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.survivedComboBox.setBounds(survivedLabel.getX() + survivedLabel.getWidth() + 1, survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.survivedComboBox);

            this.survivedComboBox.addActionListener((e) -> {
                String num=(String) this.survivedComboBox.getSelectedItem();
                assert num != null;
                if(num.startsWith("1")){
                    selectedClass="1";
                }else {
                    if(num.startsWith("2")){
                        selectedClass="2";
                    }else {
                        if(num.startsWith("3")){
                            selectedClass="3";
                        }else {
                            if(num.equals("ALL")){
                                selectedClass="";
                            }
                        }
                    }
                }

            });

            JLabel survivedLabel2 = new JLabel("Passenger ID: ");
            survivedLabel2.setBounds(survivedComboBox.getX() + survivedComboBox.getWidth() + Constants.gap * 8, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel2);
            this.survivedComboBox2 = new JComboBox<>();
            this.survivedComboBox2.setBounds(survivedLabel2.getX() + survivedLabel2.getWidth(), y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.survivedComboBox2.setEditable(true);
            this.survivedComboBox2.setPrototypeDisplayValue(3);
            this.add(survivedComboBox2);

            JTextComponent editor1 = (JTextComponent) this.survivedComboBox2.getEditor().getEditorComponent();
            DocumentFilter filter1 = new DocumentFilter() {
                public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
                    if ((filterBypass.getDocument().getLength() + string.length()) <= 3) {
                        super.insertString(filterBypass, offset, string, attributeSet);
                    }
                }

                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 3) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            };
            ((AbstractDocument) editor1.getDocument()).setDocumentFilter(filter1);

            this.survivedComboBox2.addActionListener(e -> {
                selectedNumber=(String) this.survivedComboBox2.getSelectedItem();
            });
            JLabel survivedLabel3 = new JLabel("Passenger Name: ");
            survivedLabel3.setBounds(x + Constants.MARGIN_FROM_LEFT, survivedComboBox.getY() + survivedComboBox.getHeight() + Constants.gap * 3, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel3);
            this.survivedComboBox3 = new JComboBox<>();
            this.survivedComboBox3.setBounds(survivedLabel3.getX() + survivedLabel3.getWidth(), survivedLabel3.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.survivedComboBox3.setEditable(true);
            this.add(survivedComboBox3);

            this.survivedComboBox3.addActionListener(e -> {
                selectedName=(String) this.survivedComboBox3.getEditor().getItem();
            });

            JLabel survivedLabel4 = new JLabel("Passenger Sex: ");
            survivedLabel4.setBounds(survivedLabel2.getX(), survivedComboBox.getY() + survivedComboBox.getHeight() + Constants.gap * 3, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel4);
            this.survivedComboBox4 = new JComboBox<>(Constants.PASSENGERS_SEX_OPTIONS);
            this.survivedComboBox4.setBounds(survivedLabel4.getX() + survivedLabel4.getWidth(), survivedLabel4.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.survivedComboBox4);

            this.survivedComboBox4.addActionListener(e -> {
                selectedGender = (String) survivedComboBox4.getSelectedItem();
            });


            JLabel survivedLabel5 = new JLabel("Passenger Sib/Sum: ");
            survivedLabel5.setBounds(survivedLabel3.getX(), this.survivedComboBox3.getY() + this.survivedComboBox3.getHeight() + Constants.gap * 3, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel5);
            this.survivedComboBox5 = new JComboBox<>();
            this.survivedComboBox5.setBounds(survivedLabel5.getX() + survivedLabel5.getWidth(), survivedLabel5.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.survivedComboBox5.setEditable(true);
            this.add(survivedComboBox5);

            JTextComponent editor3 = (JTextComponent) this.survivedComboBox5.getEditor().getEditorComponent();
            DocumentFilter filter3 = new DocumentFilter() {
                public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
                    if ((filterBypass.getDocument().getLength() + string.length()) <= 3) {
                        super.insertString(filterBypass, offset, string, attributeSet);
                    }
                }

                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 1) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            };
            ((AbstractDocument) editor3.getDocument()).setDocumentFilter(filter3);

            this.survivedComboBox5.addActionListener(e -> {
                selectedSib = (String) this.survivedComboBox5.getSelectedItem();
            });

            JLabel survivedLabel6 = new JLabel("Passenger Parch: ");
            survivedLabel6.setBounds(survivedLabel4.getX(), survivedLabel5.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel6);
            this.survivedComboBox6 = new JComboBox<>();
            this.survivedComboBox6.setBounds(survivedLabel6.getX() + survivedLabel6.getWidth(), survivedLabel6.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.survivedComboBox6.setEditable(true);
            this.add(survivedComboBox6);

            JTextComponent editor4 = (JTextComponent) this.survivedComboBox6.getEditor().getEditorComponent();
            DocumentFilter filter4 = new DocumentFilter() {
                public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
                    if ((filterBypass.getDocument().getLength() + string.length()) <= 3) {
                        super.insertString(filterBypass, offset, string, attributeSet);
                    }
                }

                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 1) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            };
            ((AbstractDocument) editor4.getDocument()).setDocumentFilter(filter4);

            this.survivedComboBox6.addActionListener(e -> {
                selectedParch = (String) this.survivedComboBox6.getSelectedItem();
            });


            JLabel survivedLabel7 = new JLabel("Passenger Ticket: ");
            survivedLabel7.setBounds(survivedLabel5.getX(), this.survivedComboBox5.getY() + this.survivedComboBox5.getHeight() + Constants.gap * 3, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel7);
            this.survivedComboBox7 = new JComboBox<String>();
            this.survivedComboBox7.setBounds(survivedLabel7.getX() + survivedLabel7.getWidth(), survivedLabel7.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.survivedComboBox7.setEditable(true);
            this.add(survivedComboBox7);

            JTextComponent editor5 = (JTextComponent) this.survivedComboBox7.getEditor().getEditorComponent();
            DocumentFilter filter5 = new DocumentFilter() {
                public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
                    if ((filterBypass.getDocument().getLength() + string.length()) <= 3) {
                        super.insertString(filterBypass, offset, string, attributeSet);
                    }
                }

                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 1) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            };
            ((AbstractDocument) editor5.getDocument()).setDocumentFilter(filter5);
            this.survivedComboBox7.addActionListener(e -> {
                selectedTicket = (String) this.survivedComboBox7.getSelectedItem();
            });

            JLabel survivedLabel8 = new JLabel("Passenger Fare");
            survivedLabel8.setBounds(survivedLabel6.getX(), survivedLabel7.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel8);
            this.survivedComboBox8 = new JComboBox<>();
            this.survivedComboBox8.setBounds(survivedLabel8.getX() + survivedLabel8.getWidth(), survivedLabel8.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.survivedComboBox8.setEditable(true);
            this.add(survivedComboBox8);

            JTextComponent editor6 = (JTextComponent) this.survivedComboBox8.getEditor().getEditorComponent();
            DocumentFilter filter6 = new DocumentFilter() {
                public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
                    if ((filterBypass.getDocument().getLength() + string.length()) <= 3) {
                        super.insertString(filterBypass, offset, string, attributeSet);
                    }
                }
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 1) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            };
            ((AbstractDocument) editor6.getDocument()).setDocumentFilter(filter6);
            this.survivedComboBox8.addActionListener(e -> {
                selectedFare = (String) this.survivedComboBox8.getSelectedItem();
            });

            JLabel survivedLabel9 = new JLabel("Passenger Cabin");
            survivedLabel9.setBounds(survivedLabel7.getX(), this.survivedComboBox7.getY() + this.survivedComboBox7.getHeight() + Constants.gap * 3, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel9);
            this.survivedComboBox9 = new JComboBox<>();
            this.survivedComboBox9.setBounds(survivedLabel9.getX() + survivedLabel9.getWidth(), survivedLabel9.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.survivedComboBox9.setEditable(true);
            this.add(survivedComboBox9);

            JTextComponent editor7 = (JTextComponent) this.survivedComboBox9.getEditor().getEditorComponent();
            DocumentFilter filter7 = new DocumentFilter() {
                public void insertString(FilterBypass filterBypass, int offset, String string, AttributeSet attributeSet) throws BadLocationException {
                    if ((filterBypass.getDocument().getLength() + string.length()) <= 3) {
                        super.insertString(filterBypass, offset, string, attributeSet);
                    }
                }
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    if ((fb.getDocument().getLength() - length + text.length()) <= 1) {
                        super.replace(fb, offset, length, text, attrs);
                    }
                }
            };
            ((AbstractDocument) editor7.getDocument()).setDocumentFilter(filter7);
            this.survivedComboBox9.addActionListener(e -> {
                selectedCabin = (String) this.survivedComboBox9.getSelectedItem();

            });

            JLabel survivedLabel10 = new JLabel("Passenger Embarked: ");
            survivedLabel10.setBounds(survivedLabel8.getX(), survivedLabel9.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel10);
            this.survivedComboBox10 = new JComboBox<>(Constants.PASSENGER_PORT_OPTIONS);
            this.survivedComboBox10.setBounds(survivedLabel10.getX() + survivedLabel10.getWidth(), survivedLabel10.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(survivedComboBox10);
            this.survivedComboBox10.addActionListener(e -> {
                selectedEmbarked = (String) survivedComboBox10.getSelectedItem();
            });

            filterLabel.setBounds((Constants.WINDOW_WIDTH/2)-100, survivedLabel10.getY()+survivedLabel10.getHeight()+20,400,30);
            this.add(filterLabel);
            filterButton.setBounds(survivedLabel9.getX()+Constants.gap*3,survivedLabel9.getY()+survivedLabel9.getHeight()+Constants.gap*5,Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
            this.add(filterButton);
            filterButton.addActionListener(e -> {
                int count=0;
                filterCount++;
                File file1=new File("src/data/"+Integer.toString(filterCount)+".csv");
                ArrayList<Passenger> passengers=new ArrayList<>();
                for(Passenger passenger:Test.getPassengers()){
                    boolean matchClass=selectedClass.equals("")|| selectedClass.equals(passenger.getPclass());
                    boolean matchName=selectedName.equals("")||selectedName.equals(passenger.getName());
                    boolean matchGender=selectedGender.equals("")||selectedGender.equals(passenger.getSex());
                    boolean matchSib=selectedSib.equals("")||passenger.getSibSP().startsWith(selectedSib);
                    boolean matchParch=selectedParch.equals("")||passenger.getParch().startsWith(selectedParch);
                    boolean matchTicket=selectedTicket.equals("")||passenger.getTicket().startsWith(selectedTicket);
                    boolean matchFare=selectedFare.equals("")||selectedFare.equals(passenger.getFare());
                    boolean matchCabin=selectedCabin.equals("")||passenger.getCabin().startsWith(selectedCabin);
                    boolean matchEmbarked=selectedEmbarked.equals("")||passenger.getEmbarked().startsWith(selectedEmbarked);
                    if(matchClass && matchName&&matchGender&&matchSib&&matchParch&&matchTicket&&matchFare&&matchCabin&&matchEmbarked){
                        passengers.add(passenger);
                        totalRows++;
                        if(passenger.getSurvived().equals("0")){
                            deceased++;
                        }
                        if(passenger.getSurvived().equals("1")){
                            survived++;
                        }
                    }
                    count++;
                    if(!selectedNumber.equals("") && count==Integer.parseInt(selectedNumber)){
                        break;
                    }
                }
                System.out.println("Total Rows: "+totalRows+"("+survived+" survived, "+deceased+" did not)");// צריך להופיע בחלון
                String string=("Total Rows: "+totalRows+"("+survived+" survived, "+deceased+" did not)");
                filterLabel.setText(string);
                try {
                    crateFilterFile(file1,passengers);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                totalRows=0; survived=0; deceased=0;
            });

            statisticsButton.setBounds(Constants.WINDOW_WIDTH- filterButton.getX()- filterButton.getWidth(), filterButton.getY(),Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
            this.add(statisticsButton);
            statisticsButton.addActionListener(e -> {
                int survivedFromClass1=0;     int overAllFromClass1=0;
                int survivedFromClass2=0;     int overAllFromClass2=0;
                int survivedFromClass3=0;     int overAllFromClass3=0;
                int survivedFromGenderA=0;    int overAllFromGenderA=0;
                int survivedFromGenderB=0;    int overAllFromGenderB=0;
                int haveRelatives=0;          int overAllHaveRelatives=0;
                int noRelatives=0;            int overAllNoRelatives=0;
                int survivedFromPortC=0;      int overAllFromPortC=0;
                int survivedFromPortQ=0;      int overAllFromPortQ=0;
                int survivedFromPortS=0;      int overAllFromPortS=0;
                int survivedByPounds1=0 ;     int overAllByPounds1=0;
                int survivedByPounds2=0 ;     int overAllByPounds2=0;
                int survivedByPounds3=0 ;     int overAllByPounds3=0;
                int survivedFromAgeGroup1=0;  int overAllFromAgeGroup1=0;
                int survivedFromAgeGroup2=0;  int overAllFromAgeGroup2=0;
                int survivedFromAgeGroup3=0;  int overAllFromAgeGroup3=0;
                int survivedFromAgeGroup4=0;  int overAllFromAgeGroup4=0;
                int survivedFromAgeGroup5=0;  int overAllFromAgeGroup5=0;
                int survivedFromAgeGroup6=0;  int overAllFromAgeGroup6=0;
                int survivedFromNoFoundAge=0; int overAllFromNoFoundAge=0;

                for(Passenger passenger:Test.getPassengers()) {
                       if(passenger.getPclass().equals("1") && passenger.getSurvived().equals("1")){survivedFromClass1++;}
                       if(passenger.getPclass().equals("1")){overAllFromClass1++;}
                       if(passenger.getPclass().equals("2") && passenger.getSurvived().equals("1")){survivedFromClass2++;}
                       if(passenger.getPclass().equals("2")){overAllFromClass2++;}
                        if(passenger.getPclass().equals("3") && passenger.getSurvived().equals("1")){survivedFromClass3++;}
                        if(passenger.getPclass().equals("3")){overAllFromClass3++;}
                        if(passenger.getSex().equals("male")&& passenger.getSurvived().equals("1")){survivedFromGenderA++;}
                        if(passenger.getSex().equals("male")){overAllFromGenderA++;}
                        if(passenger.getSex().equals("female") && passenger.getSurvived().equals("1")){survivedFromGenderB++;}
                        if(passenger.getSex().equals("female")){overAllFromGenderB++;}

                        if(!passenger.getAge().equals("")){
                            if(Double.parseDouble(passenger.getAge())<=10 && passenger.getSurvived().equals("1")){survivedFromAgeGroup1++;}
                            if(Double.parseDouble(passenger.getAge())<=10){overAllFromAgeGroup1++;}
                            if(Double.parseDouble(passenger.getAge())<=20 && Double.parseDouble(passenger.getAge())>=11 && passenger.getSurvived().equals("1")){survivedFromAgeGroup2++;}
                            if(Double.parseDouble(passenger.getAge())<=20 && Double.parseDouble(passenger.getAge())>=11){overAllFromAgeGroup2++;}
                            if(Double.parseDouble(passenger.getAge())<=30 && Double.parseDouble(passenger.getAge())>=21 && passenger.getSurvived().equals("1")){survivedFromAgeGroup3++;}
                            if(Double.parseDouble(passenger.getAge())<=30 && Double.parseDouble(passenger.getAge())>=21){overAllFromAgeGroup3++;}
                            if(Double.parseDouble(passenger.getAge())<=40 && Double.parseDouble(passenger.getAge())>=31 && passenger.getSurvived().equals("1")){survivedFromAgeGroup4++;}
                            if(Double.parseDouble(passenger.getAge())<=40 && Double.parseDouble(passenger.getAge())>=31){overAllFromAgeGroup4++;}
                            if(Double.parseDouble(passenger.getAge())<=50 && Double.parseDouble(passenger.getAge())>=41 && passenger.getSurvived().equals("1")){survivedFromAgeGroup5++;}
                            if(Double.parseDouble(passenger.getAge())<=50 && Double.parseDouble(passenger.getAge())>=41){overAllFromAgeGroup5++;}
                            if(Double.parseDouble(passenger.getAge())>=51 && passenger.getSurvived().equals("1")){survivedFromAgeGroup6++;}
                            if(Double.parseDouble(passenger.getAge())>=51){overAllFromAgeGroup6++;}}
                        if(passenger.getAge().equals("")&& passenger.getSurvived().equals("1")){survivedFromNoFoundAge++;}
                        if(passenger.getAge().equals("")){overAllFromNoFoundAge++;}

                        if(passenger.getEmbarked().equals("C") && passenger.getSurvived().equals("1")){survivedFromPortC++;}
                        if(passenger.getEmbarked().equals("C")){overAllFromPortC++;}
                        if(passenger.getEmbarked().equals("Q")&& passenger.getSurvived().equals("1")){survivedFromPortQ++;}
                        if(passenger.getEmbarked().equals("Q")){overAllFromPortQ++;}
                        if(passenger.getEmbarked().equals("S")&& passenger.getSurvived().equals("1")){survivedFromPortS++;}
                        if(passenger.getEmbarked().equals("S")){overAllFromPortS++;}

                        if(getByPounds(passenger.getFare()).equals("0-9")&& passenger.getSurvived().equals("1")){survivedByPounds1++;}
                        if(getByPounds(passenger.getFare()).equals("0-9")){overAllByPounds1++;}
                        if(getByPounds(passenger.getFare()).equals("11-30")&& passenger.getSurvived().equals("1")){survivedByPounds2++;}
                        if(getByPounds(passenger.getFare()).equals("11-30")){overAllByPounds2++;}
                        if(getByPounds(passenger.getFare()).equals("30+")&& passenger.getSurvived().equals("1")){survivedByPounds3++;}
                        if(getByPounds(passenger.getFare()).equals("30+")){overAllByPounds3++;}

                        int num1=Integer.parseInt(passenger.getParch());
                        int num2=Integer.parseInt(passenger.getSibSP());
                        int sumOfRelatives=num1+num2;
                        if(sumOfRelatives>=1 && passenger.getSurvived().equals("1")){haveRelatives++;}
                        if(sumOfRelatives>=1){overAllHaveRelatives++;}
                        if(sumOfRelatives==0 && passenger.getSurvived().equals("1")){noRelatives++;}
                        if(sumOfRelatives==0){overAllNoRelatives++;}

                    }
                System.out.println("survivors from class 1: "+(survivedFromClass1* 100 / overAllFromClass1)+"%");
                System.out.println("survivors from class 2: "+(survivedFromClass2 * 100 / overAllFromClass2)+"%");
                System.out.println("survivors from class 3: "+(survivedFromClass3* 100 / overAllFromClass3)+"%");
                System.out.println("male survivors: "+(survivedFromGenderA*100/overAllFromGenderA)+"%");
                System.out.println("female survivors: "+(survivedFromGenderB*100/overAllFromGenderB)+"%");

                System.out.println("survivors from age 0-10: "+(survivedFromAgeGroup1 * 100/ overAllFromAgeGroup1)+"%");
                System.out.println("survivors from age 11-20: "+(survivedFromAgeGroup2 * 100/ overAllFromAgeGroup2)+"%");
                System.out.println("survivors from age 21-30: "+(survivedFromAgeGroup3 * 100/ overAllFromAgeGroup3)+"%");
                System.out.println("survivors from age 31-40: "+(survivedFromAgeGroup4 * 100/ overAllFromAgeGroup4)+"%");
                System.out.println("survivors from age 41-50: "+(survivedFromAgeGroup5 * 100/ overAllFromAgeGroup5)+"%");
                System.out.println("survivors from age 50+: "+(survivedFromAgeGroup6 * 100/ overAllFromAgeGroup6)+"%");
                System.out.println("survivors with no found age: "+(survivedFromNoFoundAge*100/overAllFromNoFoundAge)+"%");

                System.out.println("survivors from port C: "+(survivedFromPortC*100/overAllFromPortC)+"%");
                System.out.println("survivors from port Q: "+(survivedFromPortQ*100/overAllFromPortQ)+"%");
                System.out.println("survivors from port S: "+(survivedFromPortS*100/overAllFromPortS)+"%");

                System.out.println("survivors by pounds 0-9: "+(survivedByPounds1*100/overAllByPounds1)+"%");
                System.out.println("survivors by pounds 11-30: "+(survivedByPounds2*100/overAllByPounds2)+"%");
                System.out.println("survivors by pounds 30+: "+(survivedByPounds3*100/overAllByPounds3)+"%");

                System.out.println("survivors with relatives: "+(haveRelatives*100/overAllHaveRelatives)+"%");
                System.out.println("survivors with no relatives: "+(noRelatives*100/overAllNoRelatives)+"%");
                });
        }
    }
    private static void crateFilterFile(File file,ArrayList<Passenger> passengers) throws IOException {
        StringBuilder stringBuilder=new StringBuilder();
        for(Passenger passenger:passengers){
            fileWriter=new FileWriter(file);
            stringBuilder.append(passenger.getPassengerID()+",");
            stringBuilder.append(passenger.getSurvived()+",");
            stringBuilder.append(passenger.getPclass()+",");
            stringBuilder.append(passenger.getName()+",");
            stringBuilder.append(passenger.getSex()+",");
            stringBuilder.append(passenger.getAge()+",");
            stringBuilder.append(passenger.getSibSP()+",");
            stringBuilder.append(passenger.getParch()+",");
            stringBuilder.append(passenger.getTicket()+",");
            stringBuilder.append(passenger.getFare()+",");
            stringBuilder.append(passenger.getCabin()+",");
            stringBuilder.append(passenger.getEmbarked()).append("\n");
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        }
    }
    private static String getByPounds(String string){
        String poundsGroup="";
        double pounds=Double.parseDouble(string);
        if(pounds<10)                {poundsGroup="0-9";}
        if(pounds<=30 && pounds>=11) {poundsGroup="11-30";}
        if(pounds>30)                {poundsGroup="30+";}
        return poundsGroup;
    }


}
