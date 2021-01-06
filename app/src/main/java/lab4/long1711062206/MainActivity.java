package lab4.long1711062206;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.AdapterView;

public class MainActivity extends TabActivity {

        private List<Restaurant> restaurantList = new ArrayList<Restaurant>();

        //private ArrayAdapter<Restaurant> adapter = null; lab5
        RestaurantAdapter adapter = null;
//    private Restaurant r = new Restaurant(); lab4
    class RestaurantAdapter extends ArrayAdapter<Restaurant>{
        public RestaurantAdapter(Context context,int textViewResourceId){
            super(context, textViewResourceId);
        }
        public RestaurantAdapter()
        {
            super(MainActivity.this, android.R.layout.simple_list_item_1,restaurantList);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TODO Auto-generated method
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row, null);
            }
            Restaurant r = restaurantList.get(position);
            ((TextView) row.findViewById(R.id.title)).setText(r.getName());
            ((TextView) row.findViewById(R.id.address)).setText(r.getAddress());
            ImageView icon = (ImageView) row.findViewById(R.id.icon);
            String type = r.getType();
            if(type.equals("Take out"))
                icon.setImageResource(R.drawable.icont);
            else if(type.equals("Sit down"))
                icon.setImageResource(R.drawable.icons);
            else
                icon.setImageResource(R.drawable.icond);
            return row;
        }
    }
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Restaurant r = restaurantList.get(position); // lấy item được chọn
            EditText name;
            EditText address;
            RadioGroup types;
// Tham chiếu đến các view trong details
            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.types);

// thiết lập thông tin tương ứng
            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("Sit down"))
                types.check(R.id.sit_down);
            else if (r.getType().equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);
// sinh viên có thể bổ sung lệnh sau để chuyển view về tab details
            getTabHost().setCurrentTab(1);
        }
    };

    //@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(onSave);
        ListView list = (ListView)findViewById(R.id.restaurants);
        list.setOnItemClickListener(onListClick);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);

        // Phần bổ sung cho Tab
        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);

        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details",
                getResources().getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);

        getTabHost().setCurrentTab(0);

    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();

            String msg;
            EditText name = findViewById(R.id.name);
            EditText address =findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            RadioGroup type =findViewById(R.id.types);
            switch (type.getCheckedRadioButtonId()) {
                case R.id.take_out:
                    r.setType("Take out");
                    msg = name.getText().toString()+" " + address.getText().toString() + " Take_out" ;
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                    break;
                case R.id.sit_down:
                    r.setType("Sit down");
                    msg = name.getText().toString()+" " + address.getText().toString() + " sit_down" ;
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                    break;
                case R.id.delivery:
                    r.setType("Delivery");
                    msg = name.getText().toString()+" " + address.getText().toString() + " Delivery" ;
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                    break;

            }
            restaurantList.add(r);
        }


    };
}
