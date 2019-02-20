
import com.sun.javaws.ui.ApplicationIconGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class biControll implements Initializable {

    @FXML
    private TextField TF1;
    @FXML
    private ProgressBar p1;
    @FXML
    Pane pane1;
    @FXML
    Pane pane2;
    @FXML
    Pane pane3;
    @FXML
    Pane pane4;
    @FXML
    Pane pane5;
    @FXML
    Pane pane6;
    @FXML
    private TextField TF2;
    @FXML
    private TextField TF3;
    @FXML
    private TextField TF4;
    @FXML
    TextField TF5;
    @FXML
    TextField TF6;
    @FXML
    TextArea TA1;
    @FXML
    TextArea TA2;
    @FXML
    Button bt1;
    @FXML
    Button play;
    @FXML
    Button back;
    @FXML
    Button ok;
    @FXML
    CheckBox cb1;
    @FXML
    CheckBox cb2;

    boolean showParse = false;
    boolean start = false, comp = false;
    Thread ParsingShow = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {

                if (showParse) {

                    try {
                        TA1.setText("Equation Parsing.");
                        Thread.sleep(100);
                        TA1.setText("Equation Parsing..");
                        Thread.sleep(100);
                        TA1.setText("Equation Parsing...");
                        Thread.sleep(100);
                        TA1.setText("Equation Parsing....");
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(biControll.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    TA1.setText("Parsing Complete!!!");
                    pane3.setVisible(true);
                    char[] tt = func.get(0).toCharArray();
                    String sohog = "", base = "", p = "";
                    int i = 0, j;
                    //System.out.println(t[i]);
                    while (i < tt.length && !Character.isLetter(tt[i])) {
                        sohog += tt[i];
                        i++;
                    }
                    j = i;
                    while ((j < tt.length && tt[j] != '^')) {
                        base += tt[j++];
                        //System.out.println(base);
                    }
                    int k = ++j;
                    while (k < tt.length) {
                        p += tt[k++];
                    }
                    if (p.equalsIgnoreCase("")) {
                        TA1.appendText("\nHigest Power Of The Equation: 1");
                    } else {
                        TA1.appendText("\nHigest Power Of The Equation: " + p);
                    }
                    ParsingShow.stop();
                }

            }
        }
    });

    RotateTransition r = new RotateTransition();

    ArrayList<String> func = new ArrayList<>();

    Thread bisec = new Thread(new Runnable() {

        Pane p;

        void gen(String str) {
            //Scanner nw = new Scanner(System.in);
            //String str  = nw.nextLine();
            //nw.close();
            char[] s = str.toCharArray();
            String temp = "";
            for (int i = 0; i < s.length; i++) {
                if (s[i] != '+' && s[i] != '-') {
                    temp += s[i];
                } else {
                    func.add(temp);
                    temp = "";
                    temp += s[i];
                    func.add(temp);
                    temp = "";
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            func.add(temp);
            temp = "";
        }

        double f(double x
        ) {
            double sum = 0.0;
            String plus = "+";
            String minus = "-";
            for (int i = 0; i < func.size(); i++) {
                if (i == 0) {
                    sum += eval(x, func.get(i).toCharArray());
                } else if (func.get(i).equals(plus)) {
                    i++;
                    sum += eval(x, func.get(i).toCharArray());
                } else if (func.get(i).equals(minus)) {
                    i++;
                    sum -= eval(x, func.get(i).toCharArray());
                }
            }
            //System.out.println(sum);
            return sum;
        }

        double eval(double x, char[] t
        ) {
            //System.out.println(t.length);
            String sohog = "", base = "", power = "";
            int i = 0, j;
            //System.out.println(t[i]);
            while (i < t.length && !Character.isLetter(t[i])) {
                sohog += t[i];
                i++;
            }
            j = i;
            while ((j < t.length && t[j] != '^')) {
                base += t[j++];
                //System.out.println(base);
            }
            int k = ++j;
            while (k < t.length) {
                power += t[k++];
            }
            int shg, pwr = 0;
            if (!sohog.equalsIgnoreCase("")) {
                shg = Integer.parseInt(sohog);
            } else {
                shg = 1;
            }
            if (!power.equalsIgnoreCase("")) {
                try {
                    pwr = Integer.parseInt(power);
                } catch (NumberFormatException n) {

                }
            } else {
                pwr = 1;
            }
            //System.out.println(shg+" "+pwr);
            double res = 0.0;
            //System.out.println(base);
            if (base.matches("X")) {
                res = Math.pow(x, pwr);
                res = shg * res;
            } else if (base.matches("e")) {
                res = Math.E;
                res = Math.pow(res, x);
                res = shg * res;
            } else if (base.equals("ln(X)")) {
                //System.out.println("ln");
                res = Math.log(x);
                res = Math.pow(res, pwr);
                res = shg * res;
            } else {
                res = shg;
            }
            return res;
        }

        private void bisection(double xl, double xu, double root, double Et) {
            double xr = 0;
            int maxI = 100;
            int i = 0;
            double oldXr = 0;
            double localEa = 0;
            double localEt = 0;
            while (i < maxI) {
                xr = (xl + xu) / 2;
                //xr = (xl * f(xu) - xu * f(xl)) / (f(xu) - f(xl));
                //SPxr = xu - (f(xu) * ((xl - xu))) / (f(xl) - f(xu));
                if (f(xr) == 0.0) {
                    //JOptionPane.showMessageDialog(null, "Root: " + xr + " Found After " + i + "th Iteration");
                    TA2.appendText("Root: " + xr + " Found After " + i + "th Iteration");
                    disableEnable();
                    
                    
                    break;
                }
                if (f(xl) * f(xr) < 0) {
                    //System.out.println((f(xl) * f(xr)));
                    xu = xr;
                } else {
                    xl = xr;
                }
                /*SPxl = xu;
                xu = xr;*/
                oldXr = xr;
                localEt = Math.abs((root - xr) / root) * 100;
                if (localEt < Et) {
                    //JOptionPane.showMessageDialog(null, "Root: " + xr + " Found After " + i + "th Iteration");
                    TA2.appendText("Root: " + xr + " Found After " + i + "th Iteration");
                    disableEnable();
                    
                    
                    break;
                }
                TA2.appendText("Iter " + (i + 1) + ": Xr:" + oldXr + " Et:" + localEt + "\n\n");
                i++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(biControll.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (i >= maxI) {
                JOptionPane.showMessageDialog(null, "Root Not Found Within 100 Iteration");
                disableEnable();
                
                
            }
        }
        private void bisection(double xl, double xu, double Ea) {
            double xr = 0;
            int maxI = 100;
            int i = 0;
            double oldXr = 0;
            double localEa = 0;
            double localEt = 0;
            while (i < maxI) {
                //System.out.println("Xl:"+xl+" Xu:"+xu+" Xr:"+xr);
                xr = (xl + xu) / 2;
                //FPxr = (xl * f(xu) - xu * f(xl)) / (f(xu) - f(xl));
                //SPxr = xu - (f(xu) * ((xl - xu))) / (f(xl) - f(xu));
                if (f(xr) == 0.0) {
                    //JOptionPane.showMessageDialog(null, "Root: " + xr + " Found After " + i + "th Iteration");
                    TA2.appendText("Root: " + xr + " Found After " + i + "th Iteration");
                    disableEnable();
                    
                    
                    break;
                }
                if (f(xl) * f(xr) < 0) {
                    xu = xr;
                    //System.out.println((f(xl) +" "+ f(xr)));
                } else {
                    xl = xr;
                }
                /*SPxl = xu;
                xu = xr;*/
                if (oldXr != 0) {
                    localEa = Math.abs((xr - oldXr) / xr) * 100;
                    //System.out.println((f(xl) +" "+ f(xr))+" "+localEa);
                    if (localEa < Ea) {
                        //JOptionPane.showMessageDialog(null, "Root: " + xr + " Found After " + i + "th Iteration");
                        TA2.appendText("Root: " + xr + " Found After " + i + "th Iteration");
                        disableEnable();
                        
                        
                        break;
                    }
                }
                oldXr = xr;
                TA2.appendText("Iter " + (i + 1) + ": Xr:" + oldXr + " Ea:" + localEa +  "\n\n");
                i++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ex) {
                    Logger.getLogger(biControll.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (i >= maxI) {
                JOptionPane.showMessageDialog(null, "Root Not Found Within 100 Iteration");
                disableEnable();
                
                
            }
        }
        @Override
        public void run() {
            showParse = true;
            System.out.println("In");
            gen(TF1.getText());
            System.out.println("out");
            showParse = false;
            comp = true;
            r.stop();

            //System.out.println(f(2));
            switch(root){
                case 1:
                {
                    bisection(Integer.parseInt(TF2.getText()), Integer.parseInt(TF3.getText()),
                    Integer.parseInt(TF4.getText()), Double.parseDouble(TF5.getText()));
                    break;
                }
                case 2:
                {
                    bisection(Integer.parseInt(TF2.getText()), Integer.parseInt(TF3.getText()),
                    Double.parseDouble(TF6.getText()));
                    break;
                }
            }
        }

    }
    );

    @FXML
    private void allClear() {
        TF1.setText("");
        TF2.setText("");
        TF3.setText("");
        TF4.setText("");
        TF5.setText("");
        TF6.setText("");
        TA1.setText("");
        TA2.setText("");
        pane2.setVisible(false);
        pane3.setVisible(false);
        showParse = false;
    }

    void disableEnable() {

        TF1.setDisable(true);
        TF2.setDisable(true);
        TF3.setDisable(true);
        TF4.setDisable(true);
        TF5.setDisable(true);
        TF6.setDisable(true);
    }

    @FXML
    private void back() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("page1.fxml"));
        Scene nwScene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(nwScene);

        stage.setResizable(false);
        stage.show();
        stage = (Stage) TA1.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void biPlay() {
        pane1.setVisible(true);
        //System.out.println("here");
        r.setNode(pane1);
        r.setToAngle(360);
        r.setRate(.3);
        r.setAutoReverse(true);
        r.setCycleCount(RotateTransition.INDEFINITE);
        r.play();
    }

    @FXML
    private void biStart() {
        switch (root) {
            case 1: {
                if (!TF1.getText().isEmpty() && !TF2.getText().isEmpty()
                        && !TF3.getText().isEmpty() && !TF4.getText().isEmpty()
                        && !TF5.getText().isEmpty()) {
                    pane1.setVisible(false);
                    bisec.start();
                    ParsingShow.start();
                    pane2.setVisible(true);
                } else {
                    Alert nw = new Alert(Alert.AlertType.ERROR, "Please Give All Required Data", null);
                    nw.showAndWait();
                    allClear();
                }
                break;
            }
            case 2: {
                if (!TF1.getText().isEmpty() && !TF2.getText().isEmpty()
                        && !TF3.getText().isEmpty() && !TF6.getText().isEmpty()) {
                    pane1.setVisible(false);
                    bisec.start();
                    ParsingShow.start();
                    pane2.setVisible(true);
                } else {
                    Alert nw = new Alert(Alert.AlertType.ERROR, "Please Give All Required Data", null);
                    nw.showAndWait();
                    allClear();
                }
            }
        }
    }

    int root;

    @FXML
    public void ok() {
        if (cb1.isSelected() && !cb2.isSelected()) {
            root = 1;
            pane4.setVisible(true);
            play.setVisible(true);
            back.setVisible(true);
            TF1.setDisable(false);
            TF2.setDisable(false);
            TF3.setDisable(false);
            pane6.setVisible(false);
        } else if (!cb1.isSelected() && cb2.isSelected()) {
            root = 2;
            pane5.setVisible(true);
            play.setVisible(true);
            back.setVisible(true);
            TF1.setDisable(false);
            TF2.setDisable(false);
            TF3.setDisable(false);
            pane6.setVisible(false);
        } else {
            Alert nw = new Alert(Alert.AlertType.ERROR, "Please Select only one.", null);
            nw.showAndWait();
        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        pane1.setVisible(false);
        pane2.setVisible(false);
        pane3.setVisible(false);
        pane4.setVisible(false);
        pane5.setVisible(false);
        play.setVisible(false);
        back.setVisible(false);
        TA1.setText("");
        TA2.setText("");
        TF1.setDisable(true);
        TF2.setDisable(true);
        TF3.setDisable(true);
        
    }
}
