package ud.example.notificaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void noti01(View v){

        String mess= getResources().getString(R.string.mensaje01);
        Toast toast1 = Toast.makeText(getApplicationContext(),mess,Toast.LENGTH_LONG);
        toast1.show();
    }

    public void noti02(View v){

        String mess= getResources().getString(R.string.mensaje01);
        Toast toast1 = Toast.makeText(getApplicationContext(),mess,Toast.LENGTH_LONG);
        toast1.setGravity(Gravity.CENTER|Gravity.LEFT,10,10);
        toast1.show();
    }
    public void noti03(View v){

        Toast toas1 = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout= inflater.inflate(R.layout.layout_toast,(ViewGroup) findViewById(R.id.layoutToast));
        TextView txtMsg=(TextView) layout.findViewById(R.id.mensajeLbl);
        txtMsg.setText("Mensaje con diseño personalizado por nosotros");
        toas1.setView(layout);
        toas1.show();
    }



    public void noti04(View v){

        String ns = Context.NOTIFICATION_SERVICE;
        String CHannel_ID ="ud.com.Android";
        NotificationManager ntManager = (NotificationManager)getSystemService(ns);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "ANDROID CHANNEL";
            String descripcion = "Canal de Notificaciones de Android para la UD";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHannel_ID,name,importance);
            channel.setDescription(descripcion);
            ntManager.createNotificationChannel(channel);
        }

        int icono = android.R.drawable.stat_sys_warning;
        CharSequence textEstado="¡Atención!";
        CharSequence titulo = "Mensaje de Alerta";
        CharSequence descripcion = "Ejemplo de Notificación";
        long hora = System.currentTimeMillis();
        Context contexto = getApplicationContext();
        Intent notIntent = new Intent(contexto,MainActivity.class);
        PendingIntent contIntent = PendingIntent.getActivity(contexto,0, notIntent,0);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                new NotificationCompat.Builder(MainActivity.this,CHannel_ID)
                .setSmallIcon(icono)
                .setLargeIcon((((BitmapDrawable)getResources().getDrawable(R.drawable.info)).getBitmap()))
                .setContentTitle(titulo)
                .setContentText(descripcion)
                .setContentInfo(textEstado)
                .setWhen(hora)
                .setContentIntent(contIntent)
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorPrimaryDark))
                .setVibrate(new long[]{100,250,100,500})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        ntManager.notify(ID_MEN_BARRA_NOTIF,mBuilder.build());
    }
    private static final int ID_MEN_BARRA_NOTIF =1;

    public void noti05(View v){

        String mess= getResources().getString(R.string.mensaje01);
        Snackbar.make(v,mess,Snackbar.LENGTH_LONG)
                .setAction("Acción", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),"Hola Mundo",Toast.LENGTH_LONG);

                        toast1.show();
                        Log.i("SNACKBAR ","Acción Realizada");
                    }
                }).show();



    }
}