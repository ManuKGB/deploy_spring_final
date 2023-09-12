package kgbStage.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "confirmation_payement")
public class ConfirmationPayement {
    @Id
    private String identifier;
    private String tx_reference;

    private String payment_reference;
    private String amount;
    private String datetime;
    private  String payment_method;
    private String phone_number;

    public ConfirmationPayement() {
    }

    public ConfirmationPayement(String tx_reference, String identifier, String payment_reference,
                                String amount, String datetime, String payement_methode, String phone_number) {
        this.tx_reference = tx_reference;
        this.identifier = identifier;
        this.payment_reference = payment_reference;
        this.amount = amount;
        this.datetime = datetime;
        this.payment_method = payement_methode;
        this.phone_number = phone_number;
    }


    public String getTx_reference() {
        return tx_reference;
    }

    public void setTx_reference(String tx_reference) {
        this.tx_reference = tx_reference;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPayment_reference() {
        return payment_reference;
    }

    public void setPayment_reference(String payment_reference) {
        this.payment_reference = payment_reference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
