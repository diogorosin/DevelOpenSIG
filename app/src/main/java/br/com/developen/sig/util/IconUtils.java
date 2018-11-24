package br.com.developen.sig.util;


import br.com.developen.sig.R;

public class IconUtils {

    public static int getListIconByType(String type){

        int result = 0;

        switch (type) {

            case "I": result = R.drawable.icon_individual_24;
                break;

            case "O": result = R.drawable.icon_organization_24;
                break;

        }

        return result;

    }

}