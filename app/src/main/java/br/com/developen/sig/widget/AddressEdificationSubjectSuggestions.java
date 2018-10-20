package br.com.developen.sig.widget;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

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

        return mAddressEdificationSubject.getSubject().getIdentifier().toString();

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


    public AddressEdificationSubjectModel getmAddressEdificationSubject() {

        return mAddressEdificationSubject;

    }


}