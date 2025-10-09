package receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.colorpicker.MainActivity;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            String format = bundle.getString("format");
            StringBuilder messageBody = new StringBuilder();
            String sender = "";

            for (Object pdu : pdusObj) {
                SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdu, format);
                sender = currentMessage.getDisplayOriginatingAddress();
                messageBody.append(currentMessage.getDisplayMessageBody());
            }
            String message = messageBody.toString();

            // Show a toast with the message
            Toast.makeText(context, "From: " + sender + "\nMessage: " + message, Toast.LENGTH_LONG).show();

            // Launch the main activity with the message
            Intent launchIntent = new Intent(context, MainActivity.class);
            launchIntent.putExtra("sms", message);
            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(launchIntent);
        }
    }
}
