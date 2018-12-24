package br.com.developen.sig.widget;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.Objects;

import br.com.developen.sig.database.AddressEdificationDwellerModel;

public class AddressEdificationDwellerSuggestions implements SearchSuggestion {


    private final AddressEdificationDwellerModel addressEdificationDwellerModel;


    public static final Parcelable.Creator<AddressEdificationDwellerSuggestions> CREATOR = new Parcelable.Creator<AddressEdificationDwellerSuggestions>() {

        public AddressEdificationDwellerSuggestions createFromParcel(Parcel in) {

            return new AddressEdificationDwellerSuggestions(in);

        }

        public AddressEdificationDwellerSuggestions[] newArray(int size) {

            return new AddressEdificationDwellerSuggestions[size];

        }

    };


    public AddressEdificationDwellerSuggestions(AddressEdificationDwellerModel addressEdificationDwellerModel) {

        this.addressEdificationDwellerModel = addressEdificationDwellerModel;

    }


    public String getBody() {

        return addressEdificationDwellerModel.getSubject().getNameOrDenomination();

    }


    public int describeContents() {

        return 0;

    }


    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(addressEdificationDwellerModel.getSubject().getNameOrDenomination());

    }


    private AddressEdificationDwellerSuggestions(Parcel in) {

        addressEdificationDwellerModel = (AddressEdificationDwellerModel) in.readSerializable();

    }


    public AddressEdificationDwellerModel getAddressEdificationDwellerModel() {

        return addressEdificationDwellerModel;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressEdificationDwellerSuggestions that = (AddressEdificationDwellerSuggestions) o;
        return Objects.equals(addressEdificationDwellerModel, that.addressEdificationDwellerModel);

    }

    public int hashCode() {

        return Objects.hash(addressEdificationDwellerModel);

    }

}