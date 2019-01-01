package br.com.developen.sig;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.developen.sig.fragment.ModifiedAddressEdificationDwellerIndividualFragment;
import br.com.developen.sig.fragment.ModifiedAddressEdificationDwellerOrganizationFragment;

public class ModifiedAddressEdificationDwellerActivity extends AppCompatActivity {


    public static final String MODIFIED_ADDRESS_IDENTIFIER = "ARG_MODIFIED_ADDRESS_IDENTIFIER";

    public static final String EDIFICATION_IDENTIFIER = "ARG_EDIFICATION_IDENTIFIER";

    public static final String DWELLER_IDENTIFIER = "ARG_DWELLER_IDENTIFIER";


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modified_address_edification_dweller);

        Toolbar toolbar = findViewById(R.id.activity_modified_address_edification_dweller_toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Spinner spinner = findViewById(R.id.activity_modified_address_edification_dweller_spinner);

        spinner.setAdapter(new MyAdapter(
                toolbar.getContext(),
                new String[]{
                        "Pessoa Física",
                        "Pessoa Jurídica"
                }));

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){

                    case 0: getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, ModifiedAddressEdificationDwellerIndividualFragment.newInstance(
                                    getIntent().getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0),
                                    getIntent().getIntExtra(EDIFICATION_IDENTIFIER, 0),
                                    getIntent().getIntExtra(DWELLER_IDENTIFIER, 0)))
                            .commit();

                        break;

                    case 1: getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, ModifiedAddressEdificationDwellerOrganizationFragment.newInstance(
                                    getIntent().getIntExtra(MODIFIED_ADDRESS_IDENTIFIER, 0),
                                    getIntent().getIntExtra(EDIFICATION_IDENTIFIER, 0),
                                    getIntent().getIntExtra(DWELLER_IDENTIFIER, 0)))
                            .commit();

                        break;

                }

            }

            public void onNothingSelected(AdapterView<?> parent) {}

        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_modified_address_edification_dweller, menu);

        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;

        }

        return super.onOptionsItemSelected(item);

    }


    private static class MyAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
        private final ThemedSpinnerAdapter.Helper mDropDownHelper;

        public MyAdapter(Context context, String[] objects) {
            super(context, android.R.layout.simple_list_item_1, objects);
            mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
                view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            } else {
                view = convertView;
            }

            TextView textView = view.findViewById(android.R.id.text1);
            textView.setText(getItem(position));

            return view;
        }

        @Override
        public Theme getDropDownViewTheme() {
            return mDropDownHelper.getDropDownViewTheme();
        }

        @Override
        public void setDropDownViewTheme(Theme theme) {
            mDropDownHelper.setDropDownViewTheme(theme);
        }
    }


    /*
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_modified_address_edification_dweller, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
*/

}