package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.UniqueBillList;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePatientList patients;
    private final UniqueAppointmentList appointments;
    private final UniqueBillList bills;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
        appointments = new UniqueAppointmentList();
        bills = new UniqueBillList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Patients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Replaces the contents of the bill list with {@code bills}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setBills(List<Bill> bills) {
        this.bills.setBills(bills);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
        setAppointments(newData.getAppointmentList());
        setBills(newData.getBillList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the same identity as {@code name} exists in the address book.
     */
    public boolean hasPatient(Name name) {
        requireNonNull(name);
        return patients.contains(name);
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the address book.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the address book.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The appointment identity of {@code editedAppointment} must not be the same as
     * another existing appointment in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    //// bill-level operations

    /**
     * Returns true if an bill with the same identity as {@code bill} exists in the address book.
     */
    public boolean hasBill(Bill bill) {
        requireNonNull(bill);
        return bills.contains(bill);
    }

    /**
     * Adds an bill to the address book.
     * The bill must not already exist in the address book.
     */
    public void addBill(Bill bill) {
        bills.add(bill);
    }

    /**
     * Replaces the given bill {@code target} in the list with {@code editedBill}.
     * {@code target} must exist in the address book.
     * The bill identity of {@code editedBill} must not be the same as
     * another existing bill in the address book.
     */
    public void setBill(Bill target, Bill editedBill) {
        requireNonNull(editedBill);

        bills.setBill(target, editedBill);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeBill(Bill key) {
        bills.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients;\n"
                + appointments.asUnmodifiableObservableList().size() + "appointments;\n"
                + bills.asUnmodifiableObservableList().size() + "bills;";
        // TODO: refine later
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Bill> getBillList() {
        return bills.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && patients.equals(((AddressBook) other).patients))
                && appointments.equals(((AddressBook) other).appointments);
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }

    void sortPatients(Comparator<Patient> comparator, boolean isAscending) {
        patients.sort(comparator, isAscending);
    }

    void sortBills(Comparator<Bill> comparator, boolean isAscending) {
        bills.sort(comparator, isAscending);
    }

    void setBillAsUnpaid(Bill bill) {
        Bill editedBill = bill;
        editedBill.setBillAsUnpaid();
        setBill(bill, editedBill);
    }

    void sortAppointments(Comparator<Appointment> comparator, boolean isAscending) {
        appointments.sort(comparator, isAscending);
    }
}
