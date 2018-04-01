package eventable.pkgfor.arc;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 *
 * @author paulstrasser
 */
public class Offer {
    public Long offerID;
    public Long offererID;
    public StringProperty strtSuburb;
    public Integer strtPostCode;
    public Integer strtStreetNo;
    public StringProperty strtStreetName;
    public StringProperty endSuburb;
    public Integer endPostCode;
    public Integer endStreetNo;
    public StringProperty endStreetName;
    public StringProperty date;
    public Double price;
    public StringProperty pickUpTime;
    public StringProperty dateCreated;
    public StringProperty status;
    public Integer numSeatsAvailable;

    public Offer(Long offerID, Long offererID, String strtSuburb, Integer strtPostCode, Integer strtStreetNo, String strtStreetName, String endSuburb, Integer endPostCode, Integer endStreetNo, String endStreetName, String date, Double price, String pickUpTime, String dateCreated, String status, Integer numSeatsAvailable) {
            this.offerID = new Long(offerID);
            this.offererID = new Long(offererID);
            this.strtSuburb = new SimpleStringProperty(strtSuburb);
            this.strtPostCode = new Integer(strtPostCode);
            this.strtStreetNo = new Integer(strtStreetNo);
            this.strtStreetName = new SimpleStringProperty(strtStreetName);
            this.endSuburb = new SimpleStringProperty(endSuburb);
            this.endPostCode = new Integer(endPostCode);
            this.endStreetNo = new Integer(endStreetNo);
            this.endStreetName = new SimpleStringProperty(endStreetName);
            this.date = new SimpleStringProperty(date);
            this.price = new Double(price);
            this.pickUpTime = new SimpleStringProperty(pickUpTime);
            this.dateCreated = new SimpleStringProperty(dateCreated);
            this.status = new SimpleStringProperty(status);
            this.numSeatsAvailable = new Integer(numSeatsAvailable);
        }

        public String getOfferID() {
            return offerID.toString();
        }

        public String getOffererID() {
            return offererID.toString();
        }

        public String getStrtSuburb() {
            return strtSuburb.get();
        }

        public String getStrtPostCode() {
            return strtPostCode.toString();
        }

        public String getStrtStreetNo() {
            return strtStreetNo.toString();
        }

        public String getStrtStreetName() {
            return strtStreetName.get();
        }

        public String getEndSuburb() {
            return endSuburb.get();
        }

        public String getEndPostCode() {
            return endPostCode.toString();
        }

        public String getEndStreetNo() {
            return endStreetNo.toString();
        }

        public String getEndStreetName() {
            return endStreetName.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getPrice() {
            return price.toString();
        }

        public String getPickUpTime() {
            return pickUpTime.get();
        }

        public String getDateCreated() {
            return dateCreated.get();
        }

        public String getStatus() {
            return status.get();
        }

        public String getNumSeatsAvailable() {
            return numSeatsAvailable.toString();
        }

        public Long getOfferIDProperty() {
            return offerID;
        }

        public Long getOffererIDProperty() {
            return offererID;
        }

        public StringProperty getStrtSuburbProperty() {
            return strtSuburb;
        }

        public Integer getStrtPostCodeProperty() {
            return strtPostCode;
        }

        public Integer getStrtStreetNoProperty() {
            return strtStreetNo;
        }

        public StringProperty getStrtStreetNameProperty() {
            return strtStreetName;
        }

        public StringProperty getEndSuburbProperty() {
            return endSuburb;
        }

        public Integer getEndPostCodeProperty() {
            return endPostCode;
        }

        public Integer getEndStreetNoProperty() {
            return endStreetNo;
        }

        public StringProperty getEndStreetNameProperty() {
            return endStreetName;
        }

        public StringProperty getDateProperty() {
            return date;
        }

        public Double getPriceProperty() {
            return price;
        }

        public StringProperty getPickUpTimeProperty() {
            return pickUpTime;
        }

        public StringProperty getDateCreatedProperty() {
            return dateCreated;
        }

        public StringProperty getStatusProperty() {
            return status;
        }

        public Integer getNumSeatsAvailableProperty() {
            return numSeatsAvailable;
        }
    }