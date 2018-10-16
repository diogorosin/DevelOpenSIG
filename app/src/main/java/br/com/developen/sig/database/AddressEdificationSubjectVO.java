package br.com.developen.sig.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "AddressEdificationSubject",
        primaryKeys = {"address", "edification", "subject"},
        foreignKeys = {
                @ForeignKey(entity = AddressEdificationVO.class,
                        parentColumns = {"address", "edification"},
                        childColumns = {"address", "edification"},
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = SubjectVO.class,
                        parentColumns = "identifier",
                        childColumns = "subject",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = IndividualVO.class,
                        parentColumns = "identifier",
                        childColumns = "verifiedBy",
                        onDelete = ForeignKey.RESTRICT,
                        onUpdate = ForeignKey.CASCADE)},
        indices = {@Index(value={"address", "edification", "subject"})})
public class AddressEdificationSubjectVO {

    @NonNull
    @ColumnInfo(name="address")
    private Integer address;

    @NonNull
    @ColumnInfo(name="edification")
    private String edification;

    @NonNull
    @ColumnInfo(name="subject")
    private Integer subject;

    @NonNull
    @TypeConverters({TimestampConverter.class})
    @ColumnInfo(name="from")
    private Date from;

    @ColumnInfo(name="to")
    @TypeConverters({TimestampConverter.class})
    private Date to;

    @NonNull
    @ColumnInfo(name="verifiedBy")
    private Integer verifiedBy;

    @NonNull
    @ColumnInfo(name="verifiedAt")
    @TypeConverters({TimestampConverter.class})
    private Date verifiedAt;

    @NonNull
    public Integer getAddress() {

        return address;

    }

    public void setAddress(@NonNull Integer address) {

        this.address = address;

    }

    @NonNull
    public String getEdification() {

        return edification;

    }

    public void setEdification(@NonNull String edification) {

        this.edification = edification;

    }

    @NonNull
    public Integer getSubject() {

        return subject;

    }

    public void setSubject(@NonNull Integer subject) {

        this.subject = subject;

    }

    @NonNull
    public Date getFrom() {

        return from;

    }

    public void setFrom(@NonNull Date from) {

        this.from = from;

    }

    public Date getTo() {

        return to;

    }

    public void setTo(Date to) {

        this.to = to;

    }

    @NonNull
    public Integer getVerifiedBy() {

        return verifiedBy;

    }

    public void setVerifiedBy(@NonNull Integer verifiedBy) {

        this.verifiedBy = verifiedBy;

    }

    @NonNull
    public Date getVerifiedAt() {

        return verifiedAt;

    }

    public void setVerifiedAt(@NonNull Date verifiedAt) {

        this.verifiedAt = verifiedAt;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEdificationSubjectVO that = (AddressEdificationSubjectVO) o;
        if (!address.equals(that.address)) return false;
        if (!edification.equals(that.edification)) return false;
        return subject.equals(that.subject);

    }

    @Override
    public int hashCode() {

        int result = address.hashCode();
        result = 31 * result + edification.hashCode();
        result = 31 * result + subject.hashCode();
        return result;

    }

}