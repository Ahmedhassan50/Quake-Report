package com.code.ahmed.quakereport;

import android.content.Context;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class wordAdapter extends ArrayAdapter<word> {

// constructor
    public wordAdapter(Context context, ArrayList<word>words) {
        super(context,0,words);
    }

    private static final String LOCATION_SEPARATOR = " of ";
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {




        View listitemview=convertView;
        if(listitemview==null){
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.listview_items,parent,false);
        }

        word curentposito=getItem(position);

        String originalLocation = curentposito.getLocation();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }



//to set power in textview
        TextView power=(TextView)listitemview.findViewById(R.id.power);
        String powerformated=formatpower(curentposito.getPower());
        power.setText(powerformated);



        //to set location1 in textview

        TextView locationset=(TextView)listitemview.findViewById(R.id.location_set);
         locationset.setText(locationOffset);


       //to set location2 in textview
        TextView location=(TextView)listitemview.findViewById(R.id.location);
        location.setText(primaryLocation);



        Date dateobject=new Date(curentposito.getDate());
        TextView date=(TextView)listitemview.findViewById(R.id.date);
        String formateddate=formatdate(dateobject);
        date.setText(formateddate);


        TextView time=(TextView)listitemview.findViewById(R.id.time);
        String formatedtime=formattime(dateobject);
        time.setText(formatedtime);


        GradientDrawable magnitudeCircle = (GradientDrawable) power.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(curentposito.getPower());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);




        return listitemview;
    }

    //to get date
    private String formatdate(Date dateObject){
        SimpleDateFormat dateFormat=new SimpleDateFormat("LLL dd,yyyy", Locale.ENGLISH);
        return dateFormat.format(dateObject);
    }

    //to get time
    private String formattime(Date dateObject){
        SimpleDateFormat timeFormat=new SimpleDateFormat("h:mm a",Locale.ENGLISH);
        return timeFormat.format(dateObject);
    }

private String formatpower(double power){
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat powerformat=new DecimalFormat("0.0",symbols);
    return powerformat.format(power);
}


private  int getMagnitudeColor(double power){
int powercolorid;
int powerfloor=(int)Math.floor(power);
switch (powerfloor) {
    case 0:
    case 1:
        powercolorid = R.color.magnitude1;
        break;
    case 2:
        powercolorid = R.color.magnitude2;
        break;
    case 3:
        powercolorid = R.color.magnitude3;
        break;
    case 4:
        powercolorid = R.color.magnitude4;
        break;
    case 5:
        powercolorid = R.color.magnitude5;
        break;
    case 6:
        powercolorid = R.color.magnitude6;
        break;
    case 7:
        powercolorid = R.color.magnitude7;
        break;
    case 8:
        powercolorid = R.color.magnitude8;
        break;
    case 9:
        powercolorid = R.color.magnitude9;
        break;
    default:
        powercolorid = R.color.magnitude10plus;
        break;
}
    return ContextCompat.getColor(getContext(),powercolorid);
}






}
