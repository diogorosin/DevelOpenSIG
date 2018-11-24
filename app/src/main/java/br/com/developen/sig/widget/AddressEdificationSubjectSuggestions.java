package br.com.developen.sig.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.Objects;

import br.com.developen.sig.database.AddressEdificationSubjectModel;

public class AddressEdificationSubjectSuggestions implements SearchSuggestion {


    private final AddressEdificationSubjectModel mAddressEdificationSubject;


    public static final Parcelable.Creator<AddressEdificationSubjectSuggestions> CREATOR = new Parcelable.Creator<AddressEdificationSubjectSuggestions>() {

        public AddressEdificationSubjectSuggestions createFromParcel(Parcel in) {

            return new AddressEdificationSubjectSuggestions(in);

        }

        public AddressEdificationSubjectSuggestions[] newArray(int size) {

            return new AddressEdificationSubjectSuggestions[size];

        }

    };


    public AddressEdificationSubjectSuggestions(AddressEdificationSubjectModel addressEdificationSubjectModel) {

        mAddressEdificationSubject = addressEdificationSubjectModel;

    }


    public String getBody() {

        return mAddressEdificationSubject.getSubject().getNameOrDenomination();

    }


    public int describeContents() {

        return 0;

    }


    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(mAddressEdificationSubject.getSubject().getNameOrDenomination());

    }


    private AddressEdificationSubjectSuggestions(Parcel in) {

        mAddressEdificationSubject = (AddressEdificationSubjectModel) in.readSerializable();

    }


    public AddressEdificationSubjectModel getAddressEdificationSubject() {

        return mAddressEdificationSubject;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEdificationSubjectSuggestions that = (AddressEdificationSubjectSuggestions) o;
        return Objects.equals(mAddressEdificationSubject, that.mAddressEdificationSubject);

    }

    public int hashCode() {

        return Objects.hash(mAddressEdificationSubject);

    }

}