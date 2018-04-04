/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventable.pkgfor.arc;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author edhopkins
 */
public class SeekARideController implements Initializable {

    @FXML
    Stage stage;
    Parent root;
    
    @FXML
    private Button Signout, Search, Post;
    
    @FXML
    private Text errorText, notifyText;//, name;
    
    @FXML
    private TextField startNum, startStreet, startSub, startPC, endNum, endStreet, endSub, endPC, pickupTime, maxPrice; 
    
    @FXML
    private RadioButton startHome, startWork, startOther, endHome, endWork, endOther;
    
    @FXML
    private DatePicker pickupDate;
    
    @FXML
    private ComboBox numPass;
    
    @FXML
    private TableView<Offer> Table;
    
    @FXML
    private TableColumn<Offer, String> offerIDCol;
    
    @FXML
    private TableColumn<Offer, String> offererIDCol;
    
    @FXML
    private TableColumn<Offer, String> strtSuburbCol;
    
    @FXML    
    private TableColumn<Offer, String> strtPostCodeCol;    
    
    @FXML    
    private TableColumn<Offer, String> strtStreetNoCol; 
    
    @FXML
    private TableColumn<Offer, String> strtStreetNameCol;
    
    @FXML
    private TableColumn<Offer, String> endSuburbCol;
    
    @FXML
    private TableColumn<Offer, String> endPostCodeCol;
    
    @FXML
    private TableColumn<Offer, String> endStreetNoCol;
    
    @FXML
    private TableColumn<Offer, String> endStreetNameCol;
    
    @FXML
    private TableColumn<Offer, String> dateCol;
    
    @FXML
    private TableColumn<Offer, String> priceCol;
    
    @FXML
    private TableColumn<Offer, String> pickUpTimeCol;
    
    @FXML
    private TableColumn<Offer, String> dateCreatedCol;
    
    @FXML
    private TableColumn<Offer, String> statusCol;
    
    @FXML
    private TableColumn<Offer, String> numSeatsAvailableCol; 
    
    @FXML
    private AnchorPane SingleOffer;
    
    @FXML
    private Label offerID;
    
    @FXML
    private Label offerID2;
    
    @FXML
    private Label offererID;
    
    @FXML
    private Label dateCreated;
    
    @FXML
    private Label status;
    
    @FXML
    private Label strtStreetNo;
    
    @FXML
    private Label strtStreetName;
    
    @FXML
    private Label strtSuburb;
    
    @FXML
    private Label strtPostCode;
    
    @FXML
    private Label endStreetNo;
    
    @FXML
    private Label endStreetName;
    
    @FXML
    private Label endSuburb;
    
    @FXML
    private Label endPostCode;
    
    @FXML
    private Label date;
    
    @FXML
    private Label price;
    
    @FXML
    private Label pickUpTime;
    
    @FXML
    private Label numberOfSeats;
    
    @FXML
    private Button Back;
    
    @FXML
    private Button Match;
    
    @FXML
    private TitledPane searchResultsTitledPane;
    
    DBController d = new DBController(); //Establish a connection to the db
    
    ArrayList<Offer> offersList = new ArrayList<>(); //Creates the array list
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //name.setText(d.returnSingleQuery("SELECT FNAME AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'"));
        
        System.out.println(SignInController.getUser());
        int type = Integer.parseInt(d.returnSingleQuery("SELECT USERTYPE AS ANSWER FROM USER WHERE USERNAME LIKE '" + SignInController.getUser() + "'"));
        
        //Commented for Eventable editing
        /*if (type == 1) { //Ty
           Offer.setDisable(true);
            
        }
        else if (type == 2) {
            Seek.setDisable(true);
        }*/
        
        searchResultsTitledPane.setCollapsible(false); 
        SingleOffer.setVisible(false);
        numPass.getItems().addAll("1", "2", "3","4");
        numPass.getSelectionModel().selectFirst();
        
        offerIDCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOfferIDProperty().toString()));
        
        offererIDCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOffererIDProperty().toString()));
        
        strtSuburbCol.setCellValueFactory(cellData -> cellData.getValue().getStrtSuburbProperty());
        
        strtPostCodeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStrtPostCodeProperty().toString()));

        strtStreetNoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStrtStreetNoProperty().toString()));
        
        strtStreetNameCol.setCellValueFactory(cellData -> cellData.getValue().getStrtStreetNameProperty());
        
        endSuburbCol.setCellValueFactory(cellData -> cellData.getValue().getEndSuburbProperty());
        
        endPostCodeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndPostCodeProperty().toString()));

        endStreetNoCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndStreetNoProperty().toString()));
        
        endStreetNameCol.setCellValueFactory(cellData -> cellData.getValue().getEndStreetNameProperty());
        
        dateCol.setCellValueFactory(cellData -> cellData.getValue().getDateProperty());
        
        priceCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriceProperty().toString()));
        
        pickUpTimeCol.setCellValueFactory(cellData -> cellData.getValue().getPickUpTimeProperty());
        
        dateCreatedCol.setCellValueFactory(cellData -> cellData.getValue().getDateCreatedProperty());
        
        statusCol.setCellValueFactory(cellData -> cellData.getValue().getStatusProperty());
        
        numSeatsAvailableCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumSeatsAvailableProperty().toString()));
        getOffers();
        
        Table.setItems(FXCollections.observableArrayList(offersList));
          
        Table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOffer(newValue));
        
        
    }
    
    public void getOffers() {
        try {
            offersList.clear();
            ResultSet rs = d.getResultSet("SELECT * FROM OFFER WHERE STATUS LIKE 'Pending'");
            while (rs.next()) {
                offersList.add(new Offer(rs.getLong("OFFERID"), rs.getLong("OFFERERID"), rs.getString("STRTSUBURB"), rs.getInt("STRTPOSTCODE"), rs.getInt("STRTSTREETNO"), rs.getString("STRTSTREETNAME"), rs.getString("ENDSUBURB"), rs.getInt("ENDPOSTCODE"), rs.getInt("ENDSTREETNO"), rs.getString("ENDSTREETNAME"), rs.getString("DATE"), rs.getDouble("PRICE"), rs.getString("PICKUPTIME"), rs.getString("DATECREATED"), rs.getString("STATUS"), rs.getInt("NUMSEATSREQUIRED")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeekARideController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void showOffer(Offer offer) {
        SingleOffer.setVisible(true);
        offerID2.setText(offer.getOfferID());
        offerID.setText(offer.getOfferID());
        offererID.setText(offer.getOffererID());
        dateCreated.setText(offer.getDateCreated());
        status.setText(offer.getStatus());
        strtStreetNo.setText(offer.getStrtStreetNo());
        strtStreetName.setText(offer.getStrtStreetName());
        strtSuburb.setText(offer.getStrtSuburb());
        strtPostCode.setText(offer.getStrtPostCode());
        endStreetNo.setText(offer.getEndStreetNo());
        endStreetName.setText(offer.getEndStreetName());
        endSuburb.setText(offer.getEndSuburb());
        endPostCode.setText(offer.getEndPostCode());
        date.setText(offer.getDate());
        price.setText(offer.getPrice());
        pickUpTime.setText(offer.getPickUpTime());
        numberOfSeats.setText(offer.getNumSeatsAvailable());
    }
    @FXML
    private void SignOut(ActionEvent event) throws Exception {

        stage=(Stage) Signout.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Sign In.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
}
    
    @FXML //this method makes the address fields enabled
    private void RBbuttonPressed(ActionEvent event) throws Exception {
        if (startOther.isSelected()){
            startNum.setDisable(false);
            startStreet.setDisable(false);
            startSub.setDisable(false);
            startPC.setDisable(false);
        }
        else{
            startNum.setDisable(true);
            startStreet.setDisable(true);
            startSub.setDisable(true);
            startPC.setDisable(true);
        }
        if (endOther.isSelected()){
            endNum.setDisable(false);
            endStreet.setDisable(false);
            endSub.setDisable(false);
            endPC.setDisable(false);
        }
        else{
            endNum.setDisable(true);
            endStreet.setDisable(true);
            endSub.setDisable(true);
            endPC.setDisable(true);
        }
    }
    
    @FXML //HAMISH THIS IS WHERE THE RESULT SET IS
    private void makeSearch(ActionEvent event) throws Exception {
        if (verifySeek() == false){
            offersList.clear();
            String startPCX;
            String endPCX;
            LocalDate dateX = pickupDate.getValue();
            String timeX = pickupTime.getText();
            String PriceX = maxPrice.getText();
            String StatusX = "Pending";
            String[] temp = pickupTime.getText().split(":");
            //System.out.println(temp[0]);
            int time1 = Integer.parseInt(temp[0]); //hour
            int time2 = Integer.parseInt(temp[0]) - 1; //hour - 1
            int time3 = Integer.parseInt(temp[0]) + 1; //hour + 1
            
            String tm1 = Integer.toString(time1);
            String tm2 = Integer.toString(time2);
            String tm3 = Integer.toString(time3);
            
            String tm1x;
            String tm2x;
            String tm3x;
            
            if (time1 < 10) {
                tm1x = "0" + tm1;
            }
            else {
                tm1x = tm1;
            }
            
            if (time2 < 10) {
                tm2x = "0" + tm2;
            }
            else {
                tm2x = tm2;
            }
            
            if (time3 < 10) {
                tm3x = "0" + tm3;
            }
            else {
                tm3x = tm3;
            }
            
            
            //System.out.println(dateX);
            
            //determining address
            if (startHome.isSelected()){
                startPCX = d.returnSingleQuery("SELECT HPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else if (startWork.isSelected()){
                startPCX = d.returnSingleQuery("SELECT WPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else{
                startPCX = startPC.getText();
            }
            
            if (endHome.isSelected()){
                endPCX = d.returnSingleQuery("SELECT HPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else if (endWork.isSelected()){
                endPCX = d.returnSingleQuery("SELECT WPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else{
                endPCX = endPC.getText();
            }
            //Search conditions: same start + end suburbs, price window and time window
            ResultSet searchResults = d.getResultSet("SELECT * FROM OFFER WHERE STRTPOSTCODE = '" + startPCX + "' AND ENDPOSTCODE = '" + endPCX + "' AND DATE = '" + dateX + "' AND (PICKUPTIME LIKE '"+tm1x+":%' OR PICKUPTIME LIKE '"+tm2x+":%' OR PICKUPTIME LIKE '"+tm3x+":%') AND PRICE <= " + PriceX + " and STATUS = 'Pending'");
            while (searchResults.next()) {
                offersList.add(new Offer(searchResults.getLong("OFFERID"), searchResults.getLong("OFFERERID"), searchResults.getString("STRTSUBURB"), searchResults.getInt("STRTPOSTCODE"), searchResults.getInt("STRTSTREETNO"), searchResults.getString("STRTSTREETNAME"), searchResults.getString("ENDSUBURB"), searchResults.getInt("ENDPOSTCODE"), searchResults.getInt("ENDSTREETNO"), searchResults.getString("ENDSTREETNAME"), searchResults.getString("DATE"), searchResults.getDouble("PRICE"), searchResults.getString("PICKUPTIME"), searchResults.getString("DATECREATED"), searchResults.getString("STATUS"), searchResults.getInt("NUMSEATSREQUIRED")));
            }
            
            //HAMISH - ADD DISPLAY LOGIC HERE
            //System.out.println(searchResults);
            //System.out.println("");
            Table.setItems(FXCollections.observableArrayList(offersList));
        }
    }
    
    @FXML //this method makes the address fields enabled
    private void postSeek(ActionEvent event) throws Exception {
        if (verifySeek() == false){
            DBController db1 = new DBController();
            int thisPK = Integer.parseInt(db1.returnSingleQuery("SELECT MAX(SEEKID) AS ANSWER FROM SEEK"))+1;
            int SeekerPK = Integer.parseInt(db1.returnSingleQuery("SELECT USERID AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'"));
            String startNumX;
            String startStreetX;
            String startSubX;
            String startPCX;
            String endNumX;
            String endStreetX;
            String endSubX;
            String endPCX;
            LocalDate dateX = pickupDate.getValue();
            String timeX = pickupTime.getText();
            LocalDate rightNow = LocalDate.now();
            String PriceX = maxPrice.getText();
            String StatusX = "Pending";
            //Seats in the INSERT statement
            
            //determining address
            if (startHome.isSelected()){
                startNumX = db1.returnSingleQuery("SELECT HNUM AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                startStreetX = db1.returnSingleQuery("SELECT HSTREET AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                startSubX = db1.returnSingleQuery("SELECT HSUBURB AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                startPCX = db1.returnSingleQuery("SELECT HPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else if (startWork.isSelected()){
                startNumX = db1.returnSingleQuery("SELECT WNUM AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                startStreetX = db1.returnSingleQuery("SELECT WSTREET AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                startSubX = db1.returnSingleQuery("SELECT WSUBURB AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                startPCX = db1.returnSingleQuery("SELECT WPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else{
                startNumX = startNum.getText();
                startStreetX = startStreet.getText();
                startSubX = startSub.getText();
                startPCX = startPC.getText();
            }
            
            if (endHome.isSelected()){
                endNumX = db1.returnSingleQuery("SELECT HNUM AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                endStreetX = db1.returnSingleQuery("SELECT HSTREET AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                endSubX = db1.returnSingleQuery("SELECT HSUBURB AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                endPCX = db1.returnSingleQuery("SELECT HPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else if (endWork.isSelected()){
                endNumX = db1.returnSingleQuery("SELECT WNUM AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                endStreetX = db1.returnSingleQuery("SELECT WSTREET AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                endSubX = db1.returnSingleQuery("SELECT WSUBURB AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
                endPCX = db1.returnSingleQuery("SELECT WPOSTCODE AS ANSWER FROM USER WHERE USERNAME = '" + SignInController.getUser() + "'");
            }
            else{
                endNumX = endNum.getText();
                endStreetX = startStreet.getText();
                endSubX = startSub.getText();
                endPCX = startPC.getText();
            }
            
            String insertStatement = "INSERT INTO SEEK (SEEKID,SEEKERID,STRTSTREETNO,STRTSTREETNAME,STRTSUBURB,STRTPOSTCODE,ENDSTREETNO,ENDSTREETNAME,ENDSUBURB,ENDPOSTCODE,DATE,PRICE,PICKUPTIME,DATECREATED,STATUS,NUMSEATSREQUIRED) VALUES ("+thisPK+","+SeekerPK+",'"+startNumX+"','"+startStreetX+"','"+startSubX+"','"+startPCX+"','"+endNumX+"','"+endStreetX+"','"+endSubX+"','"+endPCX+"',"+"PARSEDATETIME('"+dateX+"', 'YYYY-MM-DD'),'"+PriceX+"','"+timeX+"',"+"PARSEDATETIME('"+rightNow+"', 'YYYY-MM-DD'),'"+StatusX+"','"+numPass.getValue()+"')";
            System.out.println(insertStatement);
            db1.Insert(insertStatement);
            notifyText.setText("            Your Seek has been posted successfully!");
            errorText.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Action Processed");
            alert.setHeaderText(null);
            alert.setContentText("Seek Posted Successfully!");

            alert.showAndWait();
        }
    }
   
    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }
    
    //this method verifies a new seek
    private boolean verifySeek()  {
        notifyText.setText("If there are no suitable Search results, Post a Seek!");
        
        //CHECK FOR EMPTY AND SPECIAL TEXT
        //Date
        if (pickupTime.getText().equals("")){
            errorText.setText("Pick Up Time field is empty");
            errorText.setVisible(true);
            return true;
        }
        if (pickupTime.getText().length() != 5){
            errorText.setText("Pick Up Time in an invalid time");
            errorText.setVisible(true);
            return true;
        }
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        try{
            java.sql.Time timeValue = new java.sql.Time(formatter.parse(pickupTime.getText()).getTime());
        }
        catch (ParseException ex){
            errorText.setText("Pick Up Time in an invalid time");
            errorText.setVisible(true);
            return true;
        }
        String[] temp = pickupTime.getText().split(":");
        if (Integer.parseInt(temp[0]) > 23 || Integer.parseInt(temp[1]) > 59){
            errorText.setText("Pick Up Time in an invalid - use 24 hour time");
            errorText.setVisible(true);
            return true;
        }
        
        //Currency
        if (maxPrice.getText().equals("")){
            errorText.setText("      Max Price field is empty");
            errorText.setVisible(true);
            return true;
        }
        if (isNumeric(maxPrice.getText()) == false){
            errorText.setText("Max Price field is invalid - use full dollars");
            errorText.setVisible(true);
            return true;
        }
        if ((maxPrice.getText()).contains(".")){
            errorText.setText("Max Price field is invalid - use full dollars");
            errorText.setVisible(true);
            return true;
        }
        
        //Other text
        if (startOther.isSelected()){
            TextField[] startText = {startNum, startStreet, startSub, startPC};
            String startTextName[] = {"   Start Number", "     Start Street", "   Start Suburb", "Start Postcode"};

            for (int i =0; i < (startText.length); i++){
                if ((startText[i].getText()).equals("")){
                    errorText.setText(startTextName[i] + " field is empty");
                    errorText.setVisible(true);
                    return true;
                }

                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(startText[i].getText());
                boolean b = m.find();

                if (b) {
                    errorText.setText(startTextName[i] + " field has special character");
                    errorText.setVisible(true);
                    return true;
                }
                if (i==0 || i==3){
                    if (isNumeric(startText[i].getText()) == false){
                        errorText.setText(startTextName[i] + " must be numeric");
                        errorText.setVisible(true);
                        return true;
                    }
                    if (i==3){
                        if (startText[i].getText().length() != 4){
                            errorText.setText("Postcode must be 4 numbers");
                            errorText.setVisible(true);
                            return true;
                        }
                    }
                }
            }

        }
        if (endOther.isSelected()){
            TextField[] endText = {endNum, endStreet, endSub, endPC};
            String endTextName[] = {"Destination Number", "Destination Street", "Destination Suburb", "Destination Postcode"};

            for (int i =0; i < (endText.length); i++){
                if ((endText[i].getText()).equals("")){
                    errorText.setText(endTextName[i] + " field is empty");
                    errorText.setVisible(true);
                    return true;
                }

                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(endText[i].getText());
                boolean b = m.find();

                if (b) {
                    errorText.setText(endTextName[i] + " field has special character");
                    errorText.setVisible(true);
                    return true;
                }
            }

        }
        
        //Date Picker
        if (pickupDate.getValue() == null) {
                errorText.setText("   Pick Up date is not set");
                errorText.setVisible(true);
                return true;
        }

        LocalDate localDate = LocalDate.now();
        
        if (pickupDate.getValue().isBefore(localDate)){
            errorText.setText("Pick today or a future date for pick up");
            errorText.setVisible(true);
            return true;
        }
        
        if ((startWork.isSelected() && endWork.isSelected()) || (startHome.isSelected() && endHome.isSelected())){
            errorText.setText("Start and destination must be different");
            errorText.setVisible(true);
            return true;
        }
        
        return false;
    }
    @FXML
    private void Back(ActionEvent event) throws Exception {
        SingleOffer.setVisible(false);       
    }
    
    @FXML
    private void Match(ActionEvent event) throws Exception {
        int thisPK = Integer.parseInt(d.returnSingleQuery("SELECT MAX(AGREEMENTID) AS ANSWER FROM AGREEMENT"))+1;
        long thisSeeker = Integer.parseInt(d.returnSingleQuery("SELECT USERID AS ANSWER FROM USER WHERE USERNAME LIKE '" + SignInController.getUser() + "'"));
        long AddOffererID = Long.parseLong(offererID.getText());
        long AddOfferID = Long.parseLong(offerID.getText());
        String AddStrtSuburb = strtSuburb.getText();
        int AddStrtPostCode = Integer.parseInt(strtPostCode.getText());
        int AddStrtStreetNo = Integer.parseInt(strtStreetNo.getText());
        String AddStrtStreetName = strtStreetName.getText();
        String AddEndSuburb = endSuburb.getText();
        int AddEndPostCode = Integer.parseInt(endPostCode.getText());
        int AddEndStreetNo = Integer.parseInt(endStreetNo.getText());
        String AddEndStreetName = endStreetName.getText();
        LocalDate AddDate = LocalDate.parse(date.getText());
        double AddPrice = Double.parseDouble(price.getText());
        String AddPickUpTime = pickUpTime.getText();
        LocalDate AddDateCreated = LocalDate.parse(dateCreated.getText());
        String AddStatus = "Matched";
        long AddPaymentID = 0;
        int AddNumSeatsRequired = Integer.parseInt(numberOfSeats.getText());
        int OldSeatNum = Integer.parseInt(d.returnSingleQuery("SELECT NUMSEATSREQUIRED AS ANSWER FROM OFFER WHERE OFFERID = " + AddOfferID));
        int NewSeatNum = OldSeatNum - AddNumSeatsRequired;
        
        d.Insert("INSERT INTO AGREEMENT(AGREEMENTID, SEEKERID, OFFERERID, OFFERID, STRTSUBURB, STRTPOSTCODE, STRTSTREETNO, STRTSTREETNAME, ENDSUBURB, ENDPOSTCODE, ENDSTREETNO, ENDSTREETNAME, DATE, PRICE, PICKUPTIME, DATECREATED, STATUS, PAYMENTID, NUMSEATSREQUIRED) VALUES(" + thisPK + ", " + thisSeeker + ", " + AddOffererID + ", " + AddOfferID + ", '" + AddStrtSuburb + "', " + AddStrtPostCode + ", " + AddStrtStreetNo + ", '" + AddStrtStreetName + "', '" + AddEndSuburb + "', " + AddEndPostCode + ", " + AddEndStreetNo + ", '" + AddEndStreetName + "', " + "PARSEDATETIME('" + AddDate + "', 'YYYY-MM-DD'), " + AddPrice + ", '" + AddPickUpTime + "', PARSEDATETIME('" + AddDateCreated + "', 'YYYY-MM-DD'), '" + AddStatus + "', " + AddPaymentID + ", " + AddNumSeatsRequired + ")");
        d.Insert("UPDATE OFFER SET STATUS = 'Matched', NUMSEATSREQUIRED = " + NewSeatNum + " WHERE OFFERID = " + AddOfferID);
        SingleOffer.setVisible(false);       
        refreshTable();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action Processed");
        alert.setHeaderText(null);
        alert.setContentText("You have matched this ride!");

        alert.showAndWait();
        
    }
    
    public void refreshTable() {
        offersList.clear();
        getOffers();
        Table.setItems(FXCollections.observableArrayList(offersList));
    }
}
